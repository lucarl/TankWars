package main.java.com.mygdx.game.view;

import com.badlogic.gdx.Game;
import com.prototype.game.SplashScreen2;

public class MenuDemo extends Game {

    SplashScreen2 splashScreen2;

    @Override
    public void create(){
        splashScreen2 = new SplashScreen2();
        setScreen(splashScreen2);
    }


}
