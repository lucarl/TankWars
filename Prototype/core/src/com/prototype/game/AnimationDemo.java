package com.prototype.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.List;
import com.sun.glass.ui.SystemClipboard;

import java.util.ArrayList;

public class AnimationDemo extends ApplicationAdapter {
    SpriteBatch batch;
    Texture img;
    TextureRegion[] animationFrames;
    Animation animation;
    float elaspedTime;

    @Override
    public void create(){
        batch = new SpriteBatch();
        img = new Texture("birdanimation.png");

        TextureRegion[][] tmpFrames = TextureRegion.split(img,34,8);

        animationFrames = new TextureRegion[3];

        int index = 0;

        for(int r = 0; r < 1; r++) {
            for (int c = 0; c < 3; c++) {
                animationFrames[index++] = tmpFrames[c][r];
            }
        }
        animation = new Animation(1f/3f, animationFrames);
    }

    @Override
    public void render(){
        elaspedTime = Gdx.graphics.getDeltaTime();
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        batch.draw((TextureRegion) animation.getKeyFrame(elaspedTime,true), Gdx.graphics.getWidth()/2 - img.getWidth()/6
                , Gdx.graphics.getHeight()/2 - img.getHeight()/2);
        batch.end();
    }

    @Override
    public void dispose () {
        batch.dispose();
        img.dispose();
    }
}
