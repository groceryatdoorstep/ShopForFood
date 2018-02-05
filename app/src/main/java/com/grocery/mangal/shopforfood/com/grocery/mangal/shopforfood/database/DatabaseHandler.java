package com.grocery.mangal.shopforfood.com.grocery.mangal.shopforfood.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

/**
 * Created by mangal on 20/12/17.
 */

public class DatabaseHandler extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "Shop_For_Food";
    private static final String TABLE_NAME = "sff_user";
    private static final String USER_TABLE = "users";
    private static final String USER_ID = "USER_ID";
    private static final String USER_NAME = "USER_NAME";
    private static final String PHONE_NO = "PHONE_NO";
    private static final String EMAIL = "EMAIL";
    private static final String PASSWORD = "PASSWORD";
    private static final String ADDRESS = "ADDRESS";
    SQLiteDatabase sQLiteDatabase;
    ContentValues values;

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String Shop_For_Food = "CREATE TABLE " + TABLE_NAME +
                "(" + USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                USER_NAME + " TEXT, " + PHONE_NO + " TEXT, " +
                ADDRESS + " TEXT, " + EMAIL + " TEXT, " + PASSWORD + " TEXT)";
        String User_Table = "CREATE TABLE " + USER_TABLE +
                "(" + USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                USER_NAME + " TEXT, " + PHONE_NO + " TEXT, " +
                ADDRESS + " TEXT, " + EMAIL + " TEXT, " + PASSWORD + " TEXT)";
        //sqLiteDatabase.execSQL(Shop_For_Food);
        sqLiteDatabase.execSQL(User_Table);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + USER_TABLE);
    }

    public boolean createUser(String userName, String mobileNo, String password, String email, String address) {
        sQLiteDatabase = this.getWritableDatabase();
        values = new ContentValues();
        values.put(USER_NAME, userName);
        values.put(PHONE_NO, mobileNo);
        values.put(EMAIL, email);
        values.put(PASSWORD, password);
        values.put(ADDRESS, address);
        long result = sQLiteDatabase.insert(USER_TABLE, null, values);

        if (result > 0) {
            return  true;
        } else {
            return false;
        }
    }

    public String userValidation(String userName, String password) {
        sQLiteDatabase = this.getWritableDatabase();
        values = new ContentValues();
        String email = "";
        String userLogin = "SELECT * FROM " + USER_TABLE + " WHERE " + USER_NAME + " = ? and " +
                PASSWORD + " = ? ";
        Cursor c = sQLiteDatabase.rawQuery(userLogin, new String[] {userName, password});
        if (c.moveToFirst()) {
           email  = c.getString(c.getColumnIndex("EMAIL"));
        }
        return email;
    }
}
