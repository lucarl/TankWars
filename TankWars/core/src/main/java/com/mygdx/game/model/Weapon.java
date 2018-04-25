package com.mygdx.game.model;

import java.util.Vector;


/**
 * Created by Carl on 2018-04-19.
 */
public class Weapon {
    private int damage;
    private float radius;
    private float weight;
    private volatile int ammo;
    private Vector vector;

    public float getWeight() {
        return weight;
    }

    public int getDamage() {
        return damage;
    }

    public float getRadius() {
        return radius;
    }
}
