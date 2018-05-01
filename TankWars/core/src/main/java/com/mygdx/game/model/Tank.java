package com.mygdx.game.model;


public class Tank implements IDrawable {
    private static String tankImgSrc = "tank14.png";
    private static int width = 120;
    private static int height = 85;
    private static int originX = 0;
    private static int originY = 0;
    private static final int speed = 80;
    private static int positionOffset = 100;

    private Position pos;
    private float angle;
    private int healthPoints;
    private float fuel;

    private TankGun gun;

    private boolean isVisible;
    private boolean rightMove;
    private boolean leftMove;

    public Tank() {
        this.pos = new Position(positionOffset, 100);
        this.angle = 0;
        this.healthPoints = 100;
        this.fuel = 100;

        // Place the gun on the tank
        this.gun = new TankGun(new Position(pos.getX() + width / 2, pos.getY() + height));

        isVisible = true;
        rightMove = false;
        leftMove = false;
        positionOffset += 200;
    }

    public Position moveTank(float delta) {

        if (rightMove && fuel > 0) {
            pos.setX(pos.getX() + speed * delta);
            decreaseFuel();
        }

        if (leftMove && fuel > 0) {
            pos.setX(pos.getX() - speed * delta);
            decreaseFuel();
        }
        gun.setPos(pos, width, height);
        return this.pos;
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


    private double decreaseFuel() {
        if (leftMove || rightMove) {
            this.fuel -= 0.1;
        }
        return this.fuel;
    }

    public TankGun getGun(){
        return gun;
    }
    @Override
    public String getImgSrc() {
        return tankImgSrc;
    }

    @Override
    public Position getPos() {
        return pos;
    }

    public int getHealthPoints() {
        return healthPoints;
    }


    public float getFuel() {
        return fuel;
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }

    public float getAngle() {
        return angle;
    }

    @Override
    public int getOriginX() {
        return 0;
    }

    @Override
    public int getOriginY() {
        return 0;
    }


    public void setVisibility(boolean bool) {
        isVisible = bool;
    }

    public boolean isVisible() {
        return isVisible;
    }

}
