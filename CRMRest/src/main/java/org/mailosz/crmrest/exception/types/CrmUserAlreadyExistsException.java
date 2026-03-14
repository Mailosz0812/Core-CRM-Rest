package org.mailosz.crmrest.exception.types;


public class CrmUserAlreadyExistsException extends EntityAlreadyExists {
    public CrmUserAlreadyExistsException(String mail) {
        super(String.format("User with mail: %s already exists",mail),
                "USER_ALREADY_EXISTS" );
    }
}
