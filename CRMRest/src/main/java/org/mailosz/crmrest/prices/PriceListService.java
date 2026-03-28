package org.mailosz.crmrest.prices;

import org.mailosz.crmrest.crmclient.ClientRepository;
import org.mailosz.crmrest.crmclient.CrmClientEntity;
import org.mailosz.crmrest.exception.types.CrmClientNotFoundException;
import org.mailosz.crmrest.exception.types.PriceListNotFoundException;
import org.mailosz.crmrest.prices.request.ListProduct;
import org.mailosz.crmrest.prices.request.PriceListCreationReq;
import org.mailosz.crmrest.prices.response.PriceListResponse;
import org.mailosz.crmrest.prices.response.PriceListShortResp;
import org.mailosz.crmrest.product.ProductEntity;
import org.mailosz.crmrest.product.ProductResponse;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class PriceListService {
    private final PriceListRepository priceRepository;
    private final ClientRepository clientRepo;

    public PriceListService(PriceListRepository priceRepository, ClientRepository clientRepo) {
        this.priceRepository = priceRepository;
        this.clientRepo = clientRepo;
    }

    public PriceListResponse createPriceList(PriceListCreationReq creationReq){
        UUID clientId = UUID.fromString(creationReq. getClientId());

        CrmClientEntity client = this.clientRepo.findCrmClientEntityById(clientId)
                .orElseThrow(() -> new CrmClientNotFoundException(creationReq.getClientId(), "CLIENT_NOT_FOUND"));


        PriceListEntity priceList = new PriceListEntity();
        List<ProductEntity> products = this.mapProducts(creationReq.getItems(), priceList);
        priceList.setProducts(products);
        priceList.setClient(client);
        priceList.setTitle(creationReq.getListTitle());
        PriceListEntity savedList = this.priceRepository.save(priceList);
        System.out.println(savedList);
        return new PriceListResponse(
                savedList.getId().toString(),
                savedList.getTitle(),
                savedList.getCreatedAt(),
                this.mapProductResponse(savedList.getProducts())
        );
    }

    public PriceListResponse getPriceListById(UUID id){
        PriceListEntity priceList = this.priceRepository.findPriceListEntityById(id)
                .orElseThrow(() -> new PriceListNotFoundException(id.toString(),"PRICE_LIST_NOT_FOUND"));
        return new PriceListResponse(
                priceList.getId().toString(),
                priceList.getTitle(),
                priceList.getCreatedAt(),
                this.mapProductResponse(priceList.getProducts())
        );
    }

    public List<PriceListShortResp> getPricesListByClientId(UUID id){
        this.clientRepo.findCrmClientEntityById(id).orElseThrow(() ->
                new CrmClientNotFoundException(id.toString(),"CLIENT_NOT_FOUND")
        );
        List<PriceListEntity> prices = this.priceRepository.findPriceListEntitiesByClient_Id(id);
        return this.mapPriceListShort(prices);
    }

    public List<ProductResponse> getProductsByListId(UUID id){
        PriceListEntity listEntity = this.priceRepository.findPriceListEntityById(id)
                .orElseThrow(() -> new PriceListNotFoundException(id.toString(),"PRICE_LIST_NOT_FOUND"));
        return mapProductResponse(listEntity.getProducts());
    }

    private List<ProductEntity> mapProducts(List<ListProduct> products,PriceListEntity priceList){
        return products.stream().map(product -> {
            ProductEntity prod = new ProductEntity();
            prod.setProductName(product.getName());
            prod.setUnitPrice(product.getUnitPrice());
            prod.setVisibility(true);
            prod.setCategory(product.getProdCategory());
            prod.setPriceList(priceList);
            return prod;
        }).toList();
    }

    private List<ProductResponse> mapProductResponse(List<ProductEntity> productsList){
        return productsList.stream().map(prod -> new ProductResponse(
                prod.getId().toString(),
                prod.getProductName(),
                prod.getUnitPrice(),
                prod.getCategory().name())
        ).toList();
    }

    private List<PriceListShortResp> mapPriceListShort(List<PriceListEntity> prices){
        return prices.stream().map(price -> new PriceListShortResp(
                price.getId().toString(),
                price.getTitle(),
                price.getCreatedAt())
        ).toList();
    }
}
