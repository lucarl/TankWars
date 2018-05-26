package com.mygdx.game.model;

/**
 * This class can make rectangles and check if two rectangles have overlapped (collided)
 */
public class CollisionRect {
    private float x, y;
    private int width, height;

    /**
     * Constructor for a CollisionRect
     * @param x
     * @param y
     * @param width
     * @param height
     */
    public CollisionRect(float x, float y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    /**
     * Updates the rectangles position according to the given parameters
     * @param x
     * @param y
     */
    public void move(float x, float y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Check is this rectangle overlaps the other rectangle
     * @param other
     * @return
     */
    public boolean collidesWith(CollisionRect other) {
        return this.x < other.x + other.width && this.y < other.y + other.height
                && this.x + this.width > other.x && this.y + this.height > other.y;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
