package com.mygdx.game.model;
import org.junit.Before;
import org.junit.Test;
import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

/**
 *
 * A test class for some of the methods
 * included in the Taerrain class.
 *
 * @author Adam Kjäll
 * Revised by: Patricia Zabecka, Adam Kjäll
 *
 */

public class testTerrain {

    private Terrain terrain;
    private TerrainTile[][] terrainTiles;

    /**
     * Creates objects for testing.
     */
    @Before
    public void setUp(){
        terrain = new Terrain();
        terrainTiles = terrain.getTerrainMatrix();
    }

    //TODO - test methods of the Terrain class here.

}
