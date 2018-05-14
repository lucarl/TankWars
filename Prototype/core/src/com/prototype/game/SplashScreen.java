package com.prototype.game;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.prototype.MVC_Demo.View.MenuScreen;
import com.prototype.managers.Assets;

/**
 * Tank Wars splashscreen used in the beginning of the game.
 * Loading bar shows the user the time left for the game menu
 * to start.
 */
public class SplashScreen implements Screen{

    //In the Assets class you have the picture and then you use manager.get in this class.

    //private Texture texture = new Texture(Gdx.files.internal("tanks.jpg"));
    //private Image splashImage = new Image(texture);
    private Stage stage = new Stage();
    private Texture texture;
    //private Image splashImage = new Image(texture);
    private Image splashImage = new Image();
    private MenuDemo game;

    public SplashScreen(){

    }

    @Override
    public void show(){
        game.AssetsManager.queueAddImage();
        game.AssetsManager.manager.finishLoading();
        texture = game.AssetsManager.manager.get("tanks.jpg",Texture.class);

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
    }

}


    /*
    @Override
    public void show(){



        texture = new Texture(Gdx.files.internal("testgun.jpg"));
        batch = new SpriteBatch();
        //stage.addActor(splashImage);
        sprite = new Sprite(texture);
        sprite.setPosition(500,320);
        sprite.setOrigin(0,0);
        sprite.setSize(300,50);


    }

    @Override
    public void render(float delta){
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        sprite.setRotation(i++);
        batch.begin();
        sprite.draw(batch);
        batch.end();

    }

    */