package com.prototype.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class GroupDemo extends ApplicationAdapter implements InputProcessor {

    Stage stage;

    @Override
    public void create() {
        stage = new Stage(new ScreenViewport());
        Group group = new Group();

        Image tableImg = new Image(new Texture(Gdx.files.internal("table.png")));
        Image aceImg = new Image(new Texture(Gdx.files.internal("ace.png")));
        Image kingImg = new Image(new Texture(Gdx.files.internal("king.png")));

        group.addActor(tableImg);
        group.addActor(aceImg);
        group.addActor(kingImg);

        stage.addActor(group);

        tableImg.setName("table");
        aceImg.setName("ace");
        kingImg.setName("king");

        kingImg.setPosition(300, 250);
        kingImg.scaleBy(-0.25f);
        aceImg.setPosition(400, 250);

        Gdx.input.setInputProcessor(this);

    }

    @Override
    public void render() {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
    }

    @Override
    public boolean keyDown(int keycode) {
        Group group = (Group) stage.getActors().first();
        Image ace = (Image) group.findActor("ace");

        if (keycode == Input.Keys.RIGHT) {
            if (Gdx.input.isKeyPressed(Input.Keys.CONTROL_LEFT)) {
                ace.setRotation(ace.getRotation() - 1f);
            } else {
                group.setRotation(group.getRotation() - 1f);
            }
        }

        if (keycode == Input.Keys.LEFT) {
            if (Gdx.input.isKeyPressed(Input.Keys.CONTROL_LEFT)) {
                ace.setRotation(ace.getRotation() + 1f);
            } else {
                group.setRotation(group.getRotation() + 1f);
            }
        }

        if (keycode == Input.Keys.UP) {
            group.setColor(group.getColor().r, group.getColor().g,
                    group.getColor().b, group.getColor().a + 0.1f);
        }

        if (keycode == Input.Keys.DOWN) {
            group.setColor(group.getColor().r, group.getColor().g,
                    group.getColor().b, group.getColor().a - 0.1f);
        }

        if(keycode == Input.Keys.NUM_1){
            if(ace.getZIndex() > 0) {
                ace.setZIndex(ace.getZIndex() - 1);
            }
        }

        if(keycode == Input.Keys.NUM_2){
            ace.setZIndex(ace.getZIndex() + 1);
        }

        return true;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        Vector2 coord = stage.screenToStageCoordinates(new Vector2((float) screenX, (float) screenY));
        Actor hitActor = stage.hit(coord.x, coord.y, false);

        if(hitActor != null){
            Gdx.app.log("HIT", hitActor.getName());
        }
        return true;


    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
