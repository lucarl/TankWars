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

}
