package com.mygdx.game.view;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.game.Application;

import com.mygdx.game.ctrl.PlayController;
import com.mygdx.game.events.Event;
import com.mygdx.game.events.EventBus;
import com.mygdx.game.events.IEventHandler;
import com.mygdx.game.model.Tank;
import com.mygdx.game.model.factorys.TankWarsFactory;
import com.mygdx.game.services.Assets;

import com.mygdx.game.model.TankWars;
import com.mygdx.game.utils.Hud;

import java.util.ArrayList;
import com.mygdx.game.view.PlaySounds;
import com.mygdx.game.view.Explosion;
import java.util.List;

public class PlayScreen implements Screen, IEventHandler {
    private Sprite background;
    private TankWars tankWars;
    private Application app;
    private PlayController controller;
    private Hud hud;
    private Renderer renderer;
    private Skin skin;
    private Stage stage;
    private TextButton menuButton;
    private TankWarsFactory tankWarsFactory;

    private List<Explosion> explosions;

    public PlayScreen(Application app) {
        tankWarsFactory = new TankWarsFactory();
        this.app = app;
        tankWars = tankWarsFactory.makeTankWars();
        controller = new PlayController(tankWars);
        renderer = new Renderer(app.batch);
        hud = new Hud(app.batch, tankWars);
        stage = new Stage();

        skin = new Skin(Gdx.files.internal("uiskin.json"));

        Texture texture = Assets.manager.get("background.jpg");
        background = new Sprite(texture);

        explosions = new ArrayList<>();

        // Register to the eventBus
        initEvent();
    }

    public void show() {
        renderer.loadResources(tankWars.getTiles());
        renderer.loadResources(tankWars.getObjects());
        background.setSize(Application.GAME_WIDTH, Application.GAME_HEIGHT);
        InputMultiplexer im = new InputMultiplexer(stage, controller);
        Gdx.input.setInputProcessor(im);

        //return to menu button
        menuButton = new TextButton("Return to Menu", skin);
        menuButton.getLabel().setFontScale(0.9f);
        menuButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                app.setMenuScreen();
                PlaySounds.playThemeReturn();
                PlaySounds.stopFire();
                PlaySounds.stopMove();
                PlaySounds.stopAim();
                PlaySounds.stopExplosion();
                PlaySounds.stopNuke();
                PlaySounds.stopMissile();
                PlaySounds.stopTankDestroy();

            }
        });

        stage.addActor(menuButton);

    }


    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        tankWars.updateWorld(delta);
        updateExplosions(delta);

        renderer.loadResources(tankWars.getShots());

        app.batch.begin();
        background.draw(app.batch);
        renderer.render(tankWars.getTiles());
        renderer.render(tankWars.getObjects());
        renderer.render(tankWars.getShots());

        explosions.forEach(explosion -> {
            explosion.render(app.batch);
        });

        app.batch.end();

        hud.update();
        hud.stage.draw();
        stage.draw();
        stage.act();

        hud.stage.act();
    }

    @Override
    public void onEvent(Event evt) {
        if (evt.getTag() == Event.Tag.PLAY_SOUND_FIRE) {
            PlaySounds.playFire();
        } else if (evt.getTag() == Event.Tag.PLAY_SOUND_EXPLOSION) {
            //PlaySounds.playFire();
            PlaySounds.playExplosion();
        }
        else if (evt.getTag() == Event.Tag.PLAY_SOUND_AIM){
            PlaySounds.playAim();
        }
        else if(evt.getTag() == Event.Tag.PLAY_SOUND_MOVE){
            PlaySounds.playMove();
        }
        else if (evt.getTag() == Event.Tag.PLAY_ANIMATION_EXPLOSION || evt.getTag() == Event.Tag.PLAY_SOUND_ANIMATION_EXPLOSION) {
            Tank tank = (Tank) evt.getValue();
            explosions.add(new Explosion(tank.getPos()));
            PlaySounds.playTankDestroy();
        }

        else if(evt.getTag() == Event.Tag.PLAY_SOUND_NUKE){
            PlaySounds.playNuke();
        }
        else if(evt.getTag() == Event.Tag.PLAY_SOUND_MISSILE){
            PlaySounds.playMissile();
        }

    }

    private void initEvent() {
        EventBus.BUS.register(this);
    }

    private void updateExplosions(float delta){
        List<Explosion> explosionsToRemove = new ArrayList<>();
        for (Explosion explosion : explosions) {
            explosion.update(delta);
            if (explosion.isRemove()) {
                explosionsToRemove.add(explosion);
            }
        }
        explosions.removeAll(explosionsToRemove);
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
        stage.dispose();

    }

}
