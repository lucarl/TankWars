package com.mygdx.game.model.factorys;

import com.mygdx.game.Application;
import com.mygdx.game.model.*;


import com.mygdx.game.view.OptionsScreen;


import java.util.ArrayList;
import java.util.List;


/**
 * A factory class for creating all objects
 * during the start of the program
 *
 * @author Carl Lundborg, Adam Kjäll
 * revised by Carl Lundborg
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

    private Wind wind;
    private Terrain terrain = new Terrain();
    private TankWars tankWars;

    /**
     * This constructor creates all objects and then pass these to a new TankWars
     * object.
     *
     * @return tankWars
     */
    public TankWars makeTankWars() {
        wind = new Wind(difficulty);
        players = new ArrayList<>();
        tanks = new ArrayList<>();
        objects = new ArrayList<>();
        tiles = new ArrayList<>();
        gun = new ArrayList<>();
        setupObjects(nPlayers, players, objects, tanks, gun);
        setupTerrainTiles(tiles);
        tankWars = new TankWars(terrain, players, objects, tiles, wind, tanks, gun);
        return tankWars;
    }

    /**
     * Set up the amount of objects according to the number of players in the game.
     *
     * @param nPlayers Number of players in the game
     */
    public void setupObjects(int nPlayers, List<Player> players, List<IDrawable> objects, List<IDrawable> tanks, List<IDrawable> gun) {
        int xPos;
        Tank tank;
        for (int i = 0; i < nPlayers; i++) {
            xPos = generateXPos(i);
            tank = new Tank(xPos, 0);
            Upgrade upgrade = new Upgrade(10, 1000);
            players.add(new Player(tank, "Player " + (i+1)));
            objects.add(new Upgrade(upgrade.getPos().getX(), upgrade.getPos().getY()));
            gun.add(players.get(i).getTank().getGun());
            tanks.add(players.get(i).getTank());
        }
    }

    /**
     * Placing objects evenly on map
     * @param i
     * @return x position for placement
     */
    public int generateXPos(int i) {
        int xPos = 50; // start value acts as padding for both sides
        int offset = Application.GAME_WIDTH / OptionsScreen.NUMBER_OF_PLAYERS / 2;

        if (i % 2 == 0) {
            xPos = xPos + offset * i;
        } else {
            xPos = Application.GAME_WIDTH - xPos - offset * (i-1) - 40; // - 40 for tank width
        }
        return xPos;
    }


    /**
     * Sets up the terrain matrix with tiles
     *
     * @param tiles individual tiles for the terrain
     */
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
