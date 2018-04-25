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
    boolean rightMove;
    boolean leftMove;



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

    public Position moveTank() {

        if (rightMove){
            this.positionTank.x += 5 * System.nanoTime();
            decreaseFuel();
        }

        if (leftMove){
            this.positionTank.x -= 5 * System.nanoTime();
            decreaseFuel();
        }

        return positionTank;
    }

    public void setLeftMove(boolean b){
        if(rightMove && b){rightMove = false;}
        leftMove = b;
    }

    public void setRightMove(boolean b){
        if(leftMove && b){leftMove = false;}
        rightMove = b;
    }

    public int decreaseFuel(){
        return fuel --;
    }


}
