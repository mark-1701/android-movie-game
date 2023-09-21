package com.example.moviegamesapp.model;

import java.util.LinkedList;

public class Game {
    private int idGame;
    private String name;
    private LinkedList<Riddle> listRiddles;

    public Game() {
    }

    public Game(int idGame, String name, LinkedList<Riddle> listRiddles) {
        this.idGame = idGame;
        this.name = name;
        this.listRiddles = listRiddles;
    }

    public int getIdGame() {
        return idGame;
    }

    public void setIdGame(int idGame) {
        this.idGame = idGame;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LinkedList<Riddle> getListRiddles() {
        return listRiddles;
    }

    public void setListRiddles(LinkedList<Riddle> listRiddles) {
        this.listRiddles = listRiddles;
    }

    @Override
    public String toString() {
        return "Game{" +
                "idGame=" + idGame +
                ", name='" + name + '\'' +
                ", listRiddles=" + listRiddles +
                '}';
    }
}
