package com.mygdx.game.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Stack;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.Application;
import com.mygdx.game.services.Assets;
import com.mygdx.game.model.TankWars;

// Heads up display, is used for showing information about the game during gameplay.
// It polls the model for data
public class Hud implements Disposable {
    private TankWars tankWars;
    public Stage stage;
    private Viewport viewport;

    private HealthBar hpBar;
    private FuelBar fuelBar;
    private PowerBar powerBar;

    private Integer score;
    private String name;
    private Integer hp;
    private float angle;
    private float power;
    private float fuel;
    private Integer wind;
    private String shot;


    private Label shotLabel;
    private Label scoreLabel;
    private Label nameLabel;
    private Label hpLabel;
    private Label angleLabel;
    private Label powerLabel;
    private Label fuelLabel;
    private Label windLabel;
    private Label menuLabel;

    public Hud(SpriteBatch batch, TankWars tankWars) {
        this.tankWars = tankWars;
        viewport = new FitViewport(Application.GAME_WIDTH, Application.GAME_HEIGHT);
        stage = new Stage(viewport, batch);

        hpBar = new HealthBar(100, 20);
        fuelBar = new FuelBar(100, 20);
        powerBar = new PowerBar(100, 20);

        score = tankWars.getPlayer().getScore();
        name = tankWars.getPlayer().getName();
        angle = tankWars.getPlayer().getTank().getAngle() + 90;
        power = tankWars.getPlayer().getTank().getGun().getPower() * 100;
        fuel = tankWars.getPlayer().getTank().getFuel();
        hp = tankWars.getPlayer().getTank().getHealthPoints();
        wind = tankWars.getWind().getWindSpeed();
        //shot = tankWars.getPlayer().getTank().getShot().getName();

        // Bitmap font for labels
        BitmapFont font = Assets.manager.get("hudFont.fnt");

        // Create label with a string and a style
        scoreLabel = new Label(String.format("Score: %03d", score), new Label.LabelStyle(font, Color.WHITE));
        nameLabel = new Label("Player: " + name, new Label.LabelStyle(font, Color.WHITE));
        hpLabel = new Label(String.format("HP: %03d", hp), new Label.LabelStyle(font, Color.WHITE));
        angleLabel = new Label(String.format("Angle: %.0f", angle), new Label.LabelStyle(font, Color.WHITE));
        powerLabel = new Label(String.format("Power: %03f", power), new Label.LabelStyle(font, Color.WHITE));
        fuelLabel = new Label(String.format("Fuel: %03f", fuel), new Label.LabelStyle(font, Color.WHITE));
        windLabel = new Label(String.format("Wind: %03d <--", wind), new Label.LabelStyle(font, Color.WHITE));
        shotLabel = new Label("Shot: " + shot, new Label.LabelStyle(font, Color.WHITE));

        // Setup the table layout
        Table table = new Table();
        table.setWidth(stage.getWidth());
        table.setFillParent(true);
        table.setColor(Color.RED);
        table.top();


        table.row().width(Application.GAME_WIDTH / 6).padTop(5);
        table.add(nameLabel);

        // Stack label on top of the bar
        Stack stack = new Stack();
        stack.add(hpBar);
        stack.add(hpLabel);
        table.add(stack).width(hpBar.getPrefWidth());

        // Stack label on top of the bar
        stack = new Stack();
        stack.add(powerBar);
        stack.add(powerLabel);
        table.add(stack).width(powerBar.getPrefWidth());

        // Stack label on top of the bar
        stack = new Stack();
        stack.add(fuelBar);
        stack.add(fuelLabel);
        table.add(stack).width(fuelBar.getPrefWidth());

        table.row().width(Application.GAME_WIDTH / 6).padTop(5);

        table.add(scoreLabel);
        table.add(angleLabel);
        table.add(shotLabel);
        //table.add(menuButton).width(fuelLabel.getPrefWidth());

        // Align the labels
        nameLabel.setAlignment(Align.center);
        hpLabel.setAlignment(Align.center);
        powerLabel.setAlignment(Align.center);
        windLabel.setAlignment(Align.center);
        scoreLabel.setAlignment(Align.center);
        fuelLabel.setAlignment(Align.center);
        angleLabel.setAlignment(Align.center);
        shotLabel.setAlignment(Align.center);

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
        //shot = tankWars.getPlayer().getTank().getGun().getShot().getName();

        scoreLabel.setText(String.format("Score: %02d", score));
        nameLabel.setText("Player: " + name);
        angleLabel.setText(String.format("Angle: %.0f", angle));
        powerLabel.setText(String.format("Power: %.0f", power));
        hpLabel.setText(String.format("HP: %03d", hp));
        fuelLabel.setText(String.format("Fuel: %.0f", fuel));
        shotLabel.setText(String.format("Shot:", shot));

        hpBar.setValue(hp / 100f);
        fuelBar.setValue(fuel / 100f);
        powerBar.setValue(power / 100f);

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