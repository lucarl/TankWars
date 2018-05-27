package com.mygdx.game.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
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
 *
 * View class for the help screen. This screen
 * gives the user help information about
 * Tank Wars.
 *
 * @author Patricia Zabecka, Thomas Jinton
 *
 */
public class HelpScreen implements Screen {

    private Application app;
    private TextButton backButton;
    private TextButton getOptionsButton;

    private Skin skin;
    private Skin skin2;
    private Table table;
    private FitViewport viewport;
    private Stage stage;
    private Sprite background;
    private TextureAtlas atlas;

    private Label heading;
    private Label tutorialText;

    public HelpScreen(Application app) {
        this.app = app;

        //background setup
        Texture texture = Assets.manager.get("menuscreen.jpg");
        background = new Sprite(texture);
        background.setSize(Application.GAME_WIDTH, Application.GAME_HEIGHT);
    }
    /**
     * @see MenuScreen
     */
    @Override
    public void show() {
        viewport = new FitViewport(Application.GAME_WIDTH, Application.GAME_HEIGHT);
        stage = new Stage(viewport, app.batch);
        atlas = new TextureAtlas(Gdx.files.internal("button-pack.atlas"));
        skin = new Skin(Gdx.files.internal("uiskin.json"));
        skin2 = new Skin(atlas);

        TextButton.TextButtonStyle bigTextButtonStyle = new TextButton.TextButtonStyle();
        bigTextButtonStyle.font = new BitmapFont(Gdx.files.internal("myfont.fnt"));
        bigTextButtonStyle.fontColor = Color.WHITE;
        bigTextButtonStyle.up = skin2.getDrawable("bigButton.up");
        bigTextButtonStyle.down = skin2.getDrawable("bigButton.down");

        backButton = new TextButton("BACK", bigTextButtonStyle);
        getOptionsButton = new TextButton("CHANGE OPTIONS", bigTextButtonStyle);

        heading = new Label("TUTORIAL",
                new Label.LabelStyle(new BitmapFont(Gdx.files.internal("tankWarsFont.fnt")), Color.WHITE));
        heading.setFontScale(1.4f);
        heading.setAlignment(Align.center);

        tutorialText = new Label("Basic instructions for how to play Tank Wars:",
                new Label.LabelStyle(new BitmapFont(Gdx.files.internal("myfont.fnt")), Color.WHITE));
        tutorialText.setFontScale(0.8f);
        tutorialText.setAlignment(Align.center);

        setupHelpTable();
        stage.addActor(table);

        addMenuButtonListeners();
        Gdx.input.setInputProcessor(stage);

    }
    /**
     * A table with labels and buttons is created.
     */
    private void setupHelpTable() {

        table = new Table();
        table.setFillParent(true);
        table.top();
        table.padTop(25);

        table.row().width(500);
        table.add(heading);
        table.row().padTop(50);
        table.add(tutorialText);

        table.row();
        table.add(getOptionsButton).padTop(50);
        table.add();

        table.row();
        table.add(backButton).padTop(50);
        table.add();

    }
    /**
     * Listeners for the buttons are added,
     * each button changes the screen.
     */
    private void addMenuButtonListeners() {
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

        getOptionsButton.addListener(new ClickListener() {
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
