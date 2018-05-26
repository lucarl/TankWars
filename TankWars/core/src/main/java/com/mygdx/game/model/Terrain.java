package com.mygdx.game.model;

import com.mygdx.game.Application;

import static java.lang.Math.*;

/**
 * A class representing the terrain by creating it using
 * the class TerrainTile and storing each object in a
 * matrix of the class
 *
 * @author Carl Lundborg, Adam Kjäll
 */

public class Terrain {
    private TerrainTile[][] terrainMatrix;
    private int rows, cols;
    private int x, y;
    private int tileSize;

    public Terrain() {
        tileSize = 2;
        x = 0;
        y = 0;
        cols = Application.GAME_WIDTH / tileSize;
        rows = Application.GAME_HEIGHT / tileSize;

        terrainMatrix = new TerrainTile[rows][cols];

        // TODO Out of bounds sometimes
        for (int col = 0; col < cols; col++) {
            for (int row = 0; row < min(abs(cos(toRadians(col)) * 50 + 50), rows); row++) {
                terrainMatrix[row][col] = new TerrainTile(x + col * tileSize,
                        y + row * tileSize, true, tileSize);

            }
        }

    }

    /**
     *
     * @param col
     * @return
     */

    // Finds the heighest point of the ground at a given column (convert float x pos to column pos first)
    public int getMaxHeightOfCol(int col) {
        int colHeight = 0;
        if (col >= 0 && col <= cols) {
            for (int row = 0; row < rows; row++) {
                if (terrainMatrix[row][col] != null && terrainMatrix[row][col].isAlive()) {
                    colHeight = row * tileSize;
                }
            }
        }
        return colHeight;
    }

    /**
     *
     * @param col
     * @param rows
     * @return
     */
    // Finds the height of the ground at a point (e.g. could be a hole or a tunnel in the terrain)
    public int getActualHeightAtPos(int col, int rows) {
        int colHeight = 0;
        if (col >= 0 && col <= cols) {
            for (int row = 0; row <= rows; row++) {
                if (terrainMatrix[row][col] != null && terrainMatrix[row][col].isAlive()) {
                    colHeight = row * tileSize;
                }
            }
        }
        return colHeight;
    }

    public TerrainTile[][] getTerrainMatrix() {
        return terrainMatrix;
    }

    public int getTileSize() {
        return tileSize;
    }

    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }
}
