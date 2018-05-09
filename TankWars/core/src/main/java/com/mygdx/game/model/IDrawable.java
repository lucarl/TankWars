package com.mygdx.game.model;

//implementeras av alla objekt som måste ritas ut
//isAlive - lever objektet?

public interface IDrawable {

    public Position getPos();
    public String getImgSrc();
    public int getWidth();
    public int getHeight();
    public float getAngle();
    public int getOriginX();
    public int getOriginY();
    public boolean isAlive();
}
