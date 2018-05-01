package com.mygdx.game.model;

/**
 * Created by Carl on 2018-04-19.
 */
public class Player {
    private static int nPlayers = 1;
    private Tank tank;
    private int score;
    private String name;


    public Player() {
        tank = new Tank();
        score = 0;
        name = "Player" + nPlayers;
        nPlayers++;
    }

    public Tank getTank() {
        return tank;
    }

    public int getScore() {
        return score;
    }

    public String getName() {
        return name;
    }
}
