package com.mygdx.game.events;

/**
 A single event

 @author hajo

 */
public class Event {

    // All possible (so far) events listed
    public enum Tag {
        PLAY_SOUND_FIRE,
        PLAY_SOUND_MOVE,
        PLAY_SOUND_AIM,
        PLAY_SOUND_EXPLOSION,
        PLAY_ANIMATION_EXPLOSION,
        PLAY_SOUND_ANIMATION_EXPLOSION,
        PLAY_SOUND_THEME,
        PLAY_SOUND_NUKE,
        PLAY_SOUND_MISSILE,

    }

    /*
       PLAYER_IN_JAIL,
        PLAYER_POSITION,
        DICE_FST,
        DICE_SEC,
     */

    private final Tag tag;
    // Data to send
    private final Object value;

    public Event(Tag tag, Object value) {
        this.tag = tag;
        this.value = value;
    }

    public Tag getTag() {
        return tag;
    }

    public Object getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "Event [tag=" + tag + ", value=" + value + "]";
    }
}
