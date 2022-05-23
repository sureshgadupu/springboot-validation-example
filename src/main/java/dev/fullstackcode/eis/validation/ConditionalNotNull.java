package dev.fullstackcode.eis.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({ FIELD, METHOD, PARAMETER, ANNOTATION_TYPE, TYPE_USE })
@Retention(RUNTIME)
@Constraint(validatedBy = ConditionalNotNullValidator.class)
@Documented
@Repeatable(ConditionalNotNull.List.class)
public @interface ConditionalNotNull {
    String message() default "{validation.conditionalNotNull}";
    Class<?>[] groups() default { };
    Class<? extends Payload>[] payload() default { };
    String fields() default "";
    String dependsOn() default "";

    @Target({ FIELD, METHOD, PARAMETER, ANNOTATION_TYPE, TYPE_USE })
    @Retention(RUNTIME)
    @Documented
    @interface List {
        ConditionalNotNull[] value();
    }
}