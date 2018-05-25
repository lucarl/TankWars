package com.mygdx.game.model;

import com.mygdx.game.Application;
import com.mygdx.game.events.Event;
import com.mygdx.game.events.EventBus;
import com.mygdx.game.model.factorys.TankWarsFactory;
import com.mygdx.game.view.OptionsScreen;

import java.util.List;

public class TankWars {

    private Player currentPlayer;
    private Wind wind;
    private Terrain terrain;
    private TankWarsFactory tankWarsFactory;

    private List<IDrawable> upgrade;
    private List<Player> players;
    private List<IDrawable> objects; // TODO se över om denna är användbar
    private List<IDrawable> tiles;
    private List<IDrawable> shots;
    private List<IDrawable> tanks;
    private List<IDrawable> gun;

    private int playerIndex = 0;
    private int round = 0;

    private boolean isTurnOver = false;
    private boolean shooting = false;
    private boolean gameOver = false;

    public TankWars(Terrain terrain, List<Player> players, List<IDrawable> objects, List<IDrawable> shots,
                    List<IDrawable> tiles, Wind wind, List<IDrawable> tanks, List<IDrawable> gun) {
        this.terrain = terrain;
        this.players = players;
        this.objects = objects;
        this.shots = shots;
        this.tiles = tiles;
        this.wind = wind;
        this.tanks = tanks;
        this.gun = gun;

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
                for (int i = 0; i < tiles.size(); i++) {
                    TerrainTile tile = (TerrainTile) tiles.get(i);
                    tile.setAlive(true);
                }
                for (int i = 0; i < tanks.size(); i++) {
                    Tank tank = (Tank) tanks.get(i);
                    TankGun gun = (TankGun) getGun().get(i);
                    tank.setAlive(true);
                    gun.setAlive(true);

                }

                /**
                 * TODO make a new tank for every player
                 * and a new terrain
                 */

            } else {
                // TODO game over
                gameOver = true;
            }
        }

        // While shooting, update shot and check for collisions
        if (shooting) {
            shots.forEach(drawableShot -> {
                Shot shot = (Shot) drawableShot;
                shot.update(delta);
                Tank tank = hasCollidedWithTank(shot);
                if (tank != null) {
                    // Removes terrain around the collision
                    shotExplosion(shot);
                    shot.setAlive(false);
                    shooting = false;
                    isTurnOver = true;
                    // If hp < 0, kill the tank
                    if (tank.getHealthPoints() <= 0) {
                        tank.setAlive(false);
                        tank.getGun().setAlive(false);
                        EventBus.BUS.publish(new Event(Event.Tag.PLAY_ANIMATION_EXPLOSION, tank));
                    }

                    // Send explosion event
                    EventBus.BUS.publish(new Event(Event.Tag.PLAY_SOUND_EXPLOSION, null));
                } else if (hasCollidedWithWorld(shot)) {
                    /**
                     * TODO hade vart nice ifall explosionen skadar
                     * alla tanks den träffar :)
                     */
                    // Removes terrain around the collision
                    shotExplosion(shot);
                    shot.setAlive(false);
                    shooting = false;
                    isTurnOver = true;
                    // Send explosion event
                    EventBus.BUS.publish(new Event(Event.Tag.PLAY_SOUND_EXPLOSION, null));
                }
            });
        }

        if (isTurnOver && !shooting) nextPlayer();
    }

    private void updateObjects(float delta) {
        // Remove dead objects
        removeObjects();

        // Updates position and angle of tanks & tankGuns
        aim(delta);
        move(delta);
    }

    private void shotExplosion(Shot shot) {
        // Check collision with terrain

        int startCol = (int) (shot.getPos().getX() - shot.getRadius()) / terrain.getTileSize() > 0 ?
                (int) (shot.getPos().getX() - shot.getRadius()) / terrain.getTileSize() : 0;
        int endCol = (int) (shot.getPos().getX() + shot.getWidth() + shot.getRadius()) / terrain.getTileSize() < terrain.getCols() ?
                (int) (shot.getPos().getX() + shot.getWidth() + shot.getRadius()) / terrain.getTileSize() : terrain.getCols();
        int startRow = (int) (shot.getPos().getY() - shot.getRadius()) / terrain.getTileSize() > 0 ?
                (int) (shot.getPos().getY() - shot.getRadius()) / terrain.getTileSize() : 0;
        int endRow = (int) (shot.getPos().getY() + shot.getHeight() + shot.getRadius()) / terrain.getTileSize() < terrain.getRows() ?
                (int) (shot.getPos().getY() + shot.getHeight() + shot.getRadius()) / terrain.getTileSize() : terrain.getRows();

        int startX = (int) ((shot.getPos().getX() - shot.getWidth()/2) / terrain.getTileSize());
        int startY = (int) ((shot.getPos().getY() + shot.getWidth()/2) / terrain.getTileSize());

        TerrainTile terrainMatrix[][] = terrain.getTerrainMatrix();

        for (int col = startCol; col < endCol; col++) {
            for (int row = startRow; row < endRow; row++) {
                if (terrainMatrix[row][col] != null) {
                    // TODO cirkulära explosioner
                    if (Math.pow(col - startX, 2) + Math.pow(row - startY, 2) <= Math.pow((int) shot.getRadius(), 2)) {
                        terrainMatrix[row][col].setAlive(false);

                        // If tank is within the shot explosion it should take damage
                        // TODO funkar inte riktigt
                        for (int i = 0; i < players.size(); i++) {
                            Tank tank = players.get(i).getTank();
                            if (tank.isAlive() && tank != currentPlayer.getTank()) {
                                int x = (int) tank.getPos().getX() / terrain.getTileSize();
                                int y = (int) tank.getPos().getY() / terrain.getTileSize();
                                if (x == col && y == row) {
                                    tank.decreaseHealth(shot.getDamage());
                                }

                            }
                        }

                    }
                }
            }
        }
    }


        private boolean hasCollidedWithWorld (Shot shot){
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

        // If a tank and shot collided, return that tank else returns null
        private Tank hasCollidedWithTank (Shot shot){
            for (Player player : players) {
                if (player.getTank().isAlive() && player != currentPlayer) {
                    if (shot.getRect().collidesWith(player.getTank().getRect())) {
                        return player.getTank();
                    }
                }
            }
            return null;
        }

        //ändrar till protected för att testa metoden

        /**
         * Method for removing objects such as shots and tiles.
         * When the shot is removed a sound is added.
         */
        /**
         * TODO samma här, ut med libgdx grejerna till viewn eller någon annan lyssnare
         */
        protected void removeObjects () {
            for (int i = 0; i < shots.size(); i++) {
                if (shots.get(i) != null && !shots.get(i).isAlive()) {
                    shots.remove(i);
                }

            }

        }

        //ändrar till protected för att testa metoden
        protected boolean isRoundOver () {
            int nTanks = 0;
            for (int i = 0; i < players.size(); i++) {
                if (players.get(i).getTank().isAlive()) {
                    nTanks++;
                }
            }

            //if only one tank is left on the field we have a winner and the round is over
            return nTanks <= 1; //är det inte mer rimligt att ha nTanks == 1 ??
        }

        public void nextPlayer () {
            playerIndex++;
            currentPlayer = players.get(playerIndex % players.size());

            while (!currentPlayer.getTank().isAlive()) {
                nextPlayer();
            }
            isTurnOver = false;
        }

        public void fire () {
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
        public void aim ( float delta){
            currentPlayer.getTank().getGun().aimTank(delta);

        }

        /**
         * Updates the tanks position
         *
         * @param delta is the time since the last frame
         */
        public void move ( float delta){
            players.forEach(player -> {
                player.getTank().moveTank(delta, terrain);
            });
        }

        public Player getPlayer () {
            return currentPlayer;
        }

        public List<IDrawable> getObjects () {
            return objects;
        }

        public List<IDrawable> getTanks () {
            return tanks;
        }

        public List<IDrawable> getGun () {
            return gun;
        }

        public Wind getWind () {
            return wind;
        }

        public List<IDrawable> getTiles () {
            return tiles;
        }

        public List<IDrawable> getShots () {
            return shots;
        }

        public boolean isTurnOver () {
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
                        tank.getGun().setAlive(false);
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