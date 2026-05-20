package org.mailosz.crmrest.prices;

import org.mailosz.crmrest.crmclient.ClientRepository;
import org.mailosz.crmrest.crmclient.CrmClientEntity;
import org.mailosz.crmrest.crmclient.response.ShortPriceListResp;
import org.mailosz.crmrest.exception.types.*;
import org.mailosz.crmrest.helpers.CategoryHelper;
import org.mailosz.crmrest.prices.request.*;
import org.mailosz.crmrest.prices.response.BasePriceListResponse;
import org.mailosz.crmrest.prices.response.PriceListResponse;
import org.mailosz.crmrest.prices.response.PriceListShortResp;
import org.mailosz.crmrest.product.ProductEntity;
import org.mailosz.crmrest.product.Product;
import org.mailosz.crmrest.product.category.CategoryEntity;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class PriceListService {
    private final PriceListRepository priceRepository;
    private final IndividualPriceListRepository individualRepository;
    private final BasePriceListRepository baseRepository;
    private final ClientRepository clientRepo;
    private final CategoryHelper categoryHelper;

    public PriceListService(PriceListRepository priceRepository, IndividualPriceListRepository individualRepository,
                            BasePriceListRepository baseRepository, ClientRepository clientRepo, CategoryHelper categoryHelper) {
        this.priceRepository = priceRepository;
        this.individualRepository = individualRepository;
        this.baseRepository = baseRepository;
        this.clientRepo = clientRepo;
        this.categoryHelper = categoryHelper;
    }

    @Transactional
    public PriceListResponse createPriceList(PriceListCreationReq creationReq){
        UUID clientId = UUID.fromString(creationReq. getClientId());

        CrmClientEntity client = this.clientRepo.findCrmClientEntityById(clientId)
                .orElseThrow(() -> new CrmClientNotFoundException(creationReq.getClientId(), "CLIENT_NOT_FOUND"));

        List<ListProduct> customItems = creationReq.getItems();
        List<BaseItem> baseItems = creationReq.getBaseItems();

        if(customItems.isEmpty() && baseItems.isEmpty()){
            throw new EmptyPriceListItems("Both products lists are empty");
        }

        Map<UUID,ProductEntity> baseProducts = getExistingBasePrices();

        Map<UUID,CategoryEntity> categoriesMap = this.categoryHelper.getCategoriesMap();
        IndividualPriceList priceList = new IndividualPriceList();
        List<ProductEntity> mappedProducts = this.mapFromBasePrices(baseProducts,baseItems,priceList);

        List<ProductEntity> products = new ArrayList<>(this.mapProducts(customItems, priceList,categoriesMap));
        products.addAll(mappedProducts);
        priceList.setProducts(products);
        priceList.setClient(client);
        priceList.setTitle(creationReq.getListTitle());
        IndividualPriceList savedList = this.priceRepository.save(priceList);
        System.out.println(savedList);
        return new PriceListResponse(
                savedList.getId().toString(),
                savedList.getTitle(),
                savedList.getCreatedAt(),
                this.mapProductResponse(savedList.getProducts())
        );
    }

    private Map<UUID, ProductEntity> getExistingBasePrices() {
        return this.baseRepository.findFirstBy().orElseThrow(() -> new PriceListNotFoundException("BASE", "PRICE_LIST_NOT_FOUND"))
                .getProducts().stream()
                .collect(Collectors.toMap(
                        ProductEntity::getId,
                        p -> p
                ));
    }

    @Transactional
    public BasePriceListResponse patchBasePriceList(BasePriceListOperationReq req){
        BasePriceList priceList = this.baseRepository.findFirstBy().orElseGet(() -> {
            BasePriceList saved = this.baseRepository.save(new BasePriceList());
            saved.setProducts(new ArrayList<>());
            return saved;
        });

        Map<Boolean, List<ProductOperation>> operations = this.divideOperations(req.getProductList());

        List<ProductUpdateReq> updateReqs = this.getReqs(false,operations);
        List<ProductUpdateReq> deleteReqs = this.getReqs(true,operations);

        Map<UUID,ProductEntity> existingEntities = priceList.getProducts().stream()
                .collect(Collectors.toMap(ProductEntity::getId,p -> p));

        Map<UUID,CategoryEntity> categoriesMap = this.categoryHelper.getCategoriesMap();

        this.processUpdates(updateReqs,existingEntities,priceList,categoriesMap);
        this.processDeletions(deleteReqs,existingEntities,priceList);
        BasePriceList savedList = this.priceRepository.save(priceList);
        return new BasePriceListResponse(this.mapProductResponse(savedList.getProducts()),priceList.getId().toString());
    }

    public BasePriceListResponse getBasePriceList(){
        BasePriceList priceList = this.baseRepository.findFirstBy().orElseThrow(() -> new PriceListNotFoundException("BASE","PRICE_LIST_NOT_FOUND"));
        return new BasePriceListResponse(this.mapProductResponse(priceList.getProducts()),priceList.getId().toString());
    }

    public PriceListResponse getPriceListById(UUID id){
        PriceListEntity priceList = this.priceRepository.findPriceListEntityById(id)
                .orElseThrow(() -> new PriceListNotFoundException(id.toString(),"PRICE_LIST_NOT_FOUND"));
        return new PriceListResponse(
                priceList.getId().toString(),
                priceList instanceof IndividualPriceList ? ((IndividualPriceList) priceList).getTitle(): "INDIVIDUAL",
                priceList.getCreatedAt(),
                this.mapProductResponse(priceList.getProducts())
        );
    }

    public List<PriceListShortResp> getPricesListByClientId(UUID id){
        this.clientRepo.findCrmClientEntityById(id).orElseThrow(() ->
                new CrmClientNotFoundException(id.toString(),"CLIENT_NOT_FOUND")
        );
        List<IndividualPriceList> prices = this.individualRepository.findPriceListEntitiesByClient_Id(id,Pageable.unpaged());
        return this.mapPriceListShort(prices);
    }

    public ShortPriceListResp getLatestProductsByClientId(UUID id){
        this.clientRepo.findCrmClientEntityById(id).orElseThrow(() ->
                new CrmClientNotFoundException(id.toString(),"CLIENT_NOT_FOUND")
        );
        Pageable latestOne = PageRequest.of(0, 1, Sort.by("createdAt").descending());
        List<IndividualPriceList> latestPrices = this.individualRepository.findPriceListEntitiesByClient_Id(id,latestOne);
        if(latestPrices.isEmpty()){
            return new ShortPriceListResp(null,Collections.emptyList());
        }
        return new ShortPriceListResp(latestPrices.get(0).getId().toString(),this.mapProductResponse(latestPrices.get(0).getProducts()));
    }

    public List<Product> getProductsByListId(UUID id){
        PriceListEntity listEntity = this.priceRepository.findPriceListEntityById(id)
                .orElseThrow(() -> new PriceListNotFoundException(id.toString(),"PRICE_LIST_NOT_FOUND"));
        return mapProductResponse(listEntity.getProducts());
    }
    public List<Product> getProductsToPrint(UUID id){
        List<ProductEntity> productsToPrint = this.priceRepository.findProductsToPrint(id);
        return this.mapProductResponse(productsToPrint);
    }

    @Transactional
    public PriceListResponse updateProductsList(PriceListUpdateReq req){
        PriceListEntity listEntity = this.priceRepository.findPriceListEntityById(req.getListId())
                .orElseThrow(() -> new PriceListNotFoundException(req.getListId().toString(),"PRICE_LIST_NOT_FOUND"));

        List<ProductUpdateReq> products = req.getProducts();
        List<BaseItem> baseItems = req.getBaseItems();
        if(products.isEmpty() && baseItems.isEmpty()){
            throw new EmptyPriceListItems("Both products lists are empty");
        }
        Map<UUID,ProductEntity> baseProducts = getExistingBasePrices();

        List<ProductEntity> fromBaseProducts = this.mapFromBasePrices(baseProducts,baseItems,listEntity);

        List<ProductUpdateReq> updateReqs = this.filterNullableProducts(products);
        Map<UUID,Boolean> existingProducts = this.mapExistingProducts(products);
        Map<UUID,CategoryEntity> categoriesMap = this.categoryHelper.getCategoriesMap();

        List<ProductEntity> entityProducts = listEntity.getProducts();
        entityProducts.removeIf(product -> !existingProducts.containsKey(product.getId()));
        entityProducts.addAll(this.mapUpdateProducts(updateReqs,listEntity,categoriesMap));
        entityProducts.addAll(fromBaseProducts);
        PriceListEntity savedList = this.priceRepository.save(listEntity);
        return new PriceListResponse(
                savedList.getId().toString(),
                savedList instanceof IndividualPriceList ? ((IndividualPriceList) savedList).getTitle(): "INDIVIDUAL",
                savedList.getCreatedAt(),
                this.mapProductResponse(savedList.getProducts())
        );
    }

    private void processUpdates(List<ProductUpdateReq> updateReqs,Map<UUID,ProductEntity> existingEntities, PriceListEntity priceList,Map<UUID,CategoryEntity> categoriesMap){
        for (ProductUpdateReq prodReq : updateReqs) {
            UUID currId = prodReq.getId();
            ProductEntity prod = new ProductEntity();
            if(currId != null){
                prod = existingEntities.get(currId);
                if(prod == null){
                    throw new ProductNotFoundException(currId.toString(),"PROD_NOT_FOUND");
                }
            }else{
                prod.setPriceList(priceList);
                prod.setVisibility(true);
                priceList.getProducts().add(prod);
            }
            UUID categoryId = prodReq.getCategory();
            CategoryEntity category = categoriesMap.get(categoryId);
            if(category == null){
                throw new CrmCategoryNotFoundException(categoryId.toString());
            }
            prod.setProductName(prodReq.getName());
            prod.setUnitPrice(prodReq.getUnitPrice());
            prod.setInternalName(prodReq.getInternal());
            prod.setPack(prodReq.getPack());
            prod.setProducer(prodReq.getProducer());
            prod.setUnit(prodReq.getUnit());
            prod.setTps(prodReq.getTps());
            prod.setCategory(category);
        }
    }
    private void processDeletions(List<ProductUpdateReq> deletionReqs,Map<UUID,ProductEntity> existingEntities, PriceListEntity priceList){
        for (ProductUpdateReq deletionReq : deletionReqs) {
            UUID id = deletionReq.getId();
            if(id == null){
                throw new UnprocessableContent("Product id is null","PROD_ID_NULL");
            }
            ProductEntity prod = existingEntities.get(id);
            if(prod == null){
                throw new ProductNotFoundException(id.toString(),"PROD_NOT_FOUND");
            }
            priceList.getProducts().remove(prod);
        }
    }
    private Map<Boolean,List<ProductOperation>> divideOperations(List<ProductOperation> operations){
        return operations.stream()
                .collect(Collectors.partitioningBy(ProductOperation::getDelete));
    }

    private List<ProductUpdateReq> getReqs(Boolean isDelete, Map<Boolean,List<ProductOperation>> operations){
        return operations.get(isDelete).stream()
                .map(ProductOperation::getProdReq)
                .toList();
    }
    private List<ProductUpdateReq> filterNullableProducts(List<ProductUpdateReq> products){
        return products.stream()
                .filter(product -> product.getId() == null)
                .toList();
    }

    private Map<UUID,Boolean> mapExistingProducts(List<ProductUpdateReq> products){
        return products.stream()
                .filter(product -> product.getId() != null)
                .collect(Collectors.toMap(
                        ProductUpdateReq::getId,
                        product -> true
                ));
    }

    private List<ProductEntity> mapUpdateProducts(List<ProductUpdateReq> products,PriceListEntity priceList,Map<UUID,CategoryEntity> categoriesMap){
        return products.stream().map(product -> {
            UUID categoryId = product.getCategory();
            if(!categoriesMap.containsKey(categoryId)){
                throw new CrmCategoryNotFoundException(categoryId.toString());
            }
            ProductEntity prod = new ProductEntity();
            prod.setProductName(product.getName());
            prod.setInternalName(product.getInternal());
            prod.setUnitPrice(product.getUnitPrice());
            prod.setVisibility(true);
            prod.setUnit(product.getUnit());
            prod.setPriceList(priceList);
            prod.setTps(product.getTps());
            prod.setPack(product.getPack());
            prod.setProducer(product.getProducer());
            prod.setCategory(categoriesMap.get(categoryId));
            return prod;
        }).toList();
    }

    private List<ProductEntity> mapProducts(List<ListProduct> products,PriceListEntity priceList,Map<UUID,CategoryEntity> categoriesMap){
        return products.stream().map(product -> {
            UUID categoryId = product.getCategoryId();
            if(!categoriesMap.containsKey(categoryId)){
                throw new CrmCategoryNotFoundException(categoryId.toString());
            }
            ProductEntity prod = new ProductEntity();
            prod.setProductName(product.getName());
            prod.setInternalName(product.getInternalName());
            prod.setUnitPrice(product.getUnitPrice());
            prod.setVisibility(true);
            prod.setUnit(product.getUnit());
            prod.setPriceList(priceList);
            prod.setTps(product.getTps());
            prod.setPack(product.getPack());
            prod.setProducer(product.getProducer());
            prod.setCategory(categoriesMap.get(categoryId));
            return prod;
        }).toList();
    }

    private List<Product> mapProductResponse(List<ProductEntity> productsList){
        return productsList.stream().map(prod -> new Product(
                prod.getId().toString(),
                prod.getProductName(),
                prod.getUnitPrice(),
                prod.getUnit(),
                prod.getInternalName(),
                prod.getTps(),
                prod.getProducer(),
                prod.getPack(),
                prod.getCategory().getName())
        ).toList();
    }

    private List<PriceListShortResp> mapPriceListShort(List<IndividualPriceList> prices){
        return prices.stream().map(price -> new PriceListShortResp(
                price.getId().toString(),
                price.getTitle(),
                price.getCreatedAt())
        ).toList();
    }

    private List<ProductEntity> mapFromBasePrices(Map<UUID,ProductEntity> existingProducts, List<BaseItem> baseItemsReq,PriceListEntity priceList){
        List<ProductEntity> prodList = new ArrayList<>();
        for (BaseItem baseItem : baseItemsReq) {
            ProductEntity prod = existingProducts.get(baseItem.getProdId());
            if(prod == null){
                throw new ProductNotFoundException(baseItem.getProdId().toString(),"PROD_NOT_FOUND");
            }
            ProductEntity e = new ProductEntity(prod);
            e.setPriceList(priceList);
            prodList.add(e);
        }
        return prodList;
    }
}
