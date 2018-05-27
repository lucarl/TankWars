package com.mygdx.game.model;

import com.mygdx.game.Application;
import com.mygdx.game.events.Event;
import com.mygdx.game.events.EventBus;

/**
 * An abstract class used by each ammunition
 * extention, TankGun and ShotFactory
 * to form a factory pattern
 *
 * @author  Carl Lundborg, Adam Kjäll
 */

public abstract class Shot implements IDrawable {
    private static final float GRAVITY = -9.8f;
    private static String imgSrc;
    private static int width = 15;
    private static int height = 15;
    private static int originX = width / 2;
    private static int originY = height / 2;

    private float angle = 0;
    private float radius;
    private float weight = 100;
    private int damage;
    private String name;

    private float[] vector = new float[2]; // speed
    private Position pos;
    private int speed = 15;

    private boolean isAlive;
    private int windSpeed;

    private CollisionRect rect;

    public void update(float delta) {
        if (pos.getX() > 0 && pos.getX() < Application.GAME_WIDTH && pos.getY() > 0) {
            pos.setX(pos.getX() + vector[0] * delta * speed);
            pos.setY(pos.getY() + vector[1] * delta * speed);
            vector[0] += windSpeed/3 * delta; // /3 because windspeed was to strong
            vector[1] += GRAVITY * delta;
            rect.move(pos.getX(), pos.getY());
            // TODO not working, want shot to rotate according to vector direction
            //angle = (float) Math.atan(vector[1] / vector[0]);
        } else {
            //if the shot object is outside the screen x-wise it's marked as dead and will get removed
            isAlive = false;
        }
    }

    @Override
    public String toString() {
        return name ;
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

    @Override
    public boolean isAlive() {
        return isAlive;
    }

    public void setAlive(boolean bool){
        if (!bool && isAlive) {
            EventBus.BUS.publish(new Event(Event.Tag.PLAY_SOUND_EXPLOSION, this));
        }
        isAlive = bool;
    }

    public CollisionRect getRect() {
        return rect;
    }

    public float getRadius() {
        return radius;
    }

    public void setRadius(float newRadius) {
        radius = newRadius;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int newDamage) {
        damage = newDamage;
    }

    public String getName() {
        return name;
    }

    public void setName(String newName) {
        name = newName;
    }

    public void setPos(Position newPosition) {
        pos = newPosition;
    }

    public void setAngle(float newAngle) {
        angle = newAngle;
    }

    public void setVector(float[] newVector) {
        vector = newVector;
    }

    public void setSpeed(int newSpeed) {
        speed = newSpeed;
    }

    public float[] getVector() {
        return vector;
    }

    public int getSpeed() {
        return speed;
    }

    public void setWindSpeed(int newWindSpeed) {
        windSpeed = newWindSpeed;
    }

    public void setRect(CollisionRect newRect) {
        rect = newRect;
    }

    public void setImgSrc(String newImgSrc) {
        imgSrc = newImgSrc;
    }

    public void setWidth(int newWidth) {
        width = newWidth;
    }

    public void setHeight(int newHeight) {
        height = newHeight;
    }

    public int getWindSpeed() {
        return windSpeed;
    }
}
