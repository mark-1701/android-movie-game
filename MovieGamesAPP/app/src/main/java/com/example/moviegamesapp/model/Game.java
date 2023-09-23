package com.example.moviegamesapp.model;

import java.util.LinkedList;

public class Game {
    private String gameName;
    private boolean state;
    private LinkedList<Riddle> listRiddles;

    public Game() {
    }

    public Game(String gameName, boolean state, LinkedList<Riddle> listRiddles) {
        this.gameName = gameName;
        this.state = state;
        this.listRiddles = listRiddles;
    }

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String game_name) {
        this.gameName = game_name;
    }

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
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
                "gameName='" + gameName + '\'' +
                ", state=" + state +
                ", listRiddles=" + listRiddles +
                '}';
    }
}
