package com.prototype.managers;

import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;

public class Assets {

    public final AssetManager manager = new AssetManager();

   /* public static final AssetDescriptor<Texture> splashImg =
            new AssetDescriptor<Texture>("tanks.jpg", Texture.class);*/

    public void queueAddImage(){

        manager.load("tanks.jpg", Texture.class);

    }

    public void dispose(){

        manager.update();
    }

}