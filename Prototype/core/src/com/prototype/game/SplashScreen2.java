package com.prototype.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
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
    TextureRegion loadingBarStart;
    TextureRegion loadingBarBody;
    TextureRegion loadingBarEnd;
    //new Texture(Gdx.files.internal("loadbg.png"));
    //new Texture(Gdx.files.internal("loadbar.png"));


    public SplashScreen2(){

    }

    private void loadAssets(){

        assetManager.load("loadbg.png",Texture.class);
        assetManager.load("loadbar.png",Texture.class);
        assetManager.finishLoading();

        //Assets Manager loading
        assetManager.load("assetMan1.jpeg",Texture.class);
        assetManager.load("assetMan2.jpeg",Texture.class);
        assetManager.load("assetMan6.jpeg",Texture.class);
        assetManager.load("assetMan7.jpeg",Texture.class);
        assetManager.load("assetMan11.jpeg",Texture.class);
        assetManager.load("assetMan12.jpeg",Texture.class);
        assetManager.load("assetMan13.jpeg",Texture.class);
        assetManager.load("assetMan14.jpeg",Texture.class);
        assetManager.load("assetMan3.jpg",Texture.class);
        assetManager.load("assetMan4.jpg",Texture.class);
        assetManager.load("assetMan5.jpg",Texture.class);
        assetManager.load("assetMan8.jpg",Texture.class);
        assetManager.load("assetMan9.jpg",Texture.class);
        assetManager.load("assetMan10.jpg",Texture.class);
        assetManager.load("assetMan15.jpg",Texture.class);
        assetManager.load("assetMan16.jpg",Texture.class);
        assetManager.load("assetMan17.jpg",Texture.class);
        assetManager.load("assetMan18.jpg",Texture.class);
        assetManager.load("assetMan19.jpg",Texture.class);
        assetManager.load("assetMan20.jpg",Texture.class);


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
        loadingBarStart = new TextureRegion(loadingBar,0,0,20,loadingBar.getHeight());
        loadingBarBody = new TextureRegion(loadingBar,20,0,449,loadingBar.getHeight());
        loadingBarEnd = new TextureRegion(loadingBar,20+449,0,20,loadingBar.getHeight());


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
        assetManager.update();

        batch.draw(loadingBackground,20,20,200,50);
        //batch.draw(loadingBar,20,20);
        batch.draw(loadingBarStart,0,0);
        batch.draw(loadingBarBody,0+loadingBarStart.getRegionWidth(),0,loadingBarBody.getRegionWidth()*assetManager.getProgress(),loadingBarBody.getRegionHeight());
        batch.draw(loadingBarEnd,0+loadingBarStart.getRegionWidth()+loadingBarBody.getRegionWidth()*assetManager.getProgress(),0);
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
        assetManager.dispose();
    }

}
