package com.mygdx.game.model;


public class TankWars {
    private int angle; // Behövs denna?

        Tank tank = player.getTank();
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
