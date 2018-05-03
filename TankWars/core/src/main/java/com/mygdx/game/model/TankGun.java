package com.mygdx.game.model;

public class TankGun implements IDrawable {
    private static String imgSrc = "toptube.png";
    private static int width = 10;
    private static int height = 40;
    private static int originX = width / 2;
    private static int originY = 0;
    private static final int speed = 50;

    private Position pos;
    private float angle;
    private float power;
    private boolean specialShot;
    private boolean isVisible;
    private boolean rightAim;
    private boolean leftAim;

    private Shot shot;

    public TankGun(Position pos) {
        this.pos = pos;
        angle = 0;
        power = 0.5f;
        shot = new Shot(new Position(-100, -100), angle, 0);
        specialShot = false;
        isVisible = true;
        rightAim = false;
        leftAim = false;
    }

    // fires a new shot at the end of the gun
    public Shot fire() {
        if (specialShot) {
            shot.setVisibility(false);
            shot = new AngryShot(new Position(pos.getX(), pos.getY()), angle, power);
            specialShot = false;
            return shot;
        } else {
            shot.setVisibility(false);
            shot = new Shot(new Position(pos.getX(), pos.getY()), angle, power);

            return shot;
        }
    }

    public void increasePower() {
        power = power >= 0 && power <= 1 ? power + 0.05f : this.power;
    }

    public void decreasePower() {
        power = power >= 0 && power <= 1 ? power - 0.05f : this.power;
    }


    public float aimTank(float delta) {
        if (rightAim) {
            this.angle = angle < 110 ? angle + speed * delta : 110;
        }

        if (leftAim) {
            this.angle = angle > -110 ? angle - speed * delta : -110;
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

    public void changeWeapon() {
        specialShot = !specialShot;
    }

    public boolean hasSpecialShot() {
        return specialShot;
    }

    public void setSpecialShot(boolean special){this.specialShot = special;}

    public float getPower() {
        return power;
    }

    public Shot getShot() {
        return shot;
    }

    public void setPos(Position pos, int width, int height) {
        this.pos = new Position(pos.getX() + width/2, pos.getY() + height);
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

    @Override
    public float getAngle() {
        return this.angle;
    }

    @Override
    public int getOriginX() {
        return originX;
    }

    @Override
    public int getOriginY() {
        return originY;
    }

    @Override
    public boolean isVisible() {
        return isVisible;
    }

    public void setVisibility(boolean bool) {
        isVisible = bool;
    }
}
