package com.mygdx.game.model;


public class TankWars {
    private int angle; // Behövs denna?
    Player player = new Player(new Tank(new Position(100,200), 100, 1000, 90));

    public Shot fire(int power) {
        Tank tank = player.getTank();
        Shot shot = tank.fireTank(power);
        return shot;
    }

    public float aim(float delta) {
        Tank tank = player.getTank();
        return tank.aimTank(delta);
    }

    public int getAngle() {
        return angle;
    }

    public Player getPlayer() {
        return player;
    }

    public Position move(float delta){
        return player.getTank().moveTank(delta);
    }

}
