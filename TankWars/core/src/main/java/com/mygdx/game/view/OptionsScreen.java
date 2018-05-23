package com.mygdx.game.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.Application;
import com.mygdx.game.services.Assets;
import com.mygdx.game.model.Difficulty;
import com.mygdx.game.events.Event;
import com.mygdx.game.events.IEventHandler;
import com.mygdx.game.view.PlaySounds;
import com.mygdx.game.events.EventBus;

public class OptionsScreen implements Screen, IEventHandler {

    public static int NUMBER_OF_PLAYERS = 2;
    public static int NUMBER_OF_ROUNDS = 3;
    public static Difficulty DIFFICULTY = Difficulty.EASY;

    //Constants for the next button
    private static final int NEXT_BUTTON_WIDTH = 20;
    private static final int NEXT_BUTTON_HEIGHT = 5;
    private static final int ARROW_BUTTON_SIDE = 40;


    private static final int NEXT_BUTTON_Y = 75;
    private static final int NEXT_BUTTON_X = 400;


    private static final int ARROW_BUTTON_X = 450;

    private Stage stage;
    private Viewport viewport;
    private Application app;

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
    private TextButton backButton;
    private TextButton muteButton;

    private Label optionsLabel;
    private Label roundsLabel;
    private Label playersLabel;
    private Label difficultyLabel;
    private Label nRoundsLabel;
    private Label nPlayersLabel;
    private Label nDiffLabel; // TODO kom på bättre namn

    private BitmapFont font;
    private TextureAtlas atlas;

    private Skin skin;
    private Table table;


    public OptionsScreen(Application app) {

        this.app = app;
        initEvent();

    }

    @Override
    public void show() {
        viewport = new ScreenViewport();
        stage = new Stage(viewport, app.batch);

        atlas = new TextureAtlas(Gdx.files.internal("button-pack.atlas"));
        skin = new Skin(atlas);
        //skin = new Skin(Gdx.files.internal("uiskin.json"));

        TextButton.TextButtonStyle bigTextButtonStyle = new TextButton.TextButtonStyle();
        BitmapFont font = Assets.manager.get("myfont.fnt");
        bigTextButtonStyle.font = font;
        bigTextButtonStyle.up = skin.getDrawable("bigButton.up");
        bigTextButtonStyle.down = skin.getDrawable("bigButton.down");
        //bigTextButtonStyle.pressedOffsetX = 1;
        //bigTextButtonStyle.pressedOffsetY = -1;

        //nextButton = new TextButton("NEXT", bigTextButtonStyle);

        TextButton.TextButtonStyle smallTextButtonStyle = new TextButton.TextButtonStyle();
        smallTextButtonStyle.font = font;
        smallTextButtonStyle.up = skin.getDrawable("smallButton.up");
        smallTextButtonStyle.down = skin.getDrawable("smallButton.down");
        //smallTextButtonStyle.pressedOffsetX = 1;
        //smallTextButtonStyle.pressedOffsetY = -1;

        nextButton = new TextButton("START GAME", bigTextButtonStyle);
        muteButton = new TextButton("MUTE SOUND", bigTextButtonStyle);
        backButton = new TextButton("BACK", smallTextButtonStyle);

        arrowButtonLeft1 = new TextButton("<", smallTextButtonStyle);
        arrowButtonRight1 = new TextButton(">", smallTextButtonStyle);
        arrowButtonLeft2 = new TextButton("<", smallTextButtonStyle);
        arrowButtonRight2 = new TextButton(">", smallTextButtonStyle);
        arrowButtonLeft3 = new TextButton("<", smallTextButtonStyle);
        arrowButtonRight3 = new TextButton(">", smallTextButtonStyle);

        // OptionsScreen big header and sub headers for the options
        optionsLabel = new Label("Options",
                new Label.LabelStyle(font, Color.CYAN));
        roundsLabel = new Label("Number of rounds",
                new Label.LabelStyle(font, Color.WHITE));
        playersLabel = new Label("Number of players",
                new Label.LabelStyle(font, Color.WHITE));
        difficultyLabel = new Label("Difficulty",
                new Label.LabelStyle(font, Color.WHITE));

        // Labels that show the values of the current options selection
        nRoundsLabel = new Label(String.valueOf(NUMBER_OF_PLAYERS),
                new Label.LabelStyle(font, Color.CYAN));
        nPlayersLabel = new Label(String.valueOf(NUMBER_OF_PLAYERS),
                new Label.LabelStyle(font, Color.CYAN));
        nDiffLabel = new Label(DIFFICULTY.toString(),
                new Label.LabelStyle(font, Color.CYAN));

        // Makes a table and adds labels and buttons to it
        setupTable();

        // Only for debug table layout
        //table.setDebug(true);

        stage.addActor(table);

        //table.add(nextButton);
        //table.row();
        //table.add(arrowButtonLeft1);
        //table.add(arrowButtonRight1);

        // Take input from ui
        Gdx.input.setInputProcessor(stage);

        addButtonListeners();
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

    // Creates a 8x3 table with labels and buttons
    private void setupTable(){

        table = new Table(skin);
        table.setFillParent(true);

        table.top();
        table.padTop(20);

        table.row();
        table.add();
        optionsLabel.setFontScale(2.0f);
        table.add(optionsLabel);
        table.add();

        table.row();
        table.add();
        table.add(roundsLabel).padTop(20);
        table.add();

        table.row();
        table.add(arrowButtonLeft1).right();
        table.add(nRoundsLabel);
        table.add(arrowButtonRight1).left();

        table.row();
        table.add();
        table.add(playersLabel);
        table.add();

        table.row();
        table.add(arrowButtonLeft2).right();
        table.add(nPlayersLabel).padTop(20);
        table.add(arrowButtonRight2).left();

        table.row();
        table.add();
        table.add(difficultyLabel).padTop(20);
        table.add();

        table.row();
        table.add(arrowButtonLeft3).right();
        table.add(nDiffLabel);
        table.add(arrowButtonRight3).left();

        table.row();
        table.add(backButton).center().padRight(100);
        table.add(muteButton).center();
        table.add(nextButton).center().padLeft(100);
        table.add();
        table.row();

    }

    @Override
    public void onEvent(com.mygdx.game.events.Event evt) {

        if(evt.getTag() == Event.Tag.PLAY_SOUND_THEME){
            PlaySounds.stopTheme();
        }

    }

    private void initEvent() {
        EventBus.BUS.register(this);
    }


    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        app.batch.begin();

        //Draw left arrow buttons
        //arrowButtonLeft1.updateObjects(batch,Gdx.input.getX(), Gdx.input.getY());

        //Draw next button
        //batch.draw(nextButton, NEXT_BUTTON_X, NEXT_BUTTON_Y ,NEXT_BUTTON_WIDTH, NEXT_BUTTON_HEIGHT);
        //Gdx.graphics.getWidth() / 2 - NEXT_BUTTON_WIDTH / 2,
        //                Gdx.graphics.getHeight() / 2 - NEXT_BUTTON_HEIGHT / 2

        //handleNextButton();

        app.batch.end();

        stage.act(delta);
        stage.draw();

    }

