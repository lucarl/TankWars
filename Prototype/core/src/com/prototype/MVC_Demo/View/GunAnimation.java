package com.prototype.MVC_Demo.View;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * View for the TankGun
 */
public class GunAnimation {

    private Texture texture;
    private Animation gunAnimation;

    public GunAnimation() {
        texture = new Texture("testgun.jpg");
        gunAnimation = new Animation(new TextureRegion(texture), 3, 0.5f);
    }

    public Animation getGunAnimation() {
        return gunAnimation;
    }
}
