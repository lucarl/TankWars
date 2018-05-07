package com.mygdx.game.view;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.*;
import com.mygdx.game.ctrl.Controller;
import com.mygdx.game.model.IDrawable;
import com.mygdx.game.model.TankWars;

import java.util.HashMap;
import java.util.Map;


public class PlayScreenTest implements Screen {
    //private Map<IDrawable, Sprite> sprites;
    private Sprite background;
    private TankWars tankWars;
    private Controller controller;
    private Viewport viewport;
    private OrthographicCamera camera;
    private Hud hud;
    private Renderer renderer;
    private Sprite terrain;



    public PlayScreenTest(Controller controller, TankWars tankWars) {
        this.controller = controller;
        this.tankWars = tankWars;
      //  sprites = new HashMap<>();
        renderer = new Renderer(controller.batch);
        hud = new Hud(controller.batch, tankWars);
        background = new Sprite(new Texture("background.jpg"));

    }

    public void show() {
        // For each obj in tankWars, load its image and set it according to the objects state
        /*tankWars.getObjects().forEach(obj -> {
            if (!obj.isVisible()) {
                sprites.remove(obj);
            } else {
                Sprite sprite = new Sprite(new Texture(obj.getImgSrc()));
                sprites.put(obj, sprite);
                sprite.setOrigin(obj.getOriginX(), obj.getOriginY());
                // Sets position of sprite and its width and height
                sprite.setBounds(obj.getPos().getX(), obj.getPos().getY(),
                        obj.getWidth(), obj.getHeight());
            }
        });
*/
        background.setSize(Controller.GAME_WIDTH, Controller.GAME_HEIGHT);
        Gdx.input.setInputProcessor(controller);
    }

    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        show();
        controller.batch.begin();
        background.draw(controller.batch);
        renderer.render(tankWars.getObjects());


        /*      // For each object update it's corresponding sprite with the objects state
        sprites.forEach((obj, sprite) -> {
            sprite.setRotation(obj.getAngle());
            sprite.setPosition(obj.getPos().getX(), obj.getPos().getY());

            if (obj.isVisible()) {
                sprite.draw(controller.batch);
            }
        });*/
        controller.batch.end();

        hud.update();
        hud.stage.draw();
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
        controller.dispose();
        hud.dispose();

    }
}
