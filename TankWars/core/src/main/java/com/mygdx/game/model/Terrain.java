package com.mygdx.game.model;

/**
 * Created by Carl on 2018-05-03.
 */
public class Terrain {
    private TerrainTile[][] terrainMatrix;

    public void drawTerrain(String imageSource, float x, float y, float width, float height) {
    }

    public Terrain() {
        terrainMatrix = new TerrainTile[1000][200];
        for (int i = 0; i < terrainMatrix.length; i++) {
            for (int j = 0; j < terrainMatrix[i].length; j++) {
                terrainMatrix[i][j] = new TerrainTile(i * 5, j * 5);
            }
        }
    }

    public void draw() {
        for (int i = 0; i < terrainMatrix.length; i++) {
            for (int j = 0; j < terrainMatrix[i].length; j++) {
                TerrainTile t = terrainMatrix[i][j];
                
            }
        }
    }
}
