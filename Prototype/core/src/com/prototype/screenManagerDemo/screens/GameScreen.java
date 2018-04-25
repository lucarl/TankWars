package com.prototype.screenManagerDemo.screens;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.prototype.screenManagerDemo.Application;
import com.prototype.screenManagerDemo.managers.GameScreenManager;

public abstract class GameScreen {

    protected GameScreenManager gsm;
    protected Application app;
    protected SpriteBatch batch;
    protected OrthographicCamera cam;

    protected GameScreen(GameScreenManager gsm) {
        this.gsm = gsm;
        this.app = gsm.getApp();
        batch = app.getSpriteBatch();
        cam = app.getCamera();
    }


    public abstract void update(float dt);


    public abstract void render(float dt);


    public abstract void resize(int width, int height);


    public abstract void dispose();
}
