package com.mygdx.game.model;
import java.util.Random;

public class TerrainTile implements IDrawable{
    private static final String imageSource = "terrainTile.png";
    private static int width, height;
    private boolean isAlive;
    private Position position;

    public TerrainTile(int x, int y, boolean alive) {
        this.position = new Position(x, y);
        width = 5;
        height = 5;
        this.isAlive = alive;
    }

    @Override
    public Position getPos() {
        return position;
    }

    @Override
    public String getImgSrc() {
        return imageSource;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    @Override
    public float getAngle() {
        return 0;
    }

    @Override
    public int getOriginX() {
        return 0;
    }

    @Override
    public int getOriginY() {
        return 0;
    }

    @Override
    public boolean isAlive() {
        return isAlive;
    }

    public void setAlive(boolean bool) {
        isAlive = bool;
    }

}
