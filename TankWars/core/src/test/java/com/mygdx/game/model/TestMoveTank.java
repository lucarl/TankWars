package com.mygdx.game.model;

import com.badlogic.gdx.Gdx;
import org.junit.Test;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;

public class TestMoveTank {

    @Test
    public void testMoveRight(){
        //TankWars tankWars = new TankWars();
        Tank tank = new Tank(new Position(30, 0), 50, 100, 90);
        tank.setRightMove(true);
        tank.moveTank(Gdx.graphics.getDeltaTime());

        assertTrue(  30 < tank.getPositionTank().getX());
    }

    @Test
    public void testMoveTankLeft(){
        //test2 - checks change in x-coordinate to the left...
        Tank tank = new Tank(new Position(30, 0), 50, 50, 90);
        tank.setLeftMove(true);
        tank.moveTank(Gdx.graphics.getDeltaTime());

        assertTrue(  30 > tank.getPositionTank().getX());
    }



}
