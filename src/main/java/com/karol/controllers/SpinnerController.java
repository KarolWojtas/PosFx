package com.karol.controllers;

import com.karol.interfaces.Controller;
import javafx.animation.FadeTransition;
import javafx.animation.PathTransition;
import javafx.animation.Timeline;
import javafx.animation.Transition;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.util.Duration;

public class SpinnerController implements Controller {
    @FXML
    private VBox root;
    @FXML
    private Label loadingLbl;

    private PathTransition circlePathTransitionOne;
    private PathTransition circlePathTransitionTwo;
    private PathTransition circlePathTransitionThree;
    private FadeTransition labelFadeTransition;
    private Transition[] transitions;
    private String labelText = "proszę czekać";

    @FXML
    @Override
    public void initialize(){
        setupTransitions();
        loadingLbl.getStyleClass().add("spinnerLoadingLbl");
        loadingLbl.setText(labelText);
        transitions = new Transition[]{circlePathTransitionOne, circlePathTransitionTwo, circlePathTransitionThree, labelFadeTransition};

        circlePathTransitionOne.play();
        circlePathTransitionTwo.playFrom(Duration.millis(200));
        circlePathTransitionThree.playFrom(Duration.millis(400));
        labelFadeTransition.play();
    }
    public void pauseAnimation(){
        for(Transition transition : transitions){
            transition.pause();
        }
    }
    public void playAnimation(){
        for (Transition transition: transitions){
            transition.play();
        }
    }
    private void setupTransitions(){
        Circle circle1 = new Circle();
        circle1.setFill(Color.BLUE);
        circle1.setStroke(Color.TRANSPARENT);
        circle1.setRadius(10);
        Circle circle2 = new Circle();
        circle2.setFill(Color.RED);
        circle2.setStroke(Color.TRANSPARENT);
        circle2.setRadius(10);
        Circle circle3 = new Circle();
        circle3.setFill(Color.YELLOW);
        circle3.setStroke(Color.TRANSPARENT);
        circle3.setRadius(10);
        root.getChildren().addAll(circle1, circle2, circle3);

        Path circlePathOne = createCircleePath(0);
        circlePathTransitionOne = createCirclePathTransition(circle1, circlePathOne);
        Path circlePathTwo = createCircleePath(15);
        circlePathTransitionTwo = createCirclePathTransition(circle2, circlePathTwo);
        Path circlePathThree = createCircleePath(30);
        circlePathTransitionThree = createCirclePathTransition(circle3, circlePathThree);

        labelFadeTransition = new FadeTransition();
        labelFadeTransition.setFromValue(1.0f);
        labelFadeTransition.setToValue(0.3f);
        labelFadeTransition.setCycleCount(Timeline.INDEFINITE);
        labelFadeTransition.setAutoReverse(true);
        labelFadeTransition.setDuration(Duration.seconds(1));
        labelFadeTransition.setNode(loadingLbl);
    }

    private Path createCircleePath(double layoutOffset) {
        double centerX = 120;
        double centerY = -20;
        double radius = 125;
        ArcTo arcTo = new ArcTo();
        arcTo.setX(centerX - radius + 1 - layoutOffset);
        arcTo.setY(centerY - radius - layoutOffset);
        arcTo.setSweepFlag(false);
        arcTo.setLargeArcFlag(true);
        arcTo.setRadiusX(radius);
        arcTo.setRadiusY(radius);

        Path path = new Path();
        path.getElements().addAll(
                new MoveTo(centerX - radius - layoutOffset, centerY - radius - layoutOffset),
                arcTo,
                new ClosePath());
        return path;
    }

    private PathTransition createCirclePathTransition(Node node, Path path){
        PathTransition circlePathTransition = new PathTransition();
        circlePathTransition.setDuration(Duration.seconds(2));
        circlePathTransition.setPath(path);
        circlePathTransition.setNode(node);
        circlePathTransition.setOrientation(PathTransition.OrientationType.NONE);
        circlePathTransition.setCycleCount(Timeline.INDEFINITE);
        circlePathTransition.setAutoReverse(false);
        return circlePathTransition;
    }

    public void setText(String text){
        labelText = text.toUpperCase();
    }
}
