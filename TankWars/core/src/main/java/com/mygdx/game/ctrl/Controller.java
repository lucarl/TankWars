package com.mygdx.game.ctrl;

import com.badlogic.gdx.*;
import com.mygdx.game.model.Shot;
import com.mygdx.game.model.TankWars;
import com.mygdx.game.view.PlayScreen;
import com.mygdx.game.view.PlayScreenTest;
import com.mygdx.game.view.StartScreen;


import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class Controller extends Game implements InputProcessor {

    private Screen screen;
    private TankWars tankWars;

    public Controller(TankWars tankWars) {
        this.tankWars = tankWars;
    }

    @Override
    public void create() {
        setStartScreen();
    }

    public void setStartScreen() {
        screen = new StartScreen(this);
        setScreen(screen);
    }

    public void setPlayScreen() {
        screen = new PlayScreen(this, tankWars);
        setScreen(screen);
    }

    @Override
    public void render() {
        super.render();
        float delta = Gdx.graphics.getDeltaTime();
        tankWars.update(delta);

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
            tankWars.fire(20);
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


}
