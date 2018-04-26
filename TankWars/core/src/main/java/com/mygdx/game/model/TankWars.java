package com.mygdx.game.model;


public class TankWars {
    private int angle; // Behövs denna?
    Player player = new Player(new Tank(new Position(100,200), 100, 1000, 90));

    public Shot fire(int power) {
        Tank tank = player.getTank();
        Shot shot = tank.fireTank(power);
        return shot;
    }

    public int aim(int i, boolean keyPressed, float delta) {
        // skicka in 0 för att öka vinkel (vänster), skicka in 1 för att minska vinkeln.
        // keyPressed = true när användaren håller ned en knapp
        Tank tank = player.getTank();
        return tank.aim(i, keyPressed, delta);
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
