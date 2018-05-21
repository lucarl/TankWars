package com.mygdx.game.events;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.services.Assets;
import com.mygdx.game.utils.Animation;

/**
 * Created by adamk on maj, 2018
 */
public class AnimationEvent {
    private String msg;
    private Animation animation;

    public AnimationEvent(String msg) {
        this.msg = msg;
    }

    public void makeAnimatrion(){
        switch (msg) {
            case "explosion":
                explosionAnimation();
                break;
        }
    }

    public void explosionAnimation(){
        Texture texture = Assets.manager.get("explosion.png");
        animation = new Animation(new TextureRegion(texture), 3, 0.5f);

    }

    public Animation getAnimation() {
        return animation;
    }
}
