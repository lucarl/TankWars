package com.mygdx.game.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
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


/**
 * Created by marianarale on 2018-05-12.
 */
public class MenuScreen implements Screen {

    private Application app;

    private TextButton startButton;
    private TextButton optionsButton;
    private TextButton exitButton;

    private Skin skin;
    private Table table;
    private FitViewport viewport;
    private Stage stage;
    private SpriteBatch batch;
    private TextureAtlas atlas;
    private Label heading;

    Sound soundTheme = Assets.manager.get("TankWarsTheme.mp3", Sound.class);

    public MenuScreen(Application app) {
        this.app = app;

        final long soundThemeID = soundTheme.loop(0.1f,1.0f,0.1f);

        Timer.schedule((new Timer.Task() {
            @Override
            public void run() {
                soundTheme.loop(soundThemeID);
            }
        }),1);
    }

    @Override
    public void show() {
        viewport = new FitViewport(Application.GAME_WIDTH, Application.GAME_HEIGHT);
        stage = new Stage(viewport, app.batch);

        atlas = new TextureAtlas(Gdx.files.internal("button-pack.atlas"));
        skin = new Skin(atlas);
        // skin = new Skin(Gdx.files.internal("uiskin.json"));

        TextButton.TextButtonStyle bigTextButtonStyle = new TextButton.TextButtonStyle();
        bigTextButtonStyle.font = new BitmapFont(Gdx.files.internal("myfont.fnt"));
        bigTextButtonStyle.fontColor = Color.WHITE;
        bigTextButtonStyle.up = skin.getDrawable("bigButton.up");
        bigTextButtonStyle.down = skin.getDrawable("bigButton.down");

        //startButton
        startButton = new TextButton("START", bigTextButtonStyle);
        startButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                app.setPlayScreen();
                soundTheme.stop();
            }
        });

        //optionsButton
        optionsButton = new TextButton("OPTIONS", bigTextButtonStyle);
        optionsButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                app.setOptionScreen();
            }
        });

        //exitButton
        exitButton = new TextButton("EXIT", bigTextButtonStyle);
        exitButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.exit();
                soundTheme.stop();
            }
        });

        //BitmapFont menuFont = new BitmapFont(Gdx.files.internal("menu.fnt"));

        //heading label setup
        heading = new Label("TANK WARS", new Label.LabelStyle(
                new BitmapFont(Gdx.files.internal("tankWarsFont.fnt")), Color.WHITE));

        heading.setFontScale(1.4f);

        table = new Table(skin);
        table.setFillParent(true);
        table.top();
        table.padTop(25);

        //button setup in table
        table.row().width(500);
        table.add(heading);
        table.row().padTop(50);
        table.add(startButton);
        table.row().pad(20);
        table.add(optionsButton);
        table.row();
        table.add(exitButton);

        heading.setAlignment(Align.center);

        //table.setDebug(true);

        stage.addActor(table);

        // Take input from ui
        Gdx.input.setInputProcessor(stage);


    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        app.batch.begin();
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
        soundTheme.dispose();
    }
}
