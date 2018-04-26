package com.mygdx.game.model;

import org.junit.Test;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;

public class TestMoveTank {

    @Test
    public void testMoveRight(){
        //TankWars tankWars = new TankWars();
        tank.setRightMove(true);

        assertTrue(  30 < tank.positionTank.x);
    }

    @Test
    public void testMoveTankLeft(){
        //test2 - checks change in x-coordinate to the left...
        Tank tank = new Tank(new Position(30, 0), 50, 50, 90);
        tank.setLeftMove(true);

        assertTrue(  30 > tank.positionTank.x);
    }



}
