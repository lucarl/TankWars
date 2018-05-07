package com.mygdx.game.model;

import java.util.List;

public class TankWarsFactory {

    public void setupObjects(int nPlayers, List<Player> players, List<IDrawable> objects) {

        for (int i = 0; i < nPlayers; i++) {
            players.add(new Player());
            objects.add(players.get(i).getTank().getGun().getShot());
            objects.add(players.get(i).getTank().getGun());
            objects.add(players.get(i).getTank());
        }
    }

    public void setupTerrainTiles(List<IDrawable> objects) {
        Terrain terrain = new Terrain();
        TerrainTile[][] terrainMatrix = terrain.getTerrainMatrix();
        for (int i = 0; i < terrainMatrix.length; i++) {
            for (int j = 0; j < terrainMatrix[i].length; j++) {
                objects.add(terrainMatrix[i][j]);
            }
        }
    }
}
