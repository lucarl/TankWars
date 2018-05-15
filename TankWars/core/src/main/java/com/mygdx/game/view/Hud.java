package com.mygdx.game.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar.ProgressBarStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.Application;
import com.mygdx.game.model.Assets;
import com.mygdx.game.model.TankWars;

// Heads on display, is used for showing information about the game during gameplay.
// It polls the model for data
public class Hud implements Disposable {
    private TankWars tankWars;
    public Stage stage;
    private Viewport viewport;

    HealthBar hpBar;

    private Integer score;
    private String name;
    private Integer hp;
    private float angle;
    private float power;
    private float fuel;
    private Integer wind;

    Label scoreLabel;
    Label nameLabel;
    Label hpLabel;
    Label angleLabel;
    Label powerLabel;
    Label fuelLabel;
    Label windLabel;

    public Hud(SpriteBatch batch, TankWars tankWars) {
        this.tankWars = tankWars;
        viewport = new FitViewport(Application.GAME_WIDTH, Application.GAME_HEIGHT);
        stage = new Stage(viewport, batch);

        hpBar = new HealthBar(100, 20);


        score = tankWars.getPlayer().getScore();
        name = tankWars.getPlayer().getName();
        angle = tankWars.getPlayer().getTank().getAngle() + 90;
        power = tankWars.getPlayer().getTank().getGun().getPower() * 100;
        fuel = tankWars.getPlayer().getTank().getGun().getPower();
        hp = tankWars.getPlayer().getTank().getHealthPoints();
        wind = tankWars.getWind().getWindSpeed();



        // Create label with a string and a style
        BitmapFont font = Assets.manager.get("hudFont.fnt");

        scoreLabel = new Label(String.format("Score: %03d", score), new Label.LabelStyle(font, Color.WHITE));
        nameLabel = new Label("Player: " + name, new Label.LabelStyle(font, Color.WHITE));
        hpLabel = new Label(String.format("HP: %03d", hp), new Label.LabelStyle(font, Color.WHITE));
        angleLabel = new Label(String.format("Angle: %.0f", angle), new Label.LabelStyle(font, Color.WHITE));
        powerLabel = new Label(String.format("Power: %03f", power), new Label.LabelStyle(font, Color.WHITE));
        fuelLabel = new Label(String.format("Fuel: %03f", fuel), new Label.LabelStyle(font, Color.WHITE));
        windLabel = new Label(String.format("Wind: %03d <--", wind), new Label.LabelStyle(font, Color.WHITE));

        // Setup the table layout
        Table table = new Table();
        table.setWidth(stage.getWidth());
        table.setFillParent(true);
        table.setColor(Color.RED);
        table.top();


        table.row().width(Application.GAME_WIDTH / 6).padTop(5);
        table.add(nameLabel);
        table.add(hpLabel);
        table.add(powerLabel);
        table.add(windLabel);
        table.row().width(Application.GAME_WIDTH / 6).padTop(5);
        table.add(scoreLabel);
        table.add(hpBar).maxWidth(hpBar.getPrefWidth());
        table.add(fuelLabel);
        table.add(angleLabel);

        // Align the labels
        nameLabel.setAlignment(Align.center);
        hpLabel.setAlignment(Align.center);
        powerLabel.setAlignment(Align.center);
        windLabel.setAlignment(Align.center);
        scoreLabel.setAlignment(Align.center);
        fuelLabel.setAlignment(Align.center);
        angleLabel.setAlignment(Align.center);

        //table.setDebug(true);
        stage.addActor(table);


    }

    public void update() {
        score = tankWars.getPlayer().getScore();
        name = tankWars.getPlayer().getName();
        angle = tankWars.getPlayer().getTank().getGun().getAngle() + 90;
        power = tankWars.getPlayer().getTank().getGun().getPower() * 100;
        hp = tankWars.getPlayer().getTank().getHealthPoints();
        fuel = tankWars.getPlayer().getTank().getFuel();
        wind = tankWars.getWind().getWindSpeed();

        scoreLabel.setText(String.format("Score: %02d", score));
        nameLabel.setText("Player: " + name);
        angleLabel.setText(String.format("Angle: %.0f", angle));
        powerLabel.setText(String.format("Power: %.0f", power));
        hpLabel.setText(String.format("HP: %03d", hp));
        fuelLabel.setText(String.format("Fuel: %.0f", fuel));

        hpBar.setValue(hp / 100f);

        if (wind < 0) {
            windLabel.setText(String.format("Wind: %02d <--", Math.abs(wind)));
        } else if (wind == 0) {
            windLabel.setText(String.format("Wind: %02d ", wind));
        } else {
            windLabel.setText(String.format("Wind: %02d -->", wind));
        }
    }


    @Override
    public void dispose() {
        stage.dispose();
    }
}
