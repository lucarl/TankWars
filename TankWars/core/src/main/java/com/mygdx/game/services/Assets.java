package com.mygdx.game.services;

import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

/**
 * An assets class whose purpose is to load and manage
 * all the assets that are used in the game.
 *
 * @author Patricia Zabecka, Thomas Jinton
 * Revised by: Patricia Zabecka, Thomas Jinton
 *
 */
public class Assets {

    //The AssetsManager
    public static final AssetManager manager = new AssetManager();

    //------------------- TEXTURES ----------------------------
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

    //background menu image
    private static final AssetDescriptor<Texture> menuImg =
            new AssetDescriptor<Texture>("menuscreen.jpg", Texture.class);

    //splash image
    private static final AssetDescriptor<Texture> splashImg =
            new AssetDescriptor<Texture>("tanks.jpg", Texture.class);

    //------------------- ANIMATIONS ----------------------------
    //explosion animation
    private static final AssetDescriptor<Texture> explosionAni =
            new AssetDescriptor<Texture>("explosions3.png", Texture.class);

    //------------------- FONTS ----------------------------
    // HUD font
    private static final AssetDescriptor<BitmapFont> hudFont =
            new AssetDescriptor<>("hudFont.fnt", BitmapFont.class);

    // tank wars font
    private static final AssetDescriptor<BitmapFont> headingFont =
            new AssetDescriptor<>("myfont.fnt", BitmapFont.class);

    //tank wars font 2
    private static final AssetDescriptor<BitmapFont> headingFont2 =
            new AssetDescriptor<>("tankWarsFont.fnt", BitmapFont.class);

    //Menu font
    private static final AssetDescriptor<BitmapFont> menuFont =
            new AssetDescriptor<>("tankWarsFont.fnt", BitmapFont.class);

    // Skin font
    private static final AssetDescriptor<Skin> skinFont =
            new AssetDescriptor<Skin>("uiskin.json", Skin.class);

    //------------------- SOUNDS ----------------------------
    private static final AssetDescriptor<Sound> soundShoot =
            new AssetDescriptor<Sound>("cannon.mp3", Sound.class);
    private static final AssetDescriptor<Sound> soundMove =
            new AssetDescriptor<Sound>("tanker.mp3", Sound.class);
    private static final AssetDescriptor<Sound> soundBoom =
            new AssetDescriptor<Sound>("boom.mp3", Sound.class);
    private static final AssetDescriptor<Sound> soundDestroy =
            new AssetDescriptor<Sound>("destroy.mp3",Sound.class);
    private static final AssetDescriptor<Sound> soundAim =
            new AssetDescriptor<Sound>("badaim.mp3", Sound.class);
    private static final AssetDescriptor<Sound> soundTheme =
            new AssetDescriptor<Sound>("TankWarsTheme.mp3", Sound.class);
    private static final AssetDescriptor<Sound> soundNuke =
            new AssetDescriptor<Sound>("nuke.mp3",Sound.class);
    private static final AssetDescriptor<Sound> soundMissile =
            new AssetDescriptor<Sound>("missile.mp3",Sound.class);
    private static final AssetDescriptor<Sound> soundVictory =
            new AssetDescriptor<Sound>("victory.mp3",Sound.class);


    /**
     * Method for loading all of our assets.
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
        manager.load(menuImg);
        manager.load(hudFont);
        manager.load(headingFont);
        manager.load(headingFont2);
        manager.load(menuFont);
        manager.load(skinFont);
        manager.load(soundAim);
        manager.load(soundBoom);
        manager.load(soundShoot);
        manager.load(soundMove);
        manager.load(soundTheme);
        manager.load(soundMissile);
        manager.load(soundNuke);
        manager.load(soundDestroy);
        manager.load(soundVictory);
    }

    public static void dispose(){
        manager.dispose();
        Assets.dispose();
    }
}