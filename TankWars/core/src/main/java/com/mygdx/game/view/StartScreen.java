package com.mygdx.game.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.ctrl.Controller;

public class StartScreen implements Screen {

    //Constants for the next button
    private static final int NEXT_BUTTON_WIDTH = 200;
    private static final int NEXT_BUTTON_HEIGHT = 75;
    private static final int NEXT_BUTTON_Y = 75;
    private static final int NEXT_BUTTON_X = 400;


    private static final int ARROW_BUTTON_X = 450;

    private Stage stage;
    private SpriteBatch batch;
    private Viewport viewport;
    private Controller controller;

    private Texture imgRight;
    private Texture imgLeft;


    private ArrowButton arrowButtonRight1;
    private ArrowButton arrowButtonRight2;
    private ArrowButton arrowButtonRight3;

    private ArrowButton arrowButtonLeft1;
    private ArrowButton arrowButtonLeft2;
    private ArrowButton arrowButtonLeft3;

    private Texture nextButton;


    public StartScreen(Controller controller) {
        this.controller = controller;

    }


    @Override
    public void show() {
        viewport = new ScreenViewport();
        stage = new Stage(viewport);
        batch = new SpriteBatch();

        imgRight = new Texture("rightarrow.png");
        imgLeft = new Texture("leftarrow.png");

        //Create left arrow buttons
        arrowButtonLeft1 = new ArrowButton(imgLeft,Controller.GAME_WIDTH/3, (int)(Controller.GAME_HEIGHT * 0.78),
          imgLeft.getWidth()/15,imgLeft.getHeight()/15);

        /*
        arrowButtonLeft2 = new ArrowButton(imgLeft,Controller.GAME_WIDTH/3, (int)(Controller.GAME_HEIGHT * 0.58),
                imgLeft.getWidth()/15,imgLeft.getHeight()/15);

        arrowButtonLeft3 = new ArrowButton(imgLeft,Controller.GAME_WIDTH/3, (int)(Controller.GAME_HEIGHT * 0.38),
                imgLeft.getWidth()/15,imgLeft.getHeight()/15);

                */

        //Create right arrow buttons

       arrowButtonRight1 = new ArrowButton(imgRight,Controller.GAME_WIDTH/9, (int)(Controller.GAME_HEIGHT * 0.78),
                imgLeft.getWidth()/15,imgLeft.getHeight()/15);
             /*
        arrowButtonRight2 = new ArrowButton(imgRight,Controller.GAME_WIDTH/6, (int)(Controller.GAME_HEIGHT * 0.58),
                imgLeft.getWidth()/15,imgLeft.getHeight()/15);

        arrowButtonRight3 = new ArrowButton(imgRight,Controller.GAME_WIDTH/6, (int)(Controller.GAME_HEIGHT * 0.38),
                imgLeft.getWidth()/15,imgLeft.getHeight()/15);

                */

        //Create next-button
       nextButton = new Texture("next.png");

        // Take input from ui
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();

        //Draw left arrow buttons
        arrowButtonLeft1.update(batch,Gdx.input.getX(), Gdx.input.getY());

       // arrowButtonLeft2.update(batch, Gdx.input.getX(), Gdx.input.getY());

       // arrowButtonLeft3.update(batch, Gdx.input.getX(), Gdx.input.getY());

        //Draw right arrow buttons
        arrowButtonRight1.update(batch, Gdx.input.getX(), Gdx.input.getY());

      //  arrowButtonRight2.update(batch, Gdx.input.getX(), Gdx.input.getY());

       // arrowButtonRight3.update(batch, Gdx.input.getX(), Gdx.input.getY());

        //Draw next button
        batch.draw(nextButton,NEXT_BUTTON_X, NEXT_BUTTON_Y ,
                NEXT_BUTTON_WIDTH,
                NEXT_BUTTON_HEIGHT);





       //Gdx.graphics.getWidth() / 2 - NEXT_BUTTON_WIDTH / 2,
        //                Gdx.graphics.getHeight() / 2 - NEXT_BUTTON_HEIGHT / 2

        handleNextButton();

        batch.end();

        stage.act(delta);
        stage.draw();

    }

    private void handleNextButton() {

        boolean xRange = false;
        boolean yRange = false;

        int x = Gdx.graphics.getWidth()/2 - NEXT_BUTTON_WIDTH/2;
        int y = Gdx.graphics.getHeight()/2  - NEXT_BUTTON_HEIGHT / 2 + NEXT_BUTTON_HEIGHT;

        if(Gdx.input.getX() > x &&  Gdx.input.getX() < x + NEXT_BUTTON_WIDTH ){
            xRange = true;
        }

        if(Gdx.graphics.getHeight() - Gdx.input.getY() < y && Gdx.graphics.getHeight()
                - Gdx.input.getY() > Gdx.graphics.getHeight() / 2 - NEXT_BUTTON_HEIGHT / 2) {
            yRange = true;
        }

        if (xRange && yRange && Gdx.input.justTouched()
                || Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
                controller.setPlayScreen();
        }
    }


    @Override
    public void resize(int width, int height) {
        viewport.setScreenSize(width, height);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

        stage.dispose();
        nextButton.dispose();
    }
}


