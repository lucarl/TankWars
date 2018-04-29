package com.mygdx.game.model;


import java.awt.event.KeyEvent;

public class Tank {


    private Position positionTank;
    private int healthPoints;
    private float fuel;
    private float angle;
    private Shot shot;
    private float power;
    private String tankImgSrc = "tank14.png";
    private String gunImgSrc = "toptube.png";


    private boolean rightMove;
    private boolean leftMove;

    private boolean rightAim;
    private boolean leftAim;

    //konstant för vår hastighet
    private final int speed = 80;
    private final int aimSpeed = 50;



    public Tank(Position position, int healthPoints, int fuel, int angle) {
        this.positionTank = position;
        this.healthPoints = healthPoints;
        this.fuel = fuel;
        this.angle = angle;
        power = 0.5f;
        this.shot = new Shot(position, angle, power);
    }

    public void fireTank() {// döper om till fire för att inte blanda ihop med Shot klassen
        shot = new Shot(positionTank, angle, power);
        shot.setVisible(true);
    }

    public float aimTank(float delta) {
        if (rightAim) {
            this.angle = angle < 110 ? angle + aimSpeed * delta : 110;
        }

        if (leftAim) {
            this.angle = angle > -110 ? angle - aimSpeed * delta : -110;
        }
        return angle;
    }

    public Position moveTank(float delta) {

        if (rightMove && fuel > 0) {
            positionTank.setX(positionTank.getX() + speed * delta);
            decreaseFuel();
        }

        if (leftMove && fuel > 0) {
            positionTank.setX(positionTank.getX() - speed * delta);
            decreaseFuel();
        }
        return this.positionTank;
    }

    public void setLeftMove(boolean b) {
        if (rightMove && b) {
            rightMove = false;
        }
        leftMove = b;
    }

    public void setRightMove(boolean b) {
        if (leftMove && b) {
            leftMove = false;
        }
        rightMove = b;
    }

    public void setLeftAim(boolean b) {
        if (rightAim && b) {
            rightAim = false;
        }
        leftAim = b;
    }

    public void setRightAim(boolean b) {
        if (leftAim && b) {
            leftAim = false;
        }
        rightAim = b;
    }

    private double decreaseFuel() {
        if (leftMove || rightMove) {
            this.fuel -= 0.1;
        }
        return this.fuel;
    }

    public float getPower() {
        return power;
    }

    public void increasePower() {
        power = power >= 0 && power <= 1 ? power + 0.05f : this.power;
    }

    public void decreasePower() {
        power = power >= 0 && power <= 1 ? power - 0.05f : this.power;
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

    public float getFuel() {
        return fuel;
    }

    public float getAngle() {
        return angle;
    }
}
