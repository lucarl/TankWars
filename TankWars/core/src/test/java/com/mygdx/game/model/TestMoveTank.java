package com.mygdx.game.model;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class TestMoveTank {

    @Test
    public void testMoveRight(){
        //test1 - checks change in x-coordinate
        Tank tank = new Tank(new Position(30, 0), 50, 50, 90);
        int expectedPos= 31;
        tank.moveTankRight();
        int result = tank.positionTank.x;

        assertEquals(expectedPos, result);
    }

}
