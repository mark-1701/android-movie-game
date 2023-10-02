package com.example.moviegamesapp.model;

import java.time.LocalDateTime;

public class Result {
    private String username;
    private double score;
    private String game;
    private LocalDateTime date;

    public Result() {
    }

    public Result(String username, double score, String game, LocalDateTime date) {
        this.username = username;
        this.score = score;
        this.game = game;
        this.date = date;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public String getGame() {
        return game;
    }

    public void setGame(String game) {
        this.game = game;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Result{" +
                "username='" + username + '\'' +
                ", score=" + score +
                ", game='" + game + '\'' +
                ", date=" + date +
                '}';
    }
}
