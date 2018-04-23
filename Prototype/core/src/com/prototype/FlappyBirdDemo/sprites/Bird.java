package com.prototype.FlappyBirdDemo.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;


public class Bird {
    private static final int GRAVITY = -15;
    private static final int MOVEMENT = 100;
    private Vector2 pos;
    private Vector2 vel;
    private Rectangle bounds;

    private Texture bird;

    public Bird(int x, int y) {
        pos = new Vector2(x, y);
        vel = new Vector2(0, 0);
        bird = new Texture("bird.png");
        bounds = new Rectangle(x, y, bird.getWidth(), bird.getHeight());
    }

    public void update(float dt) {
        if (pos.y > 0) vel.add(0, GRAVITY);
        vel.scl(dt);
        pos.add(MOVEMENT * dt, vel.y);

        if (pos.y < 0) pos.y = 0;

        vel.scl(1 / dt);
        bounds.setPosition(pos.x, pos.y);
    }

    public void jump() {
        vel.y = 250;
    }

    public Vector2 getPos() {
        return pos;
    }

    public Texture getTexture() {
        return bird;
    }

    public Rectangle getBounds(){
        return bounds;
    }

    public void dispose(){
        bird.dispose();
    }
}
