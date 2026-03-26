package org.mailosz.crmrest.exception.types;

public class CrmClientAlreadyExistsException extends EntityAlreadyExists {
    public CrmClientAlreadyExistsException(String message) {
        super(message,"CLIENT_ALREADY_EXISTS");
    }
}
