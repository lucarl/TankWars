package com.mygdx.game.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.ctrl.Controller;

public class StartScreen implements Screen {

    private static final int START_BUTTON_WIDTH = 313;
    private static final int START_BUTTON_HEIGHT = 161;

    private Stage stage;
    private SpriteBatch batch;
    private Viewport viewport;
    private Controller controller;

    private Texture start_button;

    public StartScreen(Controller controller) {
        this.controller = controller;

    }


    @Override
    public void show() {
        viewport = new ScreenViewport();
        stage = new Stage(viewport);
        batch = new SpriteBatch();

        start_button = new Texture("start_game.png");

        // Take input from ui
        Gdx.input.setInputProcessor(stage);


    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        batch.draw(start_button, Gdx.graphics.getWidth() / 2 - START_BUTTON_WIDTH / 2,
                Gdx.graphics.getHeight() / 2 - START_BUTTON_HEIGHT / 2,
                START_BUTTON_WIDTH,
                START_BUTTON_HEIGHT);

        handleButton();
        batch.end();

        stage.act(delta);
        stage.draw();

    }

    private void handleButton() {
        int x = Gdx.graphics.getWidth() / 2 - START_BUTTON_WIDTH / 2;
        // TODO gör dessa booleans mer förståliga
        boolean xRange = Gdx.input.getX() > x && Gdx.input.getX() < x + START_BUTTON_WIDTH;
        boolean yRange = Gdx.graphics.getHeight() - Gdx.input.getY()
                < Gdx.graphics.getHeight() / 2 - START_BUTTON_HEIGHT / 2 + START_BUTTON_HEIGHT
                && Gdx.graphics.getHeight() - Gdx.input.getY() > Gdx.graphics.getHeight() / 2 - START_BUTTON_HEIGHT / 2;

        // Click on start button
        if (xRange && yRange && Gdx.input.justTouched()
                || Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
            controller.setBaseScreen();
        }
    }

    @Override
    public void resize(int width, int height) {
        viewport.setScreenSize(width, height);
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
        stage.dispose();
    }
}

