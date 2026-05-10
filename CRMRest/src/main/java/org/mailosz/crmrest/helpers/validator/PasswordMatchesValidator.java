package org.mailosz.crmrest.helpers.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.mailosz.crmrest.crmuser.request.UserCreateReq;
import org.springframework.beans.factory.annotation.Value;


public class PasswordMatchesValidator implements ConstraintValidator<ValidPassword, UserCreateReq> {

    private final String PASS_PATTERN;

    public PasswordMatchesValidator(@Value("${app.security.pass-pattern}") String PASS_PATTERN) {
        this.PASS_PATTERN = PASS_PATTERN;
    }

    @Override
    public boolean isValid(UserCreateReq value, ConstraintValidatorContext context) {
        context.disableDefaultConstraintViolation();
        if(value.getPassword() == null) {
            setErrorMessage(context,"EMPTY_PASS","password");
            return false;
        }
        if(value.getConfirmPassword() == null) {
            setErrorMessage(context,"EMPTY_CONF_PASS","confirmPassword");
            return false;
        }

        String dtoPass = value.getPassword();
        String dtoConfirm = value.getConfirmPassword();

        if(!dtoConfirm.equals(dtoPass)) {
            setErrorMessage(context,"PASS_NOT_SAME","confirmPassword");
            return false;
        }
        if (!dtoPass.matches(PASS_PATTERN)){
            setErrorMessage(context,"PASS_WEAK","password");
            return false;
        }
        return true;
    }

    private void setErrorMessage(ConstraintValidatorContext context, String message, String propertyName) {
        context.buildConstraintViolationWithTemplate(message)
                .addPropertyNode(propertyName)
                .addConstraintViolation();
    }
}
