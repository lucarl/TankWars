package com.mygdx.game.model;

import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

public class TestTankGun {

    //A test for the fire() method in TankGun
    //Checks if the fire method creates a shot of type AngryShot
    @Test
    public void testFireAngryShot() {
        TankGun tankGun = new TankGun(new Position(0.1f, 0.1f));
        //Set to true in order to test if fire returns AngryShot
        tankGun.setSpecialShot(true);
        //The shot created by the method
        Shot actualShot = tankGun.fire();
        //The expected shot - the shot the method is expected to create

        //check if actualShot is of type AngryShot
        assertTrue(actualShot instanceof AngryShot);
    }

    //Checks if the fire method creates a shot of type AngryShot
    @Test
    public void testFireShot() {
        TankGun tankGun = new TankGun(new Position(0.1f, 0.1f));
        //Set to false in order to test if fire method returns Shot object
        tankGun.setSpecialShot(false);
        //The shot created by the method
        Shot actualShot = tankGun.fire();
        //The expected shot - the shot the method is expected to create

        //check if actualShot is of type AngryShot
        assertTrue(actualShot instanceof Shot);
    }

}
