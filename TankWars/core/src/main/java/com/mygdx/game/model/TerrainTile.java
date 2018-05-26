package com.mygdx.game.model;

/**
 * A class representing each tile in the terrain
 *
 * @author  Carl Lundborg
 * revised by Carl Lundborg
 */
public class TerrainTile implements IDrawable{
    private static final String imageSource = "terrainTile.png";
    private static int width, height;
    private boolean isAlive;
    private Position position;
    private CollisionRect collisionRect;

    public TerrainTile(int x, int y, boolean alive, int tileSize) {
        position = new Position(x, y);
        width = tileSize;
        height = tileSize;
        isAlive = alive;
        collisionRect = new CollisionRect(position.getX(),position.getY(), width, height);
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

    public CollisionRect getRect() {
        return collisionRect;
    }

    public void setAlive(boolean bool) {
        isAlive = bool;
    }


}
