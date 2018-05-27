package com.mygdx.game.model;

import com.badlogic.gdx.Gdx;

import com.mygdx.game.Application;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;

/**
 *
 * A test class for some of the core methods
 * included in the TankGun class.
 *
 * @author Patricia Zabecka
 * Revised by: Patricia Zabecka, Adam Kjäll
 *
 */


public class TestTankGun {
    private TankGun tankGun;
    private int testWindSpeed;

    /**
     * Creates a TankGun object and initializes a
     * speed of the wind for testing.
     */
    @Before
    public void setup(){
        tankGun = new TankGun(new Position(0.1f, 0.1f));
        testWindSpeed = 100;
    }
    /**
     * A test for the fire() method in TankGun
     * @result the test i passed when a shot of type Nuke
     * is created with the expected properties.
     */
    @Test
    public void testFireNukeShot() {
        //Change weapon to nuke
        tankGun.changeNuke();
        //The shot created by the method
        Shot actualShot = tankGun.fire(testWindSpeed);
        //test if correct position
        //TODO - should be true:
        assertTrue(actualShot.getPos().getX() == actualShot.getPos().getX() - tankGun.getOriginX() &&
                actualShot.getPos().getY() == actualShot.getPos().getY() - tankGun.getOriginX());
        //test if correct windspeed
        assertTrue(testWindSpeed == actualShot.getWindSpeed());
        //test if correct type
        assertTrue(actualShot instanceof NukeShot);
    }
    /**
     * A test for the fire() method in TankGun
     * @result the test i passed when a shot of type Shot
     * is created with the expected properties.
     */
    @Test
    public void testFireShot() {
        //Set to false in order to test if fire method returns Shot object
        tankGun.setSpecialShot(false);
        //The shot created by the method
        Shot actualShot = tankGun.fire(testWindSpeed);
        //TODO - should be true:
        //test if correct interval
        assertTrue(actualShot.getPos().getX() == actualShot.getPos().getX() - tankGun.getOriginX() &&
                actualShot.getPos().getY() == actualShot.getPos().getY() - tankGun.getOriginX());
        //correct windspeed?
        assertTrue(testWindSpeed == actualShot.getWindSpeed());
        //check if Shot type
        assertTrue(actualShot instanceof Shot);
    }
    /**
     * Tests if the tank can be aimed to the right.
     */
    @Test
    public void testAimTankRight() {
        float startAngle = tankGun.getAngle(); // 0
        //setRightAim to true and call aim method
        tankGun.setRightAim(true);
        tankGun.aimTank(System.nanoTime()); // Gdx.graphics.getDeltaTime() returns null? That's why we use System.nanoTime()
        float endAngle = tankGun.getAngle();
        assertTrue(startAngle < endAngle);
    }
    /**
     * Tests if the tank can be aimed to the left.
     */
    @Test
    public void testAimTankLeft() {
        float startAngle = tankGun.getAngle(); // 0
        //setLeftAim to true and call aim method
        tankGun.setLeftAim(true);
        tankGun.aimTank(System.nanoTime()); //Gdx.graphics.getDeltaTime() returns null? That's why we use System.nanoTime()
        float endAngle = tankGun.getAngle();

        assertTrue(startAngle >= endAngle);
    }
    /**
     * Tests for the increasePower() method.
     * @result the test is passed if the actualPower variable
     * has a smaller value than the increasedPower variable.
     */
    @Test
    public void testIncreasePower() {
        float actualPower = tankGun.getPower();
        // call increasePower and set endPower to the value of power after the call has been made
        tankGun.increasePower();
        float increasedPower = tankGun.getPower();
        assertTrue(actualPower < increasedPower);
    }
    /**
     * Tests for the decreasePower() method.
     * @result the test is passed if the actualPower variable
     * has a bigger value than the decreasedPower variable.
     */
    @Test
    public void testDecreasePower() {
        float actualPower = tankGun.getPower();
        // call decreasePower and set endPower to the value of power after the call has been made
        tankGun.decreasePower();
        float decreasedPower = tankGun.getPower();
        assertTrue(actualPower > decreasedPower);
    }
    /**
     * Test if the setAimLeft() method detects that
     * a left aim has occurred.
     */
    @Test
    public void testSetAimLeft() {
        tankGun.setLeftAim(true);
        assertTrue(tankGun.isLeftAim());
        assertFalse(tankGun.isRightAim());
    }
    /**
     * Test if the setAimRight() method detects that
     * a right aim has occurred.
     */
    @Test
    public void testSetAimRight() {
        tankGun.setRightAim(true);
        assertTrue(tankGun.isRightAim());
        assertFalse(tankGun.isLeftAim());
    }
    /**
     * Tests if the changeNuke() method changes
     * the shot correctly.
     */
    @Test
    public void testChangeNuke() {
        tankGun.changeNuke();
        assertTrue(tankGun.hasSpecialShot());
    }
    /**
     * Tests if the changeMissile() method changes
     * the shot correctly.
     */
    @Test
    public void testChangeMissile() {
        tankGun.changeMissile();
        assertFalse(tankGun.hasSpecialShot());
    }

}




