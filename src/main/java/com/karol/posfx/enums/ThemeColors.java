package com.karol.posfx.enums;

import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.RadialGradient;
import javafx.scene.paint.Stop;

public enum ThemeColors {
    PASTEL_BLUE(Color.rgb(72, 137, 242),
            new RadialGradient(100, 0, .3, .3, .8, true, CycleMethod.NO_CYCLE, new Stop(0, Color.rgb(72, 137, 242)), new Stop(1, Color.BLUE))),
    PASTEL_YELLOW(Color.rgb(247, 233, 103),
            new RadialGradient(100, 0, .3, .3, .8, true, CycleMethod.NO_CYCLE, new Stop(0, Color.rgb(247, 233, 103)), new Stop(1, Color.YELLOW))),
    PASTEL_RED(Color.rgb(242, 92, 92),
            new RadialGradient(100, 0, .3, .3, .8, true, CycleMethod.NO_CYCLE, new Stop(0, Color.rgb(242, 92, 92)), new Stop(1, Color.RED)));

    private Color color;
    private RadialGradient radialGradient;

    ThemeColors(Color color, RadialGradient radialGradient) {
        this.color = color;
        this.radialGradient = radialGradient;
    }

    public Color getColor() {
        return color;
    }

    public RadialGradient getRadialGradient() {
        return radialGradient;
    }
}
