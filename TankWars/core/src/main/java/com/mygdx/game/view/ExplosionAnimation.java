package com.mygdx.game.view;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.model.Assets;

public class ExplosionAnimation {

    private Texture texture;
    private Animation animation;
    private int frameCount = 3;
    private float cycleTime = 0.5f;

    public ExplosionAnimation(){
        texture = Assets.manager.get("explosion.png");
        animation = new Animation(new TextureRegion(texture), frameCount, cycleTime);
    }

    public Animation getAnimation(){
        return animation;
    }
}
