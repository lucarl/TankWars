package com.mygdx.game.model;


public class Tank {
    private TankGun tankGun;
    private Weapon weapon;

    int x,y;
    int direction;

    public Tank(int x, int y) {
        this.x = x;
        this.y = y;
        this.tankGun = new TankGun();
        this.weapon = new Weapon();

    }

    public Shot shootTank(int power, int angle) {

        return new Shot();
    }

}
