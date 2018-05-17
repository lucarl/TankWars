package com.mygdx.game.model;

import com.mygdx.game.Application;
import org.junit.Test;
import static junit.framework.TestCase.assertTrue;

/**
 * A test class for Shot where the update()
 * method is tested.
 *
 */

public class TestShot {
    /**
     * Create Shot object and call update().
     * @result The object is not visible if its x position is bigger
     * than the game screen's width.
     *
     */
    @Test
    public void testUpdate() {
        Shot shot = new StandardShot(new Position(Application.GAME_WIDTH +1,10), 90, 0.1f, 50);
        shot.update(System.nanoTime());
        assertTrue(!shot.isAlive());
    }

    @Test
    public void testName() {
        String name;
        String name1;
        Shot shot = new StandardShot(new Position(Application.GAME_WIDTH +1,10), 90, 0.1f, 50);
        Shot shot1 = new NukeShot(new Position(Application.GAME_WIDTH +1,10), 90, 0.1f, 50);
        name = shot.getName();
        System.out.println(name);
        name1 = shot1.getName();
        System.out.println(name1);
    }
}
