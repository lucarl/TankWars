package com.prototype.FlappyBirdDemo.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.prototype.FlappyBirdDemo.FlappyBird;
import com.prototype.FlappyBirdDemo.sprites.Background;
import com.prototype.FlappyBirdDemo.sprites.Bird;
import com.prototype.FlappyBirdDemo.sprites.Ground;
import com.prototype.FlappyBirdDemo.sprites.Tube;


public class PlayState extends State {
    private static final int TUBE_SPACING = 125;
    private static final int TUBE_COUNT = 4;

    private Array<Tube> tubes;

    private Bird bird;
    private Background bg;
    private Ground ground;


    public PlayState(GameStateManager gsm) {
        super(gsm);
        bird = new Bird(50, 300);
        bg = new Background();
        ground = new Ground(cam.position.x - cam.viewportWidth / 2, 0);
        cam.setToOrtho(false, FlappyBird.WIDTH / 2, FlappyBird.HEIGHT / 2);

        tubes = new Array<Tube>();
        for (int i = 1; i <= TUBE_COUNT; i++) {
            tubes.add(new Tube(i * (TUBE_SPACING + Tube.TUBE_WIDTH)));
        }

    }



    @Override
    protected void handleInput() {
        if (Gdx.input.justTouched()) {
            bird.jump();
        }
        if(Gdx.input.isKeyPressed(Input.Keys.ESCAPE)){
            gsm.set(new MenuState(gsm));
        }
    }

    @Override
    public void update(float dt) {
        handleInput();
        updateGround();
        bird.update(dt);
        cam.position.x = bird.getPos().x + 80;

        for (int i = 0; i < tubes.size; i++) {
            Tube tube = tubes.get(i);

            if (cam.position.x - cam.viewportWidth / 2 >
                    tube.getPosTopTube().x + tube.getTopTube().getWidth()) {
                tube.reposition(tube.getPosTopTube().x + (Tube.TUBE_WIDTH + TUBE_SPACING) * TUBE_COUNT);
            }

            if (tube.hasCollided(bird.getBounds())) {
                gsm.set(new PlayState(gsm));
            }
        }
        if(bird.getPos().y <= ground.getTexture().getHeight() + ground.GROUND_Y_OFFSET){
            gsm.set(new PlayState(gsm));
        }

        cam.update();
    }

    private void updateGround(){
        if (cam.position.x - cam.viewportWidth / 2 > ground.getGroundPos1().x + ground.getTexture().getWidth()){
            ground.updatePos1();
        }
        if(cam.position.x - cam.viewportWidth / 2 > ground.getGroundPos2().x + ground.getTexture().getWidth()){
            ground.updatePos2();
        }
    }



    @Override
    public void render(SpriteBatch sb) {
        // ser till så vi inte ritar ut saker utanför viewport
        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        sb.draw(bg.getTexture(), cam.position.x - cam.viewportWidth / 2, 0);
        sb.draw(bird.getTexture(), bird.getPos().x, bird.getPos().y);
        for (Tube tube : tubes) {
            sb.draw(tube.getTopTube(), tube.getPosTopTube().x, tube.getPosTopTube().y);
            sb.draw(tube.getBottomTube(), tube.getPosBotTube().x, tube.getPosBotTube().y);
        }

        sb.draw(ground.getTexture(), ground.getGroundPos1().x, ground.getGroundPos1().y);
        sb.draw(ground.getTexture(), ground.getGroundPos2().x, ground.getGroundPos2().y);
        sb.end();
    }

    @Override
    public void dispose() {
        bg.dispose();
        bird.dispose();
        ground.dispose();
        for (Tube tube : tubes) {
            tube.dispose();
        }
        System.out.println("Play state disposed");
    }
}
