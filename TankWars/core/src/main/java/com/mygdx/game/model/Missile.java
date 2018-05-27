package com.mygdx.game.model;

/**
 * A class representing a shot upgrade
 * to a missile shot, containing specific
 * values for this shot
 *
 * @author  Carl Lundborg
 * revised by Carl Lundborg
 */

public class Missile extends Shot {

    private float[] vector = getVector();

    public Missile (Position pos, float angle, float power, int windSpeed) {
        setWidth(10);
        setHeight(50);
        setPos(new Position(pos.getX(), pos.getY()));
        setAngle(angle);
        this.vector[0] = (float) Math.sin(Math.toRadians(angle)) * power * -getSpeed(); // x speed
        this.vector[1] = (float) Math.cos(Math.toRadians(angle)) * power * getSpeed(); // y speed
        setAlive(true);
        setWindSpeed(windSpeed);
        setRect(new CollisionRect(pos.getX(), pos.getY(), getWidth(), getHeight()));
        setDamage(20);
        setName("Nuke");
        setImgSrc("nuke.png");
        setRadius(20);
    }

}
