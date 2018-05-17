package com.mygdx.game.model;

public class AngryShot extends Shot {

    private static String imgSrc = "angrybird.png";
    private static int width = 50;
    private static int height = 50;
    private int damage = 100;
    private final int speed = 5;

    private float[] vector = getVector();


    public AngryShot(Position pos, float angle, float power, int windSpeed) {
        setPos(pos);
        setAngle(angle);
        this.vector[0] = (float) Math.sin(Math.toRadians(angle)) * power * -getSpeed(); // x speed
        this.vector[1] = (float) Math.cos(Math.toRadians(angle)) * power * getSpeed(); // y speed
        setAlive(true);
        setWindSpeed(windSpeed);
        setRect(new CollisionRect(pos.getX(), pos.getY(), getWidth(), getHeight()));
        setDamage(5);
        setName("Gun");
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
    public int getDamage() { return damage; }

    @Override
    public void update(float delta) {
        super.update(delta);
    }
}
