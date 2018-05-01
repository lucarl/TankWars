package com.mygdx.game.model;

public class TankGun implements IDrawable {
    private static String gunImgSrc = "toptube.png";
    private static int width = 20;
    private static int height = 80;
    private static int originX = width/2;
    private static int originY = 0;
    private static final int speed = 50;

    private Position pos;
    private float angle;
    private float power;
    private boolean specialShot;

    private boolean rightAim;
    private boolean leftAim;

    private Shot shot;

    public TankGun(Position pos) {
        pos = new Position(pos.getX(), pos.getY());
        angle = 90;
        shot = null;
        specialShot = true;
    }

    // fires a new shot at the end of the gun
    public void fire() {
        if(specialShot){
            shot = new AngryShot(new Position(pos.getX() + width/2, pos.getY() + height), angle, power);
        }
        else {

            shot = new Shot(new Position(pos.getX() + width/2, pos.getY() + height), angle, power);
            shot.setVisible(true);
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

    public Shot getShot(){
        return shot;
    }

    @Override
    public Position getPos() {
        return null;
    }

    @Override
    public String getImgSrc() {
        return null;
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
        return angle;
    }

    @Override
    public int getOriginX() {
        return originX;
    }

    @Override
    public int getOriginY() {
        return originY;
    }
}
