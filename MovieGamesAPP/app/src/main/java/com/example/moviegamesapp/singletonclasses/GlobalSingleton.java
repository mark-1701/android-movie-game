package com.example.moviegamesapp.singletonclasses;

import android.content.Context;

import com.example.moviegamesapp.database.DatabaseGamesManager;
import com.example.moviegamesapp.model.Game;

import java.util.LinkedList;

public class GlobalSingleton {
    private static GlobalSingleton instance;
    public static LinkedList<Game> listGames;
    private DatabaseGamesManager databaseGamesManager;

    private GlobalSingleton(Context context) {
        listGames = new LinkedList<>();
        databaseGamesManager = new DatabaseGamesManager(context);

        /*
        String newRiddleId = databaseGamesManager.generateRandomNumbers();
        databaseGamesManager.insertGame("quantin extremo3", true);
        databaseGamesManager.insertRiddle(newRiddleId, "quantin extremo3", "🤖🌌👶🚀", "2001 Odisea en el Espacio");
        databaseGamesManager.insertClues(newRiddleId, "Jurassic Park");
        databaseGamesManager.insertClues(newRiddleId, "El Padrino");
        databaseGamesManager.insertClues(newRiddleId, "2001 Odisea en el Espacio");
        databaseGamesManager.insertClues(newRiddleId, "Matrix");

         */


        //LinkedList<Clue> listClues = databaseGamesManager.listClues(newRiddleId);
        //LinkedList<Riddle> listRiddles = databaseGamesManager.listRiddles("quantin tarantino");
        //LinkedList<Game> listGame = databaseGamesManager.listGames();


        //BUSCAR SI EL NOMBRE DEL JUEGO ESTA DISPONIBLE
        //databaseGamesManager.searchInformation("quantin extremo");
        //databaseGamesManager.searchInformation("quantin extremo2");

        //AGREGAR LOS REGISTROS AL AMBITO GLOBAL
        //GlobalSingleton.listGames.add(databaseGamesManager.listGames().get(0));

        //databaseGamesManager.closeDatabase();

        //ELIMINAR LA BASE DE DATOS
        //databaseGamesManager.deleteDatabase();
    }

    public static GlobalSingleton getInstance(Context context) {
        if (instance == null) {
            instance = new GlobalSingleton(context);
        }
        return instance;
    }

    public static LinkedList<Game> getListGames() {
        return listGames;
    }

    public static void setListGames(LinkedList<Game> listGames) {
        GlobalSingleton.listGames = listGames;
    }
}





