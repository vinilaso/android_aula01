package com.example.myapplication.db.entities;

import com.example.myapplication.db.DBColumnType;

import java.lang.reflect.Field;

public class DBColumnInfo {
    private final Field javaField;
    private final String fieldName;
    private final DBColumnType columnType;
    private final boolean isPrimaryKey;
    private final boolean isNull;

    public DBColumnInfo(Field javaField, String fieldName, DBColumnType columnType, boolean isPrimaryKey, boolean isNull) {
        this.javaField = javaField;
        this.fieldName = fieldName;
        this.columnType = columnType;
        this.isPrimaryKey = isPrimaryKey;
        this.isNull = isNull;
    }

    public Field getJavaField() {
        return javaField;
    }

    public String getFieldName() {
        return fieldName;
    }

    public DBColumnType getColumnType() {
        return columnType;
    }

    public boolean isPrimaryKey() {
        return isPrimaryKey;
    }

    public boolean isNull() {
        return isNull;
    }
}
