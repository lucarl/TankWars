package com.mygdx.game.model;

import com.badlogic.gdx.Gdx;

/**
 * Created by Carl on 2018-04-23.
 */
public class Shot implements Object {
    private static final float GRAVITY = -0.05f;
    private float[] vector = new float[2]; // speed
    private Position position;
    private static float radius = 10;
    private static float weight = 100;
    private int width, height;
    private String shotImgSrc = "bird.png";

    // private int damage; borde kanske istället vara en metod i terrain som tar in skottets radius och weight


    public Shot(Position p, float angle, int power){
        this.position = new Position(p.getX(), p.getY());
        this.vector[0] = (float) Math.cos(angle) * power; // x speed
        this.vector[1] = (float) Math.sin(angle) * power; // y speed
        //this.damage = damage; borde kanske istället vara en metod i terrain som tar in skottets radius och weight
        width = 25;
        height = 25;
    }

    public float[] getVector() {
        return vector;
    }

    public Position getPosition() {
        return position;
    }

    public void update(float delta) {
        if(position.getX() > 0 && position.getX() < Gdx.graphics.getWidth()){
            position.setX(position.getX() + vector[0] * delta);
        }
        if(position.getY() > 0 && position.getY() < Gdx.graphics.getHeight()){
            position.setY(position.getY() + vector[1]);
            vector[1] += GRAVITY;

        }
    }


    @Override
    public Position getPos() {
        return position;
    }

    @Override
    public String getImgSrc() {
        return shotImgSrc;
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
    public boolean canRotate() {
        return false;
    }

    @Override
    public float getAngle() {
        return 0;
    }
}
