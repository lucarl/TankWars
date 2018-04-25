package com.prototype.screenManagerDemo.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Player extends Sprite {

    public Player(String name, float x, float y){
        super(new Texture(name));
        setPosition(x - getWidth() / 2f, y - getHeight() / 2f);
    }

    void movePlayer(){

    }
}
