package com.mygdx.game.model;

public class Missile extends Shot {

    private float[] vector = getVector();

    public Missile (Position pos, float angle, float power, int windSpeed) {

        setPos(pos);
        setAngle(angle);
        this.vector[0] = (float) Math.sin(Math.toRadians(angle)) * power * -getSpeed(); // x speed
        this.vector[1] = (float) Math.cos(Math.toRadians(angle)) * power * getSpeed(); // y speed
        setAlive(true);
        setWindSpeed(windSpeed);
        setRect(new CollisionRect(pos.getX(), pos.getY(), getWidth(), getHeight()));
        setDamage(20);
        setName("Nuke");
        setImgSrc("nuke.png");
        setWidth(10);
        setHeight(50);
        setRadius(20);
    }

}
