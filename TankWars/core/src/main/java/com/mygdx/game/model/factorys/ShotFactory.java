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
<<<<<<< HEAD:TankWars/core/src/main/java/com/mygdx/game/model/factorys/ShotFactory.java
        if (changeWeapon == 2) {
            return new NukeShot(position, angle, power, windSpeed);
=======
>>>>>>> 1d1b801db7f92a7373f4699eee1100f3ee7b66f5:TankWars/core/src/main/java/com/mygdx/game/model/ShotFactory.java
        }
        else return new StandardShot (position, angle, power, windSpeed);
    }
}
