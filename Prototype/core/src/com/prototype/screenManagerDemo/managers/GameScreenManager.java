package com.prototype.screenManagerDemo.managers;

import com.badlogic.gdx.Gdx;
import com.prototype.screenManagerDemo.Application;
import com.prototype.screenManagerDemo.helpers.STATE;
import com.prototype.screenManagerDemo.screens.GameScreen;
import com.prototype.screenManagerDemo.screens.MainMenu;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class GameScreenManager {
    private final Application app;


    private Stack<GameScreen> screens;

    public GameScreenManager(Application app) {
        this.app = app;
        //this.screens = new Stack<>();
        setScreen(STATE.PLAY);
    }

    private void initGameScreen() {
        //this.gameScreens = new HashMap<STATE, AbstractScreen>();
        //this.gameScreens.put(STATE.PLAY, new GameScreen(app));
    }


    private GameScreen getScreen(STATE state) {
        switch (state) {
            case PLAY:
                break;
            case MAIN_MENU: return new MainMenu(this);
            case SETTINGS:
                break;
        }
        return null;
    }


    public Application getApp() {
        return app;
    }

    public void update(float delta) {
        screens.peek().update(delta);
    }

    public void render() {
        screens.peek().render(Gdx.graphics.getDeltaTime());
    }

    public void resize() {
    }

    public void setScreen(STATE nextScreen) {
        screens.pop().dispose();
        screens.push(getScreen(nextScreen));
    }

    public void dispose() {
        for (GameScreen screen : screens) {
            if (screen != null) {
                screen.dispose();
            }
            screens.clear();
        }
    }


}
