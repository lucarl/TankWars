package com.mygdx.game.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.Application;
import com.mygdx.game.model.Difficulty;



public class OptionsScreen implements Screen {

    //Constants for the next button
    private static final int NEXT_BUTTON_WIDTH = 20;
    private static final int NEXT_BUTTON_HEIGHT = 5;

    //private static final int ARROW_BUTTON_SIDE = 40;
   // private static final int NEXT_BUTTON_Y = 75;
    //private static final int NEXT_BUTTON_X = 400;
    //private static final int ARROW_BUTTON_X = 450;

    private Stage stage;
    private Viewport viewport;
    private Application app;


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

    private TextField textFieldRounds;
    private TextField textFieldPlayers;
    private TextField textFieldDifficulty;

    private Label optionsLabel;
    private Label roundsLabel;
    private Label playersLabel;
    private Label difficultyLabel;

    private BitmapFont font;
    private TextureAtlas atlas;

    private Skin skin;
    private Skin textFieldSkin;
    private Table table;

    private int defaultRounds = 3;
    private int defaultPlayers = 2;
    private Difficulty defaultEasy = Difficulty.EASY;


    public OptionsScreen(Application app) {
        this.app = app;
    }

    @Override
    public void show() {
        viewport = new ScreenViewport();
        stage = new Stage(viewport, app.batch);

        atlas = new TextureAtlas(Gdx.files.internal("button-pack.atlas"));
        skin = new Skin(atlas);
        textFieldSkin = new Skin(Gdx.files.internal("uiskin.json"));


        //skin = new Skin(Gdx.files.internal("uiskin.json"));

        TextButton.TextButtonStyle bigTextButtonStyle = new TextButton.TextButtonStyle();
        bigTextButtonStyle.font = new BitmapFont(Gdx.files.internal("myfont.fnt"));
        bigTextButtonStyle.up = skin.getDrawable("bigButton.up");
        bigTextButtonStyle.down = skin.getDrawable("bigButton.down");
        bigTextButtonStyle.pressedOffsetX = 1;
        bigTextButtonStyle.pressedOffsetY = -1;
        nextButton = new TextButton("NEXT", bigTextButtonStyle);
        nextButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                app.setPlayScreen();
            }
        });

        TextButton.TextButtonStyle smallTextButtonStyle = new TextButton.TextButtonStyle();
        smallTextButtonStyle.font = new BitmapFont(Gdx.files.internal("myfont.fnt"));
        smallTextButtonStyle.up = skin.getDrawable("smallButton.up");
        smallTextButtonStyle.down = skin.getDrawable("smallButton.down");
        smallTextButtonStyle.pressedOffsetX = 1;
        smallTextButtonStyle.pressedOffsetY = -1;

        arrowButtonLeft1 = new TextButton("<", smallTextButtonStyle);
        arrowButtonRight1 = new TextButton(">", smallTextButtonStyle);
        arrowButtonLeft2 = new TextButton("<", smallTextButtonStyle);
        arrowButtonRight2 = new TextButton(">", smallTextButtonStyle);
        arrowButtonLeft3 = new TextButton("<", smallTextButtonStyle);
        arrowButtonRight3 = new TextButton(">", smallTextButtonStyle);

        BitmapFont font = new BitmapFont(Gdx.files.internal("myfont.fnt"));

        optionsLabel = new Label("Options",
                new Label.LabelStyle(font, Color.CYAN));
        roundsLabel = new Label("Number of rounds",
                new Label.LabelStyle(font, Color.WHITE));
        playersLabel = new Label("Number of players",
                new Label.LabelStyle(font, Color.WHITE));
        difficultyLabel = new Label("Difficulty",
                new Label.LabelStyle(font, Color.WHITE));

        //textfields

        //TextField.TextFieldStyle textFieldStyle = new TextField.TextFieldStyle();
        //textFieldStyle.fontColor = Color.WHITE;
        //textFieldStyle.font = textFieldSkin.getFont("myfont.fnt");

        textFieldRounds = new TextField(Integer.toString(defaultRounds), textFieldSkin);
        textFieldPlayers = new TextField(Integer.toString(defaultPlayers), textFieldSkin);
        textFieldDifficulty = new TextField(defaultEasy.toString(), textFieldSkin);

        textFieldRounds.setDisabled(true);
        textFieldPlayers.setDisabled(true);
        textFieldDifficulty.setDisabled(true);

        arrowButtonLeft1.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                defaultRounds--;
                textFieldRounds.setText(Integer.toString(defaultRounds));
            }
        });

        arrowButtonRight1.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                    defaultRounds++;
                    textFieldRounds.setText(Integer.toString(defaultRounds));
            }
        });

        arrowButtonLeft2.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                defaultPlayers--;
                textFieldPlayers.setText(Integer.toString(defaultPlayers));
            }
        });

        arrowButtonRight2.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                defaultPlayers++;
                textFieldPlayers.setText(Integer.toString(defaultPlayers));
            }
        });

        arrowButtonLeft3.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
               for(int i = Difficulty.values().length - 1 ; i >= 0; i--) {
                   textFieldDifficulty.setText(Difficulty.values()[i].toString());
               }
            }
        });

        arrowButtonRight3.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
               for(int i = 1; i <= Difficulty.values().length - 1; i++){
                   textFieldDifficulty.setText(Difficulty.values()[i].toString());
               }

               /* for(Difficulty difficulty : Difficulty.values()){
                    textFieldDifficulty.setText(difficulty.toString());
                }
                */
            }
        });




        //table setup

        table = new Table(skin);
        table.setFillParent(true);

        table.top();
        table.padTop(30);

        table.row().height(125);
        table.add().width(100);
        optionsLabel.setFontScale(2.0f);
        table.add(optionsLabel).width(optionsLabel.getPrefWidth());
        table.add().width(100);

        table.row();
        table.add();
        table.add(roundsLabel);
        table.add();

        table.row().height(75);
        table.add(arrowButtonLeft1).right().width(arrowButtonLeft1.getPrefWidth());
        //table.add().width(50);
        table.add(textFieldRounds).width(optionsLabel.getPrefWidth()); //ska vara lika bred som label...
        table.add(arrowButtonRight1).left();

        table.row();
        table.add();
        table.add(playersLabel);
        table.add();

        table.row().height(75);
        table.add(arrowButtonLeft2).right();
        //table.add().width(50);
        table.add(textFieldPlayers).width(optionsLabel.getPrefWidth()); //ska vara lika bred som label...
        table.add(arrowButtonRight2).left();

        table.row();
        table.add();
        table.add(difficultyLabel);
        table.add();

        table.row().height(75);
        table.add(arrowButtonLeft3).right();
        //table.add().width(50);
        table.add(textFieldDifficulty).width(optionsLabel.getPrefWidth()); //ska vara lika bred som label...
        table.add(arrowButtonRight3).left();

        table.row().height(75);
        table.add();
        table.add(nextButton).center();
        table.add();

        table.setDebug(true);

        stage.addActor(table);

        //table.add(nextButton);
        //table.row();
        //table.add(arrowButtonLeft1);
        //table.add(arrowButtonRight1);

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

        app.batch.begin();

        //Draw left arrow buttons
        //arrowButtonLeft1.update(batch,Gdx.input.getX(), Gdx.input.getY());

        //Draw next button
       //batch.draw(nextButton, NEXT_BUTTON_X, NEXT_BUTTON_Y ,NEXT_BUTTON_WIDTH, NEXT_BUTTON_HEIGHT);
       //Gdx.graphics.getWidth() / 2 - NEXT_BUTTON_WIDTH / 2,
        //                Gdx.graphics.getHeight() / 2 - NEXT_BUTTON_HEIGHT / 2

        handleNextButton();

        app.batch.end();

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
                app.setPlayScreen();
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