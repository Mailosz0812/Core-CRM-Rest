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
    private final Mapper<ProductEntity,ProductResponse> responseMapper;


    public ProductService(ProductRepository productRepository, Mapper<ProductEntity, ProductResponse> responseMapper) {
        this.productRepository = productRepository;
        this.responseMapper = responseMapper;
    }

    public ProductResponse getProduct(UUID id){
        ProductEntity foundProduct = this.productRepository.findProductEntityById(id).orElseThrow(() -> new ProductNotFoundException(id.toString(),"PRODUCT_NOT_FOUND"));
        return this.responseMapper.mapFrom(foundProduct);
    }

    public List<ProductResponse> getAllProducts(){
        List<ProductEntity> productEntities = this.productRepository.findAll();
        return productEntities.stream().map(
                entity -> this.responseMapper.mapFrom(entity)
        ).toList();
    }
 }
