package com.mygdx.game.model;


public class TankWars {
    Player player = new Player();



    public Shot shoot(int power, int angle) {
        Tank tank = player.getTank();
        Shot shot = tank.shootTank(power, angle);
        

        return shot;
    }



}
