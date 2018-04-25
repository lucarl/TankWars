package com.mygdx.game.model;


import java.awt.event.KeyEvent;

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

        return new Shot(positionTank.x, positionTank.y, angle, power, weapon.getDamage(),
                    weapon.getRadius(), weapon.getWeight());
    }

    public Position moveTankRight(){

        this.positionTank.x += 1;

        return positionTank;

    }

    public Position moveTankLeft(){

        this.positionTank.x -= 1;

        return positionTank;

    }


}
