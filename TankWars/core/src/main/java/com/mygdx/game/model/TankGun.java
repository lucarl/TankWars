package com.mygdx.game.model;

public class TankGun implements Object {
    private Position pos;
    private String imgSrc = "toptube.png";
    private float angle;
    private int width, height;
    private boolean rightAim;
    private boolean leftAim;
    private final int speed = 100;

    public TankGun(Position pos, int xOffset, int yOffset) {
        this.pos = new Position(pos.getX() + xOffset, pos.getY() + yOffset);
        width = 30;
        height = 80;
        angle = 20;
    }

    public void update(float delta) {
        aimTank(delta);
    }

    private float aimTank(float delta) {
        if (rightAim) {
            this.angle = angle < 100 ? angle + speed * delta : 100;
        }

        if (leftAim) {
            this.angle = angle > -100 ? angle - speed * delta : -100;
        }
        return angle;
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


    @Override
    public Position getPos() {
        return pos;
    }

    @Override
    public String getImgSrc() {
        return imgSrc;
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

    public boolean canRotate() {
        return true;
    }
}
