package com.mygdx.game.view;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.game.Application;

import com.mygdx.game.ctrl.PlayController;
import com.mygdx.game.model.Assets;
import com.mygdx.game.model.Difficulty;

import com.mygdx.game.model.TankWars;

public class PlayScreen implements Screen {
    private Sprite background;
    private TankWars tankWars;
    private Application app;
    private PlayController controller;
    private Hud hud;
    private Renderer renderer;
    private Skin skin;
    private TextButton menuButton;

    public PlayScreen(Application app) {
        this.app = app;
        // TODO tankWars parametrar borde finnas i en setup fil?
        tankWars = new TankWars(OptionsScreen.NUMBER_OF_PLAYERS, OptionsScreen.NUMBER_OF_ROUNDS, OptionsScreen.DIFFICULTY);
        controller = new PlayController(tankWars);
        renderer = new Renderer(app.batch);
        hud = new Hud(app.batch, tankWars);
        skin = new Skin(Gdx.files.internal("uiskin.json"));

        Texture texture = Assets.manager.get("background.jpg");
        background = new Sprite(texture);
    }

    public void show() {
        renderer.loadResources(tankWars.getTiles());
        renderer.loadResources(tankWars.getObjects());
        background.setSize(Application.GAME_WIDTH, Application.GAME_HEIGHT);
        Gdx.input.setInputProcessor(controller);


        //return to menu button
        menuButton = new TextButton("Return to Menu",skin);
        menuButton.getLabel().setFontScale(0.9f);
        menuButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                app.setMenuScreen();
            }
        });

        hud.stage.addActor(menuButton);
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
