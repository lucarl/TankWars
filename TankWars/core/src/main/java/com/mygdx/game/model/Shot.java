package com.mygdx.game.model;

/**
 * Created by Carl on 2018-04-23.
 */
public class Shot {
    private int[] vector;
    private int x, y;
    private int damage;
    private float radius;
    private float weight;



    public Shot(int x, int y, int[] vector, int damage, int radius, int weight){
        this.x = x;
        this.y = y;
        this.vector = vector;
        this.damage = damage;
        this.radius = radius;
        this.weight = weight;
    }

    


}
