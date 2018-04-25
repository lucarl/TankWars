package com.prototype.screenManagerDemo;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.prototype.screenManagerDemo.helpers.STATE;
import com.prototype.screenManagerDemo.managers.GameScreenManager;
import com.prototype.screenManagerDemo.screens.MainMenu;

public class Application extends ApplicationAdapter {

    private SpriteBatch batch;
    private GameScreenManager gsm;
    private OrthographicCamera cam;

    @Override
    public void create() {
        batch = new SpriteBatch();
        gsm = new GameScreenManager(this);

    }

    @Override
    public void render() {
        gsm.update(Gdx.graphics.getDeltaTime());
        gsm.render();
        if(Gdx.input.isKeyPressed(Input.Keys.ESCAPE)){
            Gdx.app.exit();
        }
    }

    public SpriteBatch getSpriteBatch() {
        return batch;
    }

    @Override
    public void dispose() {
        super.dispose();
        batch.dispose();
    }

    public OrthographicCamera getCamera(){
        return cam;
    }
}
