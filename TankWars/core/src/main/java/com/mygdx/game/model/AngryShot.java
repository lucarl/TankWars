package com.mygdx.game.model;

public class AngryShot extends Shot {

    private static String imgSrc = "angrybird.png";
    private static int width = 50;
    private static int height = 50;
    private static int damage = 1;

    public AngryShot(Position p, float angle, float power, int windSpeed) {
        super(p, angle, power, windSpeed);
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
}
