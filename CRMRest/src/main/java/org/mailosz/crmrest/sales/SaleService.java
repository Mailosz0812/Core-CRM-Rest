package org.mailosz.crmrest.sales;

import org.mailosz.crmrest.crmclient.ClientRepository;
import org.mailosz.crmrest.crmclient.CrmClientEntity;
import org.mailosz.crmrest.crmuser.CrmUserEntity;
import org.mailosz.crmrest.crmuser.UserRepository;
import org.mailosz.crmrest.exception.types.*;
import org.mailosz.crmrest.product.ProductCacheRepository;
import org.mailosz.crmrest.product.ProductEntity;
import org.mailosz.crmrest.sales.request.*;
import org.mailosz.crmrest.sales.response.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class SaleService {
    private final SaleRepository saleRepository;
    private final SaleItemRepository saleItemRepository;
    private final ClientRepository clientRepository;
    private final UserRepository userRepository;
    private final SaleStageRepository stageRepository;
    private final ProductCacheRepository cacheRepository;

    public SaleService(SaleRepository saleRepository, SaleItemRepository saleItemRepository, ClientRepository clientRepository,
                       UserRepository userRepository, SaleStageRepository stageRepository,
                       ProductCacheRepository cacheRepository) {
        this.saleRepository = saleRepository;
        this.saleItemRepository = saleItemRepository;
        this.clientRepository = clientRepository;
        this.userRepository = userRepository;
        this.stageRepository = stageRepository;
        this.cacheRepository = cacheRepository;
    }

    public List<ShortSaleResp> getAllSales(Pageable pageable,Stage reqStage, String term){
        SaleStage stage = null;
        if(reqStage != null){
            stage = this.stageRepository.findSaleStageByStage(reqStage)
                    .orElse(null);
        }

        Specification<SaleEntity> spec = Specification
                .where(SaleSpecification.hasStage(stage))
                .and(SaleSpecification.searchByTerm(term));
        Page<SaleEntity> saleEntities = this.saleRepository.findAll(spec,pageable);
        return this.mapShortSaleResp(saleEntities.stream().toList());
    }

    @Transactional
    public SaleCreationResp createSale(SaleCreateReq saleReq, String username){
        UUID clientId = saleReq.getClientId();
        CrmClientEntity clientEntity = this.clientRepository.findCrmClientEntityById(clientId).orElseThrow(
                () -> new CrmClientNotFoundException(saleReq.getClientId().toString(),"CLIENT_NOT_FOUND"));

        CrmUserEntity userEntity = this.userRepository.findCrmUserEntityByMail(username).orElseThrow(
                () -> new CrmUserNotFoundException(username));

        SaleStage stage = this.stageRepository.findSaleStageByStage(Stage.NOWA).orElseThrow(
                () -> new SaleStageNotFoundException("Internal error, sale stage not found","INTERNAL_ERROR")
        );

        List<SaleItemReq> items = saleReq.getSaleItems();
        List<CustomSaleItem> customItems = saleReq.getCustomItems();

        if(items.isEmpty() && customItems.isEmpty()){
            throw new EmptySaleItemsException("Both items lists are empty");
        }

        SaleEntity saleEntity = new SaleEntity();
        saleEntity.setSaleName(saleReq.getSaleName());
        saleEntity.setClient(clientEntity);
        saleEntity.setUser(userEntity);
        saleEntity.setStage(stage);
        saleEntity.setSaleData(saleReq.getSaleData());
        saleEntity.setWarehouseNote(saleReq.getWarehouseNote());

        List<SaleItem> itemsEntities = this.mapSaleItems(saleReq.getSaleItems(),saleEntity);
        itemsEntities.addAll(this.mapCustomItems(customItems,saleEntity));

        BigDecimal sum = calcTotal(itemsEntities);
        saleEntity.setSumPrice(sum);

        SaleEntity saleResult = this.saleRepository.save(saleEntity);
        List<SaleItem> itemsResult = this.saleItemRepository.saveAll(itemsEntities);
        return this.mapSaleCreationResponse(itemsResult,saleResult);
    }

    private BigDecimal calcTotal(List<SaleItem> itemsEntities) {
        return itemsEntities.stream()
                .map(SaleItem::getSumPrice)
                .reduce(BigDecimal.ZERO,BigDecimal::add);
    }

    @Transactional
    public SaleCreationResp updateSale(SaleUpdateReq req){
        SaleEntity sale = this.saleRepository.findSaleEntityById(req.getSaleId())
                .orElseThrow(() -> new SaleNotFoundException(req.getSaleId().toString(),"SALE_NOT_FOUND"));

        List<SaleItemReq> itemReqs = req.getSaleItems();
        List<CustomSaleItem> customItems = req.getCustomItems();
        if(itemReqs.isEmpty() && customItems.isEmpty()){
            throw new EmptySaleItemsException("Both items lists are empty");
        }

        List<SaleItem> items = this.mapSaleItems(itemReqs,sale);
        items.addAll(this.mapCustomItems(customItems,sale));
        BigDecimal sum = calcTotal(items);

        sale.getSaleItems().clear();
        sale.getSaleItems().addAll(items);
        sale.setSumPrice(sum);

        SaleEntity savedSale = this.saleRepository.save(sale);
        return this.mapSaleCreationResponse(savedSale.getSaleItems(),savedSale);
    }

    public SaleCreationResp getSaleBySaleId(UUID saleId){
        SaleEntity saleEntity = saleRepository.findSaleEntityById(saleId).orElseThrow(() -> new SaleNotFoundException(saleId.toString(),"SALE_NOT_FOUND"));
        return this.mapSaleCreationResponse(saleEntity.getSaleItems(),saleEntity);
    }

    public List<ShortSaleResp> getSalesByClientId(UUID clientId, Pageable pageable){
        List<SaleEntity> clientsSales = saleRepository.findSaleEntitiesByClientId(clientId,pageable);
        return mapShortSaleResp(clientsSales);
    }

    public List<ShortSaleResp> getSalesByUser(String username,Pageable pageable){
        List<SaleEntity> sales = this.saleRepository.findAllByUser_Mail(username,pageable);
        return this.mapShortSaleResp(sales);
    }

    @Transactional
    public SaleCreationResp modifySaleStage(StageOperationReq req){
        SaleEntity sale = this.saleRepository.findSaleEntityById(req.getSaleId())
                .orElseThrow(() -> new SaleNotFoundException(req.getSaleId().toString(),"SALE_NOT_FOUND"));

        if(req.getPackageDate() != null) {
            if (req.getPackageDate().isBefore(OffsetDateTime.now().truncatedTo(ChronoUnit.DAYS))) {
                throw new InvalidSaleDateException("Sale date cannot be in the past");
            }
            sale.setSaleDate(req.getPackageDate());
        }

        SaleStage stage = this.stageRepository.findSaleStageByStage(req.getStage())
                .orElseThrow(() -> new SaleStageNotFoundException(String.format("Sale stage %s not found",req.getStage()),"STAGE_NOT_FOUND"));

        if(stage.getStage() == Stage.DO_REALIZACJI){
            sale.setCheckedAt(OffsetDateTime.now());
        }
        sale.setStage(stage);
        SaleEntity saved = this.saleRepository.save(sale);
        return this.mapSaleCreationResponse(saved.getSaleItems(),saved);
    }

    public List<SaleResponse> getDailySales(OffsetDateTime marginDate){
        OffsetDateTime tomorrowStart;
        if(marginDate != null) {
            if(marginDate.isBefore(OffsetDateTime.now().truncatedTo(ChronoUnit.DAYS))){
                throw new InvalidSaleDateException("Invalid parameter, date cannot be in the past.");
            }
            tomorrowStart = marginDate;
        }else{
            tomorrowStart = OffsetDateTime.now();
        }
        tomorrowStart
                .plusDays(1)
                .truncatedTo(ChronoUnit.DAYS);
        List<SaleEntity> dailySales = this.saleRepository.findSaleEntitiesBySaleDateBeforeAndStage_Stage(tomorrowStart,Stage.DO_REALIZACJI);
        return this.mapWarehouseSales(dailySales);
    }

    @Transactional
    public void markSaleAsPacked(PackOperation req){
        SaleEntity sale = this.saleRepository.findSaleEntityById(req.getSaleId())
                .orElseThrow(() -> new SaleNotFoundException(req.getSaleId().toString(),"SALE_NOT_FOUND"));

        if(sale.getStage() != Stage.DO_REALIZACJI){
            throw new IllegalSaleOperation("Operation not permitted, incorrect sale stage");
        }
        SaleStage stage = this.stageRepository.findSaleStageByStage(Stage.SPAKOWANA)
                .orElseThrow(() -> new SaleStageNotFoundException(String.format("Sale stage %s not found", Stage.SPAKOWANA),"STAGE_NOT_FOUND"));
        sale.setStage(stage);
    }

    private List<ShortSaleResp> mapShortSaleResp(List<SaleEntity> clientsSales) {
        return clientsSales.stream().map(sale -> new ShortSaleResp(
                        sale.getId().toString(),
                        sale.getSaleName(),
                        sale.getStage(),
                        sale.getSumPrice().toString(),
                        sale.getClient().getName()))
                .toList();
    }

    private List<SaleResponse> mapWarehouseSales(List<SaleEntity> saleEntities){
        return saleEntities.stream()
                .map(sale -> new SaleResponse(
                        sale.getId().toString(),
                        sale.getSaleName(),
                        sale.getWarehouseNote(),
                        this.mapWarehouseItems(sale.getSaleItems()),
                        sale.getCreatedAt(),
                        sale.getCheckedAt(),
                        sale.getSaleDate(),
                        sale.getClient().getName(),
                        sale.getClient().getNipNumber()
                )).toList();
    }
    private List<SaleItemWarehouseView> mapWarehouseItems(List<SaleItem> items){
        return items.stream()
                .map(item -> new SaleItemWarehouseView(
                        item.getId().toString(),
                        item.getName(),
                        item.getUnit(),
                        item.getAmount(),
                        item.getInternalName()
                )).toList();
    }

    private List<SaleItem> mapSaleItems(List<SaleItemReq> saleItems,SaleEntity saleEntity){
        Set<UUID> ids = saleItems.stream().map(SaleItemReq::getProdId).collect(Collectors.toSet());
        List<ProductEntity> products = cacheRepository.findAllById(ids);
        Map<UUID, ProductEntity> productMap = products.stream()
                .collect(Collectors.toMap(ProductEntity::getId, p -> p));

       return saleItems.stream().map(saleItem -> {
            UUID cacheId = saleItem.getProdId();
            ProductEntity prodItem = Optional.ofNullable(productMap.get(cacheId))
                    .orElseThrow(() -> new ProductNotFoundException(cacheId.toString(),"PROD_NOT_FOUND"));

            SaleItem itemEntity = new SaleItem();
           itemEntity.setSale(saleEntity);
           itemEntity.setProduct(prodItem);
           itemEntity.setName(prodItem.getProductName());
           itemEntity.setInternalName(prodItem.getInternalName());
           itemEntity.setAmount(saleItem.getAmount());
           itemEntity.setUnitPriceAtSale(prodItem.getUnitPrice());
           itemEntity.setUnit(prodItem.getUnit());
            return itemEntity;
        }).collect(Collectors.toList());
    }

    private List<SaleItem> mapCustomItems(List<CustomSaleItem> customItems, SaleEntity entity){
        return customItems.stream()
                .map(item -> {
                    SaleItem itemEntity = new SaleItem();
                    itemEntity.setSale(entity);
                    itemEntity.setProduct(null);
                    itemEntity.setName(item.getName());
                    itemEntity.setInternalName(item.getInternal());
                    itemEntity.setAmount(item.getAmount());
                    itemEntity.setUnitPriceAtSale(item.getUnitPrice());
                    itemEntity.setUnit(item.getUnit());
                    return itemEntity;
                })
                .collect(Collectors.toList());
    }
    private SaleCreationResp mapSaleCreationResponse(List<SaleItem> items, SaleEntity saleEntity){
        List<SaleItemResponse> itemsResp = items.stream().map(item ->
                new SaleItemResponse(
                        item.getId().toString(),
                        item.getProduct() != null  ? item.getProduct().getId().toString(): null,
                        item.getName(),
                        item.getUnitPriceAtSale(),
                        item.getUnit(),
                        item.getAmount(),
                        item.getSumPrice(),
                        item.getInternalName()))
                .toList();
        return new SaleCreationResp(
                saleEntity.getId().toString(),
                saleEntity.getSaleData(),
                saleEntity.getWarehouseNote(),
                itemsResp,
                saleEntity.getStage(),
                saleEntity.getSumPrice(),
                saleEntity.getSaleName(),
                saleEntity.getClient().getId().toString(),
                saleEntity.getClient().getName(),
                saleEntity.getCreatedAt());
    }
}
