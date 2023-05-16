package ro.itschool.homesphere.validators;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Constraint(validatedBy = NoDigitsValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface NoDigits {
    String message() default "Name cannot contain digits";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

