package com.mygdx.game.model;


public class TankWars {
    Player player = new Player(new Tank(new Position(100,50), 100, 1000, 90));

    public void fire(int power) {
        Tank tank = player.getTank();
        tank.fireTank(power);

    }

    public float aim(float delta) {
        Tank tank = player.getTank();
        return tank.aimTank(delta);
    }

    public Player getPlayer() {
        return player;
    }

    public Position move(float delta){
        return player.getTank().moveTank(delta);
    }

}
