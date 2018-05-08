package com.mygdx.game.model;

import com.mygdx.game.ctrl.Controller;

public class Terrain {
    private TerrainTile[][] terrainMatrix;
    private int yPositionOffset;
    private int xPositionOffset;
    private int rows, cols;
    private int tileWidth, tileHeight;

    public Terrain() {
        yPositionOffset = 0;
        xPositionOffset = 0;
        cols = Controller.GAME_WIDTH / 5;
        rows = Controller.GAME_HEIGHT / 5 / 4;
        tileWidth = 5;
        tileHeight = 5;
        terrainMatrix = new TerrainTile[rows][cols];

        for (int i = 0; i < cols; i++) {
            for (int j = 0; j < Math.abs(Math.cos(Math.toRadians(i*2))* 20 + 5) ; j++) {
                terrainMatrix[j][i] = new TerrainTile(xPositionOffset, yPositionOffset, true);

                yPositionOffset += tileHeight;
            }
            xPositionOffset += tileWidth;
            yPositionOffset = 0;

        }


    }

    public TerrainTile[][] getTerrainMatrix() {
        return terrainMatrix;
    }

}
