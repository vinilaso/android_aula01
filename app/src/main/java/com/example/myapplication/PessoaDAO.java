package com.example.myapplication;

import android.content.ContentValues;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PessoaDAO extends ApplicationDAO<Pessoa> {

    @Override
    public String getTableName() {
        return "PESSOAS";
    }

    @Override
    public ContentValues getContentValuesFrom(Pessoa entity) {
        ContentValues cv = new ContentValues();
        cv.put("NOME", entity.getName());
        return cv;
    }

    @Override
    public List<String> getTableFields() {
        List<String> fields = new ArrayList<>();

        fields.add("ID");
        fields.add("NOME");

        return fields;
    }

    @Override
    public Pessoa buildEntityFrom(Cursor cursor) {
        int nomeIndex = cursor.getColumnIndex("NOME");
        int idIndex = cursor.getColumnIndex("ID");

        assert nomeIndex >= 0 && idIndex >= 0;

        Pessoa pessoa = new Pessoa(cursor.getString(nomeIndex));
        pessoa.setId(cursor.getLong(idIndex));

        return pessoa;
    }

    public List<Pessoa> selectAll()
    {
        return selectMany("1 = 1");
    }
}
