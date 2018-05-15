package com.mygdx.game.model;

import java.util.List;
/**
 * A factory class for creating all dynamic objects
 * during the start of the program
 *
 * @author  Carl Lundborg, Adam Kjäll
 */

public class TankWarsFactory {

    public void setupObjects(int nPlayers, List<Player> players, List<IDrawable> objects, Terrain terrain) {

        int xPos = 100;
        for (int i = 0; i < nPlayers; i++) {
            // Just nu sätts tanksen en bit ovanför marken å faller ner på marken,
            // Vill få dom att spawna på marken
            Tank tank = new Tank(xPos,0);
            int yPos = terrain.getHeightOfCol((int) tank.getPos().getX() / terrain.getTileSize() + tank.getHeight()/2);
            tank.setPos(new Position(tank.getPos().getX(), yPos));
            players.add(new Player(tank));
            //objects.add(players.get(i).getTank().getGun().getShot());
            objects.add(players.get(i).getTank().getGun());
            objects.add(players.get(i).getTank());
            xPos += 150;
        }
    }

    public void setupTerrainTiles(List<IDrawable> tiles, TerrainTile[][] terrainMatrix) {
        for (int i = 0; i < terrainMatrix.length; i++) {
            for (int j = 0; j < terrainMatrix[i].length; j++) {
                if(terrainMatrix[i][j] != null){
                    tiles.add(terrainMatrix[i][j]);
                }
            }
        }
    }
}
