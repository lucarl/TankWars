package com.mygdx.game.model;


import java.awt.event.KeyEvent;

public class Tank {


    private Position positionTank;
    private int healthPoints;
    private int fuel;
    private int angle;
    private String imgSource = "tank14.png";

    private boolean rightMove;
    private boolean leftMove;

    private boolean rightAim;
    private boolean leftAim;

    //konstant för vår hastighet
    private final int speed = 10;



    public Tank(Position position, int healthPoints, int fuel, int angle) {
        this.positionTank = position;
        this.healthPoints = healthPoints;
        this.fuel = fuel;
        this.angle = angle;
    }

   public Shot fireTank(int power) {// döper om till fire för att inte blanda ihop med Shot klassen
       return new Shot (positionTank, angle, power);
    }

    public float aim(float delta){
            if (rightAim) {
                this.angle = angle < 180 ? angle + speed * delta : 180;
            }

            if (leftAim) {
                this.angle = angle > 0 ? angle - speed * delta : 0;
            }
        return angle;

    }

    public Position moveTank(float delta) {

        if (rightMove){
            positionTank.setX(positionTank.getX() + speed * delta);
            decreaseFuel();
        }

        if (leftMove){
            positionTank.setX(speed * delta);
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
