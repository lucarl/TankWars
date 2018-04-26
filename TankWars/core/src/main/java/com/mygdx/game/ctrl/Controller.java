package com.mygdx.game.ctrl;

import com.badlogic.gdx.*;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.mygdx.game.model.TankWars;
import com.mygdx.game.view.BaseScreen;


import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class Controller extends Game implements InputProcessor {

    private BaseScreen baseScreen;
    private TankWars tankWars;

    public Controller(TankWars tankWars) {

        this.tankWars = tankWars;

        //this.baseScreen.addKeyboardListener(new KeyboardListener());
    }

    @Override
    public void render() {
        super.render();
        tankWars.getPlayer().getTank().moveTank(Gdx.graphics.getDeltaTime());
        tankWars.getPlayer().getTank().aimTank(Gdx.graphics.getDeltaTime());
    }

    public void create() {
        this.baseScreen = new BaseScreen(this, tankWars);
        this.setScreen(baseScreen);
    }

    public boolean keyDown(int keycode) {
        if (keycode == Input.Keys.LEFT) {
            tankWars.getPlayer().getTank().setLeftMove(true);
        }

        if (keycode == Input.Keys.RIGHT) {
            tankWars.getPlayer().getTank().setRightMove(true);
        }

        if (keycode == Input.Keys.UP) {
            tankWars.getPlayer().getTank().setLeftAim(true);
        }

        if (keycode == Input.Keys.DOWN) {
            tankWars.getPlayer().getTank().setRightMove(true);
        }

        if (keycode == Input.Keys.SPACE) {

            tankWars.getPlayer().getTank().fireTank(100);
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
            tankWars.getPlayer().getTank().setRightMove(true);
        }

        if (keycode == Input.Keys.DOWN) {
            tankWars.getPlayer().getTank().setRightMove(true);
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
