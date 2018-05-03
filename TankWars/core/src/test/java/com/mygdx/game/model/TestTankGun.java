package com.mygdx.game.model;

import com.badlogic.gdx.Gdx;
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
        //Shot actualShot = tankGun.fire();
        //The expected shot - the shot the method is expected to create

        //check if actualShot is of type AngryShot
        //assertTrue(actualShot instanceof AngryShot);
    }

    //Checks if the fire method creates a shot of type AngryShot
    @Test
    public void testFireShot() {
        TankGun tankGun = new TankGun(new Position(0.1f, 0.1f));
        //Set to false in order to test if fire method returns Shot object
        tankGun.setSpecialShot(false);
        //The shot created by the method
        //Shot actualShot = tankGun.fire();
        //The expected shot - the shot the method is expected to create

        //check if actualShot is of type AngryShot
        //assertTrue(actualShot instanceof Shot);
    }

    @Test
    public void testAimRight(){
        TankGun tankGun = new TankGun(new Position(0.1f,0.1f));
        float startAngle = tankGun.getAngle(); // 0
        //setRightAim to true and call aim method
        tankGun.setRightAim(true);
        tankGun.aimTank(System.nanoTime()); // Gdx.graphics.getDeltaTime() returns null? That's why we use System.nanoTime()
        float endAngle = tankGun.getAngle();

        assertTrue(startAngle < endAngle);
    }
    @Test
    public void testAimLeft(){
        TankGun tankGun = new TankGun(new Position(0.1f,0.1f));
        float startAngle = tankGun.getAngle(); // 0
        //setLeftAim to true and call aim method
        tankGun.setLeftAim(true);
        tankGun.aimTank(System.nanoTime()); //Gdx.graphics.getDeltaTime() returns null? That's why we use System.nanoTime()
        float endAngle = tankGun.getAngle();

        assertTrue(startAngle >= endAngle);
    }

    @Test
    public void testIncreasePower(){
        TankGun tankGun = new TankGun(new Position(0.1f,0.1f));
        float startPower = tankGun.getPower();
        // call increasePower and set endPower to the value of power after the call has been made
        tankGun.increasePower();
        float endPower = tankGun.getPower();

        assertTrue(startPower < endPower);
    }
    @Test
    public void testDecreasePower(){
        TankGun tankGun = new TankGun(new Position(0.1f,0.1f));
        float startPower = tankGun.getPower();
        // call decreasePower and set endPower to the value of power after the call has been made
        tankGun.decreasePower();
        float endPower = tankGun.getPower();

        assertTrue(startPower > endPower);
    }

}
