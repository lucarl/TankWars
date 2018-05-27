package com.mygdx.game.model;

import com.mygdx.game.Application;
import com.mygdx.game.model.factorys.TankWarsFactory;
import com.mygdx.game.view.OptionsScreen;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class TankWars {

    private Player currentPlayer;
    private Wind wind;
    private Terrain terrain;
    private TankWarsFactory tankWarsFactory;

    private List<IDrawable> upgrade;
    private List<Player> players;
    private List<IDrawable> objects;
    private List<IDrawable> tiles;
    private List<IDrawable> shots;
    private List<IDrawable> tanks;
    private List<IDrawable> guns;

    private int playerIndex = 0;
    private int round = 0;

    private boolean isTurnOver = false;
    private boolean shooting = false;
    private boolean gameOver = false;

    /**
     * @param terrain
     * @param players
     * @param objects
     * @param tiles
     * @param wind
     * @param tanks
     * @param guns
     */
    public TankWars(Terrain terrain, List<Player> players, List<IDrawable> objects,
                    List<IDrawable> tiles, Wind wind, List<IDrawable> tanks, List<IDrawable> guns) {
        this.terrain = terrain;
        this.players = players;
        this.objects = objects;
        this.shots = new ArrayList<>();
        this.tiles = tiles;
        this.wind = wind;
        this.tanks = tanks;
        this.guns = guns;

        currentPlayer = players.get(playerIndex);
    }

    /**
     * Updates the world one frame
     *
     * @param delta is the time since the last call to update
     */
    public void updateWorld(float delta) {
        // Update objects positions
        updateObjects(delta);

        if (isRoundOver()) {
            // TODO save which player won the round
            round++;
            currentPlayer.addScore();
            if (round < OptionsScreen.NUMBER_OF_ROUNDS) {
                for (IDrawable drawableTile : tiles) {
                    if (drawableTile instanceof TerrainTile) {
                        TerrainTile tile = (TerrainTile) drawableTile;
                        tile.setAlive(true);
                    }
                }

                for (Player player : players) {
                    Tank tank = player.getTank();
                    tank.resetTank();
                }

            } else {
                // TODO game over
                gameOver = true;
            }
        }

        // While shooting, update shot and check for collisions
        if (shooting) {
            // This list will be of length one, future implementations could use more shots,
            // like a cluster bomb or a machine gun
            shots.forEach(drawableShot -> {
                Shot shot = (Shot) drawableShot;
                shot.update(delta);

                tanks.forEach(drawableTank -> {
                    if (drawableTank instanceof Tank) {
                        Tank tank = (Tank) drawableTank;

                        if (hasCollidedWithTank(shot, tank) || hasCollidedWithWorld(shot)) {
                            // Removes terrain around the collision and return a list with the tanks that got hit
                            ArrayList<Tank> tanksThatGotHit = removeTerrain(shot);
                            tanksThatGotHit.forEach(tankToHit -> {
                                tankToHit.decreaseHealth(shot.getDamage());

                                // If hp < 0, kill the tank
                                if (tank.getHealthPoints() <= 0) {
                                    tank.setAlive(false);
                                }
                            });
                        }
                    }
                });
                shot.setAlive(false);
                shooting = false;
                isTurnOver = true;

            });
        }

        if (isTurnOver && !shooting) {
            nextPlayer();
        }
    }


    /**
     * @param delta
     */
    private void updateObjects(float delta) {
        // Remove dead shots
        removeShots();

        // Updates position and angle of tanks & tankGuns
        aim(delta);
        move(delta);
    }

    /**
     * @param shot
     */
    private ArrayList<Tank> removeTerrain(Shot shot) {
        // Check collision with terrain
        int xCenter = (int) ((shot.getPos().getX() - shot.getWidth() / 2) / terrain.getTileSize());
        int yCenter = (int) ((shot.getPos().getY() + shot.getWidth() / 2) / terrain.getTileSize());


        int startX = (int) (xCenter - shot.getRadius()) / terrain.getTileSize() > 0 ?
                (int) (xCenter - shot.getRadius()) / terrain.getTileSize() : 0;
        int endX = (int) (xCenter + shot.getRadius()) / terrain.getTileSize() < terrain.getCols() ?
                (int) (xCenter + shot.getRadius()) / terrain.getTileSize() : terrain.getCols();
        int startY = (int) (yCenter - shot.getRadius()) / terrain.getTileSize() > 0 ?
                (int) (yCenter - shot.getRadius()) / terrain.getTileSize() : 0;
        int endY = (int) (yCenter + shot.getRadius()) / terrain.getTileSize() < terrain.getRows() ?
                (int) (yCenter + shot.getRadius()) / terrain.getTileSize() : terrain.getRows();

        TerrainTile terrainMatrix[][] = terrain.getTerrainMatrix();
        ArrayList<Tank> tanksThatGotHit = new ArrayList<>();

        for (int x = startX; x < endX; x++) {
            for (int y = startY; y < endY; y++) {

                // TODO Not working properly, this should check a circular area
                if (Math.pow(x - xCenter, 2) + Math.pow(y - yCenter, 2) <= Math.pow((int) shot.getRadius(), 2)) {
                    if (terrainMatrix[y][x] != null) {
                        terrainMatrix[y][x].setAlive(false);
                    }

                    // If there are tanks within the removed terrain, we add them to the list
                    // of tanks that got hit by the shot
                    for (Player player : players) {
                        Tank tank = player.getTank();
                        if (tank.isAlive() && tank != currentPlayer.getTank()) {
                            int tankStartX = (int) tank.getPos().getX() / terrain.getTileSize();
                            int tankEndX = (int) (tank.getPos().getY() + tank.getWidth()) / terrain.getTileSize();
                            int tankStartY = (int) tank.getPos().getY() / terrain.getTileSize();
                            int tankEndY = (int) (tank.getPos().getY() + tank.getHeight()) / terrain.getTileSize();

                            // If tank is on one of the removed tiles
                            if (x >= tankStartX && x <= tankEndX && y >= tankStartY && y <= tankEndY) {
                                // Add tank if it's not in the array
                                if (!tanksThatGotHit.contains(tank)) {
                                    tanksThatGotHit.add(tank);
                                }
                            }

                        }
                    }

                }

            }
        }
        return tanksThatGotHit;
    }

    /**
     * @param shot
     * @return
     */
    private boolean hasCollidedWithWorld(Shot shot) {
        // Return true if shot is NOT within the range [0, screenWidth]
        if (shot.getPos().getX() <= 0 || shot.getPos().getX() >= Application.GAME_WIDTH) {
            shot.setAlive(false);
            return true;
        }

        // Return true if shots y pos is less than the terrains height
        int groundHeightAtShot = terrain.getActualHeightAtPos(
                (int) shot.getPos().getX() / terrain.getTileSize(),
                (int) (shot.getPos().getY() + shot.getHeight()) / terrain.getTileSize());
        if (shot.getPos().getY() <= groundHeightAtShot) {
            shot.setAlive(false);
            return true;
        }
        return false;
    }

    /**
     * If a tank and shot collided, return that tank else returns null
     *
     * @param shot
     * @return
     */
    private boolean hasCollidedWithTank(Shot shot, Tank tank) {
        return (shot.getRect().collidesWith(tank.getRect()) && tank != currentPlayer.getTank());
    }

    /**
     * Method for removing shots from the list shots
     */
    protected void removeShots() {
        for (int i = 0; i < shots.size(); i++) {
            if (shots.get(i) != null && !shots.get(i).isAlive()) {
                shots.remove(i);
            }
        }
    }

    /**
     * @return
     */
    protected boolean isRoundOver() {
        int nTanks = 0;
        for (int i = 0; i < players.size(); i++) {
            if (players.get(i).getTank().isAlive()) {
                nTanks++;
            }
        }

        //if only one tank is left on the field we have a winner and the round is over
        return nTanks == 1;
    }

    /**
     *
     */
    public void nextPlayer() {
        playerIndex++;
        currentPlayer = players.get(playerIndex % players.size());

        while (!currentPlayer.getTank().isAlive()) {
            nextPlayer();
        }
        isTurnOver = false;
    }

    /**
     *
     */
    public void fire() {
        if (!isTurnOver) {
            Shot shot = currentPlayer.getTank().getGun().fire(wind.getWindSpeed());
            shots.add(shot);
            shooting = true;
            isTurnOver = true;
        }


    }

    /**
     * Updates the tankGuns angle
     *
     * @param delta is the time since the last frame
     */
    public void aim(float delta) {
        currentPlayer.getTank().getGun().aimTank(delta);

    }

    /**
     * Updates the tanks position
     *
     * @param delta is the time since the last frame
     */
    public void move(float delta) {
        players.forEach(player -> {
            player.getTank().moveTank(delta, terrain);
        });
    }

    public boolean isShooting() {
        return shooting;
    }

    public Player getPlayer() {
        return currentPlayer;
    }

    public List<IDrawable> getObjects() {
        return objects;
    }

    public List<IDrawable> getTanks() {
        return tanks;
    }

    public List<IDrawable> getGuns() {
        return guns;
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

    public boolean isTurnOver() {
        return isTurnOver;
    }

    /*

    public void gameLoop(float delta) {
        updateObjects(delta);

        // If turn over and shot has landed, its the next players turn
        if (isTurnOver && shots.size() == 0) {
            nextPlayer();
        }

        // Check if shot hits any tank
        for (IDrawable drawableShot : shots) {
            // Convert to Shot type to get the collision rect
            Shot shot = (Shot) drawableShot;
            CollisionRect shotRect = shot.getRect();

            // TODO funkar sådär, explosionerna beter sig konstigt, inga perfekta cirkulära explosioner
            // Check collision with terrain
            int groundYPos = terrain.getMaxHeightOfCol((int) shot.getPos().getX() / terrain.getTileSize());
            if (shot.isAlive() && shot.getPos().getY() <= groundYPos) {
                shot.setAlive(false);

                int shotStartCol = (int) (shot.getPos().getX() - shot.getRadius()) / terrain.getTileSize() > 0 ?
                        (int) (shot.getPos().getX() - shot.getRadius()) / terrain.getTileSize() : 0;
                int shotEndCol = (int) (shot.getPos().getX() + shot.getRadius()) / terrain.getTileSize() < terrain.getRows() * terrain.getTileSize() ?
                        (int) (shot.getPos().getX() + shot.getRadius()) / terrain.getTileSize() : terrain.getRows() * terrain.getTileSize();
                int shotStartRow = (int) (shot.getPos().getY() - shot.getRadius()) / terrain.getTileSize() > 0 ?
                        (int) (shot.getPos().getY() - shot.getRadius()) / terrain.getTileSize() : 0;
                int shotEndRow = (int) (shot.getPos().getY() + shot.getRadius()) / terrain.getTileSize() < terrain.getRows() ?
                        (int) (shot.getPos().getY() + shot.getRadius()) / terrain.getTileSize() : terrain.getRows();
                TerrainTile terrainMatrix[][] = terrain.getTerrainMatrix();

                int midpoint = (int) shot.getPos().getY() / terrain.getTileSize();
                for (int col = shotStartCol, x = 0; col < shotEndCol; col++, x++) {
                    int yy = x - midpoint;
                    for (int row = shotStartRow, y = 0; row < shotEndRow; row++, y++) {
                        int xx = y - midpoint;
                        if (terrainMatrix[row][col] != null && terrainMatrix[row][col].isAlive()) {
                            if (Math.sqrt(xx * xx + yy * yy) <= midpoint) {
                                terrainMatrix[row][col].setAlive(false);
                            }
                        }
                    }
                }
            }

            // Check collision with tank
            for (Player player : players) {
                CollisionRect tankRect = player.getTank().getRect();
                Tank tank = player.getTank();
                //CollisionRect tileRect = terrainTile.getRect();
                if (shotRect.collidesWith(tankRect) && tank.isAlive()
                        && shot.isAlive() && !(player == currentPlayer)) {

                    currentPlayer.addScore();
                    tank.decreaseHealth(shot.getDamage());
                    // Shot collided so we set it to dead so it gets removed later
                    shot.setAlive(false);
                    if (tank.getHealthPoints() <= 0) {
                        tank.setAlive(false);
                        tank.getGuns().setAlive(false);
                    }
                }
            }

        }


        if (isRoundOver()) {
            isTurnOver = true;
        }

    }

*/
}