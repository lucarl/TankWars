package com.prototype.MVC_Demo.View;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.prototype.MVC_Demo.Controller.Controller;

public class MenuScreen implements Screen {

    private Stage stage;
    private SpriteBatch batch;
    private Viewport viewport;
    private Controller controller;

    private Texture playButtonTexture;
    private Texture exitButtonTexture;
    private Sprite bg;

    private ImageButton playButton;
    private ImageButton exitButton;



    public MenuScreen(Controller controller) {
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

        // Button setup
        playButtonTexture = new Texture("playbtn.png");
        exitButtonTexture = new Texture("exitbtn.png");
        createButtons();


        // Add button to stage to render and take input
        stage.addActor(playButton);
        stage.addActor(exitButton);


        // Take input from ui
        Gdx.input.setInputProcessor(stage);


    }

    private void createButtons(){
        Drawable drawable = new TextureRegionDrawable(new TextureRegion(playButtonTexture));
        playButton = new ImageButton(drawable);

        playButton.setPosition(Gdx.graphics.getWidth() / 2 - playButton.getWidth()/2,
                               Gdx.graphics.getHeight() / 2);

        playButton.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                controller.setScreen(new PlayScreen(controller));
                return super.touchDown(event, x, y, pointer, button);
            }
        });

        drawable = new TextureRegionDrawable(new TextureRegion(exitButtonTexture));
        exitButton = new ImageButton(drawable);
        exitButton.setWidth(200);
        exitButton.setHeight(100);
        exitButton.setPosition(Gdx.graphics.getWidth()/2 - exitButton.getWidth() / 2,
                               Gdx.graphics.getHeight() / 2 - 200);

        exitButton.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                Gdx.app.exit();
                return super.touchDown(event, x, y, pointer, button);
            }
        });
    }



    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        bg.draw(batch);

        batch.end();

        stage.act(delta);
        stage.draw();

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
