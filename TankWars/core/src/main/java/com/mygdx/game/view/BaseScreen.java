package com.mygdx.game.view;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.game.ctrl.Controller;
import com.mygdx.game.model.TankWars;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class BaseScreen extends ApplicationAdapter {
        SpriteBatch batch;
        Texture img;
        Sprite sprite;
        protected TankWars tankWars;
        protected Controller controller;
        protected Stage ui;
        InputListener ip = new InputListener();
        public JFrame f = new JFrame();


        public BaseScreen(Controller controller, TankWars tankWars) {
            this.controller = controller;
            this.tankWars = tankWars;

        }

        @Override
        public void create() {
            batch = new SpriteBatch();
            img = new Texture("TankWars.png");
            sprite = new Sprite(img);
            sprite.setPosition(Gdx.graphics.getWidth()/2 - sprite.getWidth()/2,
                    Gdx.graphics.getHeight()/2 - sprite.getHeight()/2);
        }




        public void addKeyboardListener(KeyEvent listenForKeyEvent) {
            f.addKeyListener((KeyListener) listenForKeyEvent);
        }


        public void render() {
            if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
                sprite.translateX(1f);
            }
            if(Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
                sprite.translateX(-1f);
            }
            Gdx.gl.glClearColor(0, 0, 0, 1);
            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
            batch.begin();
            batch.draw(sprite, sprite.getX(), sprite.getY());
            batch.end();
        }

        @Override
        public void dispose() {
            img.dispose();
        }







}
