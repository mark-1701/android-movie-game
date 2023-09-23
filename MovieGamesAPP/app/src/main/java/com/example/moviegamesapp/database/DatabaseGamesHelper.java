package com.example.moviegamesapp.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseGamesHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "movie_games.db";

    // Definición de la tabla "games"
    private static final String CREATE_TABLE_GAMES = "CREATE TABLE games (" +
            "game_name TEXT PRIMARY KEY," +
            "state BOOLEAN NOT NULL" +
            ");";

    // Definición de la tabla "riddles" con clave foránea a "games"
    private static final String CREATE_TABLE_RIDDLES = "CREATE TABLE riddles (" +
            "riddle_id TEXT PRIMARY KEY," +
            "game_name TEXT NOT NULL," +
            "emojis TEXT NOT NULL," +
            "correct_answer TEXT NOT NULL," +
            "FOREIGN KEY (game_name) REFERENCES games (game_name)" +
            ");";

    // Definición de la tabla "clues" con clave foránea a "riddles"
    private static final String CREATE_TABLE_CLUES = "CREATE TABLE clues (" +
            "clue_id INTEGER PRIMARY KEY AUTOINCREMENT," +
            "riddle_id TEXT NOT NULL," +
            "clue TEXT NOT NULL," +
            "FOREIGN KEY (riddle_id) REFERENCES riddles (riddle_id)" +
            ");";

    public DatabaseGamesHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_GAMES);
        db.execSQL(CREATE_TABLE_RIDDLES);
        db.execSQL(CREATE_TABLE_CLUES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS games");
        db.execSQL("DROP TABLE IF EXISTS riddles");
        db.execSQL("DROP TABLE IF EXISTS clues");
        onCreate(db);
    }
}

