package com.mygdx.game.model;

/**
 * A class for the standard shot containing the specific
 * values for this shot
 *
 * @author  Carl Lundborg
 * revised by Carl Lundborg
 */
public class StandardShot extends Shot {
    private float[] vector = getVector();

    public StandardShot(Position pos, float angle, float power, int windSpeed) {
        setWidth(15);
        setHeight(15);
        setPos(new Position(pos.getX(), pos.getY()));
        setAngle(angle);
        this.vector[0] = (float) Math.sin(Math.toRadians(angle)) * power * -getSpeed(); // x speed
        this.vector[1] = (float) Math.cos(Math.toRadians(angle)) * power * getSpeed(); // y speed
        setAlive(true);
        setWindSpeed(windSpeed);
        setRect(new CollisionRect(pos.getX(), pos.getY(), getWidth(), getHeight()));
        setDamage(20);
        setName("Gun");
        setImgSrc("cannonBall.png");
        setRadius(20);
    }
}
