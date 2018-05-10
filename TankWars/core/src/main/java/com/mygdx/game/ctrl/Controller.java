package com.mygdx.game.ctrl;

import com.badlogic.gdx.*;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.model.Assets;
import com.mygdx.game.model.TankWars;
import com.mygdx.game.view.PlayScreen;
import com.mygdx.game.view.StartScreen;


import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class Controller extends Game implements InputProcessor {
    public static final int GAME_WIDTH = 1000;
    public static final int GAME_HEIGHT = 600;
    private Screen screen;
    private TankWars tankWars;
    public SpriteBatch batch;
    private AssetManager manager;
    private Assets assets;



    public Controller(TankWars tankWars) {
        this.tankWars = tankWars;
    }

    public void create() {
        batch = new SpriteBatch();
        screen = new StartScreen(this);

        manager = new AssetManager();
        assets = new Assets();
        //starts loading assets
        assets.loadAssets();
        //Continues when done loading.
        //it won't continue until all assets are finished loading.
        assets.manager.finishLoading();

        setScreen(screen);
    }

    public void setPlayScreen() {
        screen.dispose();
        screen = new PlayScreen(this, tankWars);
        setScreen(screen);
    }

    @Override
    public void dispose() {
        assets.disposeAssets();
    }

    @Override
    public void render() {
        super.render();
        float delta = Gdx.graphics.getDeltaTime();
        tankWars.gameLoop(delta);
    }



    public boolean keyDown(int keycode) {

        if (keycode == Input.Keys.LEFT) {
            tankWars.getPlayer().getTank().setLeftMove(true);
        }

        if (keycode == Input.Keys.RIGHT) {
            tankWars.getPlayer().getTank().setRightMove(true);
        }

        if (keycode == Input.Keys.UP) {
            tankWars.getPlayer().getTank().getGun().setLeftAim(true);
        }

        if (keycode == Input.Keys.DOWN) {
            tankWars.getPlayer().getTank().getGun().setRightAim(true);
        }

        if (keycode == Input.Keys.SPACE) {
            tankWars.fire();

        }

        if (keycode == Input.Keys.A) {
            tankWars.getPlayer().getTank().getGun().increasePower();
        }
        if (keycode == Input.Keys.Z) {
            tankWars.getPlayer().getTank().getGun().decreasePower();
        }

        if (keycode == Input.Keys.X) {
            tankWars.getPlayer().getTank().getGun().changeWeapon();
        }

        if (keycode == Input.Keys.N) {
            tankWars.nextPlayer();
        }


        return true;
    }

    public boolean keyUp(int keycode) {

        if (keycode == Input.Keys.LEFT) {
            tankWars.getPlayer().getTank().setLeftMove(false);
        }
        if (keycode == Input.Keys.RIGHT) {
            tankWars.getPlayer().getTank().setRightMove(false);
        }

        if (keycode == Input.Keys.UP) {
            tankWars.getPlayer().getTank().getGun().setLeftAim(false);
        }

        if (keycode == Input.Keys.DOWN) {
            tankWars.getPlayer().getTank().getGun().setRightAim(false);
        }

        return true;
    }

    public boolean keyTyped(char character) {
        return false;
    }

    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    public boolean scrolled(int amount) {
        return false;
    }

    public class KeyboardListener implements KeyListener {

        public void keyTyped(KeyEvent e) {

        }

        public void keyPressed(KeyEvent e) {

        }

        public void keyReleased(KeyEvent e) {

        }


    }




}
