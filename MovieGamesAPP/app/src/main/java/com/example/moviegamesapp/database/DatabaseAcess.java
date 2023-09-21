package com.example.moviegamesapp.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import java.io.File;

import javax.crypto.SecretKey;

public class DatabaseAcess {
    private Context context;
    private DatabaseHelper databaseHelper;
    private SQLiteDatabase database;
    public DatabaseAcess(Context context) {
        this.context = context;
        //INSTANCIA SQLITE
        databaseHelper = new DatabaseHelper(context);
        database = databaseHelper.getWritableDatabase();
    }

    public boolean searchInformation(String username, String password) {
        String[] projection = {DatabaseHelper.COLUMN_USERNAME, DatabaseHelper.COLUMN_PASSWORD};
        String selection = DatabaseHelper.COLUMN_USERNAME + "= ?";
        String[] selectionArgs = {username};
        Cursor cursor = database.query(DatabaseHelper.TABLE_NAME, projection, selection, selectionArgs, null, null, null);
        if (cursor.moveToFirst()) {

            String passwordFound = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_PASSWORD));

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
        File databaseFile = context.getApplicationContext().getDatabasePath(DatabaseHelper.DATABASE_NAME);
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
}
