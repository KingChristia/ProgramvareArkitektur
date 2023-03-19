package com.mygdx.game.Model;

public class Score implements Comparable<Score>{

    private int score;
    private String name;

    public Score(int score, String name){
        this.score = score;
        this.name = name;
    }

    @Override
    public int compareTo(Score o) {
        if (score > o.score) {
            return -1;
        }
        else if (score < o.score) {
            return 1;
        }
        else {
            return name.compareTo(o.name);
        }
    }
}