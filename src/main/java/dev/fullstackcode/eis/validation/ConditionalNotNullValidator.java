package dev.fullstackcode.eis.validation;

import org.hibernate.validator.constraintvalidation.HibernateConstraintValidatorContext;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ConditionalNotNullValidator implements ConstraintValidator<ConditionalNotNull, Object> {

    List<String> fields;
    String dependsOnField;

    @Override
    public void initialize(ConditionalNotNull constraintAnnotation) {
        fields = Arrays.stream(constraintAnnotation.fields().split(",")).collect(Collectors.toList());
        dependsOnField = constraintAnnotation.dependsOn();

    }

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext context) {

        if(o == null) {
            return true;
        }


        Object fieldValue = getFieldValue(dependsOnField,o);
//        if(fieldValue == null) {
//            return true;
//        }
       List<String> errorFields =  fields.stream().filter(f -> getFieldValue(f,o) == null).collect(Collectors.toList());

        if(fieldValue !=null && errorFields.isEmpty()) {
            return true;
        }

        if(fieldValue ==null && errorFields.isEmpty()) {

            context.disableDefaultConstraintViolation();
            context
                    .buildConstraintViolationWithTemplate("{validation.custom.conditionalNull}")
                   .addConstraintViolation();
            return false;
        }

        ((HibernateConstraintValidatorContext)context.unwrap(HibernateConstraintValidatorContext.class)).addMessageParameter("fields",String.join(",",errorFields));

        return false;
    }



    public static Object getFieldValue(String fieldName,Object object) {
        try {
            Field field = object.getClass().getDeclaredField(fieldName);
            field.setAccessible(true);
            return  field.get(object);
        }  catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}


