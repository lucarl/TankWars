package com.mygdx.game.ctrl;

import com.badlogic.gdx.ApplicationAdapter;
import com.mygdx.game.model.TankWars;
import com.mygdx.game.view.BaseScreen;

import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class Controller extends ApplicationAdapter {

    private BaseScreen baseScreen;
    private TankWars tankWars;

    public Controller(BaseScreen baseScreen, TankWars tankWars) {
        this.baseScreen = baseScreen;
        this.tankWars = tankWars;

        this.baseScreen.f.addKeyListener(new KeyboardListener());
    }

    class KeyboardListener implements KeyListener {

        public void keyTyped(KeyEvent e) {

        }

        public void keyPressed(KeyEvent e) {
            System.out.print("key pressed");
        }

        public void keyReleased(KeyEvent e) {

        }
    }

}
