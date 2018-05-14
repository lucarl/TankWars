package com.mygdx.game.model;
/**
 * A class that uses factory pattern
 * to decide which class to use during
 * runtime
 *
 * @author  Carl Lundborg
 */
public class ShotFactory {

    public Shot makeTankGun(boolean shot, Position position, float angle, float power, int windSpeed) {

        if(shot) {
            return new NukeShot(position, angle, power, windSpeed);
        } else return new StandardShot(position, angle, power, windSpeed);
    }
}
