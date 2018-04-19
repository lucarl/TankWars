package com.prototype.game;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.actions.*;
import javafx.scene.shape.MoveTo;

public class MyActor extends Actor {
    Sprite sprite = new Sprite(new Texture(Gdx.files.internal("badlogic.jpg")));

    public MyActor() {
        setBounds(sprite.getX(), sprite.getY(), sprite.getWidth(), sprite.getHeight());
        setTouchable(Touchable.enabled);
        addListener(new InputListener() {
            @Override
            public boolean keyDown(InputEvent event, int keycode) {
                switch (keycode) {
                    case Input.Keys.NUM_1:
                        MoveToAction mta = new MoveToAction(); //To is to move to a set coordinate
                        mta.setPosition(200f, 200f);
                        mta.setDuration(5f);
                        MyActor.this.addAction(mta); //Trigger action
                        break;
                    case Input.Keys.NUM_2:
                        MoveByAction mba = new MoveByAction(); //By is to move relative to current position
                        mba.setAmount(-200f, 0f);
                        MyActor.this.addAction(mba);
                        mba.setDuration(5f);
                        break;
                    case Input.Keys.NUM_3:
                        MoveToAction mba1 = new MoveToAction();
                        mba1.setPosition(Gdx.graphics.getWidth() - 200f, Gdx.graphics.getHeight() - 200f);
                        mba1.setDuration(3f);

                        MoveToAction mba2 = new MoveToAction();
                        mba2.setPosition(300, 100);
                        mba2.setDuration(3f);

                        ParallelAction pa = new ParallelAction(mba1, mba2); //group actions

                        MyActor.this.addAction(pa);
                        break;
                    case Input.Keys.NUM_4:
                        MoveToAction mba3 = new MoveToAction();
                        mba3.setPosition(Gdx.graphics.getWidth() - 200f, Gdx.graphics.getHeight() - 200f);
                        mba3.setDuration(3f);

                        MoveToAction mba4 = new MoveToAction();
                        mba4.setPosition(-200f, -100f);
                        mba4.setDuration(3f);

                        SequenceAction sa = new SequenceAction(mba3, mba4); //sequence actions

                        MyActor.this.addAction(sa);
                        break;
                    case Input.Keys.NUM_5:
                        RunnableAction ra = new RunnableAction(); //will run everything inside run method
                        ra.setRunnable(new Runnable() {
                            @Override
                            public void run() {
                                MyActor.this.setPosition(0f, 0f);
                            }
                        });
                        MyActor.this.addAction(ra);
                        break;
                }
                return true;
            }
        });
    }

    @Override
    protected void positionChanged() { //Link Sprite and Actors position data together
        sprite.setPosition(getX(), getY());
        super.positionChanged();
    }

    @Override
    public void draw(Batch batch, float parentAlpha) { //Actors responsibility to draw itself
        sprite.draw(batch);
    }
}

    /*@Override
    public void act(float delta) {
        super.act(delta);
    }
}*/
