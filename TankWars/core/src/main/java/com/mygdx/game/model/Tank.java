package com.mygdx.game.model;


import java.awt.event.KeyEvent;

public class Tank {
    //private TankGun tankGun;
    // private Weapon weapon;

    Position positionTank;
    int directionShot; // ?
    int healthPoints;
    int fuel;
    int angle;
    int power;
    //
    boolean rightMove;
    boolean leftMove;



    public Tank(Position position, int healthPoints, int fuel, int angle, int power) {
        this.positionTank = position;
        this.healthPoints = healthPoints;
        this.fuel = fuel;
        this.angle = angle;
        this.power = power;
    }

   public Shot fire(int power, int angle) {// döper om till fire för att inte blanda ihop med Shot klassen
       return new Shot (positionTank, angle, power);
    }

    public int aim(int i, boolean keyPressed){
        // skicka in 0 för att öka vinkel (vänster), skicka in 1 för att minska vinkeln.
        // keyPressed = true när användaren håller ned en knapp
        while (keyPressed) {
            if (i == 0) {
                this.angle += System.nanoTime();
            }

            if (i == 1) {
                this.angle -= System.nanoTime();
            }
        }
        return angle;

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
        return this.positionTank;
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
        if(leftMove || rightMove){
            this.fuel--;
        }
        return this.fuel;
    }


}
