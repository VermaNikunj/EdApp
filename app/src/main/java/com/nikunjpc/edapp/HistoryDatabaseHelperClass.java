package com.nikunjpc.edapp;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class HistoryDatabaseHelperClass extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 3;

    public static final String DATABASE_NAME = "tracking_database";

    private static final String TABLE_NAME = "HISTORY_TABLE";

    public static final String ID = "id";
    public static final String CLASS_TYPE = "class_type";
    public static final String FILE_NAME = "file_name";
    public static  final String URL ="url";

    private SQLiteDatabase sqLiteDatabase;


    public HistoryDatabaseHelperClass(Context context) {
        super( context, DATABASE_NAME, null, DATABASE_VERSION );
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "(" + ID +
                " INTEGER PRIMARY KEY AUTOINCREMENT, " + CLASS_TYPE + " TEXT NOT NULL," +
                FILE_NAME + " TEXT NOT NULL,"+ URL+" TEXT NOT NULL ) ";

        db.execSQL( CREATE_TABLE );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL( " DROP TABLE IF EXISTS " + TABLE_NAME );
        onCreate( db );
    }

    public void addItem(HistoryModelClass modelClass) {
        ContentValues contentValues = new ContentValues();
        contentValues.put( HistoryDatabaseHelperClass.CLASS_TYPE, modelClass.getClass_type() );
        contentValues.put( HistoryDatabaseHelperClass.FILE_NAME, modelClass.getFile_name() );
        contentValues.put( HistoryDatabaseHelperClass.URL, modelClass.getUrl() );

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
                String class_type = cursor.getString( 1 );
                String file_name = cursor.getString( 2 );
                String url = cursor.getString( 3 );
                store.add( new HistoryModelClass( id, class_type, file_name, url ) );
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