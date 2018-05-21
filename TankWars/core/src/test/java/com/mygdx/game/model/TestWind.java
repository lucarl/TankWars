package com.mygdx.game.model;

import org.junit.Test;
import static junit.framework.TestCase.assertTrue;

/**
 *
 * A test class for the method, setWindSpeedEasy(),
 * included in the Wind class.
 *
 * @author Patricia Zabecka
 *
 */

public class TestWind {

    private int expectedSpeed;

    @Test
    public void testSetWindSpeedEasy(){
        Wind easyWind = new Wind(Difficulty.EASY);
        expectedSpeed = easyWind.getWindSpeed();
        assertTrue(expectedSpeed >= -2 && expectedSpeed <= 2);
    }

    @Test
    public void testSetWindSpeedMedium(){
        Wind mediumWind = new Wind(Difficulty.MEDIUM);
        expectedSpeed = mediumWind.getWindSpeed();
        assertTrue(expectedSpeed >= -4 && expectedSpeed <= -2
         || expectedSpeed >= 2 && expectedSpeed <= 4);//TEST FAILED

    }

    @Test
    public void testSetWindSpeedHard() {
        Wind hardWind = new Wind(Difficulty.HARD);
        expectedSpeed = hardWind.getWindSpeed();
        assertTrue(expectedSpeed >= -6 && expectedSpeed <= -4
                || expectedSpeed >= 4 && expectedSpeed <= 6);//TEST FAILED
    }

}
