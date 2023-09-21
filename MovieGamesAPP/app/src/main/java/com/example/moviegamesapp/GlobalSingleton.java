package com.example.moviegamesapp;

import com.example.moviegamesapp.model.Clue;
import com.example.moviegamesapp.model.Game;
import com.example.moviegamesapp.model.Riddle;

import java.util.LinkedList;

public class GlobalSingleton {
    private static GlobalSingleton instance;
    public static LinkedList<Game> listGames;
    private static LinkedList<Riddle> listRiddles;
    private static LinkedList<Clue> listClues;

    private GlobalSingleton() {
        // Inicialización de la instancia, si es necesario.
        listRiddles = new LinkedList<>();
        listClues = new LinkedList<>();
        listGames = new LinkedList<>();

        //LISTA DE LAS PISTAS DE LAS OPCIONES CORRECTAS
        listClues.add(new Clue(1, "Jurassic Park"));
        listClues.add(new Clue(2, "El Padrino"));
        listClues.add(new Clue(3, "2001 Odisea en el Espacio"));
        listClues.add(new Clue(4, "Matrix"));

        //LISTA DE ACERTIJOS POR RETO
        listRiddles.add(new Riddle(1, "🤖🌌👶🚀","2001 Odisea en el Espacio",  listClues));
        listRiddles.add(new Riddle(2, "🚙💀🐉🚁","Jurassic Park",  listClues));
        listRiddles.add(new Riddle(3, "💻😎💊🟢","Matrix",  listClues));

        //PARTIDA O RETO
        listGames.add(new Game(1, "hardcore", listRiddles));
        listGames.add(new Game(1, "terror", listRiddles));
        listGames.add(new Game(1, "quantin tarantino", listRiddles));
    }

    public static GlobalSingleton getInstance() {
        if (instance == null) {
            instance = new GlobalSingleton();
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





