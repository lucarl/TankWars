package com.mygdx.game.view;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.ctrl.Controller;
import com.mygdx.game.model.Shot;
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
        String tank = tankWars.getPlayer().getTank().getTankImgSrc();
        String gun = tankWars.getPlayer().getTank().getGunImgSrc();
        String shot = tankWars.getPlayer().getTank().getShot().getShotImgSrc();

        //batch = new SpriteBatch();

        tankImg = new Texture(tank);
        gunImg = new Texture(gun);
        shotImg = new Texture(shot);

        tankSprite = new Sprite(tankImg);
        gunSprite = new Sprite(gunImg);
        shotSprite = new Sprite(shotImg);


        tankSprite.setPosition((float) tankWars.getPlayer().getTank().getPositionTank().getX(),
                (float) tankWars.getPlayer().getTank().getPositionTank().getY());
        tankSprite.setSize(128, 88);
        gunSprite.setPosition(tankSprite.getX(),
                tankSprite.getY());
        gunSprite.setSize(20, 80);


        Gdx.input.setInputProcessor(controller);
    }

    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        //controller.batch.setProjectionMatrix(hud.stage.getCamera().combined);

        controller.batch.begin();


        double x = tankWars.getPlayer().getTank().getPositionTank().getX();
        double y = tankWars.getPlayer().getTank().getPositionTank().getY();

        tankSprite.setPosition((float) x - tankSprite.getWidth() / 2,
                (float) y - tankSprite.getHeight() / 2);
        tankSprite.draw(controller.batch);


        gunSprite.setPosition((float) (x) + gunSprite.getWidth() / 2,
                (float) (y) + gunSprite.getHeight() / 3f);
        gunSprite.setOrigin(gunSprite.getWidth() / 2, 0 );
        gunSprite.setRotation(tankWars.getPlayer().getTank().getAngle());
        gunSprite.draw(controller.batch);


        if(tankWars.getPlayer().getTank().getShot().isVisible()){
            shotSprite.setPosition((float) tankWars.getPlayer().getTank().getShot().getPosition().getX() ,
                                    (float) tankWars.getPlayer().getTank().getShot().getPosition().getY());
            shotSprite.draw(controller.batch);
         }

        controller.batch.end();

        hud.update(delta);
        hud.stage.draw();

        //stage.act(delta);
        //stage.draw();

        System.out.println("Angle: " + tankWars.getPlayer().getTank().getAngle() +
                "\n Tankpos: (" + tankWars.getPlayer().getTank().getPositionTank().getX() +
                ", " + tankWars.getPlayer().getTank().getPositionTank().getY() + ")" +
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
