package com.mygdx.game;

/**
 * Created by Carl on 2018-04-19.
 */
public class Tank {
    TankGun tankGun;
    Weapon weapon;

    public Tank() {
        tankGun = new TankGun();
        weapon = new Weapon();
    }
}
