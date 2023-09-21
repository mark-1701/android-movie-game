package com.example.moviegamesapp;

import android.content.Intent;

import com.example.moviegamesapp.activitys.GameActivity;
import com.example.moviegamesapp.activitys.ScoreActivity;
import com.example.moviegamesapp.model.Game;
import com.example.moviegamesapp.model.Riddle;

import java.util.LinkedList;

public class GameControllerSingleton {
    private static GameControllerSingleton instance;
    private static Game game;
    private static Riddle riddle;
    private static int level = 0;
    private static int limitLevel = 0;
    private static int score = 0;

    private GameControllerSingleton() {
        //INICIALIZACION DE VARIABLES
    }

    public static GameControllerSingleton getInstance() {
        if (instance == null) {
            instance = new GameControllerSingleton();
        }
        return instance;
    }

    //CAMBIA EL ACERTIJO DEL JUEGO
    public static void changeRiddle() {
        riddle = game.getListRiddles().get(level);
    }

    //CAMBIA EL NIVEL DEL JUEGO Y SABER SI PUEDE ACCEDER AL SIGUIENTE NIVEL
    public static void changeLevel() {
        level +=1;
    }

    //SABER SI SE PUEDE ACCEDER AL SIGUIENTE NIVEL
    public static boolean checkLimit() {
        return ((level + 1) == limitLevel);
    }

    //CALIFICAR EL ACERTIJO
    public static void validateRiddle(String clue) {
         if (riddle.getCorrectAnswer().equalsIgnoreCase(clue)) score+=25;
    }

    //REINICIAR VARIABLES DEL JUEGO
    public static void restartGame() {
        game = null;
        riddle = null;
        level = 0;
        limitLevel = 0;
        score = 0;
    }

    //SETTERS AND GETTERS
    public static Game getGame() {
        return game;
    }

    public static void setGame(Game game) {
        GameControllerSingleton.game = game;
        //ESTABLECER NIVEL LIMITE DE ACERTIJOS
        limitLevel = game.getListRiddles().size();
    }

    public static Riddle getRiddle() {
        return riddle;
    }

    public static void setRiddle(Riddle riddle) {
        GameControllerSingleton.riddle = riddle;
    }

    public static int getLevel() {
        return level;
    }

    public static void setLevel(int level) {
        GameControllerSingleton.level = level;
    }

    public static int getLimitLevel() {
        return limitLevel;
    }

    public static void setLimitLevel(int limitLevel) {
        GameControllerSingleton.limitLevel = limitLevel;
    }

    public static int getScore() {
        return score;
    }

    public static void setScore(int score) {
        GameControllerSingleton.score = score;
    }
}
