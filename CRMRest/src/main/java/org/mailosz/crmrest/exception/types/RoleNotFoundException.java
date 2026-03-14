package org.mailosz.crmrest.exception.types;

import java.util.Map;

public class RoleNotFoundException extends EntityNotFoundException {
    public RoleNotFoundException(String role) {
        super(
                String.format("Role %s does not exists",role),
                "ROLE_NOT_FOUND",
                Map.of("Role",role)
        );
    }
}
