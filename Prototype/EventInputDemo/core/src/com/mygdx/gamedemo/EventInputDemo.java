package com.mygdx.gamedemo;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class EventInputDemo extends ApplicationAdapter implements InputProcessor {
	SpriteBatch batch;
	Texture img;
	Sprite sprite;
	boolean movingRight;

	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("Harley-Quinn.jpg");
		sprite = new  Sprite(img);
		sprite.setPosition(

				Gdx.graphics.getWidth()/2 - sprite.getWidth()/2,
				Gdx.graphics.getHeight()/2 - sprite.getHeight()/2
		);

		//YOU MUST REGISTER THE InputProcessor WHEN IMPLEMENTING
        Gdx.input.setInputProcessor(this); //otherwise key methods won't work
                                          // "this" bc this class is dealing with input
	}

	@Override
	public void render () {

	    if(movingRight)
	        sprite.translateX(1f); //whne right key pressed img will move until the key is unpressed

		Gdx.gl.glClearColor(-0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		batch.begin();
		batch.draw(sprite, sprite.getX(), sprite.getY());
		batch.end();
	}

	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}


	//InputProcessor - helps application handle inputs...

	@Override
	public boolean keyDown(int keycode) {
		if(keycode == Input.Keys.LEFT)
		sprite.translateX(-1f);

		if(keycode == Input.Keys.RIGHT)
		    movingRight = true;
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
        //when key relased
        if(keycode == Input.Keys.RIGHT)
            movingRight = false;

	    return false;
	}

	@Override
	public boolean keyTyped(char character) {
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		return false;
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
	    sprite.setPosition(screenX, Gdx.graphics.getHeight()- screenY);
		return false;
	}

	//can move img up and down
	@Override
	public boolean scrolled(int amount) {
	    if(amount > 0)
	        sprite.translateY(1f);
	    if(amount < 0)
	        sprite.translateY(-1f);
		return false;
	}
}
