package com.mygdx.game.model;

/**
 * A class representing a shot upgrade
 * to a nuke shot
 *
 * @author  Carl Lundborg, Adam Kjäll
 */

public class NukeShot extends Shot{

    private float[] vector = getVector();

    public NukeShot(Position pos, float angle, float power, int windSpeed) {
        setWidth(25);
        setHeight(25);
        setPos(new Position(pos.getX() - getWidth() /2, pos.getY()));
        setAngle(angle);
        this.vector[0] = (float) Math.sin(Math.toRadians(angle)) * power * -getSpeed(); // x speed
        this.vector[1] = (float) Math.cos(Math.toRadians(angle)) * power * getSpeed(); // y speed
        setAlive(true);
        setWindSpeed(windSpeed);
        setRect(new CollisionRect(pos.getX(), pos.getY(), getWidth(), getHeight()));
        setDamage(100);
        setName("Nuke");
        setImgSrc("nuke.png");
        setRadius(30);
    }
}
