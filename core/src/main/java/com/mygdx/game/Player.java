package com.mygdx.game;

/**
 * Created by Carl on 2018-04-19.
 */
public class Player {
    private Tank tank;


    public Player() {
        tank = new Tank();
    }

    public Tank getTank() {
        return tank;
    }
}
