package com.mygdx.game.model;


import com.mygdx.game.ctrl.Controller;

public class Tank implements IDrawable {
    private static String tankImgSrc = "tank1.png";
    private static int width = 40;
    private static int height = 20;
    private static int originX = width / 2;
    private static int originY = height / 2;
    private static final int speed = 100;


    private Position pos;
    private float angle;
    private int healthPoints;
    private float fuel;
    private TankGun gun;

    private boolean isVisible;
    private boolean rightMove;
    private boolean leftMove;

    private CollisionRect rect;

    public Tank(float x, float y) {
        this.pos = new Position(x + width / 2, y);
        this.angle = 0;
        this.healthPoints = 100;
        this.fuel = 100;
        // Place the gun on the tank
        this.gun = new TankGun(new Position(pos.getX() + width / 2, pos.getY() + height));

        isVisible = true;
        rightMove = false;
        leftMove = false;


        rect = new CollisionRect(pos.getX(), pos.getY(), width, height);
    }

    public Shot fire(int windSpeed) {
        return gun.fire(windSpeed);
        }

    public Position moveTank(float delta, Terrain terrain) {
        // Get grounds yPos
        float currentGroundHeight = terrain.getHeightOfCol((int) (pos.getX() + width / 2) / terrain.getTileSize());
        float newPos = rightMove ? pos.getX() + speed * delta : pos.getX() - speed * delta;
        float newGroundHeight = terrain.getHeightOfCol((int) (newPos + width / 2) / terrain.getTileSize());
        float maxHeightDifference = terrain.getTileSize() * 2f;
        boolean canMoveThere = newGroundHeight - currentGroundHeight < maxHeightDifference;

        if (pos.getY() > currentGroundHeight) pos.setY(currentGroundHeight);

        if (canMoveThere && isVisible && fuel > 0 && pos.getX() > 0 && pos.getX() + width < Controller.GAME_WIDTH) {
            if (rightMove) {
                pos.setX(pos.getX() + speed * delta);

                // Set tank yPos = groundYPos
                pos.setY(newGroundHeight);
                rect.move(pos.getX(), newGroundHeight);

                decreaseFuel();
            } else if (leftMove) {
                pos.setX(pos.getX() - speed * delta);

                // Set tank yPos = groundYPos
                pos.setY(newGroundHeight);
                rect.move(pos.getX(), newGroundHeight);

                decreaseFuel();
            }
        }
        // Gör så tankGun följer med tanken
        gun.setPos(new Position(pos.getX() + width / 2, pos.getY()));
        return pos;
    }

    public void setLeftMove(boolean b) {
        if (rightMove && b) {
            rightMove = false;
        }
        leftMove = b;
    }

    public void setRightMove(boolean b) {
        if (leftMove && b) {
            leftMove = false;
        }
        rightMove = b;
    }

    //sätter till public för att göra ett test!!!
    public double decreaseFuel() {
        if (leftMove || rightMove) {
            this.fuel -= 0.1f;
        }
        return this.fuel;
    }

    public int decreaseHealth(int damage) {
        this.healthPoints -= damage;
        return this.healthPoints;
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
        return 0;
    }

    @Override
    public int getOriginY() {
        return 0;
    }

    @Override
    public boolean isAlive() {
        return isVisible;
    }

    public void setAlive(boolean bool) {
        isVisible = bool;
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
}
