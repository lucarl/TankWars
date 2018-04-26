package com.mygdx.game.model;


import java.awt.event.KeyEvent;

public class Tank {

<<<<<<< HEAD
    private Position positionTank;
    private int healthPoints;
    private int fuel;
    private int angle;

    private boolean rightMove;
    private boolean leftMove;



=======
    Position positionTank;
    int directionShot; // ?
    int healthPoints;

    int fuel;
    int angle;
    //
    boolean rightMove;
    boolean leftMove;

    //konstant för vår hastighet
    private final int speed = 10;



>>>>>>> 625ca5b39780ebea0dbb1dbef26940ed40b4af7e
    public Tank(Position position, int healthPoints, int fuel, int angle) {
        this.positionTank = position;
        this.healthPoints = healthPoints;
        this.fuel = fuel;
        this.angle = angle;
    }

   public Shot fireTank(int power) {// döper om till fire för att inte blanda ihop med Shot klassen
       return new Shot (positionTank, angle, power);
    }

    public int aim(int i, boolean keyPressed, float delta){
        // skicka in 0 för att öka vinkel (vänster), skicka in 1 för att minska vinkeln.
        // keyPressed = true när användaren håller ned en knapp
        while (keyPressed) {
            if (i == 0) {
                this.angle += delta;
            }

            if (i == 1) {
                this.angle -= delta;
            }
        }
        return angle;

    }

    public Position moveTank(float delta) {

        if (rightMove){
            this.positionTank.x += speed * delta;
            decreaseFuel();
        }

        if (leftMove){
            this.positionTank.x -= speed * System.nanoTime();
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

    public Position getPositionTank() {
        return positionTank;
    }


    public int getHealthPoints() {
        return healthPoints;
    }

    public int getFuel() {
        return fuel;
    }

    public int getAngle() {
        return angle;
    }
}
