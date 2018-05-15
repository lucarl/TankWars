package com.mygdx.game.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.game.Application;


/**
 * Created by marianarale on 2018-05-12.
 */
public class OptionScreen implements Screen {

    private Application app;

    private TextButton startButton;

    private Skin skin;
    private Table table;
    private ScreenViewport viewport;
    private Stage stage;
    private SpriteBatch batch;
    private TextureAtlas atlas;

    public OptionScreen(Application app) {
        this.app = app;
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
        table.setBounds(0, 0, Application.GAME_WIDTH, Application.GAME_HEIGHT);

        TextButton.TextButtonStyle bigTextButtonStyle = new TextButton.TextButtonStyle();
        bigTextButtonStyle.font = new BitmapFont(Gdx.files.internal("myfont.fnt"));
        bigTextButtonStyle.up = skin.getDrawable("bigButton.up");
        bigTextButtonStyle.down = skin.getDrawable("bigButton.down");
        startButton = new TextButton("START", bigTextButtonStyle);
        startButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                app.setPlayScreen();
            }
        });

        table.add(startButton);

        stage.addActor(table);

        // Take input from ui
        Gdx.input.setInputProcessor(stage);


    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();


        batch.end();

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

    }
}
