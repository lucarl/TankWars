package com.mygdx.game.model;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class TestMoveTank {

    @Test
    public void testMove(){
        //test1 - checks change in x-coordinate
        TankWars tankwars = new TankWars();
        tankwars.move();
        int expectedPos = 31;
        int result = tankwars.player.getTank().positionTank.x;

        assertEquals(expectedPos, result);
    }

}
