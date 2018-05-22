package com.mygdx.game.model.factorys;

import com.mygdx.game.model.*;

import java.util.List;

/**
 * A factory class for creating all dynamic objects
 * during the start of the program
 *
 * @author Carl Lundborg, Adam Kjäll
 */

public class TankWarsFactory {
    private Terrain terrain = new Terrain();

    public void setupObjects(int nPlayers, List<Player> players, List<IDrawable> objects) {
        int xPos1 = 5;
        int xPos2 = 900;
        Tank tank;
        for (int i = 0; i < nPlayers; i++) {
            // Just nu sätts tanksen en bit ovanför marken å faller ner på marken,
            // Vill få dom att spawna på marken
            //Place tanks evenly on map
            if (i % 2 == 0) {
                tank = new Tank(xPos1, 0);
                xPos1 += 200;
            } else {
                tank = new Tank(xPos2, 0);
                xPos2 -= 200;
            }
            Upgrade upgrade = new Upgrade(10, 1000);
            int yPos = terrain.getMaxHeightOfCol((int) tank.getPos().getX() / terrain.getTileSize());
            tank.setPos(new Position(tank.getPos().getX(), yPos));
            players.add(new Player(tank));
            objects.add(new Upgrade(upgrade.getPos().getX(), upgrade.getPos().getY()));
            //objects.add(players.get(i).getTank().getGun().getShot());
            objects.add(players.get(i).getTank().getGun());
            objects.add(players.get(i).getTank());
        }
    }

    public void setupTerrainTiles(List<IDrawable> tiles) {
        TerrainTile[][] terrainMatrix = terrain.getTerrainMatrix();
        for (int i = 0; i < terrainMatrix.length; i++) {
            for (int j = 0; j < terrainMatrix[i].length; j++) {
                if (terrainMatrix[i][j] != null) {
                    tiles.add(terrainMatrix[i][j]);
                }
            }
        }
    }

    public Terrain getTerrain() {
        return terrain;
    }
}
