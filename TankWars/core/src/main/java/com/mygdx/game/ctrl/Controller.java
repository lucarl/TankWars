package com.mygdx.game.ctrl;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.mygdx.game.model.TankWars;
import com.mygdx.game.view.BaseScreen;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class Controller extends Game {

    private BaseScreen baseScreen;
    private TankWars tankWars;

    public Controller(BaseScreen baseScreen, TankWars tankWars) {
        this.baseScreen = baseScreen;
        this.tankWars = tankWars;

        this.baseScreen.addKeyboardListener(new KeyboardListener());
    }

    public void create() {
        
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
