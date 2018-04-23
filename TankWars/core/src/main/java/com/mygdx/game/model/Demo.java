package com.mygdx.game.model;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;


public class Demo extends ApplicationAdapter {
    Stage stage;

    @Override
    public void create() {
        ScreenViewport viewport = new ScreenViewport();
        stage = new Stage(viewport);
        Gdx.input.setInputProcessor(stage);

        MyActor actor = new MyActor();
        stage.addActor(actor);
        stage.setKeyboardFocus(actor);
    }

    @Override
    public void render() {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
    }

    public int add(int a, int b) {
        int c = a + b;
        return c;
    }

    @Override
    public void dispose() {
        stage.dispose();
    }


}
