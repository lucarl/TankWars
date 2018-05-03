package com.mygdx.game.view;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.ctrl.Controller;
import com.mygdx.game.model.TankWars;

public class PlayScreen implements Screen {

    private TankWars tankWars;
    private Controller controller;

    private Viewport gamePort;
    private Hud hud;

    private Texture tankImg;
    private Texture gunImg;
    private Texture shotImg;
    private Sprite tankSprite;
    private Sprite gunSprite;
    private Sprite shotSprite;

    //private Stage stage = new Stage();


    public PlayScreen(Controller controller, TankWars tankWars) {
        this.controller = controller;
        this.tankWars = tankWars;


        gamePort = new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        hud = new Hud(controller.batch, tankWars);

    }

    public void show() {
        String tank = tankWars.getPlayer().getTank().getImgSrc();
        String gun = tankWars.getPlayer().getTank().getGun().getImgSrc();
        String shot = tankWars.getPlayer().getTank().getGun().getShot().getImgSrc();

        //batch = new SpriteBatch();

        tankImg = new Texture(tank);
        gunImg = new Texture(gun);
        shotImg = new Texture(shot);

        tankSprite = new Sprite(tankImg);
        gunSprite = new Sprite(gunImg);
        shotSprite = new Sprite(shotImg);


        tankSprite.setPosition( tankWars.getPlayer().getTank().getPos().getX(),
                 tankWars.getPlayer().getTank().getPos().getY());
        tankSprite.setSize(tankWars.getPlayer().getTank().getWidth(), tankWars.getPlayer().getTank().getHeight());
        gunSprite.setPosition(tankWars.getPlayer().getTank().getGun().getPos().getX(),
                tankWars.getPlayer().getTank().getGun().getPos().getY());
        gunSprite.setSize(tankWars.getPlayer().getTank().getGun().getWidth(),
                tankWars.getPlayer().getTank().getGun().getHeight());


        Gdx.input.setInputProcessor(controller);
    }

    public void render(float delta) {


        Gdx.gl.glClearColor(0, 0, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        //controller.batch.setProjectionMatrix(hud.stage.getCamera().combined);

        controller.batch.begin();


        double x = tankWars.getPlayer().getTank().getPos().getX();
        double y = tankWars.getPlayer().getTank().getPos().getY();

        tankSprite.setPosition((float) x,
                (float) y );
        tankSprite.draw(controller.batch);


        gunSprite.setPosition((float)tankWars.getPlayer().getTank().getGun().getPos().getX(),
                (float)tankWars.getPlayer().getTank().getGun().getPos().getY());
        gunSprite.setOrigin(tankWars.getPlayer().getTank().getGun().getOriginX(), tankWars.getPlayer().getTank().getGun().getOriginY());
        gunSprite.setRotation(tankWars.getPlayer().getTank().getGun().getAngle());
        gunSprite.draw(controller.batch);


        if (tankWars.getPlayer().getTank().getGun().getShot().isVisible()) {
            if (tankWars.getPlayer().getTank().getGun().hasSpecialShot()) {
                String shot = tankWars.getPlayer().getTank().getGun().getShot().getImgSrc();
                shotImg = new Texture(shot);
                shotSprite = new Sprite(shotImg);
                shotSprite.setSize(tankWars.getPlayer().getTank().getGun().getShot().getWidth(),
                        tankWars.getPlayer().getTank().getGun().getShot().getWidth());
            }
            shotSprite.setPosition( tankWars.getPlayer().getTank().getGun().getShot().getPos().getX(),
                     tankWars.getPlayer().getTank().getGun().getShot().getPos().getY());
            shotSprite.draw(controller.batch);
        }

        controller.batch.end();

        hud.update();
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
