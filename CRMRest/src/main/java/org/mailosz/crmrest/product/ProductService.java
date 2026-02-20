package org.mailosz.crmrest.product;

import org.mailosz.crmrest.crmclient.ClientRepository;
import org.mailosz.crmrest.crmclient.CrmClientEntity;
import org.mailosz.crmrest.exception.types.CrmClientNotFoundException;
import org.mailosz.crmrest.exception.types.ProductCacheNotFoundException;
import org.mailosz.crmrest.exception.types.ProductNotFoundException;
import org.mailosz.crmrest.helpers.Mapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final ProductCacheRepository cacheRepository;
    private  Mapper<ProductEntity,ProductResponse> responseMapper;
    private final ClientRepository clientRepository;


    public ProductService(ProductRepository productRepository, ProductCacheRepository cacheRepository, Mapper<ProductEntity, ProductResponse> responseMapper, ClientRepository clientRepository) {
        this.productRepository = productRepository;
        this.cacheRepository = cacheRepository;
        this.responseMapper = responseMapper;
        this.clientRepository = clientRepository;
    }

    @Transactional
    public ProductResponse createProduct(ProductCreateReq productReq) {
        UUID cacheUUID = UUID.fromString(productReq.getCacheId());
        ProductState cacheEntity = cacheRepository.findProductStateById(cacheUUID).orElseThrow(() -> new ProductCacheNotFoundException(productReq.getCacheId(),"Err100"));

        UUID clientUUID = UUID.fromString(productReq.getClientId());
        CrmClientEntity clientEntity = this.clientRepository.findCrmClientEntityById(clientUUID).orElseThrow(() -> new CrmClientNotFoundException(productReq.getClientId(),"Err201"));

        ProductEntity finalProduct = productRepository.findProductEntityByClientIdAndProductStateId(clientEntity.getId(),cacheEntity.getId()).map(
                product -> {
                    product.setVisibility(true);
                    product.setUnitPrice(productReq.getUnitPrice());
                    product.setProductName(productReq.getProductName());
                    return this.productRepository.save(product);
                }
        ).orElseGet(() -> {
            ProductEntity mappedProdEntity = new ProductEntity();
            mappedProdEntity.setProductState(cacheEntity);
            mappedProdEntity.setVisibility(true);
            mappedProdEntity.setUnitPrice(productReq.getUnitPrice());
            mappedProdEntity.setProductName(productReq.getProductName());
            mappedProdEntity.setClient(clientEntity);

            return this.productRepository.save(mappedProdEntity);
        });

        return this.responseMapper.mapFrom(finalProduct);
    }
    public ProductResponse getProduct(String id){
        UUID prodUUID = UUID.fromString(id);
        ProductEntity foundProduct = this.productRepository.findProductEntityById(prodUUID).orElseThrow(() -> new ProductNotFoundException(id,"Err102"));
        return this.responseMapper.mapFrom(foundProduct);
    }

    public List<ProductResponse> getAllProducts(){
        List<ProductEntity> productEntities = this.productRepository.findAll();
        return productEntities.stream().map(
                entity -> this.responseMapper.mapFrom(entity)
        ).toList();
    }
 }
