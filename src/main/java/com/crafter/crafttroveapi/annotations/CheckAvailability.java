package com.crafter.crafttroveapi.annotations;

import com.crafter.crafttroveapi.helpers.CheckType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface CheckAvailability {

   CheckType type() default CheckType.NONE;
}