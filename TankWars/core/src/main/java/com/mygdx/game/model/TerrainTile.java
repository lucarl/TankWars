package com.mygdx.game.model;
import java.util.Random;

public class TerrainTile {
    private float x, y;
    private int width, height = 5;
    private boolean alive;

    public TerrainTile(int x, int y, boolean alive) {
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






    /*public Terrain() {
        tileMap = new Object[1000][250];
        for (int i = 0; i < tileMap.length; i++) {
            for (int j = 0; j < tileMap[i].length; j++) {
                tileMap[i][j] = new Object
            }
        }

    }*/

}
