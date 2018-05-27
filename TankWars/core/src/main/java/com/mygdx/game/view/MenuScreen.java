package com.mygdx.game.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.mygdx.game.Application;
import com.mygdx.game.services.Assets;
import com.mygdx.game.events.EventBus;
import com.mygdx.game.events.Event;
import com.mygdx.game.view.PlaySounds;
import com.mygdx.game.events.IEventHandler;


/**
 *
 * View class for the menu screen that appears
 * after the splash screen is loaded.
 *
 * @author Adam Kjäll
 * Revised by: Patricia Zabecka, Adam Kjäll, Thomas Jinton
 *
 */
public class MenuScreen implements Screen, IEventHandler {

    private Application app;

    private TextButton startButton;
    private TextButton optionsButton;
    private TextButton helpButton;
    private TextButton creditsButton;
    private TextButton exitButton;

    private Skin skin;
    private Table table;
    private FitViewport viewport;
    private Stage stage;
    private SpriteBatch batch;
    private TextureAtlas atlas;
    private Label heading;
    private Sprite background;

    public MenuScreen(Application app) {
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
        viewport = new FitViewport(Application.GAME_WIDTH, Application.GAME_HEIGHT);
        stage = new Stage(viewport, app.batch);
        atlas = new TextureAtlas(Gdx.files.internal("button-pack.atlas"));
        skin = new Skin(atlas);

        //create buttons
        startButton = new TextButton("START", setButtonStyle("myfont.fnt"));
        optionsButton = new TextButton("OPTIONS", setButtonStyle("myfont.fnt"));
        creditsButton = new TextButton("CREDITS", setButtonStyle("myfont.fnt"));
        helpButton= new TextButton("HELP", setButtonStyle("myfont.fnt"));
        exitButton = new TextButton("EXIT", setButtonStyle("myfont.fnt"));

        //heading label setup
        heading = new Label("TANK WARS", new Label.LabelStyle(
                new BitmapFont(Gdx.files.internal("tankWarsFont.fnt")), Color.WHITE));
        heading.setFontScale(1.4f);
        heading.setAlignment(Align.center);

        //create table
        setupMenuTable();
        stage.addActor(table);

        addMenuButtonListeners();
        // Take input from ui
        Gdx.input.setInputProcessor(stage);
    }

    /**
     * A table with labels and buttons is created.
     */
    private void setupMenuTable(){
        table = new Table(skin);
        table.setFillParent(true);
        table.top();
        table.padTop(25);

        //button setup in table
        table.row().width(500);
        table.add(heading);
        table.row().padTop(50);
        table.add(startButton);
        table.row().pad(10);
        table.add(optionsButton);
        table.row().pad(10);
        table.add(creditsButton);
        table.row().pad(10);
        table.add(helpButton);
        table.row();
        table.add(exitButton);

        //display layouts for debugging
        //table.setDebug(true);
    }
    /**
     * Creates a style for the button with a specific font.
     * @param path for the font.
     * @return the style for the buttons
     */
    public TextButton.TextButtonStyle setButtonStyle(String path){
        TextButton.TextButtonStyle bigTextButtonStyle = new TextButton.TextButtonStyle();
        bigTextButtonStyle.font = new BitmapFont(Gdx.files.internal(path));
        bigTextButtonStyle.fontColor = Color.WHITE;
        bigTextButtonStyle.up = skin.getDrawable("bigButton.up");
        bigTextButtonStyle.down = skin.getDrawable("bigButton.down");
        return bigTextButtonStyle;
    }
    /**
     * Listeners for the menu buttons are added.
     * Each button changes the screen when clicked.
     */
    private void addMenuButtonListeners(){

        startButton.addListener(new ClickListener() {
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
        optionsButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Timer.schedule((new Timer.Task() {
                    @Override
                    public void run() {
                        app.setOptionScreen();

                    }
                }), 1);
            }
        });

        helpButton.addListener(new ClickListener() {
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

        creditsButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Timer.schedule((new Timer.Task() {
                    @Override
                    public void run() {
                        app.setCreditsScreen();

                    }
                }), 1);

            }
        });

        exitButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.exit();
                PlaySounds.stopTheme();
            }
        });
    }

    @Override
    public void onEvent(Event evt) {

        if(evt.getTag() == Event.Tag.PLAY_SOUND_THEME){
            PlaySounds.stopTheme();
        }

    }

    private void initEvent() {
        EventBus.BUS.register(this);
    }

    /**
     * Method called by the game loop from
     * the application every time rendering should be performed.
     * @param delta the time rendering should be performed.
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
     * Called when the application is shot down
     * and disposes resources.
     */
    @Override
    public void dispose() {
        stage.dispose();
        skin.dispose();

    }

    @Override
    public void resize(int width, int height) {

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

}
