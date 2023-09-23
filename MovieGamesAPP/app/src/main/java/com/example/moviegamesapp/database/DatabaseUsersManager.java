package com.example.moviegamesapp.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

public class DatabaseUsersManager {
    private Context context;
    private DatabaseUsersHelper databaseUsersHelper;
    private SQLiteDatabase database;

    public DatabaseUsersManager(Context context) {
        this.context = context;
        //INSTANCIA SQLITE
        databaseUsersHelper = new DatabaseUsersHelper(context);
        database = databaseUsersHelper.getWritableDatabase();
    }

    public void readUsers() {
        String name = "null";
        String[] projection = {DatabaseUsersHelper.COLUMN_USERNAME};
        Cursor cursor = database.query(DatabaseUsersHelper.TABLE_NAME, projection, null, null, null, null,null);
        if (cursor.moveToFirst()) {
            do {
                name = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseUsersHelper.COLUMN_USERNAME));
                Toast.makeText(context, name, Toast.LENGTH_LONG).show();
            } while (cursor.moveToNext());
        }
        cursor.close();
    }


    public void insertUser(String name, String username, String password){
        ContentValues values = new ContentValues();
        values.put(DatabaseUsersHelper.COLUMN_NAME, name);
        values.put(DatabaseUsersHelper.COLUMN_USERNAME, username);
        values.put(DatabaseUsersHelper.COLUMN_PASSWORD, password);
        database.insert(DatabaseUsersHelper.TABLE_NAME, null, values);
    }

    public void closeDatabase() {
        database.close();
    }

}
