package org.mailosz.crmrest.product;

import org.mailosz.crmrest.exception.types.ProductCacheNotFoundException;
import org.mailosz.crmrest.exception.types.ProductNotFoundException;
import org.mailosz.crmrest.helpers.Mapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final ProductCacheRepository cacheRepository;
    private  Mapper<ProductEntity,ProductResponse> responseMapper;


    public ProductService(ProductRepository productRepository, ProductCacheRepository cacheRepository, Mapper<ProductEntity,ProductResponse> productResponseMapper) {
        this.productRepository = productRepository;
        this.cacheRepository = cacheRepository;
        this.responseMapper = productResponseMapper;
    }

    @Transactional
    public ProductResponse createProduct(ProductCreateReq productReq) {
        UUID cacheUUID = UUID.fromString(productReq.getCacheId());
        ProductState cacheEntity = cacheRepository.findProductStateById(cacheUUID).orElseThrow(() -> new ProductCacheNotFoundException(productReq.getCacheId(),"Err100"));

        ProductEntity finalProduct = productRepository.findProductEntityByProductStateId(cacheEntity.getId()).map(
                product -> {
                    product.setVisibility(true);
                    return this.productRepository.save(product);
                }
        ).orElseGet(() -> {
            ProductEntity mappedProdEntity = new ProductEntity();
            mappedProdEntity.setProductState(cacheEntity);
            mappedProdEntity.setVisibility(true);
            mappedProdEntity.setUnitPrice(productReq.getUnitPrice());

            return this.productRepository.save(mappedProdEntity);
        });
        return this.responseMapper.mapFrom(finalProduct);
    }

    public ProductResponse getProduct(String id){
        UUID prodUUID = UUID.fromString(id);
        ProductEntity foundProduct = this.productRepository.findProductEntityById(prodUUID).orElseThrow(() -> new ProductNotFoundException(id,"Err101"));
        return this.responseMapper.mapFrom(foundProduct);
    }
 }
