package com.mygdx.game.model;


import java.util.ArrayList;
import java.util.List;

public class TankWars {
    private int playerIndex;
    private Player currentPlayer;
    private List<Player> players;
    private List<IDrawable> objects;

    private int round;
    private int nRounds;

    public TankWars(int nPlayers, int nRounds) {
        players = new ArrayList<>();
        objects = new ArrayList<>();

        setupObjects(nPlayers);

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
        // Check if shot hits any tank
        for (Player playersShot : players) {
            CollisionRect shotRect = playersShot.getTank().getGun().getShot().getRect();
            Shot shot = playersShot.getTank().getGun().getShot();
            for (Player player : players) {
                CollisionRect tankRect = player.getTank().getRect();
                Tank tank = player.getTank();
                if (shotRect.collidesWith(tankRect) && tank.isVisible() && shot.isVisible()) {
                    // TODO förbättra bortagandet av obj här och i renderer
                    playersShot.addScore();
                    tank.setVisibility(false);
                    tank.getGun().setVisibility(false);
                    tank.getGun().getShot().setVisibility(false);
                    objects.remove(tank);
                    objects.remove(tank.getGun());
                    objects.remove(shot);
                }
            }
        }

        if(isRoundOver()){
            System.out.println("ROUND OVER");

            // TODO Display who won the round and some action to continue to next round
        }
    }

    private void setupObjects(int nPlayers){
        for (int i = 0; i < nPlayers; i++) {
            players.add(new Player());
            objects.add(players.get(i).getTank().getGun().getShot());
            objects.add(players.get(i).getTank().getGun());
            objects.add(players.get(i).getTank());
        }
    }

    public void update(float delta) {
        aim(delta);
        move(delta);

        players.forEach(player -> {
            player.getTank().getGun().getShot().update(delta);
        });
    }

    private boolean isRoundOver() {
        int nTanks = 0;
        for (int i = 0; i < players.size(); i++) {
            if(players.get(i).getTank().isVisible()){
                nTanks++;
            }
        }
        return nTanks <= 1;
    }

    // TODO se till att vänta med nextPlayer tills currentplayers skott skjutits färdigt
    public void nextPlayer() {
        playerIndex++;
        currentPlayer = players.get(playerIndex % players.size());

        //while(!currentPlayer.getTank().isVisible()){
        //        nextPlayer();
        //}
    }

    public void fire() {
        Shot shot = currentPlayer.getTank().fire();
        objects.add(shot);

    }

    public float aim(float delta) {
        Tank tank = currentPlayer.getTank();
        return tank.getGun().aimTank(delta);
    }

    public Position move(float delta) {
        return currentPlayer.getTank().moveTank(delta);
    }

    public Player getPlayer() {
        return currentPlayer;
    }



    public List<IDrawable> getObjects() {
        return objects;
    }


}
