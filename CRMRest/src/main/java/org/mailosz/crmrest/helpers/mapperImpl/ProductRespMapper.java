package org.mailosz.crmrest.helpers.mapperImpl;

import org.mailosz.crmrest.helpers.Mapper;
import org.mailosz.crmrest.product.ProductEntity;
import org.mailosz.crmrest.product.ProductResponse;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class ProductRespMapper implements Mapper<ProductEntity, ProductResponse> {
    private ModelMapper mapper;

    public ProductRespMapper(ModelMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public ProductEntity mapTo(ProductResponse productResponse) {
        return this.mapper.map(productResponse,ProductEntity.class);
    }

    @Override
    public ProductResponse mapFrom(ProductEntity productEntity) {
        return this.mapper.map(productEntity, ProductResponse.class);
    }
}
