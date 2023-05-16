package ro.itschool.homesphere.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class NoDigitsValidator implements ConstraintValidator<NoDigits, String> {
    @Override
    public void initialize(NoDigits constrain) {

    }
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if(value==null){
            return true;
        }

        for (char c : value.toCharArray()) {
            if(Character.isDigit(c)){
                return false;
            }
        }
        return true;
    }
}

