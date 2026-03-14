package org.mailosz.crmrest.sales;

import org.mailosz.crmrest.crmclient.ClientRepository;
import org.mailosz.crmrest.crmclient.CrmClientEntity;
import org.mailosz.crmrest.crmuser.CrmUserEntity;
import org.mailosz.crmrest.crmuser.UserRepository;
import org.mailosz.crmrest.exception.types.*;
import org.mailosz.crmrest.helpers.Mapper;
import org.mailosz.crmrest.product.ProductCacheRepository;
import org.mailosz.crmrest.product.ProductState;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class SaleService {
    private final SaleRepository saleRepository;
    private final SaleItemRepository saleItemRepository;
    private final ClientRepository clientRepository;
    private final UserRepository userRepository;
    private final SaleStageRepository stageRepository;
    private final ProductCacheRepository cacheRepository;
    private final Mapper<SaleEntity,SaleResponse> saleRespMapper;
    private final Mapper<SaleEntity,ShortSaleResp> shortSaleRespMapper;

    public SaleService(SaleRepository saleRepository, SaleItemRepository saleItemRepository, ClientRepository clientRepository,
                       UserRepository userRepository, SaleStageRepository stageRepository,
                       ProductCacheRepository cacheRepository, Mapper<SaleEntity, SaleResponse> saleRespMapper,
                       Mapper<SaleEntity,ShortSaleResp> shortSaleRespMapper) {
        this.saleRepository = saleRepository;
        this.saleItemRepository = saleItemRepository;
        this.clientRepository = clientRepository;
        this.userRepository = userRepository;
        this.stageRepository = stageRepository;
        this.cacheRepository = cacheRepository;
        this.saleRespMapper = saleRespMapper;
        this.shortSaleRespMapper = shortSaleRespMapper;
    }

    @Transactional
    public SaleCreationResp createSale(SaleCreateReq saleReq){
        UUID clientId = UUID.fromString(saleReq.getClientId());
        CrmClientEntity clientEntity = this.clientRepository.findCrmClientEntityById(clientId).orElseThrow(
                () -> new CrmClientNotFoundException(saleReq.getClientId(),"Err100"));

        UUID userId = UUID.fromString(saleReq.getUserId());
        CrmUserEntity userEntity = this.userRepository.findCrmUserEntityById(userId).orElseThrow(
                () -> new CrmUserNotFoundException(saleReq.getUserId(),"Err101"));

        SaleStage stage = this.stageRepository.findSaleStageByStage(Stage.CREATED).orElseThrow(
                () -> new SaleStageNotFoundException("Internal error, sale stage not found","Err999")
        );

        SaleEntity saleEntity = new SaleEntity();
        saleEntity.setClient(clientEntity);
        saleEntity.setUser(userEntity);
        saleEntity.setStage(stage);
        saleEntity.setSaleData(saleReq.getSaleData());

        List<SaleItem> itemsEntities = this.mapSaleItems(saleReq.getSaleItems(),saleEntity);
        BigDecimal total = itemsEntities.stream().map(SaleItem::getSumPrice).reduce(BigDecimal.ZERO,BigDecimal::add);
        saleEntity.setSumPrice(total);
        SaleEntity saleResult = this.saleRepository.save(saleEntity);
        List<SaleItem> itemsResult = this.saleItemRepository.saveAll(itemsEntities);
        return this.mapSaleResponse(itemsResult,saleResult);
    }

    public SaleResponse getSaleBySaleId(String saleId){
        UUID id = UUID.fromString(saleId);
        SaleEntity saleEntity = saleRepository.findSaleEntityById(id).orElseThrow(() -> new SaleNotFoundException(saleId,"Err100"));
        return this.saleRespMapper.mapFrom(saleEntity);
    }

    public List<ShortSaleResp> getSalesByClientId(String clientId){
        UUID id = UUID.fromString(clientId);
        List<SaleEntity> clientsSales = saleRepository.findSaleEntitiesByClientId(id);
        return clientsSales.stream().map(this.shortSaleRespMapper::mapFrom).toList();
    }

    private List<SaleItem> mapSaleItems(List<SaleItemReq> saleItems,SaleEntity saleEntity){
       return saleItems.stream().map(saleItem -> {
            UUID cacheId = UUID.fromString(saleItem.getProdCacheId());
            ProductState prodSate = this.cacheRepository.findProductStateById(cacheId).orElseThrow(
                    () -> new ProductCacheNotFoundException(saleItem.getProdCacheId(), "Err102"));

            if(prodSate.getProductState().subtract(saleItem.getAmount()).compareTo(BigDecimal.ZERO) < 0){
                throw new ProductOutOfStockException(
                        String.format("Product %s out of stock, available: %s expected: %s",saleItem.getName(),
                                prodSate.getProductState().toString(),saleItem.getAmount().toString()),
                        "Err103",
                        Map.of("available",prodSate.getProductState(), "expected",saleItem.getAmount())
                );
            }
            SaleItem itemEntity = new SaleItem();
            itemEntity.setSale(saleEntity);
            itemEntity.setAmount(saleItem.getAmount());
            itemEntity.setProduct(prodSate);
            itemEntity.setUnitPriceAtSale(saleItem.getUnitPrice());
            return itemEntity;
        }).toList();
    }
    private SaleCreationResp mapSaleResponse(List<SaleItem> items, SaleEntity saleEntity){
        List<SaleItemResponse> itemsResp = items.stream().map(item ->
                new SaleItemResponse(
                        item.getId().toString(),
                        item.getName(),
                        item.getUnitPriceAtSale(),
                        item.getAmount(),
                        item.getSumPrice()))
                .toList();
        return new SaleCreationResp(saleEntity.getId().toString(),saleEntity.getSaleData(),
                itemsResp,saleEntity.getStage(),saleEntity.getSumPrice());
    }
}
