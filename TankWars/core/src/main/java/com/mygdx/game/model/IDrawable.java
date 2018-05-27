package com.mygdx.game.model;

/**
 * This interface is implemented by all objects in the model that
 * needs to be drawn by the Renderer class in the view package
 *
 * @author Adam Kjäll
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
