package com.github.dstaflund.nts.search.area.validator;

import javax.validation.Constraint;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = NorthGreaterThanSouthValidator.class)
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface NorthGreaterThanSouth {
    String message() default "";
    String[] groups() default {};
}
