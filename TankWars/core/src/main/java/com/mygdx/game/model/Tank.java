package com.mygdx.game.model;


import java.awt.event.KeyEvent;

public class Tank {


    private Position positionTank;
    private int healthPoints;
    private double fuel;
    private float angle;
    private Shot shot;
    private String tankImgSrc = "tank14.png";
    private String gunImgSrc = "toptube.png";

    private boolean rightMove;
    private boolean leftMove;

    private boolean rightAim;
    private boolean leftAim;

    //konstant för vår hastighet
    private final int speed = 100;



    public Tank(Position position, int healthPoints, int fuel, int angle) {
        this.positionTank = position;
        this.healthPoints = healthPoints;
        this.fuel = fuel;
        this.angle = angle;
        this.shot = new Shot(position, angle, 0);
    }

   public void fireTank(int power) {// döper om till fire för att inte blanda ihop med Shot klassen
       shot = new Shot (positionTank, angle, power);
    }

    public float aimTank(float delta){
            if (rightAim) {
                this.angle = angle < 100 ? angle + speed * delta : 100;
            }

            if (leftAim) {
                this.angle = angle > -100 ? angle - speed * delta : -100;
            }
        return angle;
    }

    public Position moveTank(float delta) {

        if (rightMove){
            positionTank.setX(positionTank.getX() + speed * delta);
            decreaseFuel();
        }

        if (leftMove){
            positionTank.setX(positionTank.getX() -speed * delta);
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

    public String getGunImgSrc() {
        return gunImgSrc;
    }

    public String getTankImgSrc() {
        return tankImgSrc;
    }

    public Position getPositionTank() {
        return positionTank;
    }


    public int getHealthPoints() {
        return healthPoints;
    }

    public Shot getShot() {
        return shot;
    }

    public double getFuel() {
        return fuel;
    }

    public float getAngle() {
        return angle;
    }
}
