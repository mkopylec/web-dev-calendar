package com.github.mkopylec.webdevcalendar.api;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Constraint(validatedBy = TimeSlotBoundaryValidator.class)
@Target({TYPE, FIELD, PARAMETER})
@Retention(RUNTIME)
public @interface TimeSlotBoundary {

    String message();

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
