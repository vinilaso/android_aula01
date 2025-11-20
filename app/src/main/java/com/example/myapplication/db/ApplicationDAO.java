package com.example.myapplication.db;

import android.content.ContentValues;

import com.example.myapplication.context.DBContext;
import com.example.myapplication.db.entities.DBColumnInfo;
import com.example.myapplication.db.entities.DBEntity;
import com.example.myapplication.db.entities.DBEntityHelper;
import com.example.myapplication.result.ValueResult;

public abstract class ApplicationDAO<TEntity extends DBEntity> {

    private final DBContext context;

    public ApplicationDAO() {
        context = new DBContext();
    }

    protected abstract Class<DBEntity> getBoundedType();
    protected abstract TEntity getNewInstance();

    public ValueResult<Long> insert(TEntity entity) {
        String tableName = DBEntityHelper.getDBEntityName(getBoundedType());

        final ValueResult<Long> insertResult = context.insert(tableName, )
    }

    private ContentValues buildEntityContentValues(TEntity entity) {
        final ContentValues contentValues = new ContentValues();

        for (final DBColumnInfo columnInfo : DBEntityHelper.getDBEntityFields(getBoundedType())) {
            final Object fieldValue = columnInfo.
        }

        return contentValues;
    }
}
