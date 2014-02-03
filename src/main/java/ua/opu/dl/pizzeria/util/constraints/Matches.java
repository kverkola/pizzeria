package ua.opu.dl.pizzeria.util.constraints;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.*;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target({TYPE, ANNOTATION_TYPE})
@Retention(RUNTIME)
@Constraint(validatedBy = MatchesValidator.class)
@Documented
public @interface Matches {

    String message() default "{springapp.util.constraints.matches}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    String[] fields();

    String[] verifyFields();
}
