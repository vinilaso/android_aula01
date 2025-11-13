package com.example.myapplication;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

abstract class ApplicationDAO<TEntity extends IDbEntity> {

    private final DBContext _dbContext = new DBContext();

    public abstract String getTableName();
    public abstract ContentValues getContentValuesFrom(TEntity entity);
    public abstract List<String> getTableFields();
    public abstract  TEntity buildEntityFrom(Cursor cursor);

    public boolean insert(TEntity entity)
    {
        ContentValues cv = getContentValuesFrom(entity);
        long id = _dbContext.insert(cv, getTableName());

        if (id < 0)
            return false;

        entity.setId(id);
        return true;
    }

    public TEntity select(long id)
    {
        @SuppressLint("DefaultLocale") String criteria = String.format("ID = %d", id);

        TEntity entity = null;
        try (Cursor cursor = _dbContext.select(getTableName(), getTableFields(), criteria))
        {
            cursor.moveToFirst();

            if (!cursor.isAfterLast())
                entity = buildEntityFrom(cursor);
        }

        return entity;
    }

    public List<TEntity> selectMany(String criteria)
    {
        List<TEntity> entities = new ArrayList<>();
        try (Cursor cursor = _dbContext.select(getTableName(), getTableFields(), criteria))
        {
            cursor.moveToFirst();
            do
            {
                entities.add(buildEntityFrom(cursor));
                cursor.moveToNext();
            }
            while (!cursor.isAfterLast());
        }

        return entities;
    }
}
