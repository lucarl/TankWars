package com.mygdx.game.model;

/**
 * A class representing the upgrade
 * loot box for a tank
 *
 * @author  Carl Lundborg
 */
public class Upgrade implements IDrawable{
    private static String tankImgSrc = "AngryShot.png";
    private static int width = 50;
    private static int height = 50;
    private boolean isVisible;
    private CollisionRect rect;
    private Position pos;

    public Upgrade(float x, float y) {
        rect = new CollisionRect(pos.getX(), pos.getY(), width, height);
    }

    @Override
    public Position getPos() {
        return null;
    }

    @Override
    public String getImgSrc() {
        return null;
    }

    @Override
    public int getWidth() {
        return 0;
    }

    @Override
    public int getHeight() {
        return 0;
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
        return false;
    }
}
