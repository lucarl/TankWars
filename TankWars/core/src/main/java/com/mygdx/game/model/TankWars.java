package com.mygdx.game.model;


public class TankWars {
    Player player = new Player(new Tank(new Position(30, 0), 50, 50, 90));


    public Shot shoot(int power, int angle) {
        Tank tank = player.getTank();
        Shot shot = tank.shootTank(power, angle);
        

        return shot;
    }

    public Position move(){
       return player.getTank().moveTank();
    }



}
