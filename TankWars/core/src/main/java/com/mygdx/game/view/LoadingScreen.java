package com.mygdx.game.view;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import com.mygdx.game.Application;
import com.mygdx.game.services.Assets;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

/**

 * The class that display our loading screen. While
 * the loading image is drawn out the assets are being
 * loaded as well.
 *
 * @author Thomas Jinton
 * Revised by: Thomas Jinton, Adam Kjäll
 */

public class LoadingScreen implements Screen {

    private final Application app;
    private float progress;

    private ShapeRenderer shapeRenderer;

    public LoadingScreen(Application app) {
        this.app = app;
        this.shapeRenderer = new ShapeRenderer();
    }


    /**
     * Loading the assets.
     */
    @Override
    public void show() {
        progress = 0f;
        Assets.loadAssets();
    }

    /**
     * Setting the next screen.
     */
    private void update() {
        progress = Assets.manager.getProgress();
        if(Assets.manager.update() && progress >= 1f){
            app.setSplashScreen();
        }
    }

    /**
     * The loading bar is being rendered so that the
     * shape-animation can happen.
     *
     * @param delta update
     */
    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1f, 1f, 1f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        update();
        app.batch.begin();

        // Draw a black bar
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(Color.BLACK);
        shapeRenderer.rect(32, Application.GAME_HEIGHT / 2 - 8, Application.GAME_WIDTH - 64, 16);

        // Draw a blue bar over the black with the width scaled to the current progress
        shapeRenderer.setColor(Color.BLUE);
        shapeRenderer.rect(32, Application.GAME_HEIGHT / 2 - 8, progress * (Application.GAME_WIDTH - 64), 16);
        shapeRenderer.end();

        app.batch.end();
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
