package com.example.moviegamesapp.model;

public class Clue {
    private int idClue;
    private String clue;

    public Clue() {
    }

    public Clue(int idClue, String clue) {
        this.idClue = idClue;
        this.clue = clue;
    }

    public int getIdClue() {
        return idClue;
    }

    public void setIdClue(int idClue) {
        this.idClue = idClue;
    }

    public String getClue() {
        return clue;
    }

    public void setClue(String clue) {
        this.clue = clue;
    }

    @Override
    public String toString() {
        return "Clue{" +
                "idClue=" + idClue +
                ", clue='" + clue + '\'' +
                '}';
    }
}
