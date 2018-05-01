package com.mygdx.game.view;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.ctrl.Controller;
import com.mygdx.game.model.IDrawable;
import com.mygdx.game.model.TankWars;

import java.util.HashMap;
import java.util.Map;


public class PlayScreenTest implements Screen {

    private Map<IDrawable, Sprite> sprites;

    private TankWars tankWars;
    private Controller controller;

    private Viewport gamePort;
    private Hud hud;
    private Stage stage = new Stage();


    public PlayScreenTest(Controller controller, TankWars tankWars) {
        this.controller = controller;
        this.tankWars = tankWars;
        sprites = new HashMap<>();

        gamePort = new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        hud = new Hud(controller.batch, tankWars);

    }

    public void show() {
        // For each obj in tankWars, load its image and set it according to the objects state
        tankWars.getObjects().forEach(obj -> {
            Sprite sprite = new Sprite(new Texture(obj.getImgSrc()));
            sprites.put(obj, sprite);
            sprite.setOrigin(obj.getOriginX(), obj.getOriginY());
            // Sets position of sprite and its width and height
            sprite.setBounds(obj.getPos().getX(), obj.getPos().getY(),
                             obj.getWidth(), obj.getHeight());

        });

        Gdx.input.setInputProcessor(controller);
    }

    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        //controller.batch.setProjectionMatrix(hud.stage.getCamera().combined);
        controller.batch.begin();

        // For each object update it's corresponding sprite with the objects state
        sprites.forEach((obj, sprite) -> {
            sprite.setPosition(obj.getPos().getX(), obj.getPos().getY());
            sprite.setRotation(obj.getAngle());

                sprite.draw(controller.batch);

        });
        controller.batch.end();

        hud.update(delta);
        hud.stage.draw();

        //stage.act(delta);
        //stage.draw();

        System.out.println("Angle: " + tankWars.getPlayer().getTank().getAngle() +
                "\n Tankpos: (" + tankWars.getPlayer().getTank().getPos().getX() +
                ", " + tankWars.getPlayer().getTank().getPos().getY() + ")" +
                "\n Shotpos: (" + tankWars.getPlayer().getTank().getGun().getShot().getPos().getX() +
                ", " + tankWars.getPlayer().getTank().getGun().getShot().getPos().getY() + ")");

    }


    public void resize(int width, int height) {

    }

    public void pause() {

    }

    public void resume() {

    }

    public void hide() {

    }

    public void dispose() {

    }
}