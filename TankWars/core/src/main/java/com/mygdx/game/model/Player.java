package com.mygdx.game.model;

/**
 * Created by Carl on 2018-04-19.
 */
public class Player {
    private Tank tank;

    public Player(Tank tank) {
        this.tank = tank;
    }

    public Tank getTank() {
        return tank;
    }
}