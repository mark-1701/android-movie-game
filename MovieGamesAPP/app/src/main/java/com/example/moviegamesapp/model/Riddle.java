package com.example.moviegamesapp.model;

import java.util.LinkedList;

public class Riddle {
    private String riddleId;
    private String emojis;
    private String correctAnswer;
    private LinkedList<Clue> listClues;

    public Riddle() {
    }

    public Riddle(String riddleId, String emojis, String correctAnswer, LinkedList<Clue> listClues) {
        this.riddleId = riddleId;
        this.emojis = emojis;
        this.correctAnswer = correctAnswer;
        this.listClues = listClues;
    }

    public String getRiddleId() {
        return riddleId;
    }

    public void setRiddleId(String riddleId) {
        this.riddleId = riddleId;
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
                "riddleId=" + riddleId +
                ", emojis='" + emojis + '\'' +
                ", correctAnswer='" + correctAnswer + '\'' +
                ", listClues=" + listClues +
                '}';
    }
}
