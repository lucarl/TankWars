package com.mygdx.game.model;

import com.mygdx.game.model.factorys.ShotFactory;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertTrue;

public class TestShotFactory {

    private ShotFactory shotFactory;
    private Position pos;
    private Shot shot;

    @Before
    public void setUp(){
        shotFactory = new ShotFactory();
        pos = new Position(1, 1);
    }

    @Test
    public void testMakeTankGun(){
        shot = shotFactory.makeTankGun(3, pos,1, 10,10);
        assertTrue(shot instanceof Missile);
        assertTrue(shot.getAngle() == 1);
        assertTrue(shot.getWindSpeed() == 10);
        //TODO - test more parameters and test if the method can do other types od shots
    }

}
