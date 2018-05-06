package com.mygdx.game.model;

import com.mygdx.game.ctrl.Controller;

/**
 * Created by Carl on 2018-04-23.
 */
public class Shot implements IDrawable {
    private static final float GRAVITY = -9.8f;
    private static String imgSrc = "bird.png";
    private static int width = 15;
    private static int height = 15;
    private static int originX = width / 2;
    private static int originY = height / 2;

    private float angle = 0;
    private float radius = 10;
    private float weight = 100;
    private int damage = 1;

    private float[] vector = new float[2]; // speed
    private Position pos;
    private final int speed = 20;
    private boolean isVisible;
    private int windSpeed;

    private CollisionRect rect;



    // power should be a float between [0,1]
    public Shot(Position pos, float angle, float power, int windSpeed) {
        this.pos = pos;
        this.angle = angle;
        this.vector[0] = (float) Math.sin(Math.toRadians(angle)) * power * -speed; // x speed
        this.vector[1] = (float) Math.cos(Math.toRadians(angle)) * power * speed; // y speed
        isVisible = true;
        this.windSpeed = windSpeed;
        rect = new CollisionRect(pos.getX(), pos.getY(), width, height);
        //this.damage = damage; borde kanske istället vara en metod i terrain som tar in skottets radius och weight
    }

    public void update(float delta) {
        if (pos.getX() > 0 && pos.getX() < Controller.GAME_WIDTH && pos.getY() > 0) {
            pos.setX(pos.getX() + vector[0] * delta * speed);
            pos.setY(pos.getY() + vector[1] * delta * speed);
            vector[0] += windSpeed * delta;
            vector[1] += GRAVITY * delta;
            rect.move(pos.getX(), pos.getY());
            // TODO not working, want shot to rotate according to vector direction
            //angle = (float) Math.atan(vector[1] / vector[0]);
        } else {
            //if the shot object is outside the screen x-wise it's unvisible
            setVisibility(false);
        }
    }

    @Override
    public Position getPos() {
        return pos;
    }

    @Override
    public String getImgSrc() {
        return imgSrc;
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
        return angle;
    }

    @Override
    public int getOriginX() {
        return originX;
    }

    @Override
    public int getOriginY() {
        return originY;
    }

    @Override
    public boolean isVisible() { return this.isVisible == true; }

    public void setVisibility(boolean bool) { this.isVisible = bool; }

    public CollisionRect getRect() {
        return rect;
    }

    public int getDamage() {
        return damage;
    }
}
