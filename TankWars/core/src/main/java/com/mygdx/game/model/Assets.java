package com.mygdx.game.model;

import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;

public class Assets {

    public static final AssetManager manager = new AssetManager();

    //tank image
    public static final AssetDescriptor<Texture> tankImg =
            new AssetDescriptor<Texture>("tank1.png", Texture.class);

    //standard shot image
    public static final AssetDescriptor<Texture> shotBirdImg =
            new AssetDescriptor<Texture>("cannonBall.png", Texture.class);

    //nuke image
    public static final AssetDescriptor<Texture> angryBirdImg =
            new AssetDescriptor<Texture>("nuke.png", Texture.class);

    //upgrade image
    public static final AssetDescriptor<Texture>  upgradeBoxImg =
            new AssetDescriptor<Texture>("upgradeBox.png", Texture.class);

    //image of tile for the terrain
    public static final AssetDescriptor<Texture> terrainTileImg =
            new AssetDescriptor<Texture>("terrainTile.png", Texture.class);

    //tube image for the gun
    public static final AssetDescriptor<Texture> gunTubeImg =
            new AssetDescriptor<Texture>("tankGun.png", Texture.class);

    //background image
    public static final AssetDescriptor<Texture> backgroundImg =
            new AssetDescriptor<Texture>("background.jpg", Texture.class);

    //explosion animation
    public static final AssetDescriptor<Texture> explosionAni =
            new AssetDescriptor<Texture>("explosion.png", Texture.class);

    //splash image
    public static final AssetDescriptor<Texture> splashImg =
            new AssetDescriptor<Texture>("tanks.jpg", Texture.class);


    //skin
    /*
    public static final AssetDescriptor<Skin> uiSkin =
            new AssetDescriptor<Skin>("ui/uiskin.json", Skin.class,
                    new SkinLoader.SkinParameter("ui/uiskin.pack"));
                    */

    public static void loadAssets(){
        manager.load(tankImg);
        manager.load(shotBirdImg);
        manager.load(angryBirdImg);
        manager.load(upgradeBoxImg);
        manager.load(terrainTileImg);
        manager.load(gunTubeImg);
        manager.load(backgroundImg);
        manager.load(explosionAni);
        manager.load(splashImg);
    }

    public static void disposeAssets(){
        manager.dispose();
    }
}
