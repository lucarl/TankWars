package com.mygdx.game.model;

/**
 * A class for representing a player
 *
 * @author  Carl Lundborg, Adam Kjäll
 */
public class Player {

    private Tank tank;
    private int score;
    private String name;

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
