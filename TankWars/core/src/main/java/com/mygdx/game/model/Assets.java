package com.mygdx.game.model;

import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;

public class Assets {

    public AssetManager manager = new AssetManager();

    //tank image
    public static final AssetDescriptor<Texture> tankImg =
            new AssetDescriptor<Texture>("tank14.png", Texture.class);

    //flappybird imnage
    public static final AssetDescriptor<Texture> shotBirdImg =
            new AssetDescriptor<Texture>("bird.png", Texture.class);

    //angrybird image
    public static final AssetDescriptor<Texture> angryBirdImg =
            new AssetDescriptor<Texture>("angrybird.png", Texture.class);

    //image of tile for the terrain
    public static final AssetDescriptor<Texture> terrainTileImag =
            new AssetDescriptor<Texture>("terrain.png", Texture.class);

    //tube image for the gun
    public static final AssetDescriptor<Texture> gunTubeImg =
            new AssetDescriptor<Texture>("toptube.png", Texture.class);

    //background image
    public static final AssetDescriptor<Texture> backgroundImg =
            new AssetDescriptor<Texture>("background.jpg", Texture.class);

    //skin
    /*
    public static final AssetDescriptor<Skin> uiSkin =
            new AssetDescriptor<Skin>("ui/uiskin.json", Skin.class,
                    new SkinLoader.SkinParameter("ui/uiskin.pack"));
                    */

    //public static final String tankImage = "tank14.png";
    //public final String shotBirdImage = "bird.png";


    public void loadAssets(){
        manager.load(tankImg);
        manager.load(shotBirdImg);
        manager.load(angryBirdImg);
        manager.load(terrainTileImag);
        manager.load(gunTubeImg);
        manager.load(backgroundImg);
    }

    public void disposeAssets(){
        manager.dispose();
    }
}
