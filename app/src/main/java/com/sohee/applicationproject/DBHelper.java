package com.sohee.applicationproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

class DBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "MyNote.db";
    public static final String NOTE_TABLE_NAME = "note";
    public static final String NOTE_ID = "id";
    public static final String NOTE_LOCATION = "name";
    public static final String NOTE_NAME = "director";
    public static final String NOTE_CONTENT = "nation";
    public static final String NOTE_COST = "rating";
    public static final String NOTE_AREA = "country";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(
                "create table note " +
                        "(id integer primary key,country text, name text, director text, nation text, rating text)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS note");
        onCreate(db);
    }

    public boolean insertNote(String country, String name, String director, String nation, String rating) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("country", country);
        contentValues.put("name", name);
        contentValues.put("director", director);
        contentValues.put("nation", nation);
        contentValues.put("rating", rating);

        db.insert("note", null, contentValues);
        return true;
    }

    public Cursor getData(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from note where id=" + id + "", null);
        return res;
    }

    public int numberOfRows() {
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db, NOTE_TABLE_NAME);
        return numRows;
    }

    public boolean updateNote(Integer id, String country, String name, String director, String nation, String rating) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("country", country);
        contentValues.put("name", name);
        contentValues.put("director", director);
        contentValues.put("nation", nation);
        contentValues.put("rating", rating);
        db.update("note", contentValues, "id = ? ", new String[]{Integer.toString(id)});
        return true;
    }

    public Integer deleteNote(Integer id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("note",
                "id = ? ",
                new String[]{Integer.toString(id)});
    }

    public ArrayList getAllNote() {
        ArrayList array_list = new ArrayList();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from note", null);
        res.moveToFirst();
        while (res.isAfterLast() == false) {
            array_list.add(res.getString(res.getColumnIndex(NOTE_ID))+" "+
                    res.getString(res.getColumnIndex(NOTE_LOCATION)));
            res.moveToNext();
        }
        return array_list;
    }
}
