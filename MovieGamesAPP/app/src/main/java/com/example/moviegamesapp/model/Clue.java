package com.example.moviegamesapp.model;

public class Clue {
    private int clueId;
    private String clue;

    public Clue() {
    }

    public Clue(int clueId, String clue) {
        this.clueId = clueId;
        this.clue = clue;
    }

    public int getClueId() {
        return clueId;
    }

    public void setClueId(int clueId) {
        this.clueId = clueId;
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
                "clueId=" + clueId +
                ", clue='" + clue + '\'' +
                '}';
    }
}
