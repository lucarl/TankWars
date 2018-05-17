package com.mygdx.game.model;

import com.badlogic.gdx.Gdx;

import com.mygdx.game.Application;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;

public class TestTankGun {

    private TankGun tankGun = new TankGun(new Position(0.1f, 0.1f));
    private int testWindSpeed = 100;

    //A test for the fire() method in TankGun
    //Checks if the fire method creates a shot of type AngryShot
    @Test
        //Set to true in order to test if fire returns AngryShot
        tankGun.setSpecialShot(true);
        //The shot created by the method
        Shot actualShot = tankGun.fire(testWindSpeed );
        //check if actualShot is of type AngryShot
        assertTrue(actualShot instanceof AngryShot);
        //test if correct interval
        assertTrue(actualShot.getPos().getX() > 0 &&
                actualShot.getPos().getX() < Application.GAME_WIDTH &&
                actualShot.getPos().getY() > 0);
        //correct windspeed?
        assertTrue(testWindSpeed == actualShot.getWindSpeed());
    }

    //Checks if the fire method creates a shot of type Shot
    @Test
    public void testFireShot() {
        //Set to false in order to test if fire method returns Shot object
        tankGun.setSpecialShot(false);
        //The shot created by the method
        Shot actualShot = tankGun.fire(testWindSpeed);
        //check if Shot type
        assertTrue(actualShot instanceof Shot);
        //test if correct interval
        assertTrue(actualShot.getPos().getX() > 0 &&
                actualShot.getPos().getX() < Application.GAME_WIDTH &&
                actualShot.getPos().getY() > 0);
        //correct windspeed?
        assertTrue(testWindSpeed == actualShot.getWindSpeed());

    public void testAimTankRight(){
        float startAngle = tankGun.getAngle(); // 0
        //setRightAim to true and call aim method
        tankGun.setRightAim(true);
        tankGun.aimTank(System.nanoTime()); // Gdx.graphics.getDeltaTime() returns null? That's why we use System.nanoTime()
        float endAngle = tankGun.getAngle();

        assertTrue(startAngle < endAngle);
    }
    @Test
    public void testAimTankLeft(){
        float startAngle = tankGun.getAngle(); // 0
        //setLeftAim to true and call aim method
        tankGun.setLeftAim(true);
        tankGun.aimTank(System.nanoTime()); //Gdx.graphics.getDeltaTime() returns null? That's why we use System.nanoTime()
        float endAngle = tankGun.getAngle();

        assertTrue(startAngle >= endAngle);
    }

    @Test
    public void testIncreasePower(){
        float actualPower = tankGun.getPower();
        // call increasePower and set endPower to the value of power after the call has been made
        tankGun.increasePower();
        float increasedPower = tankGun.getPower();
        assertTrue(actualPower < increasedPower);
    }
    @Test
    public void testDecreasePower(){
        float actualPower = tankGun.getPower();
        // call decreasePower and set endPower to the value of power after the call has been made
        tankGun.decreasePower();
        float decreasedPower = tankGun.getPower();
        assertTrue(actualPower > decreasedPower);
    }

    @Test
    public void testSetAimLeft(){
        tankGun.setLeftAim(true);
        assertTrue(tankGun.isLeftAim());
        assertFalse(tankGun.isRightAim());
    }

    @Test
    public void testSetAimRight(){
        tankGun.setRightAim(true);
        assertTrue(tankGun.isRightAim());
        assertFalse(tankGun.isLeftAim());
    }

    public void testChangeWeapon(){
        boolean cuurentSpecialShot = tankGun.hasSpecialShot();
        tankGun.changeNuke();
        assertTrue(!cuurentSpecialShot);


}
