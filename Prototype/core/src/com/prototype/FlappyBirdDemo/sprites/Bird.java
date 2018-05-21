package com.prototype.FlappyBirdDemo.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;


public class Bird {
    private static final int GRAVITY = -15;
    private static final int MOVEMENT = 100;
    private Vector2 pos;
    private Vector2 vel;
    private Rectangle bounds;
    private Animation birdAnimation;
    private Texture texture;


    public Bird(int x, int y) {
        pos = new Vector2(x, y);
        vel = new Vector2(0, 0);

        texture = new Texture("birdanimation.png");
        birdAnimation = new Animation(new TextureRegion(texture), 3, 0.5f);
        bounds = new Rectangle(x, y, texture.getWidth() / 3, texture.getHeight());


    }

    public void update(float dt) {
        birdAnimation.update(dt);
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

    public TextureRegion getTexture() {
        return birdAnimation.getFrame();
    }

    public Rectangle getBounds(){
        return bounds;
    }

    public void dispose(){
        texture.dispose();
    }
}
