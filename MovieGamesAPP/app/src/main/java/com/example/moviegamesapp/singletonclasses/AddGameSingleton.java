package com.example.moviegamesapp.singletonclasses;

public class AddGameSingleton {
    private static AddGameSingleton instancia;
    public static String gameName;
    public static int numberOfRiddles;

    private AddGameSingleton() {
        gameName = "";
        numberOfRiddles = 0;
    }

    public static AddGameSingleton getInstancia() {
        if (instancia == null) {
            instancia = new AddGameSingleton();
        }
        return instancia;
    }
    public static void restartAddGame() {
        gameName = "";
        numberOfRiddles = 0;
    }
}
