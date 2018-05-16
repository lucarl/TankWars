package com.mygdx.game.model;

import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

public class Assets {

    public static final AssetManager manager = new AssetManager();

    //tank image
    private static final AssetDescriptor<Texture> tankImg =
            new AssetDescriptor<Texture>("tank1.png", Texture.class);

    //standard shot image
    private static final AssetDescriptor<Texture> shotBirdImg =
            new AssetDescriptor<Texture>("cannonBall.png", Texture.class);

    //nuke image
    private static final AssetDescriptor<Texture> angryBirdImg =
            new AssetDescriptor<Texture>("nuke.png", Texture.class);

    //upgrade image
    private static final AssetDescriptor<Texture>  upgradeBoxImg =
            new AssetDescriptor<Texture>("upgradeBox.png", Texture.class);

    //image of tile for the terrain
    private static final AssetDescriptor<Texture> terrainTileImg =
            new AssetDescriptor<Texture>("terrainTile.png", Texture.class);

    //tube image for the gun
    private static final AssetDescriptor<Texture> gunTubeImg =
            new AssetDescriptor<Texture>("tankGun.png", Texture.class);

    //background image
    private static final AssetDescriptor<Texture> backgroundImg =
            new AssetDescriptor<Texture>("background.jpg", Texture.class);

    //explosion animation
    private static final AssetDescriptor<Texture> explosionAni =
            new AssetDescriptor<Texture>("explosion.png", Texture.class);

    //splash image
    private static final AssetDescriptor<Texture> splashImg =
            new AssetDescriptor<Texture>("tanks.jpg", Texture.class);

    // HUD font
    private static final AssetDescriptor<BitmapFont> hudFont =
            new AssetDescriptor<>("hudFont.fnt", BitmapFont.class);

    // tank wars font
    private static final AssetDescriptor<BitmapFont> tankWarsFont =
            new AssetDescriptor<>("myfont.fnt", BitmapFont.class);

    // sounds

    private static final AssetDescriptor<Sound> soundShoot =
            new AssetDescriptor<Sound>("cannon.mp3", Sound.class);
    private static final AssetDescriptor<Sound> soundMove =
            new AssetDescriptor<Sound>("tanker.mp3", Sound.class);
    private static final AssetDescriptor<Sound> soundBoom =
            new AssetDescriptor<Sound>("boom.mp3", Sound.class);
    private static final AssetDescriptor<Sound> soundAim =
            new AssetDescriptor<Sound>("badaim.mp3", Sound.class);
    private static final AssetDescriptor<Sound> soundTheme =
            new AssetDescriptor<Sound>("TankWarsTheme.mp3", Sound.class);



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
        manager.load(hudFont);
        manager.load(tankWarsFont);
        manager.load(soundAim);
        manager.load(soundBoom);
        manager.load(soundShoot);
        manager.load(soundMove);
        manager.load(soundTheme);
    }

    public static void disposeAssets(){
        manager.dispose();
    }
}