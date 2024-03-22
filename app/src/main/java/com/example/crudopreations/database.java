package com.example.crudopreations;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class database extends SQLiteOpenHelper
{
    public static final String dbname = "books.db";
    public database(@Nullable Context context) {
        super(context, dbname
                , null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql = "CREATE TABLE tblbooks (bookid integer PRIMARY KEY autoincrement,title text,author text,subject text,price integer)";
        sqLiteDatabase.execSQL(sql);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1)
    {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS tblbooks");
        onCreate(sqLiteDatabase);
    }
    public long insert_data(String title,String auth,String subject,int price){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("title",title);
        cv.put("author",auth);
        cv.put("subject",subject);
        cv.put("price",price);
        long i = db.insert("tblbooks",null,cv);
        return i;
    }
    public Cursor show_data(){
       SQLiteDatabase db = this.getReadableDatabase();
       Cursor cr = db.rawQuery("SELECT * FROM tblbooks",null);
       return cr;
    }
    public int delete_data(int id){
        SQLiteDatabase db = this.getWritableDatabase();
//        ContentValues cv = new ContentValues();
        int rows= db.delete("tblbooks","bookid="+id,null);
        return rows;

    }
}
