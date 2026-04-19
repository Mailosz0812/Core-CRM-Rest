package org.mailosz.crmrest.prices;

import org.mailosz.crmrest.crmclient.ClientRepository;
import org.mailosz.crmrest.crmclient.CrmClientEntity;
import org.mailosz.crmrest.exception.types.CrmClientNotFoundException;
import org.mailosz.crmrest.exception.types.PriceListNotFoundException;
import org.mailosz.crmrest.exception.types.ProductNotFoundException;
import org.mailosz.crmrest.prices.request.*;
import org.mailosz.crmrest.prices.response.BasePriceListResponse;
import org.mailosz.crmrest.prices.response.PriceListResponse;
import org.mailosz.crmrest.prices.response.PriceListShortResp;
import org.mailosz.crmrest.product.ProductEntity;
import org.mailosz.crmrest.product.Product;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class PriceListService {
    private final PriceListRepository priceRepository;
    private final IndividualPriceListRepository individualRepository;
    private final BasePriceListRepository baseRepository;
    private final ClientRepository clientRepo;

    public PriceListService(PriceListRepository priceRepository, IndividualPriceListRepository individualRepository,
                            BasePriceListRepository baseRepository, ClientRepository clientRepo) {
        this.priceRepository = priceRepository;
        this.individualRepository = individualRepository;
        this.baseRepository = baseRepository;
        this.clientRepo = clientRepo;
    }

    public PriceListResponse createPriceList(PriceListCreationReq creationReq){
        UUID clientId = UUID.fromString(creationReq. getClientId());

        CrmClientEntity client = this.clientRepo.findCrmClientEntityById(clientId)
                .orElseThrow(() -> new CrmClientNotFoundException(creationReq.getClientId(), "CLIENT_NOT_FOUND"));


        IndividualPriceList priceList = new IndividualPriceList();
        List<ProductEntity> products = this.mapProducts(creationReq.getItems(), priceList);
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
    @Transactional
    public BasePriceListResponse patchBasePriceList(BasePriceListCreationReq req){
        BasePriceList priceList = this.baseRepository.findFirst().orElseGet(() -> this.baseRepository.save(new BasePriceList()));

        List<ProductUpdateReq> productsList = req.getProductList();

        Map<UUID,ProductEntity> existingEntities = priceList.getProducts().stream()
                .collect(Collectors.toMap(ProductEntity::getId,p -> p));

        for (ProductUpdateReq prodReq : productsList) {
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
            prod.setProductName(prodReq.getName());
            prod.setUnitPrice(prodReq.getUnitPrice());
            prod.setInternalName(prodReq.getInternal());
            prod.setCategory(prodReq.getCategory());
            prod.setUnit(prodReq.getUnit());
        }
        return new BasePriceListResponse(this.mapProductResponse(priceList.getProducts()));
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

    public List<Product> getLatestProductsByClientId(UUID id){
        this.clientRepo.findCrmClientEntityById(id).orElseThrow(() ->
                new CrmClientNotFoundException(id.toString(),"CLIENT_NOT_FOUND")
        );
        Pageable latestOne = PageRequest.of(0, 1, Sort.by("createdAt").descending());
        List<IndividualPriceList> latestPrices = this.individualRepository.findPriceListEntitiesByClient_Id(id,latestOne);
        if(latestPrices.isEmpty()){
            return Collections.emptyList();
        }
        return this.mapProductResponse(latestPrices.get(0).getProducts());
    }

    public List<Product> getProductsByListId(UUID id){
        PriceListEntity listEntity = this.priceRepository.findPriceListEntityById(id)
                .orElseThrow(() -> new PriceListNotFoundException(id.toString(),"PRICE_LIST_NOT_FOUND"));
        return mapProductResponse(listEntity.getProducts());
    }

    @Transactional
    public PriceListResponse updateProductsList(PriceListUpdateReq req){
        PriceListEntity listEntity = this.priceRepository.findPriceListEntityById(req.getListId())
                .orElseThrow(() -> new PriceListNotFoundException(req.getListId().toString(),"PRICE_LIST_NOT_FOUND"));

        List<ProductUpdateReq> products = req.getProducts();
        List<ProductUpdateReq> updateReqs = this.filterNullableProducts(products);
        Map<UUID,Boolean> existingProducts = this.mapExistingProducts(products);

        List<ProductEntity> entityProducts = listEntity.getProducts();
        entityProducts.removeIf(product -> !existingProducts.containsKey(product.getId()));
        entityProducts.addAll(this.mapUpdateProducts(updateReqs,listEntity));
        PriceListEntity savedList = this.priceRepository.save(listEntity);
        return new PriceListResponse(
                savedList.getId().toString(),
                savedList instanceof IndividualPriceList ? ((IndividualPriceList) savedList).getTitle(): "INDIVIDUAL",
                savedList.getCreatedAt(),
                this.mapProductResponse(savedList.getProducts())
        );
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

    private List<ProductEntity> mapUpdateProducts(List<ProductUpdateReq> products,PriceListEntity priceList){
        return products.stream().map(product -> {
            ProductEntity prod = new ProductEntity();
            prod.setProductName(product.getName());
            prod.setInternalName(product.getInternal());
            prod.setUnitPrice(product.getUnitPrice());
            prod.setVisibility(true);
            prod.setCategory(product.getCategory());
            prod.setUnit(product.getUnit());
            prod.setPriceList(priceList);
            return prod;
        }).toList();
    }

    private List<ProductEntity> mapProducts(List<ListProduct> products,PriceListEntity priceList){
        return products.stream().map(product -> {
            ProductEntity prod = new ProductEntity();
            prod.setProductName(product.getName());
            prod.setInternalName(product.getInternalName());
            prod.setUnitPrice(product.getUnitPrice());
            prod.setVisibility(true);
            prod.setCategory(product.getProdCategory());
            prod.setUnit(product.getUnit());
            prod.setPriceList(priceList);
            return prod;
        }).toList();
    }

    private List<Product> mapProductResponse(List<ProductEntity> productsList){
        return productsList.stream().map(prod -> new Product(
                prod.getId().toString(),
                prod.getProductName(),
                prod.getUnitPrice(),
                prod.getUnit(),
                prod.getCategory().name(),
                prod.getInternalName())
        ).toList();
    }

    private List<PriceListShortResp> mapPriceListShort(List<IndividualPriceList> prices){
        return prices.stream().map(price -> new PriceListShortResp(
                price.getId().toString(),
                price.getTitle(),
                price.getCreatedAt())
        ).toList();
    }
}
