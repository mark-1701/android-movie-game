package com.example.moviegamesapp.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

public class DatabaseUserManager {
    private Context context;
    private DatabaseHelper databaseHelper;
    private SQLiteDatabase database;

    public DatabaseUserManager(Context context) {
        this.context = context;
        //INSTANCIA SQLITE
        databaseHelper = new DatabaseHelper(context);
        database = databaseHelper.getWritableDatabase();
    }

    public void readUsers() {
        String name = "null";
        String[] projection = {DatabaseHelper.COLUMN_USERNAME};
        Cursor cursor = database.query(DatabaseHelper.TABLE_NAME, projection, null, null, null, null,null);
        if (cursor.moveToFirst()) {
            do {
                name = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_USERNAME));
                Toast.makeText(context, name, Toast.LENGTH_LONG).show();
            } while (cursor.moveToNext());
        }
        cursor.close();
    }


    public void insertUser(String name, String username, String password){
        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.COLUMN_NAME, name);
        values.put(DatabaseHelper.COLUMN_USERNAME, username);
        values.put(DatabaseHelper.COLUMN_PASSWORD, password);
        database.insert(DatabaseHelper.TABLE_NAME, null, values);
    }

}
