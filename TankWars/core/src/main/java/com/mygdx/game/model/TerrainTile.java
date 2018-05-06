package com.mygdx.game.model;
import java.util.Random;

public class TerrainTile {
    private int x, y;
    private int width, height = 5;
    private boolean alive;

    public TerrainTile(int y, int x, boolean alive) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.alive = alive;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }

    public boolean isAlive() {
        return alive;
    }

}
