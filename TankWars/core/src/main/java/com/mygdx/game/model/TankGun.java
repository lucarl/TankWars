package com.mygdx.game.model;

public class TankGun implements IDrawable {
    private static String imgSrc = "tankGun.png";
    private static int width = 5;
    private static int height = 20;
    private static int originX = width / 2;
    private static int originY = 0;
    private static final int speed = 50;

    private Position pos;
    private float angle;
    private float power;
    private boolean nuke;
    private boolean standard;
    private boolean isVisible;
    private boolean rightAim;
    private boolean leftAim;
    private ShotFactory shotFactory;
    private int changeWeapon;

    //private Shot shot;

    public TankGun(Position pos) {
        this.pos = pos;
        angle = 0;
        power = 0.5f;
        //shot = new Shot(new Position(-100, -100), angle, 0, 0);
        isVisible = true;
        rightAim = false;
        leftAim = false;
    }

    // fires a new shot at the end of the gun
    public Shot fire(int windSpeed) {
        shotFactory = new ShotFactory();
        Shot shot = null;
        if (standard) {
            changeWeapon = 1;
            shot = shotFactory.makeTankGun(changeWeapon, new Position(pos.getX(), pos.getY()), angle, power, windSpeed);
            standard = false;
            return shot;
        }
        if (nuke) {
            changeWeapon = 2;
            shot = shotFactory.makeTankGun(changeWeapon, new Position(pos.getX(), pos.getY()), angle, power, windSpeed);
            nuke = false;
            return shot;
        } else {
            changeWeapon = 1;
            shot = shotFactory.makeTankGun(changeWeapon, new Position(pos.getX(), pos.getY()), angle, power, windSpeed);
            standard = false;
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

    public void changeNuke() {
        nuke = true;
    }

    public void changeStandard() {
        standard = true;
    }

    public boolean hasSpecialShot() {
        return nuke;
    }

    public void setSpecialShot(boolean special){this.nuke = special;}

    public float getPower() {
        return power;
    }

    //public Shot getShot() {
    //    return shot;
    //}

    public void setPos(Position pos) {
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
    public boolean isAlive() {
        return isVisible;
    }

    public void setAlive(boolean bool) {
        isVisible = bool;
    }

    public boolean isRightAim() { return rightAim; }

    public boolean isLeftAim() { return leftAim; }
}
