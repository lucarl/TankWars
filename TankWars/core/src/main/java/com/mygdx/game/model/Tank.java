package com.mygdx.game.model;


public class Tank {
    //private TankGun tankGun;
    private Weapon weapon;

    int x,y;
    int directionShot;

    int fuel;
    int angle;
    int healthPoints;


    public Tank(int x, int y, int healthPoints, int fuel, int angle) {
        this.x = x;
        this.y = y;
        //this.tankGun = new TankGun();
        this.weapon = new Weapon();

        this.healthPoints = healthPoints;
        this.fuel = fuel;
        this.angle = angle;

    }

   public Shot shootTank(int power, int angle) {

        return new Shot();
    }

}
