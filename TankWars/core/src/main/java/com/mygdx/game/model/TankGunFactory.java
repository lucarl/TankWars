package com.mygdx.game.model;

public class TankGunFactory {
    private Position pos;


    public Shot makeTankGun(boolean specialShot, Position position, float angle, float power, int windSpeed) {
        Shot newShot = null;

        if(specialShot) {
            return new NukeShot(position, angle, power, windSpeed);
        } else return null;
    }
}
