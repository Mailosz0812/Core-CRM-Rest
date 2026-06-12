package org.mailosz.crmrest.exception.types;

import java.util.Map;

public class TargetNotFoundException extends EntityNotFoundException{
    public TargetNotFoundException(String userId) {
        super(String.format("Target for user with id %s not found",userId), "TARGET_NOT_FOUND");
    }
}
