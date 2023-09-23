package com.example.moviegamesapp.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import java.io.File;

public class DatabaseAcess {
    private Context context;
    private DatabaseUsersHelper databaseUsersHelper;
    private SQLiteDatabase database;
    public DatabaseAcess(Context context) {
        this.context = context;
        //INSTANCIA SQLITE
        databaseUsersHelper = new DatabaseUsersHelper(context);
        database = databaseUsersHelper.getWritableDatabase();
    }

    public boolean searchInformation(String username, String password) {
        String[] projection = {DatabaseUsersHelper.COLUMN_USERNAME, DatabaseUsersHelper.COLUMN_PASSWORD};
        String selection = DatabaseUsersHelper.COLUMN_USERNAME + "= ?";
        String[] selectionArgs = {username};
        Cursor cursor = database.query(DatabaseUsersHelper.TABLE_NAME, projection, selection, selectionArgs, null, null, null);
        if (cursor.moveToFirst()) {

            String passwordFound = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseUsersHelper.COLUMN_PASSWORD));

            if (password.equals(passwordFound)) {
                cursor.close();
                return true;
            } else {
                Toast.makeText(context, "Contraseña incorrecta", Toast.LENGTH_SHORT).show();
                cursor.close();
                return false;
            }

        } else {
            Toast.makeText(context, "Username no encontrado", Toast.LENGTH_SHORT).show();
            cursor.close();
            return false;
        }
    }

    //ELIMINAR LA BASE DE DATOS
    public void deleteDatabase() {
        File databaseFile = context.getApplicationContext().getDatabasePath(DatabaseUsersHelper.DATABASE_NAME);
        if (databaseFile.exists()) {
            try {
                database.deleteDatabase(databaseFile);
                // La base de datos se ha eliminado exitosamente
            } catch (Exception e) {
                // Ocurrió un error al eliminar la base de datos
            }
        } else {
            // La base de datos no existe, no es necesario eliminarla
        }
    }

    public void closeDatabase() {
        database.close();
    }
}
