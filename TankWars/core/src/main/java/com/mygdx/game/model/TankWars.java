package com.mygdx.game.model;


public class TankWars {
    private int angle; // Behövs denna?
    Player player = new Player(new Tank(new Position(1,1), 100, 1000, 90));

    public Shot fire(int power) {
        Tank tank = player.getTank();
        Shot shot = tank.fireTank(power);
        return shot;
    }

    public int aim(int i, boolean keyPressed) {
        // skicka in 0 för att öka vinkel (vänster), skicka in 1 för att minska vinkeln.
        // keyPressed = true när användaren håller ned en knapp
        Tank tank = player.getTank();
        return tank.aim(i, keyPressed);
    }

    public int getAngle() {
        return angle;
    }

   public Position move(){
        return player.getTank().moveTank();
    }



}
