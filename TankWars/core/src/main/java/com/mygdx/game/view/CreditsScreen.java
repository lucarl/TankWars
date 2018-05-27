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
 * View class for the credits. In this class
 * the user can find out who the creators are,
 * if the user is interested.
 *
 * @author Thomas Jinton
 * Revised by: Thomas Jinton.
 */
public class CreditsScreen implements Screen {

    private Application app;
    private TextButton backButton;
    private TextButton showCredits;
    private Label creditPerson1;
    private Label creditPerson2;
    private Label creditPerson3;
    private Label creditPerson4;

    private Skin skin;
    private Skin skin2;
    private Table table;
    private FitViewport viewport;
    private Stage stage;
    private SpriteBatch batch;
    private Label heading;
    private TextureAtlas atlas;
    private Boolean clicked = false;

    public CreditsScreen(Application app) {
        this.app = app;

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
        showCredits = new TextButton("MVP", bigTextButtonStyle);

        heading = new Label("CREDITS",
                new Label.LabelStyle(new BitmapFont(Gdx.files.internal("tankWarsFont.fnt")), Color.WHITE));
        heading.setFontScale(1.4f);
        heading.setAlignment(Align.center);

        creditPerson1 = new Label("Adam Kjäll",
                new Label.LabelStyle(new BitmapFont(Gdx.files.internal("myfont.fnt")), Color.WHITE));
        creditPerson1.setFontScale(0.1f);
        creditPerson1.setColor(Color.GRAY);
        creditPerson1.setAlignment(Align.center);

        creditPerson2 = new Label("Thomas Jinton",
                new Label.LabelStyle(new BitmapFont(Gdx.files.internal("myfont.fnt")), Color.WHITE));
        creditPerson2.setFontScale(0.1f);
        creditPerson2.setColor(Color.GRAY);
        creditPerson2.setAlignment(Align.center);

        creditPerson3 = new Label("Patricia Zabecka",
                new Label.LabelStyle(new BitmapFont(Gdx.files.internal("myfont.fnt")), Color.WHITE));
        creditPerson3.setFontScale(0.1f);
        creditPerson3.setColor(Color.GRAY);
        creditPerson3.setAlignment(Align.center);

        creditPerson4 = new Label("Carl Lundborg",
                new Label.LabelStyle(new BitmapFont(Gdx.files.internal("myfont.fnt")), Color.WHITE));
        creditPerson4.setFontScale(0.1f);
        creditPerson4.setColor(Color.GRAY);
        creditPerson4.setAlignment(Align.center);


        setupCreditsTable();
        stage.addActor(table);

        addMenuButtonListeners();
        Gdx.input.setInputProcessor(stage);

    }

    /**
     * A table with labels and buttons is created.
     */
    private void setupCreditsTable() {

        table = new Table();
        table.setFillParent(true);
        table.top();
        table.padTop(25);

        table.row().width(500);
        table.add(heading);
        table.row().padTop(25);
        table.add(showCredits);
        table.row().padTop(50);
        table.add(creditPerson1);
        table.row().padTop(10);
        table.add(creditPerson2);
        table.row().padTop(10);
        table.add(creditPerson3);
        table.row().padTop(10);
        table.add(creditPerson4);

        table.row();
        table.add(backButton).padTop(100);
        table.add();

    }

    /**
     * Listener for the buttons, when the buttons
     * are clicked an action happens. The credit button
     * enables an effect to show the group members.
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

        showCredits.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {

                clicked = !clicked;
                if(clicked){

                    Timer.schedule((new Timer.Task() {
                        @Override
                        public void run() {

                            creditPerson1.setColor(Color.WHITE);
                            creditPerson1.setFontScale(0.9f);
                            creditPerson2.setColor(Color.WHITE);
                            creditPerson2.setFontScale(0.9f);
                            creditPerson3.setColor(Color.WHITE);
                            creditPerson3.setFontScale(0.9f);
                            creditPerson4.setColor(Color.WHITE);
                            creditPerson4.setFontScale(0.9f);

                        }
                    }), 1);

                }
                else {

                    Timer.schedule((new Timer.Task() {
                        @Override
                        public void run() {

                            creditPerson1.setColor(Color.GRAY);
                            creditPerson1.setFontScale(0.1f);
                            creditPerson2.setColor(Color.GRAY);
                            creditPerson2.setFontScale(0.1f);
                            creditPerson3.setColor(Color.GRAY);
                            creditPerson3.setFontScale(0.1f);
                            creditPerson4.setColor(Color.GRAY);
                            creditPerson4.setFontScale(0.1f);

                        }
                    }), 1);

                }



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