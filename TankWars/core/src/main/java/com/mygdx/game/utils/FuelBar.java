package com.mygdx.game.utils;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class FuelBar extends ProgressBar {

    private int width;
    private int height;

    public FuelBar(int width, int height) {
        super(0f, 1f, 0.01f, false, new ProgressBarStyle());
        this.width = width;
        this.height = height;

        setupBar();

        setAnimateDuration(0.0f);
        setValue(1f);

        setAnimateDuration(0.25f);
    }

    private void setupBar() {

        // Setup the red part of the hp bar
        Pixmap pixmap = new Pixmap(width, height, Pixmap.Format.RGBA8888);
        pixmap.setColor(Color.BLACK);
        pixmap.fill();
        TextureRegionDrawable drawable = new TextureRegionDrawable(new TextureRegion(new Texture(pixmap)));
        pixmap.dispose();

        // Setup the style
        getStyle().background = drawable;

        // Setup the green part of the hp bar
        pixmap = new Pixmap(0, height, Pixmap.Format.RGBA8888);
        pixmap.setColor(Color.RED);
        pixmap.fill();
        drawable = new TextureRegionDrawable(new TextureRegion(new Texture(pixmap)));
        pixmap.dispose();

        // Knob controlls the width of the green region
        getStyle().knob = drawable;

        // Setup the green part of the hp bar
        pixmap = new Pixmap(width, height, Pixmap.Format.RGBA8888);
        pixmap.setColor(Color.RED);
        pixmap.fill();
        drawable = new TextureRegionDrawable(new TextureRegion(new Texture(pixmap)));
        pixmap.dispose();

        getStyle().knobBefore = drawable;


    }
}

