package com.mygdx.game.model;


import com.mygdx.game.ctrl.Controller;


public class Tank implements IDrawable {
    private static String tankImgSrc = "tank14.png";
    private static int width = 80;
    private static int height = 45;
    private static int originX = width / 2;
    private static int originY = height / 2;
    private static final int speed = 150;


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
        this.pos = new Position(x + width/2, y);
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
        //if (isVisible) {
            return gun.fire(windSpeed);
        //}
        //return gun.getShot();
    }

    public Position moveTank(float delta, Terrain terrain) {
        // Get grounds yPos
        float groundYPos = terrain.getHeightOfCol((int)(pos.getX()+width/2) / terrain.getTileWidth());
        if(pos.getY() > groundYPos) pos.setY(groundYPos);
        if (isVisible && fuel > 0 && pos.getX() > 0 && pos.getX() + width < Controller.GAME_WIDTH) {
            if (rightMove) {
                pos.setX(pos.getX() + speed * delta);

                // Set tank yPos = groundYPos
                pos.setY(groundYPos);

                rect.move(pos.getX(), groundYPos);
                decreaseFuel();
            }

            if (leftMove) {
                pos.setX(pos.getX() - speed * delta);
                // Get grounds yPos
                float yPos = terrain.getHeightOfCol((int)(pos.getX()+width/2) / terrain.getTileWidth());
                // Set tank yPos + heightOffset
                pos.setY(yPos);
                rect.move(pos.getX(), yPos);
                decreaseFuel();
            }
        }
        // Gör så tankGun följer med tanken
        gun.setPos(new Position(pos.getX() + width/2, pos.getY()));
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
    public boolean isVisible() {
        return isVisible;
    }

    public void setVisibility(boolean bool) {
        isVisible = bool;
    }

    public CollisionRect getRect() {
        return rect;
    }

    public boolean isRightMove() { return rightMove; }

    public boolean isLeftMove() { return leftMove; }

    public void setPos(Position pos) {
        this.pos = pos;
    }
}
