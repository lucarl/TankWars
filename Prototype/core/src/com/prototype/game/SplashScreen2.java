package com.prototype.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.prototype.MVC_Demo.View.MenuScreen;

import javax.xml.soap.Text;


public class SplashScreen2 implements Screen{


    private Texture texture = new Texture(Gdx.files.internal("tanks.jpg"));
    private Texture loadingBackground;
    private Texture loadingBar;
    private Image splashImage = new Image(texture);
    private Stage stage = new Stage();
    AssetManager assetManager;
    SpriteBatch batch;
    //new Texture(Gdx.files.internal("loadbg.png"));
    //new Texture(Gdx.files.internal("loadbar.png"));

    public SplashScreen2(){

    }

    private void loadAssets(){

        assetManager.load("loadbg.png",Texture.class);
        assetManager.load("loadbar.png",Texture.class);
        assetManager.finishLoading();

    }

    /**
     * Actions for the countdown to look more unique and cool.
     */
    @Override
    public void show(){

        batch = new SpriteBatch();
        assetManager = new AssetManager();
        this.loadAssets();
        loadingBackground = assetManager.get("loadbg.png", Texture.class);
        loadingBar = assetManager.get("loadbar.png", Texture.class);

        stage.addActor(splashImage);
        splashImage.addAction(Actions.sequence(Actions.alpha(0), Actions.fadeIn(4.0f),Actions.delay(1),Actions.run(new Runnable() {
            @Override
            public void run() {
                ((Game)Gdx.app.getApplicationListener()).setScreen(new MenuScreen(null));
            }
        })));

    }

    @Override
    public void render(float delta){
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act();
        stage.draw();
        batch.begin();
        batch.draw(loadingBackground,0,0);
        batch.draw(loadingBar,0,0);
        batch.end();
    }

    @Override
    public void resize(int width, int height){

    }
    @Override
    public void pause(){

    }
    @Override
    public void resume(){

    }
    @Override
    public void hide(){

    }
    @Override
    public void dispose(){
        texture.dispose();
        batch.dispose();
    }

}
