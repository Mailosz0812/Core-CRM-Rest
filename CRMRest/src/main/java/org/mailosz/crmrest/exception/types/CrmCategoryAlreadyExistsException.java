package org.mailosz.crmrest.exception.types;

public class CrmCategoryAlreadyExistsException extends EntityAlreadyExists {
    public CrmCategoryAlreadyExistsException(String name) {
        super(String.format("Category with name: %s already exists",name),"CATEGORY_ALREADY_EXISTS");
    }
}
