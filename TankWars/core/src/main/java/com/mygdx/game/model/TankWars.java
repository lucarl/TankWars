package com.mygdx.game.model;


import java.util.ArrayList;
import java.util.List;

public class TankWars {
    private int width;
    private int height;

    // Objects to render
    private List<Object> objects;

    private List<Player> players;
    private Player currentPlayer;

    public TankWars(){
        players = new ArrayList<>();
        currentPlayer = new Player(new Tank(new Position(100,50), 100, 1000));
        objects = new ArrayList<>();
        objects.add(currentPlayer.getTank());
        objects.add(currentPlayer.getTank().getGun());
        objects.add(currentPlayer.getTank().getShot());
    }

    public void update(float delta) {
        currentPlayer.getTank().update(delta);
    }

    public void fire(int power) {
        // call on tanks fire method and add the returned shot to the renderlist
        currentPlayer.getTank().fireTank(power);
    }

/*    public float aim(float delta) {
        Tank tank = currentPlayer.getTank();
        return tank.getGun().aimTank(delta);
    }

    public Position move(float delta){
        return currentPlayer.getTank().moveTank(delta);
    }
*/
    public Player getPlayer() {
        return currentPlayer;
    }

    public List<Object> getObjects() {
        return objects;
    }
}
