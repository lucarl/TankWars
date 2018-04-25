package com.mygdx.game.model;

/**
 * Created by Carl on 2018-04-23.
 */
public class Shot {
    private static final float GRAVITY = -9.8f;
    private double[] vector = new double[2]; // speed
    private int x, y;
    private int damage;
    private float radius;
    private float weight;



    public Shot(int x, int y, int angle, int power, int damage, float radius, float weight){
        this.x = x;
        this.y = y;
        this.vector[0] = Math.cos(angle) * power; // x speed
        this.vector[1] = Math.sin(angle) * power; // y speed
        this.damage = damage;
        this.radius = radius;
        this.weight = weight;
    }

    public void update(float delta) {
        x += vector[0] * delta;
        if(y >= 0){
            y += vector[1];
            vector[1] += GRAVITY;

        }
    }
}
