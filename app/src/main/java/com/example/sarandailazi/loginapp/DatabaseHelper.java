package com.example.sarandailazi.loginapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.renderscript.Sampler;

/**
 * Created by Saranda Ilazi on 13/09/2017.
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 3;
    private static final String DATABASE_NAME = "contacts.db";
    private static final String TABLE_NAME = "contacts";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_SURNAME = "surname";
    private static final String COLUMN_EMAIL = "email";
    private static final String COLUMN_UNAME = "uname";
    private static final String COLUMN_PASS = "pass";
    private static final String COLUMN_GENDER = "gender";
    private static final String COLUMN_BIRTHDAY = "birthday";
    private static final String COLUMN_ADDRESS = "address";

    SQLiteDatabase db;

    private static final String TABLE_CREATE = "create table contacts (id integer primary key not null, " +
            "name text not null, surname text not null, email text not null, uname text not null, " +
            "pass text not null, gender text not null, birthday text not null, address text not null);";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE);
        this.db = db;
    }

    public void insertContact(Contact c) {
        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        String query = "select * from " + TABLE_NAME;
        Cursor cursor = db.rawQuery(query, null);
        int count = cursor.getCount();

        values.put(COLUMN_ID, count);
        values.put(COLUMN_NAME, c.getName());
        values.put(COLUMN_SURNAME, c.getSurname());
        values.put(COLUMN_EMAIL, c.getEmail());
        values.put(COLUMN_UNAME, c.getUname());
        values.put(COLUMN_PASS, c.getPass());
        values.put(COLUMN_GENDER, c.getGender());
        values.put(COLUMN_BIRTHDAY, c.getBirthday());
        values.put(COLUMN_ADDRESS, c.getAddress());

        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    public String searchPass(String uname) {
        db = this.getReadableDatabase();
        String query = "select uname, pass from " + TABLE_NAME;
        Cursor cursor = db.rawQuery(query, null);
        String a, b;
        b = "not found";

        if (cursor.moveToFirst()) {
            do {
                a = cursor.getString(0);
                if (a.equals(uname)) {
                    b = cursor.getString(1);
                    break;
                }
            } while (cursor.moveToNext());
        }
        return b;
    }

    public Contact getUserByEmail(String uname) {
        db = this.getReadableDatabase();
        String query = "select name, surname, email, uname, pass, birthday, address, gender from " + TABLE_NAME +
                " WHERE email = @uname";
        Cursor cursor = db.rawQuery(query, new String [] {uname});
        Contact contact = new Contact();

        if (cursor.moveToFirst()) {
            contact.setName(cursor.getString(cursor.getColumnIndex("name")));
            contact.setSurname(cursor.getString(cursor.getColumnIndex("surname")));
            contact.setEmail(cursor.getString(cursor.getColumnIndex("email")));
            contact.setUname(cursor.getString(cursor.getColumnIndex("uname")));
            contact.setBirthday(cursor.getString(cursor.getColumnIndex("birthday")));
            contact.setAddress(cursor.getString(cursor.getColumnIndex("address")));
            contact.setGender(cursor.getString(cursor.getColumnIndex("gender")));
        }

        return contact;
    }

    public boolean userExistsByEmail(String email) {
        db = this.getReadableDatabase();
        String query = "select email from " + TABLE_NAME;
        Cursor cursor = db.rawQuery(query, null);
        String a, b;
        b = "not found";

        if (cursor.moveToFirst()) {
            do {
                a = cursor.getString(0);
                if (a.equals(email)) {
                    b = cursor.getString(0);
                    break;
                }
            } while (cursor.moveToNext());
        }
        return b.equals(email);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String query = "DROP TABLE IF EXISTS " + TABLE_NAME;
        db.execSQL(query);
        this.onCreate(db);
    }

}
