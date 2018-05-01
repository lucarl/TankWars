package com.mygdx.game.model;

public class AngryShot extends Shot {

    private static String imgSrc = "angrybird.png";
    private static int width = 50;
    private static int height = 50;

    public AngryShot(Position p, float angle, float power) {
        super(p, angle, power);
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
}
