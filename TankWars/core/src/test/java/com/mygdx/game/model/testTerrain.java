package com.mygdx.game.model;
import org.junit.Test;
import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

/**
 *
 * A test class for some of the methods
 * included in the Taerrain class.
 *
 * @author Patricia Zabecka, Adam Kjäll
 *
 */

public class testTerrain {

    @Test
    public void testCoordinates() {
        Terrain terrain = new Terrain();
        TerrainTile[][] terrainTiles = terrain.getTerrainMatrix();
        assertTrue(terrainTiles[0][0].getPos().getX() == 0);
        assertTrue(terrainTiles[0][0].getPos().getY() == 195);
        assertTrue(terrainTiles[0][1].getPos().getX() == 5);
        assertTrue(terrainTiles[2][0].getPos().getY() == 185);
        assertTrue(terrainTiles[2][2].getPos().getX() == 10);
        assertTrue(terrainTiles[2][2].isAlive());
    }
}
