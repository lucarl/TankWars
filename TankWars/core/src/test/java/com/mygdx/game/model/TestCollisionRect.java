package com.mygdx.game.model;

import org.junit.Test;
import static junit.framework.TestCase.assertTrue;

/**
 *
 * A test class for the methods
 * included in the CollisionRect class.
 *
 *  @author Patricia Zabecka
 *
 */

public class TestCollisionRect {
 /**
  * Create CollisionRect object and move it.
  * @result The test will be passed if the coordinates new coordinates
  * ,after the object is moved, are equal to the coordinateds' expected values.
  *
  */
   @Test
    public void testMove(){
    CollisionRect startValue = new CollisionRect(10,10,10,10);
    startValue.move(1,1);
    float expectedX = startValue.getX();
    float expectedY = startValue.getY();
    assertTrue(expectedX == 1 && expectedY == 1);
    }
 /**
  * Create two CollisionRect objects and test if they collide with each other .
  * @result the test will be passed if the conditions for the ranges of the rectangles are satisfied.
  *
  */
    @Test
    public void testCollideWith(){
    CollisionRect collisionTest = new CollisionRect(2,2,4,4);
    assertTrue(collisionTest.collidesWith(new CollisionRect(5,5,5,5)));
    }
}
