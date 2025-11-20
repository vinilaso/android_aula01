package com.example.myapplication.db.entities;

import com.example.myapplication.annotations.DBColumn;
import com.example.myapplication.db.DBColumnType;

public abstract class DBEntity {
    @DBColumn(dataType = DBColumnType.LONG)
    private Long id;

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
