package com.example.myapplication.annotations;

import com.example.myapplication.db.DBColumnType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface DBColumn {
    DBColumnType dataType();
    String columnName() default "";
    boolean primaryKey() default false;
    boolean isNull() default true;
}
