package com.mygdx.game.model;

public class Terrain {
    private TerrainTile[][] terrainMatrix;
    private static final String imageSource = "terrain.png";

    public Terrain() {
        this.terrainMatrix = new TerrainTile[1000][200];
        for (int i = 0; i < terrainMatrix.length; i++) {
            for (int j = 0; j < terrainMatrix[i].length; j++) {
                terrainMatrix[i][j] = new TerrainTile(i * 5, j * 5, true);
            }
        }
    }

    public TerrainTile[][] getTerrainMatrix() {
        return terrainMatrix;
    }

}
