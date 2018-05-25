package com.mygdx.game.model.factorys;

import com.mygdx.game.model.*;


import com.mygdx.game.view.OptionsScreen;
import com.mygdx.game.view.OptionsScreen.*;


import java.util.ArrayList;
import java.util.List;


/**
 * A factory class for creating all dynamic objects
 * during the start of the program
 *
 * @author Carl Lundborg, Adam Kjäll
 */

public class TankWarsFactory {
    private int nPlayers = OptionsScreen.NUMBER_OF_PLAYERS;
    private Difficulty difficulty = OptionsScreen.DIFFICULTY;
    private List<Player> players;
    private List<IDrawable> objects;
    private List<IDrawable> tiles;
    private List<IDrawable> shots;
    private List<IDrawable> tanks;
    private List<IDrawable> gun;
    //private List<IDrawable> upgrade;
    private Wind wind;
    private Terrain terrain = new Terrain();
    private TankWars tankWars;

    /**
     * This constructor creates all objects and then pass these to a new TankWars
     * object.
     * @return tankWars
     */
    public TankWars makeTankWars() {
        wind = new Wind(difficulty);
        //upgrade = new ArrayList<>();
        shots = new ArrayList<>();
        players = new ArrayList<>();
        tanks = new ArrayList<>();
        objects = new ArrayList<>();
        tiles = new ArrayList<>();
        gun = new ArrayList<>();
        setupObjects(nPlayers, players, objects, tanks, gun);
        setupTerrainTiles(tiles);
        tankWars = new TankWars(terrain, players, objects, shots, tiles, wind, tanks, gun);
        return tankWars;
    }

    /**
     * Set up the amount of objects according to the number of players in the game.
     * @param nPlayers Number of players in the game
     */
    public void setupObjects(int nPlayers, List<Player> players, List<IDrawable> objects, List<IDrawable> tanks, List<IDrawable> gun) {
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
            gun.add(players.get(i).getTank().getGun());
            tanks.add(players.get(i).getTank());
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
}
