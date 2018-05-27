package com.mygdx.game.model;

import com.mygdx.game.model.factorys.ShotFactory;
import com.mygdx.game.events.Event;
import com.mygdx.game.events.EventBus;

/**
 * A class representing the tanks gun
 *
 * @author Adam Kjäll
 * Revised by: Carl Lundborg, Thomas Jinton
 */

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
    private boolean missile;
    private boolean isVisible;
    private boolean rightAim;
    private boolean leftAim;
    private int changeWeapon;
    private Shot shot;

    public TankGun(Position pos) {
        this.pos = pos;
        angle = 0;
        power = 0.5f;
        isVisible = true;
        rightAim = false;
        leftAim = false;
    }

    /**
     * fired a new shot at the end of the gun using the
     * TankGun factory to create the shot
     *
     * @param windSpeed wind speed that influences the direction of the shot
     * @return the specific shot
     */
    public Shot fire(int windSpeed) {
        ShotFactory shotFactory = new ShotFactory();

        if (nuke) {
            changeWeapon = 2;
            shot = shotFactory.makeTankGun(changeWeapon, new Position(pos.getX() - width / 2, pos.getY() - width / 2),
                    angle, power, windSpeed);
            nuke = false;

            EventBus.BUS.publish(new Event(Event.Tag.PLAY_SOUND_NUKE, null));

            return shot;
        }
        if (missile) {
            changeWeapon = 3;
            shot = shotFactory.makeTankGun(changeWeapon, new Position(pos.getX() - width / 2, pos.getY() - width / 2),
                    angle, power, windSpeed);
            missile = false;

            EventBus.BUS.publish(new Event(Event.Tag.PLAY_SOUND_MISSILE, null));

            return shot;
        } else {
            changeWeapon = 1;
            shot = shotFactory.makeTankGun(changeWeapon, new Position(pos.getX() - width / 2, pos.getY() - width / 2),
                    angle, power, windSpeed);
            standard = false;

            EventBus.BUS.publish(new Event(Event.Tag.PLAY_SOUND_FIRE, null));

        }
        return shot;
    }

    /**
     * increase/decrease the power of the shot
     */
    public void increasePower() {
        power = power < 1 ? power + 0.05f : this.power;
    }

    public void decreasePower() {
        power = power > 0.1f ? power - 0.05f : this.power;
    }

    /**
     * Setting the angle for the shot
     *
     * @param delta delta value for update
     * @return
     */
    public float aimTank(float delta) {
        if (rightAim) {
            angle = angle < 110 ? angle + speed * delta : 110;
        }

        if (leftAim) {
            angle = angle > -110 ? angle - speed * delta : -110;
        }
        return angle;
    }

    /**
     * left/right aim of the gun
     *
     * @param b if either left or right aim has occurred
     */
    public void setLeftAim(boolean b) {

        EventBus.BUS.publish(new Event(Event.Tag.PLAY_SOUND_AIM, null));

        if (rightAim && b) {
            rightAim = false;
        }
        leftAim = b;
    }


    public void setRightAim(boolean b) {

        EventBus.BUS.publish(new Event(Event.Tag.PLAY_SOUND_AIM, null));

        if (leftAim && b) {
            leftAim = false;
        }
        rightAim = b;

    }

    public void changeNuke() {
        nuke = true;
    }

    public void changeMissile() {
        missile = true;
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
