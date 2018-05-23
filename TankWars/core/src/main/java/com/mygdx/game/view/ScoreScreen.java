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
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.mygdx.game.Application;
import com.mygdx.game.services.Assets;


/**
 * Created by marianarale on 2018-05-12.
 */
public class ScoreScreen implements Screen {

    private Application app;
    //private TextButton exitButton;
    private TextField txtScores;

    private Skin skin;
    private Table table;
    private FitViewport viewport;
    private Stage stage;
    private SpriteBatch batch;
    private Label heading;

    public ScoreScreen(Application app) {
        this.app = app;

    }

    @Override
    public void show() {
        viewport = new FitViewport(Application.GAME_WIDTH, Application.GAME_HEIGHT);
        stage = new Stage(viewport, app.batch);

        //atlas = new TextureAtlas(Gdx.files.internal("button-pack.atlas"));
        skin = new Skin(Gdx.files.internal("uiskin.json"));
        // skin = new Skin(Gdx.files.internal("uiskin.json"));


        //TextArea.TextFieldStyle txtScoresStyle = new TextField.TextFieldStyle();


        /*
        exitButton = new TextButton("EXIT", bigTextButtonStyle);
        exitButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.exit();
                soundTheme.stop();
            }
        });
        */

        //BitmapFont menuFont = new BitmapFont(Gdx.files.internal("menu.fnt"));

        //heading label setup
        heading = new Label("LEADERBOARDS",
                new Label.LabelStyle(new BitmapFont(Gdx.files.internal("tankWarsFont.fnt")), Color.WHITE));
        heading.setFontScale(1.4f);

        txtScores = new TextField("",skin);
        txtScores.setSize(200,500);

        table = new Table();
        table.setFillParent(true);
        table.top();
        table.padTop(25);

        //button setup in table
        table.row().width(500);
        table.add(heading);
        table.row().padTop(50);
        table.add(txtScores);

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
    }
}
