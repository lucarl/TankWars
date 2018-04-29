package com.mygdx.game.ctrl;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.model.Shot;
import com.mygdx.game.model.TankWars;
import com.mygdx.game.view.PlayScreen;
import com.mygdx.game.view.StartScreen;


import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class Controller extends Game implements InputProcessor {

    private Screen screen;
    private TankWars tankWars;
    public SpriteBatch batch;

    public Controller(TankWars tankWars) {
        this.tankWars = tankWars;
    }

    public void create() {
        batch = new SpriteBatch();
        screen = new StartScreen(this);
        setScreen(screen);
    }

    public void setBaseScreen() {
        screen.dispose();
        screen = new PlayScreen(this, tankWars);
        setScreen(screen);
    }

    @Override
    public void render() {
        super.render();
        tankWars.move(Gdx.graphics.getDeltaTime());
        tankWars.aim(Gdx.graphics.getDeltaTime());
        tankWars.getPlayer().getTank().getShot().updatePostion(Gdx.graphics.getDeltaTime());

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
            tankWars.getPlayer().getTank().setRightAim(true);
        }

        if (keycode == Input.Keys.SPACE) {
            tankWars.fire();
        }

        if(keycode == Input.Keys.A){
            tankWars.getPlayer().getTank().increasePower();
        }
        if(keycode == Input.Keys.Z){
            tankWars.getPlayer().getTank().decreasePower();
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
            tankWars.getPlayer().getTank().setLeftAim(false);
        }

        if (keycode == Input.Keys.DOWN) {
            tankWars.getPlayer().getTank().setRightAim(false);
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
