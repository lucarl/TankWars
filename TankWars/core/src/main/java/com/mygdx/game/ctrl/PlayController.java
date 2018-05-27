package com.mygdx.game.ctrl;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.mygdx.game.model.Tank;
import com.mygdx.game.model.TankWars;

/**
 * Controller which listens to the playScreen in the view package.
 * When the uses presses a key the controller will listen to the
 * given input.
 *
 * @author Adam Kjäll, Thomas Jinton
 * Revised by: Adam Kjäll
 *
 */
public class PlayController implements InputProcessor {

    private TankWars tankWars;

    public PlayController(TankWars tankWars) {
        this.tankWars = tankWars;
    }


    @Override
    public boolean keyDown(int keycode) {
        Tank tank = tankWars.getPlayer().getTank();
        if (!tankWars.isTurnOver() || !tankWars.isShooting()) {
            if (keycode == Input.Keys.LEFT) {
                tank.setLeftMove(true);
            }

            if (keycode == Input.Keys.RIGHT) {
                tank.setRightMove(true);
            }

            if (keycode == Input.Keys.UP) {
                tank.getGun().setLeftAim(true);
            }

            if (keycode == Input.Keys.DOWN) {
                tank.getGun().setRightAim(true);
            }

            if (keycode == Input.Keys.SPACE) {
                tankWars.fire();
            }

            if (keycode == Input.Keys.A) {
                tank.getGun().increasePower();
            }

            if (keycode == Input.Keys.Z) {
                tank.getGun().decreasePower();
            }

            if (keycode == Input.Keys.NUM_1) {
                tank.getGun().changeStandard();
            }

            if (keycode == Input.Keys.NUM_2) {
                tank.getGun().changeNuke();
            }

            if (keycode == Input.Keys.NUM_3) {
                tank.getGun().changeMissile();
            }
        } else { // Make sure you can't move the tank when turn is over
            tank.setLeftMove(false);
            tank.setRightMove(false);
            tank.getGun().setLeftAim(false);
            tank.getGun().setRightAim(false);
        }

        // FOR TESTING
        if (keycode == Input.Keys.N) {
           tankWars.nextPlayer();
        }


        return true;
    }

    @Override
    public boolean keyUp(int keycode) {
        Tank tank = tankWars.getPlayer().getTank();
        if (keycode == Input.Keys.LEFT) {
            tank.setLeftMove(false);
        }
        if (keycode == Input.Keys.RIGHT) {
            tank.setRightMove(false);
        }

        if (keycode == Input.Keys.UP) {
            tank.getGun().setLeftAim(false);
        }

        if (keycode == Input.Keys.DOWN) {
            tank.getGun().setRightAim(false);
        }

        return true;
    }


    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
