package com.mygdx.game.model;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;

public class TestTank {

    private Tank tank;
    private int damage;
    private int newWindspeed;
    private float delta;
    private Terrain terrain;

    @Before
    public void setUp(){
        tank = new Tank(0,0);
        terrain = new Terrain();
        damage = 10;
        newWindspeed = 40;
        delta = System.nanoTime();
    }

    @Test
    public void testSetLeftMove(){
       tank.setLeftMove(true);
       assertTrue(tank.isLeftMove());
    }

    @Test
    public void testSetRightMove(){
        tank.setRightMove(true);
        assertTrue(tank.isRightMove());
    }

    @Test
    public void testDecreaseFuelWhenLeftMove(){
        tank.setLeftMove(true);
        double actualFuel = tank.decreaseFuel();
        double expectedFuel = tank.getFuel(); //95
        assertEquals(expectedFuel,actualFuel);
    }

    @Test
    public void testDecreaseFuelWhenRightMove(){
        tank.setRightMove(true);
        double actualFuel = tank.decreaseFuel();
        double expectedFuel = tank.getFuel(); //95
        assertEquals(expectedFuel,actualFuel);
    }

    @Test
    public void testDecreaseHealth(){
        int actualHealth = tank.decreaseHealth(damage);
        int expectedHealth = tank.getHealthPoints(); //healthPoints - 10
        assertEquals(expectedHealth, actualHealth);
    }

    @Test
    public void testMoveTankRight(){
        tank.setAlive(true);
        tank.setRightMove(true);
        float actualPosX = tank.getPos().getX();
        Position newPos = tank.moveTank(delta, terrain);
        assertTrue(newPos.getX() > 0 && newPos.getX() > actualPosX && tank.isRightMove());
    }

    @Test
    public void testMoveTankLeft(){
        tank.setAlive(true);
        tank.setLeftMove(true);
        float actualPosX = tank.getPos().getX();
        Position newPos = tank.moveTank(delta, terrain);
        assertTrue(newPos.getX() < 0 && newPos.getX() < actualPosX && tank.isLeftMove());
    }

    @Test
    public void testFire(){
        Shot shot = new Shot(new Position(10,10), 10, 10,10);
        Shot fireShot = tank.fire(newWindspeed);
        //When a shot is fired the visibility is set to false
        assertFalse(fireShot.isAlive()); //BLIR INTE FALSE!! VARFÖR???
        //A new shot with the windspeed parameter is created
        assertTrue(fireShot.getWindSpeed() == newWindspeed);
    }

}
