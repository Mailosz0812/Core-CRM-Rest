package org.mailosz.crmrest.exception.types;

public class CrmCategoryNotFoundException extends EntityNotFoundException {
    public CrmCategoryNotFoundException(String message) {
        super(String.format("Category with name or id: %s not found",message),"CATEGORY_NOT_FOUND");
    }
}
