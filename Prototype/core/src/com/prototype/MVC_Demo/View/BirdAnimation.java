package com.prototype.MVC_Demo.View;

        import com.badlogic.gdx.graphics.Texture;
        import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class BirdAnimation {

    private Texture texture;
    private Animation birdAnimation;

    public BirdAnimation() {
        texture = new Texture("birdanimation.png");
        birdAnimation = new Animation(new TextureRegion(texture), 3, 0.5f);
    }

    public Animation getBirdAnimation() {
        return birdAnimation;
    }
}
