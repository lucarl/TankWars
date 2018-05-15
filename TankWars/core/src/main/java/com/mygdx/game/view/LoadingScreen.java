package com.mygdx.game.view;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.mygdx.game.Application;
import com.mygdx.game.model.Assets;

public class LoadingScreen implements Screen {

    private final Application app;
    private float progress;

    private ShapeRenderer shapeRenderer;

    public LoadingScreen(Application app) {
        this.app = app;
        this.shapeRenderer = new ShapeRenderer();
    }


    @Override
    public void show() {
        progress = 0f;
        Assets.loadAssets();
    }

    private void update(float delta) {
        progress = Assets.manager.getProgress();
        if(Assets.manager.update() && progress >= 1f){
            app.setSplashScreen();
        }
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1f, 1f, 1f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        update(delta);

        app.batch.begin();

        // TODO fixa snyggare load bar
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(Color.BLACK);
        shapeRenderer.rect(32, Application.GAME_HEIGHT / 2 - 8, Application.GAME_WIDTH - 64, 16);

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