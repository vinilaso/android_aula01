package com.example.myapplication;

import android.content.ContentValues;

public class Pessoa implements IDbEntity {

    private long _id;
    private final String _name;

    public Pessoa(String name)
    {
        _name = name;
    }

    public long getId()
    {
        return _id;
    }

    public String getName()
    {
        return  _name;
    }

    @Override
    public void setId(long id) {
        _id = id;
    }
}
