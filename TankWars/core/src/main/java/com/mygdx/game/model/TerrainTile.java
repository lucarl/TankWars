package com.mygdx.game.model;
import java.util.Random;

public class TerrainTile implements IDrawable{
    private static final String imageSource = "terrainTile.png";
    private static int width, height;
    private boolean alive;
    private Position position;

    public TerrainTile(int x, int y, boolean alive) {
        this.position = new Position(x, y);
        width = 5;
        height = 5;
        this.alive = alive;
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
    public boolean isVisible() {
        return alive;
    }

    public boolean isAlive() {
        return alive;
    }

}
