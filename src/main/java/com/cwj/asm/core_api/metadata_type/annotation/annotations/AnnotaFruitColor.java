package com.cwj.asm.core_api.metadata_type.annotation.annotations;

import java.lang.annotation.*;

@Target({ElementType.FIELD,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface AnnotaFruitColor {

    Color fruitColor() default Color.GREEN;

    public enum Color {BULE, RED, GREEN}
}
