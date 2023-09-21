package com.example.moviegamesapp;

import com.example.moviegamesapp.model.Game;
import com.example.moviegamesapp.model.Riddle;

import java.util.LinkedList;

public class AddGameSingleton {
    private static AddGameSingleton instancia;
    public static Game game;
    public static int numberOfRiddles;
    public static LinkedList<Riddle> listRiddle;


    private AddGameSingleton() {
        game = new Game();
        numberOfRiddles = 0;
        listRiddle = new LinkedList<>();
    }

    public static AddGameSingleton getInstancia() {
        if (instancia == null) {
            instancia = new AddGameSingleton();
        }
        return instancia;
    }
    public static void restartAddGame() {
        game = new Game();
        numberOfRiddles = 0;
        listRiddle = new LinkedList<>();
    }


}
