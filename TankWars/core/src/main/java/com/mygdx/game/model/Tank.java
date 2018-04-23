package com.mygdx.game.model;

public class Tank {
    private TankGun tankGun;
    private Weapon weapon;

    public Tank() {

    }

    public void shoot() {

    }


    public TankGun getTankGun() {
        tankGun = new TankGun();
        return tankGun;
    }
}
