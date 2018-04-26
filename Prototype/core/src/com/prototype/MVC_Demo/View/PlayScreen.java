package com.prototype.MVC_Demo.View;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.prototype.MVC_Demo.Controller.Controller;
import com.prototype.MVC_Demo.Model.FBird;

public class PlayScreen implements Screen {
    private Stage stage;
    private SpriteBatch batch;
    private Viewport viewport;
    private Controller controller;

    private FBird bird;
    private BirdAnimation birdAnimation;
    private Sprite bg;


    public PlayScreen(Controller controller) {
        this.controller = controller;
    }

    @Override
    public void show() {
        viewport = new ScreenViewport();
        stage = new Stage(viewport);
        batch = new SpriteBatch();

        // Background
        bg = new Sprite(new Texture("bg.png"));
        bg.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        // Model
        bird = new FBird(Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight());
        birdAnimation = new BirdAnimation();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        bg.draw(batch);
        batch.draw(birdAnimation.getBirdAnimation().getFrame(), bird.getPos().x, bird.getPos().y);


        batch.end();

        stage.act(delta);
        stage.draw();

        birdAnimation.getBirdAnimation().update(delta);
        update(delta);
        handleInput();

    }

    private void update(float delta){
        bird.update(delta);
        if(bird.getPos().y < 0){
            controller.setScreen(new PlayScreen(controller));
        }
    }

    protected void handleInput() {
        if (Gdx.input.justTouched()) {
            bird.jump();
        }
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
