
package com.prototype.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;


/**
 * This class will display the temporary gun image
 * and make it rotate.
 */
public class SplashScreen implements Screen {

    private Texture texture;
    //private Image splashImage = new Image(texture);
    private Stage stage = new Stage();
    private Sprite sprite;
    private SpriteBatch batch;
    int i =0;

    public SplashScreen(){

    }

    /**
     * Temporary sprites that assist in creating the rotation.
     */
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
