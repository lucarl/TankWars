package com.mygdx.game.view;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.utils.Timer;
import com.mygdx.game.services.Assets;

public class PlaySounds {

    public static void playFire(){
        Sound soundShoot = Assets.manager.get("cannon.mp3", Sound.class);
        final long soundShootID = soundShoot.loop(0.3f, 1.0f, 0.0f);

        Timer.schedule((new Timer.Task() {
            @Override
            public void run() {
                soundShoot.loop(soundShootID);
                soundShoot.stop();
            }
        }), 1);
    }
    public static void playAim(){
        Sound soundAim = Assets.manager.get("badaim.mp3", Sound.class);
        final long soundAimID = soundAim.loop(0.3f, 1.0f, 0.0f);

        Timer.schedule((new Timer.Task() {
            @Override
            public void run() {
                soundAim.loop(soundAimID);
                soundAim.stop();
            }
        }), 1);
    }

    public static void playMove(){
        Sound soundMove = Assets.manager.get("tanker.mp3", Sound.class);
        final long soundMoveID = soundMove.loop(0.3f, 1.0f, 0.0f);

        Timer.schedule((new Timer.Task() {
            @Override
            public void run() {
                soundMove.loop(soundMoveID);
                soundMove.stop();
            }
        }), 1);
    }

    public static void playExplosion(){
        Sound soundBoom = Assets.manager.get("boom.mp3", Sound.class);
        final long soundBoomID = soundBoom.loop(14.0f, 1.5f, 0.0f);

        Timer.schedule((new Timer.Task() {
            @Override
            public void run() {
                soundBoom.loop(soundBoomID);
                soundBoom.stop();
            }
        }), 4);
    }
    public static void playTankDestroy(){
        Sound soundDestroy = Assets.manager.get("destroy.mp3", Sound.class);
        final long soundDestroyID = soundDestroy.loop(14.0f, 1.5f, 0.0f);

        Timer.schedule((new Timer.Task() {
            @Override
            public void run() {
                soundDestroy.loop(soundDestroyID);
                soundDestroy.stop();
            }
        }), 4);
    }

    public static void playNuke(){
        Sound soundNuke = Assets.manager.get("nuke.mp3", Sound.class);
        final long soundNukeID = soundNuke.loop(0.3f, 1.0f, 0.0f);

        Timer.schedule((new Timer.Task() {
            @Override
            public void run() {
                soundNuke.loop(soundNukeID);
                soundNuke.stop();
            }
        }), 1);
    }

    public static void playMissile(){
        Sound soundMissile = Assets.manager.get("missile.mp3", Sound.class);
        final long soundMissileID = soundMissile.loop(14.0f, 1.5f, 0.0f);

        Timer.schedule((new Timer.Task() {
            @Override
            public void run() {
                soundMissile.loop(soundMissileID);
                soundMissile.stop();
            }
        }), 4);
    }
    public static void playTheme(){
        Sound soundTheme = Assets.manager.get("TankWarsTheme.mp3", Sound.class);
        final long soundThemeID = soundTheme.loop(5.0f, 1.0f, 0.0f);
        //soundTheme.play();

        Timer.schedule((new Timer.Task() {
            @Override
            public void run() {

                soundTheme.loop(soundThemeID);
                //soundTheme.stop();
            }
        }), 1);

    }

    public static void playThemeReturn(){
        Sound soundTheme = Assets.manager.get("TankWarsTheme.mp3", Sound.class);
        final long soundThemeID = soundTheme.loop(5.0f, 1.0f, 0.0f);
        //soundTheme.play();

        Timer.schedule((new Timer.Task() {
            @Override
            public void run() {

                soundTheme.stop();
                soundTheme.loop(soundThemeID);

                //soundTheme.stop();
            }
        }), 1);

    }

    public static void stopTheme(){

        Sound soundTheme = Assets.manager.get("TankWarsTheme.mp3", Sound.class);
        soundTheme.stop();

    }

}