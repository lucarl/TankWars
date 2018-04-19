package com.mygdx.game;

/**
 * Created by Carl on 2018-04-19.
 */
public class TankWars {
    Player player = new Player();



    public boolean shoot() {
        Tank tank = player.getTank();
        TankGun tankGun = tank.tankGun;
        Weapon weapon = tank.weapon;

        return true;
    }



}
