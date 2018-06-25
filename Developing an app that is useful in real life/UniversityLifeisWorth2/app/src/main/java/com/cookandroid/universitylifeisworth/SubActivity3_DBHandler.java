package com.cookandroid.universitylifeisworth;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;

import java.util.Calendar;


public class SubActivity3_DBHandler {
    SubActivity3_DBHelper helper;
    SQLiteDatabase db;
    Context context;

    public void openDB() {
        helper = new SubActivity3_DBHelper( context);
        db = helper.getWritableDatabase();
    }

    public void closeDB() {
        if( helper != null && db != null ) {
            helper.close();
            helper = null;
            db = null;
        }
    }

    public SubActivity3_DBHandler( Context context ){
        this.context = context;
        openDB();
    }

    public static SubActivity3_DBHandler open( Context context ) throws SQLException {
        SubActivity3_DBHandler handler = new SubActivity3_DBHandler( context );
        return handler;
    }

    public void close() {
        closeDB();
    }

    public long insert( String memo ) {
        ContentValues val = new ContentValues();
        Calendar cal = Calendar.getInstance();
        val.put( "writedate", cal.getTimeInMillis() );
        val.put( "content", memo );
        return db.insert( "memo", null, val );
    }

    public long update( int id, String memo ) {
        ContentValues val = new ContentValues();
        Calendar cal = Calendar.getInstance();
        val.put( "writedate", cal.getTimeInMillis() );
        val.put( "content", memo );
        return db.update("memo", val, "id = ?", new String[] { String.valueOf( id ) });
    }

    public Cursor selectAll() throws SQLiteException {
        Cursor cursor = db.query( "memo",
                new String[] { "id", "writedate",  "content" },
                null,  null,  null,  null,  null );

        if( cursor == null ) return null;

        if( cursor.getCount() == 0 ) {
            cursor.close();
            return null;
        }

        cursor.moveToFirst();
        return cursor;
    }

    public SubActivity3_MemoInfo select( int id ) {
        Cursor cursor = db.query( "memo",
                new String[] { "id", "writedate", "content" },
                "id = ?", new String[] { Integer.toString( id ) }, null, null, null );
        if( cursor == null ) return null;
        if( cursor.getCount() == 0 ) {
            cursor.close();
            return null;
        }

        cursor.moveToFirst();
        SubActivity3_MemoInfo memo = new SubActivity3_MemoInfo();
        memo.id = cursor.getInt( 0 );
        memo.writeDate = cursor.getLong( 1 );
        memo.memo = cursor.getString( 2 );
        return memo;
    }

    public long delete( int id ) {
        return db.delete( "memo", "id = ?", new String[] { String.valueOf(id) } );
    }
}
