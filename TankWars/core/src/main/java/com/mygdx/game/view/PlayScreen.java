package com.mygdx.game.view;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.game.ctrl.Controller;
import com.mygdx.game.model.Shot;
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
    private Stage stage = new Stage();


    public PlayScreen(Controller controller, TankWars tankWars) {
        this.controller = controller;
        this.tankWars = tankWars;

    }

    public void show() {
        String tank = tankWars.getPlayer().getTank().getTankImgSrc();
        String gun = tankWars.getPlayer().getTank().getGunImgSrc();
        String shot = tankWars.getPlayer().getTank().getShot().getShotImgSrc();

        batch = new SpriteBatch();

        tankImg = new Texture(tank);
        gunImg = new Texture(gun);
        shotImg = new Texture(shot);

        tankSprite = new Sprite(tankImg);
        gunSprite = new Sprite(gunImg);
        shotSprite = new Sprite(shotImg);

        tankSprite.setPosition(Gdx.graphics.getWidth() / 2 - tankSprite.getWidth() / 2,
                Gdx.graphics.getHeight() / 2 - tankSprite.getHeight() / 2);

        gunSprite.setPosition(tankSprite.getX() - gunImg.getWidth()/2,
                              tankSprite.getY() + gunImg.getHeight() / 2);
        shotSprite.setPosition(gunSprite.getX(), gunSprite.getY());

        Gdx.input.setInputProcessor(controller);
    }

    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();

        double x = tankWars.getPlayer().getTank().getPositionTank().getX();
        double y = tankWars.getPlayer().getTank().getPositionTank().getY();

        tankSprite.setPosition((float)x, (float)y);
        tankSprite.draw(batch);


        gunSprite.draw(batch);
        gunSprite.setPosition((float)(x + tankSprite.getWidth()/2 + gunSprite.getWidth() / 2),
                (float)(y + tankSprite.getHeight() * 0.8f));
        gunSprite.setScale(0.5f);
        // Todo placera ut tankguns origo korrekt
        gunSprite.setOrigin((float)x-50, (float)y - 50);
        gunSprite.setRotation(tankWars.getPlayer().getTank().getAngle());


        shotSprite.setPosition((float)tankWars.getPlayer().getTank().getShot().getPosition().getX(),
                               (float)tankWars.getPlayer().getTank().getShot().getPosition().getY());
        shotSprite.draw(batch);

        batch.end();

        stage.act(delta);
        stage.draw();

        System.out.println("Angle: " + tankWars.getPlayer().getTank().getAngle() +
                        "\n Tankpos: (" + tankWars.getPlayer().getTank().getPositionTank().getX() +
                                ", " + tankWars.getPlayer().getTank().getPositionTank().getX() + ")" +
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