    private void addButtonListeners() {
        // If next button is clicked change screen
        nextButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                app.setPlayScreen();
                PlaySounds.stopTheme();
            }
        });

        backButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                app.setMenuScreen();
            }
        });

        muteButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {

                PlaySounds.stopTheme();

            }
        });

        arrowButtonLeft1.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                // Loops through ints in the range [1,10] and updates the label and state
                NUMBER_OF_ROUNDS--;
                NUMBER_OF_ROUNDS = NUMBER_OF_ROUNDS < 2 ? 1 : NUMBER_OF_ROUNDS;
                nRoundsLabel.setText(String.valueOf(NUMBER_OF_ROUNDS));
            }
        });

        arrowButtonRight1.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                // Loops through ints in the range [1,10] and updates the label and state
                NUMBER_OF_ROUNDS++;
                NUMBER_OF_ROUNDS = NUMBER_OF_ROUNDS > 10 ? 10 : NUMBER_OF_ROUNDS;
                nRoundsLabel.setText(String.valueOf(NUMBER_OF_ROUNDS));
            }
        });

        arrowButtonLeft2.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                // Loops through ints in the range [2,5] and updates the label and state
                NUMBER_OF_PLAYERS--;
                NUMBER_OF_PLAYERS = NUMBER_OF_PLAYERS < 3 ? 2 : NUMBER_OF_PLAYERS;
                nPlayersLabel.setText(String.valueOf(NUMBER_OF_PLAYERS));
            }
        });

        arrowButtonRight2.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                // Loops through ints in the range [2,5] and updates the label and state
                NUMBER_OF_PLAYERS++;
                NUMBER_OF_PLAYERS = NUMBER_OF_PLAYERS > 5 ? 5 : NUMBER_OF_PLAYERS;
                nPlayersLabel.setText(String.valueOf(NUMBER_OF_PLAYERS));
            }
        });

        arrowButtonLeft3.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                // Left button decrease the difficulty
                DIFFICULTY = DIFFICULTY == Difficulty.HARD ? Difficulty.MEDIUM : Difficulty.EASY;
                nDiffLabel.setText(DIFFICULTY.toString());
            }
        });

        arrowButtonRight3.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                // Left button increase the difficulty
                DIFFICULTY = DIFFICULTY == Difficulty.EASY ? Difficulty.MEDIUM : Difficulty.HARD;
                nDiffLabel.setText(DIFFICULTY.toString());
            }
        });
    }

   /* private void handleNextButton() {

        boolean xRange = false;
        boolean yRange = false;

        int x = Gdx.graphics.getWidth() / 2 - NEXT_BUTTON_WIDTH / 2;
        int y = Gdx.graphics.getHeight() / 2 - NEXT_BUTTON_HEIGHT / 2 + NEXT_BUTTON_HEIGHT;

        if (Gdx.input.getX() > x && Gdx.input.getX() < x + NEXT_BUTTON_WIDTH) {
            xRange = true;
        }

        if (Gdx.graphics.getHeight() - Gdx.input.getY() < y && Gdx.graphics.getHeight()
                - Gdx.input.getY() > Gdx.graphics.getHeight() / 2 - NEXT_BUTTON_HEIGHT / 2) {
            yRange = true;
        }

        if (xRange && yRange && Gdx.input.justTouched()
                || Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
            app.setPlayScreen();
        }
    }*/


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
        skin.dispose();

    }
}