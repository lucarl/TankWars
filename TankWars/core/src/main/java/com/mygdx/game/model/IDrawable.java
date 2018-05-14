package com.mygdx.game.model;

//implementeras av alla objekt som måste ritas ut
//isAlive - lever objektet?

public interface IDrawable {

    Position getPos();
    String getImgSrc();
    int getWidth();
    int getHeight();
    float getAngle();
    int getOriginX();
    int getOriginY();
    boolean isAlive();
}
