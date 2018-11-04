package com.jun.de_ai;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBAdapter {
    static final String DATABASE_CREATE = "";
    static final String DATABASE_NAME = "";
    static final String DATABASE_TABLE = "";
    static final int DATABASE_VERSION = 1;
    static final String KEY_NAME = "";
    static final String KEY_EMAIL = "";

    static final String TAG = "DBAdapter";
    final Context context;
    DatabaseHelper DBHelper;
    SQLiteDatabase db;

    public  DBAdapter(Context ctx){
        this.context = ctx;
        DBHelper = new DatabaseHelper(context);
    }

    private static class DatabaseHelper extends SQLiteOpenHelper {

        DatabaseHelper(Context context){
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            try{
                db.execSQL(DATABASE_CREATE);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            Log.w(TAG, "");
            db.execSQL("");
            onCreate(db);
        }
    }

    public DBAdapter open() throws SQLException {
        db = DBHelper.getWritableDatabase();
        return this;
    }

    public void close(){
        DBHelper.close();
    }

    public long insert(String name, String email){
        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_NAME, name);
        contentValues.put(KEY_EMAIL, email);
        return db.insert(DATABASE_TABLE, null,contentValues);
    }

    public boolean delete(long rowid){
        return db.delete(DATABASE_TABLE, "_id="+rowid, null)>0;
    }

    public Cursor getAll(){
        return db.query(DATABASE_TABLE,new String[]{KEY_NAME, KEY_EMAIL},null,
                null,null,null,null);
    }

    public Cursor get(long rowid) throws SQLException{
        Cursor cursor = db.query(DATABASE_TABLE,new String[]{KEY_NAME, KEY_EMAIL},"_id="+rowid,
                null,null,null,null);
        if(cursor != null){
            cursor.moveToFirst();
        }
        return cursor;
    }

    public boolean update(long rowid,String name, String email){
        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_NAME, name);
        contentValues.put(KEY_EMAIL,email);
        return db.update(DATABASE_TABLE,contentValues,"_id="+rowid,null)>0;
    }
}
