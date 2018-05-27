package com.mygdx.game.model;

import com.mygdx.game.Application;
import org.junit.Test;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;

/**
 * A test class for Shot where the updateObjects()
 * method is tested.
 *
 * @author Patricia Zabecka
 *
 */
public class TestShot {

    /**
     * Create Shot object and call updateObjects().
     * @result The object is not visible if its x position is bigger
     * than the game screen's width.
     *
     */
    @Test
    public void testUpdate(){
        Shot shot = new StandardShot(new Position(Application.GAME_WIDTH +1,0), 90, 0.1f, 50);
        shot.update(System.nanoTime());
        assertFalse(shot.isAlive());
    }
}


