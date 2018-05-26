package com.mygdx.game.model;

/**
 * A class representing the upgrade
 * loot box for a tank that gives the player
 * a certain type of Shot
 *
 * @author  Carl Lundborg
 * revised by Carl Lundborg
 */
public class Upgrade implements IDrawable{
    private static final String imageSource = "upgradeBox.png";
    private static int width = 30;
    private static int height = 30;
    private boolean isVisible;
    private CollisionRect rect;
    private Position pos;

    public Upgrade(float x, float y) {
        this.pos = new Position(x + width / 2, y);
        isVisible = true;
        rect = new CollisionRect(pos.getX(), pos.getY(), width, height);
    }

    @Override
    public Position getPos() {
        return pos;
    }

    public void setPos(Position newPosition) {
        pos = newPosition;
    }

    @Override
    public String getImgSrc() {
        return imageSource;
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
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
        return isVisible;
    }
}
