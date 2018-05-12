package com.mygdx.game.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.ctrl.Controller;

public class StartScreen implements Screen {

    //Constants for the next button
    private static final int NEXT_BUTTON_WIDTH = 20;
    private static final int NEXT_BUTTON_HEIGHT = 5;
    private static final int ARROW_BUTTON_SIDE = 40;


    private static final int NEXT_BUTTON_Y = 75;
    private static final int NEXT_BUTTON_X = 400;


    private static final int ARROW_BUTTON_X = 450;

    private Stage stage;
    private SpriteBatch batch;
    private Viewport viewport;
    private Controller controller;

    private Texture imgRight;
    private Texture imgLeft;


    /*private ArrowButton arrowButtonRight1;
    private ArrowButton arrowButtonRight2;
    private ArrowButton arrowButtonRight3;

    private ArrowButton arrowButtonLeft1;
    private ArrowButton arrowButtonLeft2;
    private ArrowButton arrowButtonLeft3;*/

    private TextButton arrowButtonRight1;
    private TextButton arrowButtonRight2;
    private TextButton arrowButtonRight3;

    private TextButton arrowButtonLeft1;
    private TextButton arrowButtonLeft2;
    private TextButton arrowButtonLeft3;

    private TextButton nextButton;

    private BitmapFont font;
    private TextureAtlas atlas;

    private Skin skin;
    private Table table;

    public StartScreen(Controller controller) {
        this.controller = controller;

    }

    @Override
    public void show() {
        viewport = new ScreenViewport();
        stage = new Stage(viewport);
        batch = new SpriteBatch();

        atlas = new TextureAtlas(Gdx.files.internal("button-pack.atlas"));
        skin = new Skin(atlas);
        // skin = new Skin(Gdx.files.internal("uiskin.json"));

        table = new Table(skin);
        table.setBounds(0,0,controller.GAME_WIDTH,controller.GAME_HEIGHT);


        TextButton.TextButtonStyle bigTextButtonStyle = new TextButton.TextButtonStyle();
        bigTextButtonStyle.font = new BitmapFont(Gdx.files.internal("myfont.fnt"));
        bigTextButtonStyle.up = skin.getDrawable("bigButton.up");
        bigTextButtonStyle.down = skin.getDrawable("bigButton.down");
        //bigTextButtonStyle.pressedOffsetX = 1;
        //bigTextButtonStyle.pressedOffsetY = -1;
        nextButton = new TextButton("NEXT", bigTextButtonStyle);
        nextButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                controller.setPlayScreen();
            }
        });

        TextButton.TextButtonStyle smallTextButtonStyle = new TextButton.TextButtonStyle();
        smallTextButtonStyle.font = new BitmapFont(Gdx.files.internal("myfont.fnt"));
        smallTextButtonStyle.up = skin.getDrawable("smallButton.up");
        smallTextButtonStyle.down = skin.getDrawable("smallButton.down");
        //smallTextButtonStyle.pressedOffsetX = 1;
        //smallTextButtonStyle.pressedOffsetY = -1;

        arrowButtonLeft1 = new TextButton("<", smallTextButtonStyle);
        arrowButtonRight1 = new TextButton(">", smallTextButtonStyle);

        table.add(nextButton);
        table.row();
        table.add(arrowButtonLeft1);
        table.add(arrowButtonRight1);

        stage.addActor(table);

        // Take input from ui
        Gdx.input.setInputProcessor(stage);

        //table.setWidth(controller.GAME_WIDTH);
        //table.setHeight(controller.GAME_HEIGHT);

        //nextButton = new TextButton("NEXT", skin);
        //arrowButtonLeft1 = new TextButton("<", skin);
        //arrowButtonRight1 = new TextButton(">", skin);

        //table.add(arrowButtonLeft1).width(ARROW_BUTTON_SIDE).height(ARROW_BUTTON_SIDE);
        //table.add(arrowButtonRight1).width(ARROW_BUTTON_SIDE).height(ARROW_BUTTON_SIDE);
        //table.row();
        //table.add(nextButton).width(NEXT_BUTTON_WIDTH).height(NEXT_BUTTON_HEIGHT).padBottom((controller.GAME_HEIGHT/8));

        /*tableArrow = new Table();
        tableArrow.setWidth(controller.GAME_WIDTH);
        tableArrow.align(Align.center|Align.top);

        arrowButtonLeft1 = new TextButton("<", skin);
        arrowButtonRight1 = new TextButton(">", skin);
        tableArrow.add(arrowButtonRight1).width(ARROW_BUTTON_SIDE).height(ARROW_BUTTON_SIDE).top();
        tableArrow.padLeft(50);
        tableArrow.add(arrowButtonLeft1).width(ARROW_BUTTON_SIDE).height(ARROW_BUTTON_SIDE).top();
        tableArrow.padRight(50);*/

        /*
        imgRight = new Texture("rightarrow.png");
        imgLeft = new Texture("leftarrow.png");

        //Create left arrow buttons
        arrowButtonLeft1 = new ArrowButton(imgLeft,333, (int)(Controller.GAME_HEIGHT * 0.78),
          imgLeft.getWidth()/15,imgLeft.getHeight()/15);


        arrowButtonLeft2 = new ArrowButton(imgLeft,333, (int)(Controller.GAME_HEIGHT * 0.58),
                imgLeft.getWidth()/15,imgLeft.getHeight()/15);

        arrowButtonLeft3 = new ArrowButton(imgLeft,333, (int)(Controller.GAME_HEIGHT * 0.38),
                imgLeft.getWidth()/15,imgLeft.getHeight()/15);



        //Create right arrow buttons

       arrowButtonRight1 = new ArrowButton(imgRight,Controller.GAME_WIDTH/9, (int)(Controller.GAME_HEIGHT * 0.78),
                imgLeft.getWidth()/15,imgLeft.getHeight()/15);

        arrowButtonRight2 = new ArrowButton(imgRight,Controller.GAME_WIDTH/6, (int)(Controller.GAME_HEIGHT * 0.58),
                imgLeft.getWidth()/15,imgLeft.getHeight()/15);

        arrowButtonRight3 = new ArrowButton(imgRight,Controller.GAME_WIDTH/6, (int)(Controller.GAME_HEIGHT * 0.38),
                imgLeft.getWidth()/15,imgLeft.getHeight()/15);*/


    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();

        //Draw left arrow buttons
        //arrowButtonLeft1.update(batch,Gdx.input.getX(), Gdx.input.getY());

        //Draw next button
       //batch.draw(nextButton, NEXT_BUTTON_X, NEXT_BUTTON_Y ,NEXT_BUTTON_WIDTH, NEXT_BUTTON_HEIGHT);
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
        //table.dispose();
    }
}