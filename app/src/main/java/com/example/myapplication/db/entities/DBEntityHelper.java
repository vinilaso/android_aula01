package com.example.myapplication.db.entities;

import com.example.myapplication.annotations.DBColumn;
import com.example.myapplication.annotations.DBTable;
import com.example.myapplication.db.DBColumnType;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public final class DBEntityHelper {
    private static final HashMap<Class<DBEntity>, DBColumnInfo[]> cachedFields = new HashMap<>();
    private static final HashMap<Class<DBEntity>, String> cachedNames = new HashMap<>();

    public static DBColumnInfo[] getDBEntityFields(Class<DBEntity> type) {
        if (cachedFields.containsKey(type)) {
            return cachedFields.get(type);
        }

        final Field[] classFields = getClassFields(type);

        final List<DBColumnInfo> columnInfos = new ArrayList<>();

        for (final Field field : classFields) {
            final DBColumn dbColumnAttribute = field.getDeclaredAnnotation(DBColumn.class);

            if (dbColumnAttribute == null) {
                continue;
            }

            final DBColumnInfo columnInfo = new DBColumnInfo(
                    field,
                    dbColumnAttribute.columnName().isEmpty() ? field.getName().toUpperCase() : dbColumnAttribute.columnName(),
                    dbColumnAttribute.dataType(),
                    dbColumnAttribute.primaryKey(),
                    dbColumnAttribute.isNull()
            );

            columnInfos.add(columnInfo);
        }

        final DBColumnInfo[] columnArray = columnInfos.toArray(new DBColumnInfo[0]);
        cachedFields.put(type, columnArray);

        return columnArray;
    }

    public static String getDBEntityName(Class<DBEntity> type) {
        if (cachedNames.containsKey(type)) {
            return cachedNames.get(type);
        }

        final DBTable dbTableAttribute = type.getDeclaredAnnotation(DBTable.class);

        if (dbTableAttribute == null) {
            throw new RuntimeException("A classe não possui a anotação DBTable.");
        }

        cachedNames.put(type, dbTableAttribute.table());
        return dbTableAttribute.table();
    }

    private static Field[] getClassFields(Class<?> type) {
        final Class<?> superClass = type.getSuperclass();
        if (superClass == null) {
            return type.getDeclaredFields();
        }

        final Field[] superClassFields = getClassFields(superClass);
        final Field[] baseClassFields = type.getDeclaredFields();
        final Field[] allFields = new Field[superClassFields.length + baseClassFields.length];

        System.arraycopy(superClassFields, 0, allFields, 0, superClassFields.length);
        System.arraycopy(allFields, 0, allFields, superClassFields.length, baseClassFields.length);

        return allFields;
    }
}
