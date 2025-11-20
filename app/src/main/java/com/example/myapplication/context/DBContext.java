package com.example.myapplication.context;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

import com.example.myapplication.result.ValueResult;
import com.example.myapplication.utils.Path;

public class DBContext {
    public static String filesDir;

    public static void setFilesDir(String filesDir) {
        DBContext.filesDir = filesDir;
    }

    private final SQLiteDatabase database;

    public DBContext() {
        assert filesDir != null && !filesDir.isEmpty();
        database = SQLiteDatabase.openOrCreateDatabase(Path.combine(filesDir, "databases/APP_DB"), null);
    }

    public ValueResult<Long> insert(String tableName, ContentValues contentValues) {
        try {
            long insertedId = database.insert(tableName, null, contentValues);
            return ValueResult.success(insertedId);
        } catch (Exception e) {
            return ValueResult.valueFailure(e.getMessage());
        }
    }
}
