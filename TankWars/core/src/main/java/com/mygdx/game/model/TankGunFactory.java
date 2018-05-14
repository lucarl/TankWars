package com.mygdx.game.model;

public class TankGunFactory {

    public Shot makeTankGun(boolean shot, Position position, float angle, float power, int windSpeed) {

        if(shot) {
            return new NukeShot(position, angle, power, windSpeed);
        } else return new StandardShot(position, angle, power, windSpeed);
    }
}
