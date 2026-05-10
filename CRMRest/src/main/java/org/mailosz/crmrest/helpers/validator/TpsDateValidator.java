package org.mailosz.crmrest.helpers.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.OffsetDateTime;
import java.time.temporal.ChronoUnit;

public class TpsDateValidator implements ConstraintValidator<TpsDate, OffsetDateTime> {

    @Override
    public boolean isValid(OffsetDateTime value, ConstraintValidatorContext context) {
        context.disableDefaultConstraintViolation();

        if(value == null){
            return true;
        }

        OffsetDateTime marginDate = OffsetDateTime.now()
                .minusDays(7)
                .truncatedTo(ChronoUnit.DAYS);

        if(value.isBefore(marginDate)){
            this.setErrorMessage(context,"Data tps nie może być starsza niż 7 dni wstecz","tps");
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
