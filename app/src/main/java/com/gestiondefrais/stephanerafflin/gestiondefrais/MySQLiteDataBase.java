package com.gestiondefrais.stephanerafflin.gestiondefrais;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by stephane.rafflin on 15/09/2017.
 */

public class MySQLiteDataBase extends SQLiteOpenHelper {
    String createTable;
    String table;

    public MySQLiteDataBase(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, String create, String tab) {
        super(context, name, factory, version);
        createTable = create;
        table=tab;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE " + table + ";");
        onCreate(db);
    }
}

