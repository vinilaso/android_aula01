package com.example.myapplication;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.List;

public class DBContext {
    private static SQLiteDatabase  _db;

    public static void initContext(SQLiteDatabase db)
    {
        _db = db;
        createTables();
    }

    private static void createTables()
    {
        _db.execSQL("CREATE TABLE IF NOT EXISTS PESSOAS (ID INTEGER PRIMARY KEY AUTOINCREMENT, NOME TEXT)");
    }

    public DBContext()
    {
        if (_db == null)
            throw new RuntimeException("Use o método initContext.");
    }

    public long insert(ContentValues contentValues, String tableName)
    {
        try {
            return _db.insert(tableName, null, contentValues);
        } catch (Exception e) {
            return -1;
        }
    }

    public Cursor select(String tableName, List<String> fields, String whereClause)
    {
        String query = " SELECT " +
                String.join(", ", fields) +
                " FROM " +
                tableName +
                " WHERE " +
                whereClause;

        return _db.rawQuery(query, null);
    }
}
