package com.drmagdiamer.logAnnotation.secureLogAnnotation.annotaion;

import java.lang.annotation.*;

@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.ANNOTATION_TYPE)
public @interface LogSecurityAnnotationAccessType {}


