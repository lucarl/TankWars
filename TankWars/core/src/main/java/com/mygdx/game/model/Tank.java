package com.mygdx.game.model;

public class Tank implements Object {


    private int healthPoints;
    private double fuel;
    private float angle;
    private int width, height;

    private Position positionTank;
    private Shot shot;
    private TankGun gun;

    private String tankImgSrc = "tank14.png";

    private boolean rightMove;
    private boolean leftMove;

    //konstant för vår hastighet
    private final int speed = 100;

    public Tank(Position position, int healthPoints, int fuel) {
        this.positionTank = position;
        this.healthPoints = healthPoints;
        this.fuel = fuel;
        this.shot = new Shot(position, 90, 0);
        width = 150;
        height = 110;
        gun = new TankGun(position, width / 2, height);
    }

    public void update(float delta) {
        gun.update(delta);
        shot.update(delta);
        moveTank(delta);

    }

    public Shot fireTank(int power) {// döper om till fire för att inte blanda ihop med Shot klassen
        shot = new Shot(positionTank, gun.getAngle(), power);
        return shot;
    }


    public Position moveTank(float delta) {

        if (rightMove) {
            positionTank.setX(positionTank.getX() + speed * delta);
            decreaseFuel();
        }

        if (leftMove) {
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


    public double decreaseFuel() {
        if (leftMove || rightMove) {
            this.fuel -= 0.003;
        }
        return this.fuel;
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

    public boolean canRotate() {
        return false;
    }

    @Override
    public float getAngle() {
        return 0;
    }

    public TankGun getGun() {
        return gun;
    }

    @Override
    public Position getPos() {
        return positionTank;
    }

    @Override
    public String getImgSrc() {
        return tankImgSrc;
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }
}
