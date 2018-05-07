package com.mygdx.game.model;

import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;

public class TestTank {

    private Tank tank = new Tank();

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
    public void testMoveTankRight(){
        tank.setVisibility(true);
        tank.setRightMove(true);
        float actualPosX = tank.getPos().getX();
        Position newPos = tank.moveTank(System.nanoTime());
        assertTrue(newPos.getX() > 0 && newPos.getX() > actualPosX && tank.isRightMove());
    }

    @Test
    public void testMoveTankLeft(){
        tank.setVisibility(true);
        tank.setLeftMove(true);
        float actualPosX = tank.getPos().getX();
        Position newPos = tank.moveTank(System.nanoTime());
        assertTrue(newPos.getX() < 0 && newPos.getX() < actualPosX && tank.isLeftMove());
    }

    @Test
    public void testFire(){
        Shot shot = new Shot(new Position(10,10), 10, 10,10);
        int newWindspeed = 40;
        Shot fireShot = tank.fire(newWindspeed);
        //When a shot is fired the visibility is set to false
        assertFalse(fireShot.isVisible()); //BLIR INTE FALSE!! VARFÖR???
        //A new shot with the windspeed parameter is created
        assertTrue(fireShot.getWindSpeed() == newWindspeed);
    }
}
