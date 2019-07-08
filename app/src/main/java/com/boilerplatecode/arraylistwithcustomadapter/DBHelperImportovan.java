package com.boilerplatecode.arraylistwithcustomadapter;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

import static android.content.ContentValues.TAG;




    public class DBHelperImportovan extends SQLiteOpenHelper {
        private  static final String DATABASE_NAME = "db_user_arraylist.db";
        private static final int DATABASE_VERSION = 1;
        private static final String TABLE_NAME = "db_user";
        private static final String COL1 = "ID";
        private static final String COL2 = "name";
        private static final String COL3 = "note";
       // private  static final String COL4 = "temperature";


//    public DatabaseHelper(Context context) {
//        super(context, TABLE_NAME, null, 1);
//    }

        public DBHelperImportovan(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
//TODO dobro provjeriti sql string
            String createTable = "CREATE TABLE " + TABLE_NAME +
                    " (ID INTEGER PRIMARY KEY AUTOINCREMENT, " + COL2 + " TEXT, " + COL3 +
                    " TIMESTAMP DEFAULT CURRENT_TIMESTAMP )"  ;
            db.execSQL(createTable);

        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int i, int i1) {
            String s = "DROP IF TABLE EXISTS ";
            db.execSQL(s + TABLE_NAME);
            onCreate(db);
        }

        public boolean addData (String item, String note)
        {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put(COL2, item);
            contentValues.put(COL3,note );
            Log.d(TAG, "addData " + item + " " + note);

            long result = db.insert(TABLE_NAME,null,contentValues);// vraća -1 ako nije ok insert
//if (result==-1)
//{ return  false;}
//else
//{return true;}
            return (result!=-1);//primjer dobrog KODa :))) TODO
        }

        public Cursor getData()
        {
            SQLiteDatabase db = this.getWritableDatabase();
            String query = "SELECT * FROM " + TABLE_NAME;
            Cursor data = db.rawQuery(query,null);
            return  data;


        }

        public  Cursor getItemID (String name)
        {
            SQLiteDatabase db = this.getWritableDatabase();
            String query = "SELECT " + COL1 + " FROM " + TABLE_NAME + " WHERE "+ COL2 + " ='" + name + "'";
            return( db.rawQuery(query, null));


        }

        public void updateName (String newName, int id, String oldName)
        {
            SQLiteDatabase db = this.getWritableDatabase();
            String query = "UPDATE " + TABLE_NAME + " SET " + COL2 + " = '" + newName + "' WHERE " + COL1 + " ='" + id + "'" + " AND "+ COL2 + " ='" + oldName + "'";
            Log.d(TAG,"updateName: query " + query);
            Log.d(TAG, "updateName: Setting name to " + newName);
            db.execSQL(query);
        }

        //DELETE red
        public  void deleteName (int id, String name)
        {
            SQLiteDatabase db = this.getWritableDatabase();
            String query = "DELETE FROM " + TABLE_NAME  + " WHERE " + COL1 + " ='" + id + "'" + " AND " + COL2 + " ='" + name + "'";
            Log.d(TAG,"deleteName: query " + query);
            Log.d(TAG, "deleteName: Deleting name  " + name);
            db.execSQL(query);
        }

        public ArrayList<User> getAllData()
        {
            ArrayList<User> arrayList = new ArrayList<>();
            SQLiteDatabase db = this.getReadableDatabase();
            Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
while (cursor.moveToNext())
{
    String id = cursor.getString(0);
    String name = cursor.getString(1);
    String note = cursor.getString(2);
    User u = new User(id,name,note);

    arrayList.add(u);
}
return arrayList;
        }

    }


