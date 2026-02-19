package org.mailosz.crmrest.exception.types;

public class ProductCacheNotFoundException extends EntityNotFoundException {
    public ProductCacheNotFoundException(String cacheId,String errorCode) {
        super(
                String.format("Product with id: %s not found",cacheId),
                errorCode
        );
    }
}
