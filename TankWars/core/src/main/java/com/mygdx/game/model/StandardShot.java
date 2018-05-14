package com.mygdx.game.model;

/**
 * Created by Carl on 2018-05-14.
 */
public class StandardShot extends Shot {
    private float[] vector = getVector();

    public StandardShot(Position pos, float angle, float power, int windSpeed) {
        setPos(pos);
        setAngle(angle);
        this.vector[0] = (float) Math.sin(Math.toRadians(angle)) * power * -getSpeed(); // x speed
        this.vector[1] = (float) Math.cos(Math.toRadians(angle)) * power * getSpeed(); // y speed
        setAlive(true);
        setWindSpeed(windSpeed);
        setRect(new CollisionRect(pos.getX(), pos.getY(), getWidth(), getHeight()));
        setDamage(5);
        setName("Gun");
        setImgSrc("bird.png");
        setWidth(20);
        setHeight(20);
    }
}
