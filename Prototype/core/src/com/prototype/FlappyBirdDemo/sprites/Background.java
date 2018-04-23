package com.prototype.FlappyBirdDemo.sprites;

import com.badlogic.gdx.graphics.Texture;


public class Background {
    private Texture bg;

    public Background(){
        bg = new Texture("bg.png");
    }



    public Texture getTexture() {
        return bg;
    }

    public void dispose(){
        bg.dispose();
    }


}
