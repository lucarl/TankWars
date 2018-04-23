package com.prototype.FlappyBirdDemo.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public class Ground {
    private static Texture ground;
    private Vector2 groundPos1, groundPos2;
    public static final int GROUND_Y_OFFSET = -40;

    public Ground(float x, float y){
        ground = new Texture("ground.png");
        groundPos1 = new Vector2(x, GROUND_Y_OFFSET);
        groundPos2 = new Vector2(x + ground.getWidth(), GROUND_Y_OFFSET);
    }

    public void updatePos1(){
        groundPos1.add(ground.getWidth() * 2, 0);
    }

    public void updatePos2(){
        groundPos2.add(ground.getWidth() * 2, 0);
    }

    public Texture getTexture() {
        return ground;
    }

    public Vector2 getGroundPos1() {
        return groundPos1;
    }

    public Vector2 getGroundPos2() {
        return groundPos2;
    }

    public void dispose(){
    }
}
