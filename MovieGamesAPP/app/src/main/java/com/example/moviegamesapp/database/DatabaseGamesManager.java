package com.example.moviegamesapp.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import com.example.moviegamesapp.model.Clue;
import com.example.moviegamesapp.model.Game;
import com.example.moviegamesapp.model.Riddle;

import java.io.File;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.Random;

import javax.crypto.SecretKey;

public class DatabaseGamesManager {
    private Context context;
    private DatabaseGamesHelper databaseGamesHelper;
    private SQLiteDatabase database;

    public DatabaseGamesManager(Context context) {
        this.context = context;
        //INSTANCIA SQLITE
        databaseGamesHelper = new DatabaseGamesHelper(context);
        database = databaseGamesHelper.getWritableDatabase();
    }

    //BUSCAR SI EL NOMBRE DEL JUEGO EXISTE
    public boolean searchGameName(String gameName) {
        String[] projection = {"game_name"};
        String selection = "game_name= ?";
        String[] selectionArgs = {gameName};
        Cursor cursor = database.query("games", projection, selection, selectionArgs, null, null, null);
        if (cursor.moveToFirst()) {
            Toast.makeText(context, "Nombre no disponible", Toast.LENGTH_SHORT).show();
            cursor.close();
            return false;
        } else {
            cursor.close();
            return true;
        }
    }

    //LISTAR LAS PISTAS
    public LinkedList<Clue> listClues(String id_riddle) {
        LinkedList<Clue> listClues = new LinkedList<>();
        String[] projection = {"clue_id", "riddle_id", "clue"};
        String selection = "riddle_id= ?";
        String[] selectionArgs = {id_riddle};
        Cursor cursor = database.query("clues", projection, selection, selectionArgs, null, null,null);
        if (cursor.moveToFirst()) {
            do {
                int id_clue = cursor.getInt(cursor.getColumnIndexOrThrow("clue_id"));
                String clue = cursor.getString(cursor.getColumnIndexOrThrow("clue"));
                Clue newClue = new Clue(id_clue, clue);
                listClues.add(newClue);
                //Toast.makeText(context, "+" + newClue.toString()  +  "+", Toast.LENGTH_SHORT).show();
            } while (cursor.moveToNext());
        }
        cursor.close();
        return listClues;
    }

    //LISTAR LOS ACERTIJOS
    public LinkedList<Riddle> listRiddles(String game_name) {
        LinkedList<Riddle> listRiddles = new LinkedList<>();
        String[] projection = {"riddle_id", "game_name", "emojis", "correct_answer"};
        String selection = "game_name= ?";
        String[] selectionArgs = {game_name};
        Cursor cursor = database.query("riddles", projection, selection, selectionArgs, null, null,null);
        if (cursor.moveToFirst()) {
            do {
                String id_riddle = cursor.getString(cursor.getColumnIndexOrThrow("riddle_id"));
                String emojis = cursor.getString(cursor.getColumnIndexOrThrow("emojis"));
                String correct_answer = cursor.getString(cursor.getColumnIndexOrThrow("correct_answer"));
                LinkedList<Clue> listClues = listClues(id_riddle);
                Riddle newRiddle = new Riddle(id_riddle, emojis, correct_answer, listClues);
                listRiddles.add(newRiddle);
                //Toast.makeText(context, "+" + newRiddle.toString()  +  "+", Toast.LENGTH_SHORT).show();
            } while (cursor.moveToNext());
        }
        cursor.close();
        return listRiddles;
    }

    //LISTAR LOS JUEGOS
    public LinkedList<Game> listGames() {
        LinkedList<Game> listGames = new LinkedList<>();
        String[] projection = {"game_name, state"};
        String selection = "state = ?";
        String[] selectionArgs = {"1"};
        Cursor cursor = database.query("games", projection, selection, selectionArgs, null, null,null);
        if (cursor.moveToFirst()) {
            do {
                String game_name = cursor.getString(cursor.getColumnIndexOrThrow("game_name"));
                int stateInt = cursor.getInt(cursor.getColumnIndexOrThrow("state"));
                boolean state = (stateInt == 1);
                LinkedList<Riddle> listRiddles = listRiddles(game_name);
                Game newGame = new Game(game_name, state, listRiddles);
                listGames.add(newGame);
                //Toast.makeText(context, "+" + newGame.toString()  +  "+", Toast.LENGTH_SHORT).show();
            } while (cursor.moveToNext());
        }
        cursor.close();
        return listGames;
    }

