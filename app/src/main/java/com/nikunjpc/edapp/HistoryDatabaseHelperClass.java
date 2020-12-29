package com.nikunjpc.edapp;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class HistoryDatabaseHelperClass extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;

    public static final String DATABASE_NAME = "tracking_database";

    private static final String TABLE_NAME = "TRACKING";

    public static final String ID = "id";
    public static final String FILE_NAME = "file_name";
    public static final String CLASS_TYPE = "class_type";

    private SQLiteDatabase sqLiteDatabase;


    public HistoryDatabaseHelperClass(Context context) {
        super( context, DATABASE_NAME, null, DATABASE_VERSION );
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "(" + ID +
                " INTEGER PRIMARY KEY AUTOINCREMENT," + FILE_NAME + " TEXT NOT NULL," +
                CLASS_TYPE + " TEXT NOT NULL)";
        db.execSQL( CREATE_TABLE );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL( " DROP TABLE IF EXISTS " + TABLE_NAME );
        onCreate( db );
    }

    public void addItem(HistoryModelClass modelClass) {
        ContentValues contentValues = new ContentValues();
        contentValues.put( HistoryDatabaseHelperClass.FILE_NAME, modelClass.getFile_name() );
        contentValues.put( HistoryDatabaseHelperClass.CLASS_TYPE, modelClass.getClass_type() );

        SQLiteDatabase db = getWritableDatabase();
        db.insert( TABLE_NAME, null, contentValues );
    }

    public List<HistoryModelClass> getList() {
        String sql = "select * from " + TABLE_NAME;
        SQLiteDatabase db= this.getWritableDatabase();
        List<HistoryModelClass> store = new ArrayList<>();
        Cursor cursor = db.rawQuery( sql, null );
        if (cursor.moveToFirst()) {
            do {
                int id = Integer.parseInt( cursor.getString( 0 ) );
                String file_name = cursor.getString( 1 );
                String class_type = cursor.getString( 2 );
                store.add( new HistoryModelClass( id, file_name, class_type ) );
            } while (cursor.moveToNext());

        }
//        cursor.close();
        return store;
    }

//
//    public void updateItem(HistoryModelClass modelClass) {
//
//        ContentValues contentValues = new ContentValues();
//        contentValues.put( HistoryDatabaseHelperClass.NAME, modelClass.getName() );
//        contentValues.put( DatabaseHelperClass.OPERATOR, modelClass.getOperator() );
//        contentValues.put( DatabaseHelperClass.TRACKING_NO, modelClass.getTr() );
//
//        SQLiteDatabase db= this.getWritableDatabase();
//        db.update( TABLE_NAME, contentValues, ID+" = ? ", new String[]
//                { String.valueOf( modelClass.getId())});
//
//    }

    public void deleteItem (int id)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete( TABLE_NAME, ID+ " = ? ", new String[] {String.valueOf( id )} );

    }
}