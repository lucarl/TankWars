package com.mygdx.game.model;


import java.util.ArrayList;
import java.util.List;

public class TankWars {
    private int playerIndex;
    private Player currentPlayer;
    private List<Player> players;
    private List<IDrawable> objects;
    private List<IDrawable> tiles;
    private List<IDrawable> shots;
    private boolean isTurnOver = false;
    private Wind wind;
    private Terrain terrain;
    private TankWarsFactory tankWarsFactory = new TankWarsFactory();
    //private List<TerrainTile> terrainTiles;


    private int round;
    private int nRounds;


    public TankWars(int nPlayers, int nRounds, Difficulty difficulty) {
        //terrainTiles = new ArrayList<>();
        players = new ArrayList<>();
        objects = new ArrayList<>();
        shots = new ArrayList<>();
        tiles = new ArrayList<>();
        wind = new Wind(difficulty);

        terrain = tankWarsFactory.setupTerrainTiles(tiles);
        tankWarsFactory.setupObjects(nPlayers, players, objects, terrain);
        round = 0;
        this.nRounds = nRounds;
        playerIndex = 0;
        currentPlayer = players.get(playerIndex);
    }

    // Game loop gets called every frame by the controller
    public void gameLoop(float delta) {
        /* TODO Check for collisions, update score, change player, check round over
         */
        update(delta);

        // If turn over and shot has landed, its the next players turn
        if (isTurnOver && shots.size() == 0) {
            nextPlayer();
        }
        // Check if shot hits any tank

        for (Player player : players) {
            CollisionRect tankRect = player.getTank().getRect();
            Tank tank = player.getTank();
            for (IDrawable drawableShot : shots) {
                // Convert to Shot type to get the collision rect
                Shot shot = (Shot)drawableShot;
                CollisionRect shotRect = shot.getRect();
                if (shotRect.collidesWith(tankRect) && tank.isAlive()
                        && shot.isAlive() && !(player == currentPlayer)) {

                    currentPlayer.addScore();
                    tank.decreaseHealth(shot.getDamage());
                    // Shot collided so we set it to dead so it gets removed later
                    shot.setAlive(false);
                    if (tank.getHealthPoints() <= 0) {
                        tank.setAlive(false);
                        tank.getGun().setAlive(false);
                    }
                }
            }
        }

        if (isRoundOver()) {
            isTurnOver = true;
        }

    }
    // TODO Display who won the round and some action to continue to next round

    /*private void setupTerrainTiles() {
        Terrain terrain = new Terrain();
        TerrainTile[][] terrainMatrix = terrain.getTerrainMatrix();
        for (int i = 0; i < terrainMatrix.length; i++) {
            for (int j = 0; j < terrainMatrix[i].length; j++) {
                objects.add(terrainMatrix[i][j]);
            }
        }
    }*/

    /*private void setupObjects(int nPlayers) {
        for (int i = 0; i < nPlayers; i++) {
            players.add(new Player());
            objects.add(players.get(i).getTank().getGun().getShot());
            objects.add(players.get(i).getTank().getGun());
            objects.add(players.get(i).getTank());
        }
    }*/

    public void update(float delta) {
        // Remove dead objects
        removeObjects();

        aim(delta);
        move(delta);

        shots.forEach(s -> {
            Shot shot = (Shot) s;
            shot.update(delta);
        });
    }

    private void removeObjects() {
        for (int i = 0; i < objects.size(); i++) {
            if (!objects.get(i).isAlive()) {
                objects.remove(i);
            }
        }
        for (int i = 0; i < tiles.size(); i++) {
            if (!tiles.get(i).isAlive()) {
                tiles.remove(i);
            }
        }
        for (int i = 0; i < shots.size(); i++) {
            if (shots.get(i) != null && !shots.get(i).isAlive()) {
                shots.remove(i);
            }
        }
    }

    private boolean isRoundOver() {
        int nTanks = 0;
        for (int i = 0; i < players.size(); i++) {
            if (players.get(i).getTank().isAlive()) {
                nTanks++;
            }
        }
        return nTanks <= 1;
    }

    // TODO se till att vänta med nextPlayer tills currentplayers skott skjutits färdigt
    public void nextPlayer() {
        playerIndex++;
        currentPlayer = players.get(playerIndex % players.size());

        while (!currentPlayer.getTank().isAlive()) {
            nextPlayer();
        }
        isTurnOver = false;
    }

    public void fire() {
        if(!isTurnOver){
            Shot shot = currentPlayer.getTank().fire(wind.getWindSpeed());
            shots.add(shot);
        }

        isTurnOver = true;
    }

    public void aim(float delta) {
        if (!isTurnOver) {
            currentPlayer.getTank().getGun().aimTank(delta);
        }
    }

    public void move(float delta) {
        if (!isTurnOver)
            currentPlayer.getTank().moveTank(delta, terrain);
    }

    public Player getPlayer() {
        return currentPlayer;
    }

    /*public List<TerrainTile> getTerrainTiles() {
        return terrainTiles;
    }*/

    public List<IDrawable> getObjects() {
        return objects;
    }

    public Wind getWind() {
        return wind;
    }

    public List<IDrawable> getTiles() {
        return tiles;
    }

    public List<IDrawable> getShots() {
        return shots;
    }
}
