package com.mygdx.game.view;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.model.*;
import com.mygdx.game.services.Assets;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Renderer {
    private Map<IDrawable, Sprite> sprites;
    private List<IDrawable> objects;

    private SpriteBatch batch;

    public Renderer(SpriteBatch batch) {
        this.batch = batch;
        sprites = new HashMap<>();
        objects = new ArrayList<>();
    }

    public void render() {
        // For each object updateObjects it's corresponding sprite with the objects state
        objects.forEach(obj -> {
            if (obj.isAlive()) {
                Sprite sprite = sprites.get(obj);
                sprite.setRotation(obj.getAngle());
                sprite.setPosition(obj.getPos().getX(), obj.getPos().getY());
                sprite.draw(batch);
            }
        });
    }

    public void loadResources(List<IDrawable> objects) {
        this.objects.addAll(objects);
        // For each obj in tankWars, load its image and set it according to the objects state
        objects.forEach(obj -> {
            if(obj != null) {
                Texture texture = Assets.manager.get(obj.getImgSrc(), Texture.class);
                Sprite sprite = new Sprite(texture);
                sprite.setOrigin(obj.getOriginX(), obj.getOriginY());
                // Sets position of sprite and its width and height
                sprite.setBounds(obj.getPos().getX(), obj.getPos().getY(),
                        obj.getWidth(), obj.getHeight());
                sprites.put(obj, sprite);
            }
        });



    }
}
