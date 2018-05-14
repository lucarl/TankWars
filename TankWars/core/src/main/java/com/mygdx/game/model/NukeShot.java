package com.mygdx.game.model;

public class NukeShot extends Shot{
    private float[] vector = getVector();

    public NukeShot(Position pos, float angle, float power, int windSpeed) {
        setPos(pos);
        setAngle(angle);
        this.vector[0] = (float) Math.sin(Math.toRadians(angle)) * power * -getSpeed(); // x speed
        this.vector[1] = (float) Math.cos(Math.toRadians(angle)) * power * getSpeed(); // y speed
        setAlive(true);
        setWindSpeed(windSpeed);
        setRect(new CollisionRect(pos.getX(), pos.getY(), getWidth(), getHeight()));
        setDamage(30);
        setName("Nuke");
        setImgSrc("angrybird.png");
        setWidth(50);
        setHeight(50);
    }
}
