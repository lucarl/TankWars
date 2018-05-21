package com.mygdx.game.model;


import java.util.Random;

public class Wind {
    private int windSpeed;
    private Difficulty difficulty;

    public Wind(Difficulty difficulty) {
        this.difficulty = difficulty;
        setWindSpeed();
    }

    private void setWindSpeed() {
        Random r = new Random();
        int randomSign = r.nextBoolean() ? -1 : 1;
        switch (difficulty) {
            case EASY:
                // Speed [-2, 2]
                windSpeed = (r.nextInt(3)) * randomSign;
                break;
            case MEDIUM:
                // Speed [-4,-2] U [2,4]
                windSpeed = (r.nextInt(3) + 2) * randomSign;
                break;
            case HARD:
                // Speed [-6,-4] U [4,6]
                windSpeed = (r.nextInt(3) + 4) * randomSign;
                break;
        }
    }
    public int getWindSpeed() {
        return windSpeed;
    }
}
