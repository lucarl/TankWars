package com.mygdx.game.model;

/**
 * A class representing the upgrade
 * loot box for a tank
 *
 * @author  Carl Lundborg
 */
    private boolean isVisible;
    private CollisionRect rect;
    private Position pos;

    public Upgrade(float x, float y) {
        isVisible = true;
        rect = new CollisionRect(pos.getX(), pos.getY(), width, height);
    }

    @Override
    public Position getPos() {
    }

    @Override
    public String getImgSrc() {
    }

    @Override
    public int getWidth() {
    }

    @Override
    public int getHeight() {
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
