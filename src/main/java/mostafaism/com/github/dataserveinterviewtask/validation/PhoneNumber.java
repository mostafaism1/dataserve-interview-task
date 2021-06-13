package mostafaism.com.github.dataserveinterviewtask.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

/**
 * Validates that a phone number string is purely numeric and of length = 9
 * numbers.
 */
@Constraint(validatedBy = PhoneNumberValidator.class)
@Target({ ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface PhoneNumber {

    String message() default "Invalid phone number. Phone number must be 9 numbers long.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
