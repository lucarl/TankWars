package com.mygdx.game.model;

//implementeras av alla objekt som måste ritas ut

/**
 * This interface is implemented by all objects in the model that
 * needs to be drawn by the Renderer class in the view package
 */

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
