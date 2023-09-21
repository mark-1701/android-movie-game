package com.example.moviegamesapp.model;

import java.util.LinkedList;

public class Riddle {
    private int idRiddle;
    private String emojis;
    private String correctAnswer;
    private LinkedList<Clue> listClues;

    public Riddle() {
    }

    public Riddle(int idRiddle, String emojis, String correctAnswer, LinkedList<Clue> listClues) {
        this.idRiddle = idRiddle;
        this.emojis = emojis;
        this.correctAnswer = correctAnswer;
        this.listClues = listClues;
    }

    public int getIdRiddle() {
        return idRiddle;
    }

    public void setIdRiddle(int idRiddle) {
        this.idRiddle = idRiddle;
    }

    public String getEmojis() {
        return emojis;
    }

    public void setEmojis(String emojis) {
        this.emojis = emojis;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public LinkedList<Clue> getListClues() {
        return listClues;
    }

    public void setListClues(LinkedList<Clue> listClues) {
        this.listClues = listClues;
    }

    @Override
    public String toString() {
        return "Riddle{" +
                "idRiddle=" + idRiddle +
                ", emojis='" + emojis + '\'' +
                ", correctAnswer='" + correctAnswer + '\'' +
                ", listClues=" + listClues +
                '}';
    }
}
