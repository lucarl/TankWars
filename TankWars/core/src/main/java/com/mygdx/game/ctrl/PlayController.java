package com.mygdx.game.ctrl;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.mygdx.game.model.TankWars;

public class PlayController implements InputProcessor {

    private TankWars tankWars;

    public PlayController(TankWars tankWars) {
        this.tankWars = tankWars;
    }


    @Override
    public boolean keyDown(int keycode) {
        if (!tankWars.isTurnOver()) {
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

            if (keycode == Input.Keys.NUM_1) {
                tankWars.getPlayer().getTank().getGun().changeStandard();
            }

            if (keycode == Input.Keys.NUM_2) {
                tankWars.getPlayer().getTank().getGun().changeNuke();
            }

            if (keycode == Input.Keys.NUM_3) {
                tankWars.getPlayer().getTank().getGun().changeMissile();
            }
        }

        // FOR TESTING
        if (keycode == Input.Keys.N) {
           tankWars.nextPlayer();
        }


        return true;
    }

    @Override
    public boolean keyUp(int keycode) {
        if (keycode == Input.Keys.LEFT) {
            tankWars.getPlayer().getTank().setLeftMove(false);
            //tankWars.getPlayer().getTank().setSoundMoveLeft(false);
        }
        if (keycode == Input.Keys.RIGHT) {
            tankWars.getPlayer().getTank().setRightMove(false);
            //tankWars.getPlayer().getTank().setSoundMoveRight(false);
        }

        if (keycode == Input.Keys.UP) {
            tankWars.getPlayer().getTank().getGun().setLeftAim(false);
            //tankWars.getPlayer().getTank().getGun().setSoundAim(false,false);
        }

        if (keycode == Input.Keys.DOWN) {
            tankWars.getPlayer().getTank().getGun().setRightAim(false);
            //tankWars.getPlayer().getTank().getGun().setSoundAim(false,false);
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
