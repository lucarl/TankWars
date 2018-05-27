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
import com.mygdx.game.model.Player;
import com.mygdx.game.services.Assets;
import com.mygdx.game.view.PlaySounds;


/**
 * View class for when the game is over. The user can
 * choose to play again or go back to the menu.
 *
 * @author Thomas Jinton
 * Revised by: Thomas Jinton.
 */
public class ScoreScreen implements Screen {

    private Application app;
    private TextButton returnMenuButton;
    private TextButton playAgainButton;
    private TextArea txtScoresWinner;
    private Label heading;

    private Skin skin;
    private Skin skin2;
    private Table table;
    private FitViewport viewport;
    private Stage stage;
    private Sprite background;
    private SpriteBatch batch;
    private TextureAtlas atlas;

    public ScoreScreen(Application app) {
        this.app = app;

        //background setup
        Texture texture = Assets.manager.get("menuscreen.jpg");
        background = new Sprite(texture);
        background.setSize(Application.GAME_WIDTH, Application.GAME_HEIGHT);

    }

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

        returnMenuButton = new TextButton("RETURN TO MENU", bigTextButtonStyle);
        playAgainButton = new TextButton("PLAY AGAIN?", bigTextButtonStyle);

        heading = new Label("GAME OVER!",
                new Label.LabelStyle(new BitmapFont(Gdx.files.internal("tankWarsFont.fnt")), Color.WHITE));
        heading.setFontScale(1.4f);
        heading.setAlignment(Align.center);

        txtScoresWinner = new TextArea("Thank you \n  for playing \n Tank Wars",skin);
        txtScoresWinner.setSize(210,210);

        setupScoreTable();
        stage.addActor(table);

        addMenuButtonListeners();
        Gdx.input.setInputProcessor(stage);

    }

    /**
     * A table with labels, text and buttons is created.
     */
    private void setupScoreTable(){

        table = new Table();
        table.setFillParent(true);
        table.top();
        table.padTop(25);

        table.row().width(500);
        table.add(heading);

        table.row().padTop(50);
        table.add(txtScoresWinner);
        table.row().padTop(25);
        table.add();

        table.row();
        table.add(playAgainButton).padTop(50);
        table.add();

        table.row();
        table.add(returnMenuButton).padTop(50);
        table.add();

    }

    /**
     * Listeners for the different buttons.
     * When a button is clicked the screen
     * changes.
     */
    private void addMenuButtonListeners() {

        returnMenuButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Timer.schedule((new Timer.Task() {
                    @Override
                    public void run() {
                        app.setMenuScreen();
                        PlaySounds.stopVictory();
                        PlaySounds.playThemeReturn();
                    }
                }), 1);

            }
        });

        playAgainButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Timer.schedule((new Timer.Task() {
                    @Override
                    public void run() {
                        app.setPlayScreen();
                        //PlaySounds.stopTheme();
                        PlaySounds.stopVictory();
                    }
                }), 1);

            }
        });

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
    /**
     * @see MenuScreen
     */
    @Override
    public void dispose() {
        stage.dispose();
        skin.dispose();
        atlas.dispose();
    }
}
