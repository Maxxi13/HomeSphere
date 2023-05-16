package ro.itschool.homesphere.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class GmailValidator implements ConstraintValidator<Gmail,String> {

    @Override
    public void initialize(Gmail constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String email, ConstraintValidatorContext constraintValidatorContext) {
        if(email == null) {
            return true;
        }
        return email.contains("@gmail");
    }
}
