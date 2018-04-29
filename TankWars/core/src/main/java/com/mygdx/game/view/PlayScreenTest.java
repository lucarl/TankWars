package com.mygdx.game.view;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.game.ctrl.Controller;
import com.mygdx.game.model.Object;
import com.mygdx.game.model.TankWars;

import java.util.HashMap;
import java.util.Map;

public class PlayScreenTest implements Screen {

    private SpriteBatch batch;

    private Map<Object, Sprite> sprites;

    private TankWars tankWars;
    private Controller controller;
    private Stage stage = new Stage();


    public PlayScreenTest(Controller controller, TankWars tankWars) {
        this.controller = controller;
        this.tankWars = tankWars;
        sprites = new HashMap<>();

    }

    public void show() {

        batch = new SpriteBatch();

        // Load image resources for every object
        tankWars.getObjects().forEach(obj -> {
            Sprite sprite = new Sprite(new Texture(obj.getImgSrc()));
            sprites.put(obj, sprite);
            sprites.get(obj).setSize(obj.getWidth(), obj.getHeight());
            sprites.get(obj).setPosition(obj.getPos().getX(), obj.getPos().getY());
        });
        
        Gdx.input.setInputProcessor(controller);
    }

    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();

        // For each obj make sure the sprite is up to date with the object
        sprites.forEach((obj, sprite) -> {
            sprite.setPosition(obj.getPos().getX(), obj.getPos().getY());
            sprite.setSize(obj.getWidth(), obj.getHeight());

            if(obj.canRotate()){
                sprite.setOrigin(sprite.getWidth()/2, 0);
                sprite.setRotation(obj.getAngle());
            }

            sprite.draw(batch);
        });

        batch.end();

        stage.act(delta);
        stage.draw();


    }

    public void resize(int width, int height) {

    }

    public void pause() {

    }

    public void resume() {

    }

    public void hide() {

    }

    public void dispose() {

    }
}
