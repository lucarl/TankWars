package com.mygdx.game.view;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.model.Position;
import com.mygdx.game.services.Assets;


public class Explosion {
    private static final float FRAME_LENGHT = 0.2f;
    private static final int SIZE = 134;
    private static final int OFFSET = SIZE/3;

    private Position pos;
    private float statetime;
    private boolean remove = false;

    private Animation<TextureRegion> animation = null;

    public Explosion(Position pos) {
        this.pos = new Position(pos.getX(), pos.getY());
        statetime = 0;

        if(animation == null) {
            Texture texture = Assets.manager.get("explosions3.png");
            animation = new Animation<TextureRegion>(FRAME_LENGHT, TextureRegion.split(texture, SIZE, SIZE)[0]);
        }
    }

    public void update(float delta) {
        statetime += delta;
        if(animation.isAnimationFinished(statetime)){
            remove = true;
        }
    }

    public void render(SpriteBatch batch) {
        batch.draw(animation.getKeyFrame(statetime), pos.getX()-OFFSET, pos.getY()-OFFSET);
    }

    public boolean isRemove() {
        return remove;
    }
}