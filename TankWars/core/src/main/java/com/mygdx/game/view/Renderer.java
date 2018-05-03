package com.mygdx.game.view;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.model.IDrawable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Renderer {

    private Map<IDrawable, Sprite> sprites;
    private SpriteBatch batch;

    public Renderer(SpriteBatch batch) {
        this.batch = batch;
        sprites = new HashMap<>();
    }

    public void render(List<IDrawable> objects) {
        // New hashMap because we only want to draw stuff thats currently in objects
        sprites = new HashMap<>();
        // Load every objects sprite and put it in the hashmap
        loadResources(objects);
        // For each object update it's corresponding sprite with the objects state
        sprites.forEach((obj, sprite) -> {
            sprite.setRotation(obj.getAngle());
            sprite.setPosition(obj.getPos().getX(), obj.getPos().getY());

            if (obj.isVisible()) {
                sprite.draw(batch);
            }
        });
        System.out.println("Sprites: " + sprites.size());
    }

    private void loadResources(List<IDrawable> objects) {
        // For each obj in tankWars, load its image and set it according to the objects state
        objects.forEach(obj -> {

            // If objects is not visible
            Sprite sprite = new Sprite(new Texture(obj.getImgSrc()));
            sprites.put(obj, sprite);
            sprite.setOrigin(obj.getOriginX(), obj.getOriginY());
            // Sets position of sprite and its width and height
            sprite.setBounds(obj.getPos().getX(), obj.getPos().getY(),
                    obj.getWidth(), obj.getHeight());

        });

    }
}
