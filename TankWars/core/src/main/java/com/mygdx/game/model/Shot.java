package com.mygdx.game.model;

import com.badlogic.gdx.Gdx;

/**
 * Created by Carl on 2018-04-23.
 */
public abstract class Shot implements IDrawable {
    private static final float GRAVITY = -0.1f;
    private String imgSrc;
    private int width;
    private int height;
    private float radius;
    private float weight;
    private float angle;

    private double[] vector = new double[2]; // speed
    private Position pos;
    private final float speed = 25;
    private boolean isVisible;

    // private int damage; borde kanske istället vara en metod i terrain som tar in skottets radius och weight

    // power should be a float between [0,1]
    public Shot(Position p, float angle, float power){
        this.pos = new Position(p.getX(), p.getY());
        this.vector[0] = Math.sin(Math.toRadians(angle)) * power * -speed; // x speed
        this.vector[1] = Math.cos(Math.toRadians(angle)) * power * speed ; // y speed
        isVisible = false;
        //this.damage = damage; borde kanske istället vara en metod i terrain som tar in skottets radius och weight
    }

    public void updatePostion(float delta) {
        if(pos.getX() > 0 && pos.getX() < 965){
            pos.setX(pos.getX() + vector[0] * delta * speed);
        }
        if(pos.getY() > 0){
            pos.setY(pos.getY() + vector[1] * delta * speed);
            vector[1] += GRAVITY;

        }
    }

    public boolean isVisible() {
        return isVisible;
    }

    public void setVisible(boolean visible) {
        isVisible = visible;
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
}
