package com.mygdx.game.model;

// Kanske ett annat namn?
public interface Object {

    public Position getPos();
    public String getImgSrc();
    public int getWidth();
    public int getHeight();
    public boolean canRotate();
    public float getAngle();

}
