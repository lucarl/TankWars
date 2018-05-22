package com.mygdx.game.model;


import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.utils.Timer;
import com.mygdx.game.Application;
import com.mygdx.game.services.Assets;

public class Tank implements IDrawable {
    private static String tankImgSrc = "tank1.png";
    private static int width = 40;
    private static int height = 20;
    private static int originX = width / 2;
    private static int originY = height / 2;
    private static final float gravity = 4f;
    private static final int speed = 100;
    private static final int roatationSpeed = 5;


    private Position pos;
    private float angle;
    private int healthPoints;
    private float fuel;
    private TankGun gun;
    private Shot shot;

    private boolean isAlive;
    private boolean rightMove;
    private boolean leftMove;

    private CollisionRect rect;

    Sound soundMove = Assets.manager.get("tanker.mp3", Sound.class);

    public Tank(float x, float y) {
        this.pos = new Position(x + width / 2, y);
        this.angle = 0;
        this.healthPoints = 100;
        this.fuel = 100;
        // Place the gun on the tank
        this.gun = new TankGun(new Position(pos.getX() + width / 2, pos.getY() + height));

        isAlive = true;
        rightMove = false;
        leftMove = false;


        rect = new CollisionRect(pos.getX(), pos.getY(), width, height);
    }

    public Shot fire(int windSpeed) {
        shot = gun.fire(windSpeed);
        return shot;
    }

    public Position moveTank(float delta, Terrain terrain) {
        // Get grounds yPos
        float currentGroundHeight = terrain.getActualHeightAtPos(
                (int) (pos.getX() + width / 2) / terrain.getTileSize(),
                (int) (pos.getY() + height) / terrain.getTileSize());
        float newXPos = rightMove ? pos.getX()  + speed * delta : pos.getX() - speed * delta;
        float newYPos = terrain.getActualHeightAtPos(
                (int) (newXPos + width / 2) / terrain.getTileSize(),
                (int) ((pos.getY() + height) / terrain.getTileSize()));

        float newAngle = 5 * (newYPos - currentGroundHeight) * terrain.getTileSize();
        float maxAngle = 150; // Not degrees

        boolean canMoveThere = newAngle <= maxAngle;

        if (pos.getY() > currentGroundHeight) {
            pos.setY(pos.getY() - gravity);
        } else {
            pos.setY(currentGroundHeight);
        }

        if (canMoveThere && isAlive && fuel > 0 && newXPos > 0 && newXPos + width < Application.GAME_WIDTH) {
            if (rightMove) {
                pos.setX(newXPos);

                angle = angle < newAngle ? Math.min(angle + roatationSpeed, newAngle) : Math.max(angle - roatationSpeed, newAngle);

                decreaseFuel();
            } else if (leftMove) {
                pos.setX(newXPos);

                angle = angle < -newAngle ? Math.min(angle + roatationSpeed, -newAngle) : Math.max(angle - roatationSpeed, -newAngle);

                decreaseFuel();
            }
        }

        rect.move(pos.getX(), pos.getY());
        // Gör så tankGun följer med tanken
        gun.setPos(new Position(pos.getX() + width / 2, pos.getY()));
        return pos;
    }


    /**
     * If the tanks go left then they will move and make a sound.
     *
     * @param b boolean variable
     */
    public void setLeftMove(boolean b) {

        final long soundMoveID = soundMove.loop(0.3f, 1.0f, 0.0f);

        Timer.schedule((new Timer.Task() {
            @Override
            public void run() {
                soundMove.loop(soundMoveID);
                soundMove.stop();
            }
        }), 1);

        if (rightMove && b) {

            rightMove = false;
        }
        leftMove = b;

    }

   /* public void setSoundMoveLeft(boolean b) {

        final long soundMoveID = soundMove.loop(0.3f, 1.0f, 0.0f);

        Timer.schedule((new Timer.Task() {
            @Override
            public void run() {
                soundMove.stop(soundMoveID);
            }
        }), 1);

        if (rightMove && b) {

            rightMove = false;
        }
        leftMove = b;

    }
    */

    /**
     * If the tanks go right then they will move and make a sound.
     *
     * @param b boolean variable
     */
    public void setRightMove(boolean b) {

        final long soundMoveID = soundMove.loop(0.3f, 1.0f, 0.0f);

        Timer.schedule((new Timer.Task() {
            @Override
            public void run() {
                soundMove.loop(soundMoveID);
                soundMove.stop();
            }
        }), 1);


        if (leftMove && b) {

            leftMove = false;
        }
        rightMove = b;

    }

   /* public void setSoundMoveRight(boolean b) {

        final long soundMoveID = soundMove.loop(0.3f, 1.0f, 0.0f);

        Timer.schedule((new Timer.Task() {
            @Override
            public void run() {
                soundMove.stop(soundMoveID);
            }
        }), 1);

        if (leftMove && b) {

            leftMove = false;
        }
        rightMove = b;

    }
    */

    //sätter till public för att göra ett test!!!
    public double decreaseFuel() {
        if (leftMove || rightMove) {
            fuel = fuel >= 0.1f ? fuel - 0.1f : 0;
        }
        return fuel;
    }

    public int decreaseHealth(int damage) {
        healthPoints = healthPoints >= damage ? healthPoints - damage : 0;
        return healthPoints;
    }

    public TankGun getGun() {
        return gun;
    }

    @Override
    public String getImgSrc() {
        return tankImgSrc;
    }

    @Override
    public Position getPos() {
        return pos;
    }

    public int getHealthPoints() {
        return healthPoints;
    }

    public Shot getShot() {
        return shot;
    }


    public float getFuel() {
        return fuel;
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }

    public float getAngle() {
        return angle;
    }

    @Override
    public int getOriginX() {
        return originX;
    }

    @Override
    public int getOriginY() {
        return originY;
    }

    @Override
    public boolean isAlive() {
        return isAlive;
    }

    public void setAlive(boolean bool) {
        isAlive = bool;
    }

    public CollisionRect getRect() {
        return rect;
    }

    public boolean isRightMove() {
        return rightMove;
    }

    public boolean isLeftMove() {
        return leftMove;
    }

    public void setPos(Position pos) {
        this.pos = pos;
        gun.setPos(new Position(pos.getX() + width / 2, pos.getY()));
    }

    public void dispose() {
        soundMove.dispose();
    }

}