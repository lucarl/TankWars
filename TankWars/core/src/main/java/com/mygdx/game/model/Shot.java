package com.mygdx.game.model;

import com.badlogic.gdx.Gdx;
import com.mygdx.game.ctrl.Controller;

/**
 * Created by Carl on 2018-04-23.
 */
public class Shot implements IDrawable {
    private static final float GRAVITY = -0.1f;
    private static String imgSrc = "bird.png";
    private static int width = 34;
    private static int height = 24;

    private float angle = 0;
    private float radius = 10;
    private float weight = 100;

    private float[] vector = new float[2]; // speed
    private Position pos;
    private final int speed = 25;
    private boolean isVisible;

    // private int damage; borde kanske istället vara en metod i terrain som tar in skottets radius och weight

    // power should be a float between [0,1]
    public Shot(Position p, float angle, float power){
        this.pos = p;
        this.vector[0] = (float)Math.sin(Math.toRadians(angle)) * power * -speed; // x speed
        this.vector[1] = (float)Math.cos(Math.toRadians(angle)) * power * speed ; // y speed
        isVisible = true;
        //this.damage = damage; borde kanske istället vara en metod i terrain som tar in skottets radius och weight
    }

    public void updatePostion(float delta) {
        if(pos.getX() > 0 && pos.getX() < Controller.GAME_WIDTH){
            pos.setX(pos.getX() + vector[0] * delta * speed);
        }
        if(pos.getY() > 0){
            pos.setY(pos.getY() + vector[1] * delta * speed);
            vector[1] += GRAVITY;

        }
    }

    @Override
    public Position getPos() {
        return pos;
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
    public float getAngle() {
        return angle;
    }

    @Override
    public int getOriginX() {
        return 0;
    }

    @Override
    public int getOriginY() {
        return 0;
    }


    public void setVisibility(boolean bool) {
        isVisible = bool;
    }

    public boolean isVisible() {
        return isVisible;
    }

}
