package com.prototype.screenManagerDemo.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;

import com.prototype.screenManagerDemo.Application;
import com.prototype.screenManagerDemo.managers.GameScreenManager;

public class MainMenu extends GameScreen {

    private Application app;

    private Texture bg;

    public MainMenu(GameScreenManager gsm) {
        super(gsm);
        this.app = app;
        bg = new Texture("bg.png");
    }



    @Override
    public void update(float dt) {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        app.getSpriteBatch().begin();
        app.getSpriteBatch().draw(bg, 0, 0);
        app.getSpriteBatch().end();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void dispose() {

    }
}
