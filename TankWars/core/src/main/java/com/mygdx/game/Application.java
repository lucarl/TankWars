package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.view.*;


public class Application extends Game{
    public static final String TITLE = "TANK WARS";
    public static final int GAME_WIDTH = 1000;
    public static final int GAME_HEIGHT = 625;


    private Screen screen;
    public SpriteBatch batch;

    @Override
    public void create() {
        batch = new SpriteBatch();
        screen = new LoadingScreen(this);
        setScreen(screen);
    }

    public void setPlayScreen() {
        screen.dispose();
        screen = new PlayScreen(this);
        setScreen(screen);
    }

    public void setMenuScreen() {
        screen.dispose();
        screen = new MenuScreen(this);
        setScreen(screen);
    }

    public void setOptionScreen() {
        screen.dispose();
        screen = new OptionsScreen(this);
        setScreen(screen);
    }

    public void setSplashScreen() {
        screen.dispose();
        screen = new SplashScreen(this);
        setScreen(screen);
    }
}

