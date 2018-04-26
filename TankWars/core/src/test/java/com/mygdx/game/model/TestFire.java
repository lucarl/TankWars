package com.mygdx.game.model;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class TestFire {

    @Test

    public void testFireTank(){
        Tank tank = new Tank(new Position(30, 0), 50, 100, 90);
        Shot shotTest = tank.fireTank(100);
        Shot shotResult = new Shot(tank.positionTank, tank.angle, 100);

        //Kollar om fire sätter rätt position
        assertEquals(shotTest.getPosition(), shotResult.getPosition());
    }
}
