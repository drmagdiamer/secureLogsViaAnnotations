package com.drmagdiamer.logAnnotation.secureLogAnnotation.annotaion;

import java.lang.annotation.*;

@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@LogSecurityAnnotationAccessType
public @interface Masked {
    public int order() default Integer.MAX_VALUE ;
    public String maskName() default "";
}
