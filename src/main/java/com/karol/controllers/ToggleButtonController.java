package com.karol.controllers;

import com.karol.enums.ThemeColors;
import com.karol.interfaces.Controller;
import javafx.animation.FillTransition;
import javafx.animation.TranslateTransition;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.fxml.FXML;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class ToggleButtonController implements Controller {
    @FXML
    private HBox toggleButtonRoot;
    @FXML
    private Circle toggleButtonBox;
    @FXML
    private Rectangle toggleBoxBackground;

    private double translationValue = 20;
    private double transitionDuration = 200;
    private BooleanProperty state = new SimpleBooleanProperty(false);
    private Color trueColor = Color.GREEN;
    private FillTransition toTrueFillTransition = new FillTransition(Duration.millis(transitionDuration));
    private FillTransition toFalseFillTransition = new FillTransition(Duration.millis(transitionDuration));
    @Override
    public void initialize() {
        initialStateChange();
        initTransitions();
        toggleButtonRoot.getStyleClass().add("toggleButtonRoot");
        toggleButtonBox.getStyleClass().add("toggleButtonBox");
        toggleButtonRoot.setOnMouseClicked(event -> {
            boolean oldState = state.getValue();
            state.setValue(!oldState);
            triggerStateChange(!oldState);
        });

        toTrueFillTransition.setShape(toggleBoxBackground);
        toTrueFillTransition.setFromValue(ThemeColors.PASTEL_RED.getColor());
        toTrueFillTransition.setToValue(trueColor);
        toTrueFillTransition.setAutoReverse(true);

        toFalseFillTransition.setShape(toggleBoxBackground);
        toFalseFillTransition.setFromValue(trueColor);
        toFalseFillTransition.setToValue(ThemeColors.PASTEL_RED.getColor());
        toFalseFillTransition.setAutoReverse(true);

    }
    private void initialStateChange(){
        TranslateTransition initTransition =
                new TranslateTransition(Duration.millis(1), toggleButtonBox);

        if(state.getValue()){
            initTransition.setFromX(0);
            initTransition.setToX(translationValue);
            toggleBoxBackground.setFill(trueColor);
        } else {
            initTransition.setFromX(0);
            initTransition.setToX(-translationValue);
            toggleBoxBackground.setFill(ThemeColors.PASTEL_RED.getColor());
        }
        initTransition.play();
    }
    private void initTransitions(){

    }
    private void triggerStateChange(boolean state){
         TranslateTransition toTrueXTransition =
                new TranslateTransition(Duration.millis(transitionDuration), toggleButtonBox);
         TranslateTransition toFalseXTransition =
                new TranslateTransition(Duration.millis(transitionDuration), toggleButtonBox);
        toTrueXTransition.setFromX(-translationValue);
        toTrueXTransition.setToX(translationValue);
        toTrueXTransition.setAutoReverse(true);
        toFalseXTransition.setFromX(translationValue);
        toFalseXTransition.setToX(-translationValue);
        toFalseXTransition.setAutoReverse(true);

        if(state){
            toTrueXTransition.play();
            toTrueFillTransition.play();
        } else {
            toFalseXTransition.play();
            toFalseFillTransition.play();
        }
    }

    public BooleanProperty stateProperty() {
        return state;
    }
}
