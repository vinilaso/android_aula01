package com.example.myapplication.db;

import android.database.Cursor;

import java.util.function.BiFunction;

public enum DBColumnType {

    INTEGER("INTEGER", Cursor::getInt),
    LONG("INTEGER", Cursor::getLong),
    VARCHAR("VARCHAR", Cursor::getString),
    TEXT("TEXT", Cursor::getString),
    DOUBLE("DOUBLE", Cursor::getDouble);

    private final String sqliteDataType;
    private final BiFunction<Cursor, Integer, Object> cursorGetter;

    DBColumnType(String sqliteDataType, BiFunction<Cursor, Integer, Object> cursorGetter) {
        this.sqliteDataType = sqliteDataType;
        this.cursorGetter = cursorGetter;
    }

    public String getSqliteDataType() {
        return this.sqliteDataType;
    }

    public Object executeCursorGetter(Cursor cursor, int index) {
        try {
            return cursorGetter.apply(cursor, index);
        } catch (Exception e) {
            return null;
        }
    }
}
