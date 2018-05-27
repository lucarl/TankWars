package com.mygdx.game.view;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.model.*;
import com.mygdx.game.services.Assets;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Can draw objects who implement the IDrawable interface
 *
 * @author Adam Kjäll
 */


public class Renderer {
    private Map<IDrawable, Sprite> sprites;

    private SpriteBatch batch;

    public Renderer(SpriteBatch batch) {
        this.batch = batch;
        sprites = new HashMap<>();
    }

    /**
     * For each object update it's corresponding sprite with the objects state
     */
    public void render() {
        sprites.forEach((obj, sprite) -> {
            if (obj.isAlive()) {
                sprite.setRotation(obj.getAngle());
                sprite.setPosition(obj.getPos().getX(), obj.getPos().getY());
                sprite.draw(batch);
            }
        });
    }

    /**
     * For each object load its image and set it according to the objects state
     * @param objects Object has to implement IDrawable
     */
    public void loadResources(List<IDrawable> objects) {
        objects.forEach(obj -> {
            Texture texture = Assets.manager.get(obj.getImgSrc(), Texture.class);
            Sprite sprite = new Sprite(texture);
            sprite.setOrigin(obj.getOriginX(), obj.getOriginY());
            // Sets position of sprite and its width and height
            sprite.setBounds(obj.getPos().getX(), obj.getPos().getY(),
                    obj.getWidth(), obj.getHeight());
            sprites.put(obj, sprite);
        });
    }

    public void dispose() {
        batch.dispose();
    }
}
