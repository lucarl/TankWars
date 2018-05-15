package com.mygdx.game.model;


import java.util.ArrayList;
import java.util.List;

public class TankWars {
    private int playerIndex;
    private Player currentPlayer;
    private List<IDrawable> upgrade;
    private List<Player> players;
    private List<IDrawable> objects;
    private List<IDrawable> tiles;
    private List<IDrawable> shots;
    private boolean isTurnOver = false;
    private Wind wind;
    private Terrain terrain;
    private TankWarsFactory tankWarsFactory = new TankWarsFactory();
    private TerrainTile terrainTile;
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

        //terrain = tankWarsFactory.setupTerrainTiles(tiles);
        terrain = new Terrain();
        tankWarsFactory.setupTerrainTiles(tiles, terrain.getTerrainMatrix());
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
        for (IDrawable drawableShot : shots) {
            // Convert to Shot type to get the collision rect
            Shot shot = (Shot) drawableShot;
            CollisionRect shotRect = shot.getRect();

            // TODO funkar sådär, explosionerna beter sig konstigt, inga perfekta cirkulära explosioner
            // Check collision with terrain
            int groundYPos = terrain.getHeightOfCol((int) shot.getPos().getX() / terrain.getTileSize());
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

                int midpoint = (int)shot.getPos().getY() / terrain.getTileSize();
                for (int col = shotStartCol, x = 0; col < shotEndCol; col++, x++) {
                    int yy =  x - midpoint;
                    for (int row = shotStartRow, y = 0; row < shotEndRow; row++, y++) {
                        int xx = y - midpoint;
                        if (terrainMatrix[row][col] != null && terrainMatrix[row][col].isAlive()) {
                            if (Math.sqrt(xx*xx + yy*yy) <= midpoint) {
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
                    //shot.setAlive(false);
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

    //ändrar till protected för att testa metoden
    protected void removeObjects() {
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

    //ändrar till protected för att testa metoden
    protected boolean isRoundOver() {
        int nTanks = 0;
        for (int i = 0; i < players.size(); i++) {
            if (players.get(i).getTank().isAlive()) {
                nTanks++;
            }
        }
        //if only one tank is left on the field we have a winner and the round is over
        return nTanks <= 1; //är det inte mer rimligt att ha nTanks == 1 ??
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
        if (!isTurnOver) {
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

    public boolean isTurnOver() {
        return isTurnOver;
    }


}
