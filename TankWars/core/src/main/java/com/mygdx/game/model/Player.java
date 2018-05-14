package com.mygdx.game.model;

/**
 * A class representing each player
 * for a certain tank
 *
 * @author  Carl Lundborg, Adam Kjäll
 */
public class Player {
    private static int nPlayers = 1;
    private Tank tank;
    private int score;
    private String name;


    public Player(Tank tank) {
        this.tank = tank;
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

    public void addScore(){
        score++;
    }
}
