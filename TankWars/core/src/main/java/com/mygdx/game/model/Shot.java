package com.mygdx.game.model;

/**
 * Created by Carl on 2018-04-23.
 */
public class Shot {
    private static final float GRAVITY = -9.8f;
    private double[] vector = new double[2]; // speed
    private Position position;
    private static float radius = 10;
    private static float weight = 100;

    // private int damage; borde kanske istället vara en metod i terrain som tar in skottets radius och weight


    public Shot(Position p, float angle, int power){
        this.position = p;
        this.vector[0] = Math.cos(angle) * power; // x speed
        this.vector[1] = Math.sin(angle) * power; // y speed
        //this.damage = damage; borde kanske istället vara en metod i terrain som tar in skottets radius och weight
    }

    public double[] getVector() {
        return vector;
    }

    public Position getPosition() {
        return position;
    }

    public void updatePostion(float delta) {
        position.setX(position.getX() + vector[0] * delta);
        if(position.getY() >= 0){
            position.setY(position.getY() + vector[1]);
            vector[1] += GRAVITY;

        }

    }
}
