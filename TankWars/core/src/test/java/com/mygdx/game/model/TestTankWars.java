package com.mygdx.game.model;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
/**
 *
 * A test class for some of the core methods
 * included in TankWars.
 *
 * @author Patricia Zabecka
 *
 */

public class TestTankWars {

    private TankWars tankWarsTest;
    private Tank tank;
    private TerrainTile testTile;
    private Shot shot;
    private Terrain terrain;
    private float delta;

    /**
     * Sets up a tank, tile, shot, terrain and a tankWars object and initializes a delta
     * value for testing.
     */
    @Before
    public void setUp(){
        //tankWarsTest = new TankWars();
        tank = new Tank(1,1);
        testTile = new TerrainTile(10,10, false, 5);
        shot = new StandardShot(new Position(5,5),0.1f, 1f, 10);
        terrain = new Terrain();
        delta = System.nanoTime();

        //removes all objects so that the lists are empty for testing
        tankWarsTest.getObjects().removeAll(tankWarsTest.getObjects());
        tankWarsTest.getTiles().removeAll(tankWarsTest.getTiles());
        tankWarsTest.getShots().removeAll(tankWarsTest.getShots());
    }

    //updateObjects

    //removeShots
    /**
     * The test checks if
     * @result
     */
    @Test
    public void testRemoveObjectTank(){
        //adds an object at index 1 to our empty list
        tankWarsTest.getObjects().add(0,tank);
        //sets the added object to not alive
        tank.setAlive(false);
        tankWarsTest.removeShots();
        //test if object tank is removed by checking if the list is empty
        assertTrue(tankWarsTest.getObjects().isEmpty());
    }
    /**
     * The test checks if
     * @result
     */
    @Test
    public void testRemoveObjectTile(){
        tankWarsTest.getTiles().add(0,testTile);
        tankWarsTest.removeShots();
        assertTrue(tankWarsTest.getTiles().isEmpty());

    }
    /**
     * The test checks if
     * @result
     */
    @Test
    public void testRemoveObjectShot(){
        tankWarsTest.getShots().add(0,shot);
        shot.setAlive(false);
        tankWarsTest.removeShots();
        assertTrue(tankWarsTest.getShots().isEmpty());
    }
    /**
     * The test checks if
     * @result
     */
    @Test
    public void testIsRoundOver(){
        tank.setAlive(true);
        //should return true becuase only one player is alive
        assertTrue(tankWarsTest.isRoundOver());
    }
    /**
     * The test checks if
     * @result
     */
    @Test
    public void testNextPlayerTurnOver(){
        boolean actualTurnOver = tankWarsTest.isTurnOver(); //returns true
        tankWarsTest.nextPlayer();
        boolean expectedTurnOver = tankWarsTest.isTurnOver(); //should be set to true after nextPlayer() is called
        assertEquals(expectedTurnOver, actualTurnOver);
    }
    /**
     * The test checks if
     * @result
     */
    //aim
    @Test
    public void testAim(){
       tankWarsTest.aim(delta);
    }
    /**
     * The test checks if
     * @result
     */
    @Test
    public void testMove(){
        Position actualPos = tankWarsTest.getPlayer().getTank().getPos();
        tankWarsTest.move(delta);
        Position expectedPos = tankWarsTest.getPlayer().getTank().getPos();
        //assertTrue(expectedPos == actualPos); is true but should be false
        assertTrue(expectedPos != actualPos); //TEST FAILED
    }
    /**
     * The test checks if
     * @result
     */
    @Test
    public void testFire(){
        tankWarsTest.fire();
        //a shot is added to the shots list when the fire() method is called so the list shouldn't be empty
        //the method also sets isTurnOver to true
        assertTrue(!tankWarsTest.getShots().isEmpty() && tankWarsTest.isTurnOver());
    }
}
