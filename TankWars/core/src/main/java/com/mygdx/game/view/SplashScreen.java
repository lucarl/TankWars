package com.mygdx.game.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.mygdx.game.Application;
import com.mygdx.game.model.Assets;


public class SplashScreen implements Screen {

    private Application app;

    private Stage stage;
    private Image splashImage;

    public SplashScreen(Application app) {
        this.app = app;
        stage = new Stage();
    }

    @Override
    public void show() {
        Runnable transitionRunnable = new Runnable() {
            @Override
            public void run() {
                app.setStartScreen();
            }
        };

        Texture texture = Assets.manager.get("tanks.jpg", Texture.class);
        splashImage = new Image(texture);
        splashImage.setBounds(0, 0, Application.GAME_WIDTH, Application.GAME_HEIGHT);
        splashImage.addAction(Actions.sequence(Actions.alpha(0), Actions.fadeIn(4.0f),Actions.delay(1,
                Actions.run(transitionRunnable))));

        stage.addActor(splashImage);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        update(delta);

        stage.draw();
    }

    private void update(float delta) {
        stage.act(delta);
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
        stage.dispose();
    }
}
