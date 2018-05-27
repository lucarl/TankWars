package com.mygdx.game.view;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Stack;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Disposable;
import com.mygdx.game.Application;
import com.mygdx.game.services.Assets;
import com.mygdx.game.model.TankWars;
import com.mygdx.game.utils.Bar;

/**
 * Heads up display, is used for showing information about the game during gameplay.
 * @author Adam Kjäll
 */

public class Hud implements Disposable {
    private TankWars tankWars;
    public Stage stage;

    // Widgets
    private Bar hpBar;
    private Bar fuelBar;
    private Bar powerBar;

    // Values to show
    private Integer score;
    private String name;
    private Integer hp;
    private float angle;
    private float power;
    private float fuel;
    private Integer wind;

    // Labels
    private Label scoreLabel;
    private Label nameLabel;
    private Label hpLabel;
    private Label angleLabel;
    private Label powerLabel;
    private Label fuelLabel;
    private Label windLabel;

    public Hud(TankWars tankWars) {
        this.tankWars = tankWars;
        stage = new Stage();

        // Setup the progressbar widgets
        hpBar = new Bar(100, 20, Color.GREEN);
        fuelBar = new Bar(100, 20, Color.RED);
        powerBar = new Bar(100, 20, Color.RED);

        // Bitmap font for labels
        BitmapFont font = Assets.manager.get("hudFont.fnt");

        // Create label with a string and a style
        scoreLabel = new Label(String.format("Score: %02d", score), new Label.LabelStyle(font, Color.WHITE));
        nameLabel = new Label("Player: " + name, new Label.LabelStyle(font, Color.WHITE));
        hpLabel = new Label(String.format("HP: %03d", hp), new Label.LabelStyle(font, Color.WHITE));
        angleLabel = new Label(String.format("Angle: %.0f", angle), new Label.LabelStyle(font, Color.WHITE));
        powerLabel = new Label(String.format("Power: %.0f", power), new Label.LabelStyle(font, Color.WHITE));
        fuelLabel = new Label(String.format("Fuel: %.0f", fuel), new Label.LabelStyle(font, Color.WHITE));
        windLabel = new Label(String.format("Wind: %2d <--", wind), new Label.LabelStyle(font, Color.WHITE));

        // Align the labels
        nameLabel.setAlignment(Align.center);
        hpLabel.setAlignment(Align.center);
        powerLabel.setAlignment(Align.center);
        windLabel.setAlignment(Align.center);
        scoreLabel.setAlignment(Align.center);
        fuelLabel.setAlignment(Align.center);
        angleLabel.setAlignment(Align.center);

        // Initialize all values
        update();

        Table table = setupTable();
        //table.setDebug(true);
        stage.addActor(table);
    }

    /**
     * Creates the table and adds all labels and widgets in the right cells
     * @return
     */
    private Table setupTable(){
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
        table.add(windLabel);

        // Align the labels
        nameLabel.setAlignment(Align.center);
        hpLabel.setAlignment(Align.center);
        powerLabel.setAlignment(Align.center);
        windLabel.setAlignment(Align.center);
        scoreLabel.setAlignment(Align.center);
        fuelLabel.setAlignment(Align.center);
        angleLabel.setAlignment(Align.center);

        return table;
    }

    /**
     * Polling the model for updates and updates the labels texts and the bars progress
     */
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
        fuelBar.setValue(fuel / 100f);
        powerBar.setValue(power / 100f);

        // Create an arrow in the direction of the wind
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