package com.example.demo10;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class MyDBHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "ContactList";
    private static final int DATABASE_ID = 2;
    private static final String TABLE_CONTACT = "contact";
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_PHONE_NO = "phone_no";

    public MyDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_ID);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE "+TABLE_CONTACT+"("+KEY_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+KEY_NAME+" TEXT, "+KEY_PHONE_NO+" TEXT"+")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_CONTACT);
        onCreate(db);
    }
    public void addContact(ContactModel contactModel){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, contactModel.getName());
        values.put(KEY_PHONE_NO, contactModel.getNumber());
        db.insert(TABLE_CONTACT, null, values);
    }
    public ArrayList<ContactModel> fetchContact(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM "+ TABLE_CONTACT, null);
        ArrayList<ContactModel> arrContact = new ArrayList<>();
        while (cursor.moveToNext()){
            ContactModel model = new ContactModel(cursor.getString(1), cursor.getString(2));
            arrContact.add(model);
        }
        return arrContact;
    }
}




















