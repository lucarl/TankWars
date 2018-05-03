package com.mygdx.game.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.model.TankWars;

// Heads on display, is used for showing information about the game during gameplay.
// It polls the model for data
public class Hud implements Disposable {
    private TankWars tankWars;
    public Stage stage;
    private Viewport viewport;

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
        score = tankWars.getPlayer().getScore();
        name = tankWars.getPlayer().getName();
        angle = tankWars.getPlayer().getTank().getAngle() + 90;
        power = tankWars.getPlayer().getTank().getGun().getPower() * 100;
        fuel = tankWars.getPlayer().getTank().getGun().getPower();
        hp = tankWars.getPlayer().getTank().getHealthPoints();
        wind = tankWars.getWind().getWindSpeed();

        viewport = new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        stage = new Stage(viewport, batch);

        Table table = new Table();
        table.top();

        table.setFillParent(true);

        // Create label with a string and a style
        scoreLabel = new Label(String.format("Score: %03d", score), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        nameLabel = new Label("Player: " + name, new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        hpLabel = new Label(String.format("HP: %03d", hp), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        angleLabel = new Label(String.format("Angle: %.0f", angle), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        powerLabel = new Label(String.format("Power: %03f", power), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        fuelLabel = new Label(String.format("Fuel: %03f", fuel), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        windLabel = new Label(String.format("Wind: %03d <--", wind), new Label.LabelStyle(new BitmapFont(), Color.WHITE));

        table.add(nameLabel).expandX().padTop(5);
        table.add(scoreLabel).expandX().padTop(5);
        table.add(angleLabel).expandX().padTop(5);
        table.add(hpLabel);
        table.row();
        table.add(powerLabel).expandX();
        table.add(fuelLabel).expandX();
        table.add(windLabel).expandX();

        stage.addActor(table);


    }

    public void update() {
        score = tankWars.getPlayer().getScore();
        name = tankWars.getPlayer().getName();
        angle = tankWars.getPlayer().getTank().getGun().getAngle() + 90;
        power = tankWars.getPlayer().getTank().getGun().getPower() * 100;
        fuel = tankWars.getPlayer().getTank().getFuel();
        wind = tankWars.getWind().getWindSpeed();
        scoreLabel.setText(String.format("Score: %02d", score));
        nameLabel.setText("Player: " + name);
        hpLabel.setText(String.format("HP: %03d", hp));
        angleLabel.setText(String.format("Angle: %.0f", angle));
        powerLabel.setText(String.format("Power: %.0f", power));
        fuelLabel.setText(String.format("Fuel: %.0f", fuel));
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
