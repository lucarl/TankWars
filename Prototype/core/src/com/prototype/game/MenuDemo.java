package com.prototype.game;

import com.badlogic.gdx.Game;
import com.prototype.game.SplashScreen2;
import com.prototype.managers.Assets;

public class MenuDemo extends Game {

    SplashScreen2 splashScreen2;
    SplashScreen splashScreen;
    public Assets AssetsManager = new Assets();

    @Override
    public void create(){
        //splashScreen2 = new SplashScreen2();
        //setScreen(splashScreen2);
        splashScreen2 = new SplashScreen2();
        setScreen(splashScreen2);
    }


}
