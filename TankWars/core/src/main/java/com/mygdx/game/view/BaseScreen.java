package com.mygdx.game.view;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.game.ctrl.Controller;
import com.mygdx.game.model.TankWars;

public abstract class BaseScreen extends ApplicationAdapter{
        SpriteBatch batch;
        Texture img;
        Sprite sprite;
        protected TankWars tankWars;
        protected Controller controller;
        protected Stage ui;
        Menu menu;


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

        public void render() {
            Gdx.
        }







}
