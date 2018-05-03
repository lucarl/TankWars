package com.mygdx.game.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.model.TankWars;
import com.sun.media.jfxmediaimpl.MediaDisposer;

/**
 * Created by Carl on 2018-05-02.
 */
public class Terrain implements Disposable {
    private TankWars tankWars;
    public Stage stage;
    private Viewport viewport;

    public Terrain(SpriteBatch batch, TankWars tankWars) {
        this.tankWars = tankWars;
        viewport = new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        stage = new Stage(viewport, batch);
    }

    public void update() {

    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}