    //INSERTS A LAS TABLAS DE LA BASE DE DATOS
    public void insertGame(String game_name, boolean state){
        ContentValues values = new ContentValues();
        values.put("game_name", game_name);
        values.put("state", state);
        database.insert("games", null, values);
    }

    public void insertRiddle(String id_riddle, String game_name, String emojis, String correct_answer){
        ContentValues values = new ContentValues();
        values.put("riddle_id", id_riddle);
        values.put("game_name", game_name);
        values.put("emojis", emojis);
        values.put("correct_answer", correct_answer);
        database.insert("riddles", null, values);
    }

    public void insertClues(String id_riddle, String clue){
        ContentValues values = new ContentValues();
        values.put("riddle_id", id_riddle);
        values.put("clue", clue);
        database.insert("clues", null, values);
    }

    //DESABILITAR UN JUEGO EN ESPECICO
    public void disableGame(String gameName, boolean newState) {
        ContentValues values = new ContentValues();
        values.put("state", newState);
        String selection = "game_name=?";
        String[] selectionArgs={gameName};
        database.update("games", values, selection, selectionArgs);

    }

    //SELECT BASICO DE LAS TABLAS
    public void readGames() {
        String name = "null";
        String[] projection = {"game_name, state"};
        Cursor cursor = database.query("games", projection, null, null, null, null,null);
        if (cursor.moveToFirst()) {
            do {
                name = cursor.getString(cursor.getColumnIndexOrThrow("game_name"));
                Toast.makeText(context, "+" + name  +  "+", Toast.LENGTH_LONG).show();
            } while (cursor.moveToNext());
        }
        cursor.close();
    }
    public void redRiddles() {
        String emoji = "null";
        String[] projection = {"game_name", "emojis", "correct_answer"};
        Cursor cursor = database.query("riddles", projection, null, null, null, null,null);
        if (cursor.moveToFirst()) {
            do {
                emoji = cursor.getString(cursor.getColumnIndexOrThrow("emojis"));
                Toast.makeText(context, "+" + emoji  +  "+", Toast.LENGTH_LONG).show();
            } while (cursor.moveToNext());
        }
        cursor.close();
    }
    public void readClues() {
        String emoji = "null";
        String[] projection = {"riddle_id", "clue"};
        Cursor cursor = database.query("clues", projection, null, null, null, null,null);
        if (cursor.moveToFirst()) {
            do {
                emoji = cursor.getString(cursor.getColumnIndexOrThrow("clue"));
                Toast.makeText(context, "+" + emoji  +  "+", Toast.LENGTH_LONG).show();
                emoji = cursor.getString(cursor.getColumnIndexOrThrow("id_riddle"));
                Toast.makeText(context, "+" + emoji  +  "+", Toast.LENGTH_LONG).show();
            } while (cursor.moveToNext());
        }
        cursor.close();
    }

    //GENERAR LA LLAVE PARA EL ACERTIJO CON NUMEROS RANDOM
    public String generateRandomNumbers() {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuilder randomText = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            int indice = random.nextInt(characters.length());
            char randomCharacter = characters.charAt(indice);
            randomText.append(randomCharacter);
        }
        return randomText.toString();
    }

    //PARA GENERAR LLAVES CON FECHAS
    public String generateNewRiddleId() {
        String newRiddleId = "";
        LocalDateTime dateAndTime  = LocalDateTime.now();
        newRiddleId += dateAndTime + "-" + generateRandomNumbers();
        return newRiddleId;
    }

    //CERRAR AL BASE DE DATOS
    public void closeDatabase() {
        database.close();
    }

    //ELIMINAR LA BASE DE DATOS
    public void deleteDatabase() {
        File databaseFile = context.getApplicationContext().getDatabasePath("movie_games.db");
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
