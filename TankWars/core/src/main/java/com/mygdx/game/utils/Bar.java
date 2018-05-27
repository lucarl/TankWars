package com.mygdx.game.utils;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

/**
 * A progress bar widget
 * @author Adam Kjäll
 */
public class Bar extends ProgressBar {

    private int width;
    private int height;
    private Color color;

    /**
     * Constructor for bar, created a ProgressBar configured according
     * to the given parameters.
     * @param width
     * @param height
     * @param color
     */
    public Bar(int width, int height, Color color) {
        super(0f, 1f, 0.01f, false, new ProgressBarStyle());
        this.width = width;
        this.height = height;
        this.color = color;

        setupBar();
        setValue(1f);
        setAnimateDuration(0.25f);
    }

    private void setupBar() {
        // Setup the empty bar
        Pixmap pixmap = new Pixmap(0, height, Pixmap.Format.RGBA8888);
        pixmap.setColor(color);
        pixmap.fill();
        TextureRegionDrawable drawable = new TextureRegionDrawable(new TextureRegion(new Texture(pixmap)));
        pixmap.dispose();

        // Knob controls the width of the green region
        getStyle().knob = drawable;

        // Setup the full bar
        pixmap = new Pixmap(width, height, Pixmap.Format.RGBA8888);
        pixmap.setColor(color);
        pixmap.fill();
        drawable = new TextureRegionDrawable(new TextureRegion(new Texture(pixmap)));
        pixmap.dispose();

        getStyle().knobBefore = drawable;
    }
}
