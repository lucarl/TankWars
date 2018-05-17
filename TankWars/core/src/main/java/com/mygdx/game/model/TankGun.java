package com.mygdx.game.model;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.utils.Timer;
import com.mygdx.game.model.factorys.ShotFactory;
import com.mygdx.game.services.Assets;

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
    private Shot shot;

    Sound soundAim = Assets.manager.get("badaim.mp3", Sound.class);

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
        shot = null;
        shotFactory = new ShotFactory();
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
        power = power < 1 ? power + 0.05f : this.power;
    }

    public void decreasePower() {
        power = power > 0.1f ? power - 0.05f : this.power;
    }


    public float aimTank(float delta) {
        if (rightAim) {
            angle = angle < 110 ? angle + speed * delta : 110;
        }

        if (leftAim) {
            angle = angle > -110 ? angle - speed * delta : -110;
        }
        return angle;
    }

    public void setLeftAim(boolean b) {

        final long soundAimID = soundAim.loop(0.3f,1.0f,0.0f);

        Timer.schedule((new Timer.Task() {
            @Override
            public void run() {
                soundAim.loop(soundAimID);
                soundAim.stop();
            }
        }),1);

        if (rightAim && b) {
            rightAim = false;
        }
        leftAim = b;
    }


    public void setRightAim(boolean b) {

        final long soundAimID = soundAim.loop(0.3f,1.0f,0.0f);

        Timer.schedule((new Timer.Task() {
            @Override
            public void run() {
                soundAim.loop(soundAimID);
                soundAim.stop();
            }
        }),1);

        if (leftAim && b) {
            leftAim = false;
        }
        rightAim = b;

    }

   /* public void setSoundAim(boolean m, boolean n){

        soundAim.pause();
        rightAim=m;
        leftAim=n;

    }
    */

    public void changeNuke() {
        nuke = true;
    }

    public void changeStandard() {
        standard = true;
    }

    public boolean hasSpecialShot() {
        return nuke;
    }

    public void setSpecialShot(boolean special) {
        this.nuke = special;
    }

    public float getPower() {
        return power;
    }

    public Shot getShot() {
        return shot;
    }

    public void setAngle(float angle) {
        this.angle = angle;
    }

    public void setPos(Position pos) {
        this.pos = new Position(pos.getX() + width / 2, pos.getY() + height);
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

    public int getChangeWeapon() {
        return changeWeapon;
    }

    @Override
    public boolean isAlive() {
        return isVisible;
    }

    public void setAlive(boolean bool) {
        isVisible = bool;
    }

    public boolean isRightAim() {
        return rightAim;
    }

    public boolean isLeftAim() {
        return leftAim;
    }
}