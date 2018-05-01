package com.mygdx.game.model;


import java.util.ArrayList;
import java.util.List;

public class TankWars {
    private int currentPlayer;
    private List<Player> players;
    private List<IDrawable> objects;

    public TankWars(int nPlayers) {
        players = new ArrayList<>();
        objects = new ArrayList<>();
        currentPlayer = 0;

        int i=0;
        while(i < nPlayers){
            players.add(new Player());
            objects.add(players.get(i).getTank());
            objects.add(players.get(i).getTank().getGun());
            objects.add(players.get(i).getTank().getGun().getShot());
            i++;
        }

    }

    public void nextPlayer(){
        currentPlayer++;
        currentPlayer %= players.size();
    }

    public void fire() {
        players.get(currentPlayer).getTank().getGun().fire();
    }

    public float aim(float delta) {
        Tank tank = players.get(currentPlayer).getTank();
        return tank.getGun().aimTank(delta);
    }

    public Player getPlayer() {
        return players.get(currentPlayer);
    }

    public Position move(float delta){
        return players.get(currentPlayer).getTank().moveTank(delta);
    }

    public List<IDrawable> getObjects() {
        return objects;
    }
}
