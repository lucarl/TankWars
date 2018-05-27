package com.mygdx.game.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.Application;
import com.mygdx.game.services.Assets;
import com.mygdx.game.model.Difficulty;
import com.mygdx.game.events.Event;
import com.mygdx.game.events.IEventHandler;
import com.mygdx.game.events.EventBus;

/**
 *
 * View class for the screen with the different
 * game options.
 *
 * @author Patricia Zabecka.
 * Revised by: Patricia Zabecka, Adam Kjäll, Thomas Jinton
 *
 */
public class OptionsScreen implements Screen, IEventHandler {

    public static int NUMBER_OF_PLAYERS = 2;
    public static int NUMBER_OF_ROUNDS = 3;
    public static Difficulty DIFFICULTY = Difficulty.EASY;

    private Stage stage;
    private Viewport viewport;
    private Application app;

    private TextButton arrowButtonRight1;
    private TextButton arrowButtonRight2;
    private TextButton arrowButtonRight3;

    private TextButton arrowButtonLeft1;
    private TextButton arrowButtonLeft2;
    private TextButton arrowButtonLeft3;

    private TextButton nextButton;
    private TextButton backButton;
    private TextButton muteButton;
    private TextButton getHelpButton;

    private Label optionsLabel;
    private Label roundsLabel;
    private Label playersLabel;
    private Label difficultyLabel;
    private Label nRoundsLabel;
    private Label nPlayersLabel;
    private Label nDiffLabel;

    private TextureAtlas atlas;
    private Skin skin;
    private Table table;

    private Sprite background;
    private Boolean clicked = false;


    public OptionsScreen(Application app) {
        this.app = app;

        //background setup
        Texture texture = Assets.manager.get("menuscreen.jpg");
        background = new Sprite(texture);
        background.setSize(Application.GAME_WIDTH, Application.GAME_HEIGHT);

        //EventBus.BUS.publish(new Event(Event.Tag.PLAY_SOUND_THEME, null));
        initEvent();
    }
    /**
     * The different screen properties are created here such as labels,
     * buttons and their style. Also the listeners for the buttons and the
     * table that the properties are fit according to are invoked in this
     * method.
     */
    @Override
    public void show() {
        viewport = new ScreenViewport();
        stage = new Stage(viewport, app.batch);

        atlas = new TextureAtlas(Gdx.files.internal("button-pack.atlas"));
        skin = new Skin(atlas);

        //button style setup
        TextButton.TextButtonStyle bigTextButtonStyle = new TextButton.TextButtonStyle();
        BitmapFont font = Assets.manager.get("myfont.fnt");
        bigTextButtonStyle.font = font;
        bigTextButtonStyle.up = skin.getDrawable("bigButton.up");
        bigTextButtonStyle.down = skin.getDrawable("bigButton.down");

        //create button style
        TextButton.TextButtonStyle smallTextButtonStyle = new TextButton.TextButtonStyle();
        smallTextButtonStyle.font = font;
        smallTextButtonStyle.up = skin.getDrawable("smallButton.up");
        smallTextButtonStyle.down = skin.getDrawable("smallButton.down");

        //create buttons
        nextButton = new TextButton("START GAME", bigTextButtonStyle);
        muteButton = new TextButton("MUTE THEME", bigTextButtonStyle);
        getHelpButton = new TextButton("TUTORIAL", bigTextButtonStyle);
        backButton = new TextButton("BACK", bigTextButtonStyle);

        arrowButtonLeft1 = new TextButton("<", smallTextButtonStyle);
        arrowButtonRight1 = new TextButton(">", smallTextButtonStyle);
        arrowButtonLeft2 = new TextButton("<", smallTextButtonStyle);
        arrowButtonRight2 = new TextButton(">", smallTextButtonStyle);
        arrowButtonLeft3 = new TextButton("<", smallTextButtonStyle);
        arrowButtonRight3 = new TextButton(">", smallTextButtonStyle);
        //add listeners to the buttons
        addButtonListeners();
        addArrowButtonListeners();

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
        stage.addActor(table);

        // Take input from ui
        Gdx.input.setInputProcessor(stage);
    }

    /**
     * Creates a 8x3 table with labels and buttons
     */
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
        table.add(muteButton).center().padTop(20);
        table.add();
        table.add(backButton).center().padTop(20);
        table.add();

        table.row();
        table.add(getHelpButton).center().padTop(5);
        table.add();
        table.add(nextButton).center().padTop(5);
        table.add();
        table.row();

    }
    /**
     * @see MenuScreen
     */
    @Override
    public void onEvent(com.mygdx.game.events.Event evt) {

        if(evt.getTag() == Event.Tag.PLAY_SOUND_THEME){
            PlaySounds.stopTheme();
        }

    }
    /**
     * @see MenuScreen
     */
    private void initEvent() {
        EventBus.BUS.register(this);
    }

    /**
     * @see MenuScreen
     */
    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        app.batch.begin();
        background.draw(app.batch);
        app.batch.end();
        stage.act(delta);
        stage.draw();
    }

    /**
     * Listeners for the buttons of the options screen
     * Each button has its own listener.
     */
    private void addButtonListeners() {
        // If next button is clicked change screen
        nextButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Timer.schedule((new Timer.Task() {
                    @Override
                    public void run() {
                        app.setPlayScreen();
                        PlaySounds.stopTheme();
                    }
                }), 1);

            }
        });

        backButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Timer.schedule((new Timer.Task() {
                    @Override
                    public void run() {
                        app.setMenuScreen();
                    }
                }), 1);

            }
        });

        muteButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {

                clicked = !clicked;
                if (clicked) {
                    PlaySounds.pauseTheme();
                } else {
                    //PlaySounds.playThemeReturn();
                    PlaySounds.resumeTheme();
                }
            }
        });

        getHelpButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {

                Timer.schedule((new Timer.Task() {
                    @Override
                    public void run() {
                        app.setHelpScreen();
                    }
                }), 1);

            }
        });

    }

    /**
     *Listeners for the arrow buttons.
     * Each button has its own listener that changes
     * the option when clcked.
     */
    private void addArrowButtonListeners() {

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

    /**
     *
     * Resize of the screen.
     *
     * @param width of the screen
     * @param height of the screen
     */
    @Override
    public void resize(int width, int height) {
        viewport.setScreenSize(width, height);
    }
    /**
     * @see MenuScreen
     */
    @Override
    public void dispose() {
        stage.dispose();
        skin.dispose();
    }
    @Override
    public void pause() { }

    @Override
    public void resume() { }

    @Override
    public void hide() { }

}