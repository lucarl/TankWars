package com.mygdx.game.model;

public class Terrain {
    private TerrainTile[][] terrainMatrix;
    private static final String imageSource = "terrain.png";
    int yPositionOffset;
    int xPositionOffset;

    public Terrain() {
        this.yPositionOffset = 200;
        this.terrainMatrix = new TerrainTile[200][40];
        for (int i = 0; i < terrainMatrix.length; i++) {
            xPositionOffset = -5;
            yPositionOffset -= 5;
            for (int j = 0; j < terrainMatrix[i].length; j++) {
                xPositionOffset += 5;
                terrainMatrix[i][j] = new TerrainTile(yPositionOffset, xPositionOffset, true);
            }
        }
    }

    public TerrainTile[][] getTerrainMatrix() {
        return terrainMatrix;
    }

}
