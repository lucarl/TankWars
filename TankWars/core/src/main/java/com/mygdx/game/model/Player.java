package com.mygdx.game.model;

/**
 * A class representing each player
 * for a certain tank
 *
 * @author  Carl Lundborg, Adam Kjäll
 */
public class Player {

    private Tank tank;
    private int score;
    private String name;

    /**
     * Construktor for Player
     * @param tank
     * @param name
     */
    public Player(Tank tank, String name) {
        this.tank = tank;
        this.name = name;
        score = 0;
    }

    public void setTank(Tank tank) {
        this.tank = tank;
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
