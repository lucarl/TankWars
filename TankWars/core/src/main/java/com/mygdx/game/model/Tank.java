package com.mygdx.game.model;


public class Tank {
    //private TankGun tankGun;
    private Weapon weapon;

    Position positionTank;
    int directionShot;

    int fuel;
    int angle;
    int healthPoints;


    public Tank(Position position, int healthPoints, int fuel, int angle) {
        this.positionTank = position;
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
