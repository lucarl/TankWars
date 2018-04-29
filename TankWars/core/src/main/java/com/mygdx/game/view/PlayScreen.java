package com.mygdx.game.view;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.game.ctrl.Controller;
import com.mygdx.game.model.TankWars;

public class PlayScreen implements Screen {

    private SpriteBatch batch;
    private Texture tankImg;
    private Texture gunImg;
    private Texture shotImg;
    private Sprite tankSprite;
    private Sprite gunSprite;
    private Sprite shotSprite;
    private TankWars tankWars;
    private Controller controller;

    private Stage stage;


    public PlayScreen(Controller controller, TankWars tankWars) {
        this.controller = controller;
        stage = new Stage();
        this.tankWars = tankWars;
        this.controller = controller;
    }

    public void show() {
        String tank = tankWars.getPlayer().getTank().getImgSrc();
        String gun = tankWars.getPlayer().getTank().getGun().getImgSrc();
        String shot = tankWars.getPlayer().getTank().getShot().getImgSrc();

        batch = new SpriteBatch();

        tankImg = new Texture(tank);
        gunImg = new Texture(gun);
        shotImg = new Texture(shot);

        tankSprite = new Sprite(tankImg);
        gunSprite = new Sprite(gunImg);
        shotSprite = new Sprite(shotImg);

        tankSprite.setPosition(tankWars.getPlayer().getTank().getPos().getX(),
                tankWars.getPlayer().getTank().getPos().getY());
        gunSprite.setPosition(tankWars.getPlayer().getTank().getGun().getPos().getX(),
                tankWars.getPlayer().getTank().getGun().getPos().getY());
        shotSprite.setPosition(tankWars.getPlayer().getTank().getShot().getPos().getX(),
                tankWars.getPlayer().getTank().getShot().getPos().getY());

        tankSprite.setSize(tankWars.getPlayer().getTank().getWidth(),
                tankWars.getPlayer().getTank().getHeight());
        gunSprite.setSize(tankWars.getPlayer().getTank().getGun().getWidth(),
                tankWars.getPlayer().getTank().getGun().getHeight());
        shotSprite.setSize(tankWars.getPlayer().getTank().getShot().getWidth(),
                tankWars.getPlayer().getTank().getShot().getHeight());


        Gdx.input.setInputProcessor(controller);
    }

    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();

        tankSprite.setPosition(tankWars.getPlayer().getTank().getPos().getX(),
                tankWars.getPlayer().getTank().getPos().getY());
        tankSprite.draw(batch);

        gunSprite.draw(batch);
        gunSprite.setPosition(tankWars.getPlayer().getTank().getGun().getPos().getX(),
                tankWars.getPlayer().getTank().getGun().getPos().getY());
        // Todo placera ut tankguns origo korrekt
        gunSprite.setOrigin(tankWars.getPlayer().getTank().getGun().getPos().getX(),
                tankWars.getPlayer().getTank().getGun().getPos().getY());
        gunSprite.setRotation(tankWars.getPlayer().getTank().getGun().getAngle());

        shotSprite.setPosition( tankWars.getPlayer().getTank().getShot().getPosition().getX(),
                    tankWars.getPlayer().getTank().getShot().getPosition().getY());
        shotSprite.draw(batch);

        batch.end();

        stage.act(delta);
        stage.draw();

        System.out.println("Angle: " + tankWars.getPlayer().getTank().getAngle() +
                "\n Tankpos: (" + tankWars.getPlayer().getTank().getPos().getX() +
                ", " + tankWars.getPlayer().getTank().getPos().getX() + ")" +
                "\n Shotpos: (" + tankWars.getPlayer().getTank().getShot().getPosition().getX() +
                ", " + tankWars.getPlayer().getTank().getShot().getPosition().getY() + ")");

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
