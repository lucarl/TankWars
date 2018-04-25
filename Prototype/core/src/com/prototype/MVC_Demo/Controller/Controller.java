package com.prototype.MVC_Demo.Controller;

import com.badlogic.gdx.Game;
import com.prototype.MVC_Demo.View.MenuScreen;

public class Controller extends Game {





    @Override
    public void create() {

        this.setScreen(new MenuScreen(this));

    }

    @Override
    public void dispose() {
        super.dispose();
    }



    @Override
    public void render() {

        super.render();
    }
}
