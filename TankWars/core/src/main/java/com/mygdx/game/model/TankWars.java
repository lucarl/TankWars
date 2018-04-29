package com.mygdx.game.model;


public class TankWars {
    private Player currentPlayer;
    Player player = new Player(new Tank(new Position(500,300), 100, 100, 90));

    public TankWars() {
        currentPlayer = player;
    }

    public void fire() {
        player.getTank().fireTank();
    }

    public float aim(float delta) {
        Tank tank = player.getTank();
        return tank.aimTank(delta);
    }

    public Player getPlayer() {
        return currentPlayer;
    }

    public Position move(float delta){
        return player.getTank().moveTank(delta);
    }


}
