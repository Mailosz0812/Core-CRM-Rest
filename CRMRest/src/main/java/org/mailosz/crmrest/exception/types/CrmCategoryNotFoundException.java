package org.mailosz.crmrest.exception.types;

public class CrmCategoryNotFoundException extends EntityNotFoundException {
    public CrmCategoryNotFoundException(String name) {
        super(String.format("Category with name: %s not found",name),"CATEGORY_NOT_FOUND");
    }
}
