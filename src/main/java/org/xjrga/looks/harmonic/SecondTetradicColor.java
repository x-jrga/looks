package org.xjrga.looks.harmonic;

import java.awt.Color;

/**
 * This class is a harmonic color
 *
 * @author Jorge R Garcia de Alba
 */
public class SecondTetradicColor implements HarmonicColor {

    private Color color = null;
    private final float angle = 180f;
    private final ColorGenerator colorGenerator;

    public SecondTetradicColor(Color color) {
        this.color = color;
        colorGenerator = new ColorGenerator(color);
    }

    @Override
    public Color getBaseColor() {
        return color;
    }

    @Override
    public Color getColor() {
        return colorGenerator.generateColorUsingHueChange(angle);
    }

    @Override
    public float getAngle() {
        return angle;
    }
}
