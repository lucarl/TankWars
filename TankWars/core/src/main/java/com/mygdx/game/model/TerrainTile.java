package com.mygdx.game.model;
import java.util.Random;

public class TerrainTile {
    private float x, y, width, height;
    public static final String imageSource = "terrainTile.png";

    public TerrainTile(int posX, int posY) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public String getImgSrc() {
        return imageSource;
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
