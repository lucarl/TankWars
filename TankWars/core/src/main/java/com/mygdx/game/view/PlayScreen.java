package com.mygdx.game.view;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.mygdx.game.Application;

import com.mygdx.game.ctrl.PlayController;
import com.mygdx.game.events.Event;
import com.mygdx.game.events.EventBus;
import com.mygdx.game.events.IEventHandler;
import com.mygdx.game.model.Tank;
import com.mygdx.game.model.factorys.TankWarsFactory;
import com.mygdx.game.services.Assets;

import com.mygdx.game.model.TankWars;
import java.util.ArrayList;
import java.util.List;
import com.mygdx.game.view.PlaySounds;


/**
 * View screen for the playing screen. All the
 * events and actions that are happening when
 * you play the game are shown here.
 *
 * @author Thomas Jinton
 * Revised by: Adam Kjäll, Thomas Jinton
 */
public class PlayScreen implements Screen, IEventHandler {
    private Sprite background;
    private TankWars tankWars;
    private Application app;
    private PlayController controller;
    private Hud hud;
    private Renderer renderer;
    private Skin skin;
    private Stage stage;

    //private TextButton scoreButton;
    private TextButton menuButton;
    private Table table;
    private TankWarsFactory tankWarsFactory;
    private List<Explosion> explosions;

    public PlayScreen(Application app) {
        tankWarsFactory = new TankWarsFactory();
        this.app = app;
        tankWars = tankWarsFactory.makeTankWars();
        controller = new PlayController(tankWars);
        renderer = new Renderer(app.batch);
        hud = new Hud(tankWars);
        stage = new Stage();

        skin = new Skin(Gdx.files.internal("uiskin.json"));
        Texture texture = Assets.manager.get("background.jpg");
        background = new Sprite(texture);
        explosions = new ArrayList<>();

        initEvent();
    }

    /**
     * @see MenuScreen
     */
    public void show() {
        renderer.loadResources(tankWars.getObjects());
        renderer.loadResources(tankWars.getTiles());
        renderer.loadResources(tankWars.getTanks());
        renderer.loadResources(tankWars.getGuns());

        background.setSize(Application.GAME_WIDTH, Application.GAME_HEIGHT);
        InputMultiplexer im = new InputMultiplexer(stage, controller);
        Gdx.input.setInputProcessor(im);
        //viewport = new FitViewport(Application.GAME_WIDTH, Application.GAME_HEIGHT);
        //stage = new Stage(viewport, app.batch);

        table = new Table(skin);
        table.setWidth(stage.getWidth());
        table.align(Align.center | Align.bottom);
        table.setPosition(0, Gdx.graphics.getHeight());


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

        /*
        scoreButton = new TextButton("Show Leaderboards", skin);
        scoreButton.getLabel().setFontScale(0.9f);
        scoreButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                app.setScoreScreen();
                PlaySounds.playThemeReturn();
            }
        });
        */

        table.add(menuButton);
        //table.add(scoreButton).padLeft(50);
        //stage.addActor(scoreButton);
        stage.addActor(menuButton);
        stage.addActor(table);

    }

    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        tankWars.updateWorld(delta);
        updateExplosions(delta);

        renderer.loadResources(tankWars.getShots());

        app.batch.begin();
        background.draw(app.batch);
        renderer.render();


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

    /**
     * Given the event a sound or animation
     * will happen in the game.
     * @param evt the event
     */
    @Override
    public void onEvent(Event evt) {
        if (evt.getTag() == Event.Tag.PLAY_SOUND_FIRE) {
            PlaySounds.playFire();
        } else if (evt.getTag() == Event.Tag.PLAY_SOUND_EXPLOSION) {
            PlaySounds.playExplosion();
        } else if (evt.getTag() == Event.Tag.PLAY_SOUND_AIM) {
            PlaySounds.playAim();
        } else if (evt.getTag() == Event.Tag.PLAY_SOUND_MOVE) {
            PlaySounds.playMove();
        } else if (evt.getTag() == Event.Tag.PLAY_SOUND_NUKE) {
            PlaySounds.playNuke();
        } else if (evt.getTag() == Event.Tag.PLAY_SOUND_MISSILE) {
            PlaySounds.playMissile();
        } else if (evt.getTag() == Event.Tag.PLAY_ANIMATION_EXPLOSION) {
            if(evt.getValue() instanceof Tank){
                Tank tank = (Tank) evt.getValue();
                explosions.add(new Explosion(tank.getPos()));
                PlaySounds.playTankDestroy();
            }
        } else if (evt.getTag() == Event.Tag.GAME_OVER){
            app.setScoreScreen();
            PlaySounds.stopExplosion();
            PlaySounds.playVictory();
        }

    }

    private void initEvent() {
        EventBus.BUS.register(this);
    }

    /**
     * Updates the explosions.
     * @param delta
     */
    private void updateExplosions(float delta) {
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
