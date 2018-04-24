package com.mygdx.game.view;

import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.ctrl.Controller;
import com.mygdx.game.model.TankWars;

/**
 * Created by Carl on 2018-04-24.
 */
public class Menu extends BaseScreen{
    SpriteBatch batch;
    Texture img;
    Sprite sprite;

    public Menu(TankWars tankWars, Controller controller) {
        super(controller, tankWars);
    }


    final public void show() {


        // Map the controller
        InputMultiplexer input = new InputMultiplexer();
        input.addProcessor(ui);
        // Screen-specific initialization
    }






}
