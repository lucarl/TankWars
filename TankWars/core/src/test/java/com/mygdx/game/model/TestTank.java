package com.mygdx.game.model;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;

/**
 *
 * A test class for some of the core methods
 * included in the Tank class.
 *
 * @author Patricia Zabecka
 *
 */

public class TestTank {

    private Tank tank;
    private int damage;
    private int newWindspeed;
    private float delta;
    private Terrain terrain;

    /**
     * Creates objects, a tank amd a terrain, and sets up a fixed
     * damage and a fixed wind speed for testing.
     */
    @Before
    public void setup(){
        tank = new Tank(0,0);
        terrain = new Terrain();
        damage = 10;
        newWindspeed = 40;
        delta = System.nanoTime();
    }
    /**
     * A test for the method setLeftMove().
     * @result the move is set to left for the tank if the isLeftMove() is true
     */
    @Test
    public void testSetLeftMove(){
       tank.setLeftMove(true);
       assertTrue(tank.isLeftMove());
    }
    /**
     * A test for the method setRightMove().
     * @result the move is set to right for the tank if the isRightMove() is true
     */
    @Test
    public void testSetRightMove(){
        tank.setRightMove(true);
        assertTrue(tank.isRightMove());
    }
    /**
     * The test checks if the fuel decreases when the tank moves to the left.
     * @result the test is passed when the startFuel has a bigger value than the expectedFuel.
     * The actualFuel should also be equal to the expectedFuel.
     */
    @Test
    public void testDecreaseFuelWhenLeftMove(){
        tank.setLeftMove(true);
        double startFuel = tank.getFuel();
        double actualFuel = tank.decreaseFuel();
        double expectedFuel = tank.getFuel(); //95
        //assertEquals(expectedFuel,actualFuel);
        assertTrue(expectedFuel < startFuel && expectedFuel == actualFuel);
    }
    /**
     * The test checks if the fuel decreases when the tank moves to the right.
     * @result the test is passed when the startFuel has a bigger value than the expectedFuel.
     * The actualFuel should also be equal to the expectedFuel.
     */
    @Test
    public void testDecreaseFuelWhenRightMove(){
        tank.setRightMove(true);
        double startFuel = tank.getFuel();
        double actualFuel = tank.decreaseFuel();
        double expectedFuel = tank.getFuel(); //95
        assertTrue(expectedFuel < startFuel && expectedFuel == actualFuel);
    }
    /**
     * The test checks if the tank's health decreases due to damage.
     * @result
     *
     */
    @Test
    public void testDecreaseHealth(){
        int startHealth = tank.getHealthPoints();
        int actualHealth = tank.decreaseHealth(damage);
        int expectedHealth = tank.getHealthPoints(); //healthPoints - 10
        assertTrue(expectedHealth < startHealth && expectedHealth == actualHealth);
    }
    /**
     * The test checks if the tank is able to move to the right on the terrain.
     * @result
     */
    @Test
    public void testMoveTankRight(){
        tank.setAlive(true);
        tank.setRightMove(true);
        float actualPosX = tank.getPos().getX();
        Position newPos = tank.moveTank(delta, terrain);
        assertTrue(newPos.getX() > 0 && newPos.getX() > actualPosX && tank.isRightMove());
    }
    /**
     * The test checks if the tank is able to move to the left on the terrain.
     * @result
     */
    @Test
    public void testMoveTankLeft(){
        tank.setAlive(true);
        tank.setLeftMove(true);
        float actualPosX = tank.getPos().getX();
        Position newPos = tank.moveTank(delta, terrain);
        assertTrue(newPos.getX() < 0 && newPos.getX() < actualPosX && tank.isLeftMove());
    }
}
