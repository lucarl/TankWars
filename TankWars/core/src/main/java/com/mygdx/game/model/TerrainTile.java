package com.mygdx.game.model;
import java.util.Random;

public class TerrainTile implements IDrawable{
    private static final String imageSource = "terrain.png";
    private static int width, height;
    private boolean alive;
    private Position position;

    public TerrainTile(int y, int x, boolean alive) {
        this.position = new Position(x, y);
        this.width = 5;
        this.height = 5;
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
        return isVisible();
    }

    public boolean isAlive() {
        return alive;
    }

}
