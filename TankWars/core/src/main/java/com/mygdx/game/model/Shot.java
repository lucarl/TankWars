package com.mygdx.game.model;

import com.badlogic.gdx.Gdx;

/**
 * Created by Carl on 2018-04-23.
 */
public class Shot {
    private static final float GRAVITY = -0.1f;
    private double[] vector = new double[2]; // speed
    private Position position;
    private static float radius = 10;
    private static float weight = 100;
    private final float speed = 25;
    private String shotImgSrc = "bird.png";
    private boolean isVisible;

    // private int damage; borde kanske istället vara en metod i terrain som tar in skottets radius och weight

    // power should be a float between [0,1]
    public Shot(Position p, float angle, float power){
        this.position = new Position(p.getX(), p.getY());
        this.vector[0] = Math.sin(Math.toRadians(angle)) * power * -speed; // x speed
        this.vector[1] = Math.cos(Math.toRadians(angle)) * power * speed ; // y speed
        isVisible = false;
        //this.damage = damage; borde kanske istället vara en metod i terrain som tar in skottets radius och weight
    }

    public double[] getVector() {
        return vector;
    }

    public Position getPosition() {
        return position;
    }

    public void updatePostion(float delta) {
        if(position.getX() > 0 && position.getX() < 965){
            position.setX(position.getX() + vector[0] * delta * speed);
        }
        if(position.getY() > 0){
            position.setY(position.getY() + vector[1] * delta * speed);
            vector[1] += GRAVITY;

        }
    }

    public boolean isVisible() {
        return isVisible;
    }

    public void setVisible(boolean visible) {
        isVisible = visible;
    }

    public String getShotImgSrc() {
        return shotImgSrc;
    }
}
