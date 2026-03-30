package org.mailosz.crmrest.product;

import org.mailosz.crmrest.exception.types.ProductNotFoundException;
import org.mailosz.crmrest.helpers.Mapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final Mapper<ProductEntity, Product> responseMapper;


    public ProductService(ProductRepository productRepository, Mapper<ProductEntity, Product> responseMapper) {
        this.productRepository = productRepository;
        this.responseMapper = responseMapper;
    }

    public Product getProduct(UUID id){
        ProductEntity foundProduct = this.productRepository.findProductEntityById(id).orElseThrow(() -> new ProductNotFoundException(id.toString(),"PRODUCT_NOT_FOUND"));
        return this.responseMapper.mapFrom(foundProduct);
    }

    public List<Product> getAllProducts(){
        List<ProductEntity> productEntities = this.productRepository.findAll();
        return productEntities.stream().map(
                entity -> this.responseMapper.mapFrom(entity)
        ).toList();
    }
 }
