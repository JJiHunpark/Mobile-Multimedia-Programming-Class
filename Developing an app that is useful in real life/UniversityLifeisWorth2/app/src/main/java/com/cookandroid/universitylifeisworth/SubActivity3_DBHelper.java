package com.cookandroid.universitylifeisworth;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by LG on 2016-06-19.
 */
public class SubActivity3_DBHelper  extends SQLiteOpenHelper {
    public SubActivity3_DBHelper( Context context) {
        super( context, "textmemo.db", null, 1);
    }

    public void onCreate( SQLiteDatabase db ) {
        String table = "CREATE TABLE memo ( "
                + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "writedate INGEGER, content TEXT )";
        db.execSQL(table);
    }

    public void onUpgrade( SQLiteDatabase db, int oldVersion, int newVersion ) {
        db.execSQL( "DROP TABLE IF EXISTS memo" );
        onCreate( db );
    }
}