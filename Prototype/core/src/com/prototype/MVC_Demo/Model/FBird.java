package com.prototype.MVC_Demo.Model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.prototype.FlappyBirdDemo.sprites.Animation;

public class FBird {
    private static final int GRAVITY = -15;
    private static final int MOVEMENT = 0;
    private Vector2 pos;
    private Vector2 vel;

    public FBird(int x, int y) {
        pos = new Vector2(x, y);
        vel = new Vector2(0, 0);


    }

    public void update(float dt) {
        //birdAnimation.update(dt);
        if (pos.y > 0) vel.add(0, GRAVITY);
        vel.scl(dt);
        pos.add(MOVEMENT * dt, vel.y);

        if (pos.y < 0) pos.y = 0;

        vel.scl(1 / dt);

    }

    public void jump() {
        vel.y = 250;
    }

    public Vector2 getPos() {
        return pos;
    }





}