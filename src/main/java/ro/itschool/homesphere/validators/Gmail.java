package ro.itschool.homesphere.validators;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Constraint(validatedBy = GmailValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Gmail {
    String message() default "The email address must be gmail";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
