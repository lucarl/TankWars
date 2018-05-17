package com.mygdx.game.view;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.mygdx.game.Application;

import com.mygdx.game.ctrl.PlayController;
import com.mygdx.game.services.Assets;

import com.mygdx.game.model.TankWars;
import com.mygdx.game.services.Renderer;
import com.mygdx.game.utils.Hud;

public class PlayScreen implements Screen {
    private Sprite background;
    private TankWars tankWars;
    private Application app;
    private PlayController controller;
    private Hud hud;
    private Renderer renderer;

    public PlayScreen(Application app) {
        this.app = app;
        // TODO tankWars parametrar borde finnas i en setup fil?
        tankWars = new TankWars(OptionsScreen.NUMBER_OF_PLAYERS, OptionsScreen.NUMBER_OF_ROUNDS, OptionsScreen.DIFFICULTY);
        controller = new PlayController(tankWars);
        renderer = new Renderer(app.batch);
        hud = new Hud(app.batch, tankWars);

        Texture texture = Assets.manager.get("background.jpg");
        background = new Sprite(texture);
    }

    public void show() {
        renderer.loadResources(tankWars.getTiles());
        renderer.loadResources(tankWars.getObjects());
        background.setSize(Application.GAME_WIDTH, Application.GAME_HEIGHT);
        Gdx.input.setInputProcessor(controller);
    }

    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        tankWars.gameLoop(delta);

        renderer.loadResources(tankWars.getShots());

        app.batch.begin();
        background.draw(app.batch);
        renderer.render(tankWars.getTiles());
        renderer.render(tankWars.getObjects());
        renderer.render(tankWars.getShots());

        app.batch.end();

        hud.update();
        hud.stage.draw();
        hud.stage.act();
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

        hud.dispose();

    }
}
