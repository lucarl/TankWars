package com.mygdx.game.events;

/**
 A single event

 @author hajo

 */
public class Event {

    // All possible (so far) events listed
    public enum Tag {
        PLAY_SOUND_FIRE,
        PLAY_SOUND_EXPLOSION,
        PLAYER_IN_JAIL,
        PLAYER_POSITION,
        DICE_FST,
        DICE_SEC, 
        PLAY_ANIMATION_EXPLOSION,
    }

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
