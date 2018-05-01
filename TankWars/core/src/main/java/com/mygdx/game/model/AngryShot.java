package com.mygdx.game.model;

public class AngryShot extends Shot {

    private String imgSrc = "angrybird.png";

    public AngryShot(Position p, float angle, float power) {
        super(p, angle, power);
    }

    @Override
    public String getImgSrc() {
        return imgSrc;
    }
}
