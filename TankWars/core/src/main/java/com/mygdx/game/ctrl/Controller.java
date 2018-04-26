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

    public void create() {
        this.baseScreen = new BaseScreen(this, tankWars);
        this.setScreen(baseScreen);
    }

    public boolean keyDown(int keycode) {
        if (keycode == Input.Keys.LEFT) {
            //tankWars.getPlayer().getTank().moveTank(1, true, Gdx.graphics.getDeltaTime());
        }
        return false;
    }

    public boolean keyUp(int keycode) {
        return false;
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
