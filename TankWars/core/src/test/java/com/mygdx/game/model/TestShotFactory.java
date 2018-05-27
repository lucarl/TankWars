package com.mygdx.game.model;

import com.mygdx.game.model.factorys.ShotFactory;
import org.junit.Before;
import org.junit.Test;
import static junit.framework.TestCase.assertTrue;
/**
 * Test class for ShotFactory.
 */
public class TestShotFactory {

    private ShotFactory shotFactory;
    private Position pos;
    private Shot shot;

    /**
     * Create objects for testing.
     */
    @Before
    public void setUp(){
        shotFactory = new ShotFactory();
        pos = new Position(1, 1);
    }

    /**
     * Test for makeTankGun().
     * @result test is passed if the correct instance of a weapon is created
     * with the appropriate properties such as angle, power etc.
     */
    @Test
    public void testMakeTankGun(){
        shot = shotFactory.makeTankGun(3, pos,1, 10,10);
        assertTrue(shot instanceof Missile);
        assertTrue(shot.getAngle() == 1);
        assertTrue(shot.getWindSpeed() == 10);
        //TODO - test more parameters and test if the method can do other types od shots
    }

}
