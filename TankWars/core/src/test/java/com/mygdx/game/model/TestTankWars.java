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
        tank = new Tank(1,1);
        testTile = new TerrainTile(10,10, false, 5);
        shot = new StandardShot(new Position(0,0),0.1f, 1f, 10);
        terrain = new Terrain();
        delta = System.nanoTime();

    }
    /**
     * Test for hasCollidedWithWorld(Shot shot) method
     * @result
     */
    @Test
    public void testHasCollidedWithWorld(){
        //TODO - NullPointerException
        assertFalse(tankWarsTest.hasCollidedWithWorld(shot));
        shot.setPos(new Position(0,0));
        assertTrue(tankWarsTest.hasCollidedWithWorld(shot));
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
        assertTrue(expectedPos == actualPos);
    }
    /**
     * Test for the testFire() method.
     * @result the test is passed when the isShooting() returns a true value
     * snd the list with the shots should not be empty since the method will
     * add a shot to the list.
     */
    @Test
    public void testFire(){
        //TODO - fails due to NullPointerException
        tankWarsTest.fire();
        assertTrue(tankWarsTest.isShooting());
        assertFalse(tankWarsTest.getShots().isEmpty());
    }
}
