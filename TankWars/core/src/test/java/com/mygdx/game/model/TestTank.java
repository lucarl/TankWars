package com.mygdx.game.model;

import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

public class TestTank {

    Tank tank = new Tank();

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
        int damage = 10;
        int actualHealth = tank.decreaseHealth(damage);
        int expectedHealth = tank.getHealthPoints(); //healthPoints - 10
        assertEquals(expectedHealth, actualHealth);
    }

    @Test
    public void testMoveTank(){

    }

    @Test
    public void testFire(){

    }
}
