package com.mygdx.game.model.factorys;

import com.mygdx.game.model.NukeShot;
import com.mygdx.game.model.Position;
import com.mygdx.game.model.Shot;
import com.mygdx.game.model.StandardShot;

/**
 * A class that uses factory pattern
 * to decide which class to use during
 * runtime
 *
 * @author  Carl Lundborg
 */
public class ShotFactory {

    public Shot makeTankGun(int changeWeapon, Position position, float angle, float power, int windSpeed) {
        if (changeWeapon == 1) {
            return new StandardShot(position, angle, power, windSpeed);
        }
        if (changeWeapon == 2) {
            return new NukeShot(position, angle, power, windSpeed);
        }else if (changeWeapon == 2) {
            return new NukeShot (position, angle, power, windSpeed);
        }
        else return new StandardShot (position, angle, power, windSpeed);
    }
}
