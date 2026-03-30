package org.mailosz.crmrest.helpers.mapperImpl;

import org.mailosz.crmrest.helpers.Mapper;
import org.mailosz.crmrest.product.ProductEntity;
import org.mailosz.crmrest.product.Product;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class ProductRespMapper implements Mapper<ProductEntity, Product> {
    private ModelMapper mapper;

    public ProductRespMapper(ModelMapper mapper) {
        this.mapper = mapper;

    }

    @Override
    public ProductEntity mapTo(Product productResponse) {
        return this.mapper.map(productResponse,ProductEntity.class);
    }

    @Override
    public Product mapFrom(ProductEntity productEntity) {
        return this.mapper.map(productEntity, Product.class);
    }
}
