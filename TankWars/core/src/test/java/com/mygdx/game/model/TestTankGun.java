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
 *
 */
public class TestTankGun {

    private TankGun tankGun;
    private int testWindSpeed;

    @Before
    public void setup(){
        tankGun = new TankGun(new Position(0.1f, 0.1f));
        testWindSpeed = 100;
    }

    /**
     * Tests the fire() method in TankGun, the test checks
     * if the method creates a shot of type NukeShot.
     * @result test is past if the correct instance is craeted and
     * if the correct windspeed is set.
     *
     */
    @Test
    public void testFireNukeShot() {
        //Change weapon to nuke
        tankGun.changeNuke();
        //The shot created by the method
        Shot actualShot = tankGun.fire(testWindSpeed);
        //check if actualShot is of type AngryShot
        assertTrue(actualShot instanceof NukeShot);
        //test if correct interval
        //correct windspeed?
        assertTrue(testWindSpeed == actualShot.getWindSpeed());
    }

    //Checks if the fire method creates a shot of type Shot
    /**
     * Tests if the fire() in TankGun creates a shot of type Shot.
     * @result test is past if the correct instance is craeted and
     * if the correct windspeed is set.
     *
     */
    @Test
    public void testFireShot() {
        //Set to false in order to test if fire method returns Shot object
        tankGun.setSpecialShot(false);
        //The shot created by the method
        Shot actualShot = tankGun.fire(testWindSpeed);
        //check if Shot type
        assertTrue(actualShot instanceof Shot);
        //correct windspeed?
        assertTrue(testWindSpeed == actualShot.getWindSpeed());

    }
    /**
     * Tests for the aimRight() method in TankGun.
     * @result the tast is passed if the tank is aimed right.
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
     * Tests for the aimLeft() method in TankGun.
     * @result the tast is passed if the tank is aimed left.
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
     * @result the test is passed if the increased power
     * is bigger than the actual power.
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
     * @result the test is passed if the decrease power
     * is smaller  than the actual power.
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
     * Test for the setLeftAim() method
     * @result the test is passed if the boolean parameters
     * have the expected values for a left aim.
     */
    @Test
    public void testSetLeftAim() {
        tankGun.setLeftAim(true);
        assertTrue(tankGun.isLeftAim());
        assertFalse(tankGun.isRightAim());
    }
    /**
     * Test for the setRightAim() method
     * @result the test is passed if the boolean parameters
     * have the expected values for a right aim.
     */
    @Test
    public void testSetRightAim() {
        tankGun.setRightAim(true);
        assertTrue(tankGun.isRightAim());
        assertFalse(tankGun.isLeftAim());
    }
    /**
     *
     * Tests the change weapon ability.
     * @result the test is passed when the value
     * of the boolean currentSpecialShot is chnaged after
     * a change weapon method is called.
     *
     */
    @Test
    public void testChangeWeapon() {
        boolean cuurentSpecialShot = tankGun.hasSpecialShot();
        tankGun.changeMissile();
        assertTrue(!cuurentSpecialShot);
    }

}




