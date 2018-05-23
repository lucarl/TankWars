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
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.mygdx.game.Application;
import com.mygdx.game.services.Assets;
import com.mygdx.game.events.EventBus;
import com.mygdx.game.events.Event;
import com.mygdx.game.view.PlaySounds;
import com.mygdx.game.events.IEventHandler;


/**
 * Created by marianarale on 2018-05-12.
 */
public class MenuScreen implements Screen, IEventHandler {

    private Application app;

    private TextButton startButton;
    private TextButton optionsButton;
    private TextButton helpButton;
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

        //EventBus.BUS.publish(new Event(Event.Tag.PLAY_SOUND_THEME, null));
        initEvent();
    }

    @Override
    public void show() {
        viewport = new FitViewport(Application.GAME_WIDTH, Application.GAME_HEIGHT);
        stage = new Stage(viewport, app.batch);

        atlas = new TextureAtlas(Gdx.files.internal("button-pack.atlas"));
        skin = new Skin(atlas);

        background.setSize(Application.GAME_WIDTH, Application.GAME_HEIGHT);

        //Button style setup
        TextButton.TextButtonStyle bigTextButtonStyle = new TextButton.TextButtonStyle();
        bigTextButtonStyle.font = new BitmapFont(Gdx.files.internal("myfont.fnt"));
        bigTextButtonStyle.fontColor = Color.WHITE;
        bigTextButtonStyle.up = skin.getDrawable("bigButton.up");
        bigTextButtonStyle.down = skin.getDrawable("bigButton.down");

        //create buttons
        startButton = new TextButton("START", bigTextButtonStyle);
        optionsButton = new TextButton("OPTIONS", bigTextButtonStyle);
        helpButton = new TextButton("HELP", bigTextButtonStyle);
        exitButton = new TextButton("EXIT", bigTextButtonStyle);

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
        table.add(helpButton);
        table.row();
        table.add(exitButton);

        //display layouts for debugging
        //table.setDebug(true);
    }

    private void addMenuButtonListeners(){

        startButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                app.setPlayScreen();
            }
        });
        optionsButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                app.setOptionScreen();
            }
        });

        helpButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                app.setOptionScreen();
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

    @Override
    public void dispose() {
        stage.dispose();
        skin.dispose();
    }
}
