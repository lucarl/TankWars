package com.mygdx.game.model;


import java.awt.event.KeyEvent;

public class Tank {


    private Position positionTank;
    private int healthPoints;
    private double fuel;
    private float angle;

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

    public void setLeftAim(boolean b){
        if(rightAim && b){rightAim = false;}
        leftAim = b;
    }

    public void setRightAim(boolean b){
        if(leftAim && b){leftAim = false;}
        rightAim = b;
    }

    public double decreaseFuel(){
        if(leftMove || rightMove){
            this.fuel -= 0.003;
        }
        return this.fuel;
    }

    public Position getPositionTank() {
        return positionTank;
    }


    public int getHealthPoints() {
        return healthPoints;
    }

    public double getFuel() {
        return fuel;
    }

    public float getAngle() {
        return angle;
    }
}
