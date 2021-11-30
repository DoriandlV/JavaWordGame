package com.codesse.codetest.wordgame;

public class LeaderBoardItem {
    private String playerName;
    private String word;
    private int score;

    public LeaderBoardItem(String playerName, String word, int score) {
        this.playerName = playerName;
        this.word = word;
        this.score = score;
    }

    public String getPlayerName() {
        return playerName;
    }

    public LeaderBoardItem setPlayerName(String playerName) {
        this.playerName = playerName;
        return this;
    }

    public String getWord() {
        return word;
    }

    public LeaderBoardItem setWord(String word) {
        this.word = word;
        return this;
    }

    public int getScore() {
        return score;
    }

    public LeaderBoardItem setScore(int score) {
        this.score = score;
        return this;
    }


}