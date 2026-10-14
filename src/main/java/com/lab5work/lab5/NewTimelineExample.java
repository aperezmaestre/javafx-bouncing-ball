package com.lab5work.lab5;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * app that moves ball moving horizontally
 * ball changes color per 100 pixels
 * if hits wall bounces back
 */
public class NewTimelineExample extends Application {

    private int colorIndex; // Stores next color of the ball
    private Circle ball; //  the ball
    private double ballPosition;
    private double ballSpeed;
    private int lastColorZone; // last 100 pixels of ball
    private Timeline timeline; // controls the animation
    private boolean paused; // trac if  animation is paused

    /**
     * starts the app and initializes  ball.
     * @param stage the primary stage
     */
    @Override
    public void start(Stage stage) {
        BorderPane root = new BorderPane();
        Pane canvas = new Pane(); // pane that holds the ball
        Scene scene = new Scene(root, 1000, 300, Color.ALICEBLUE); // creates the scene

        ball = new Circle(80, Color.CADETBLUE); // creates  ball
        ball.relocate(0, 60); // where ball starts

        colorIndex = 0;
        ballPosition = 0;
        ballSpeed = 7;
        lastColorZone = (int) ballPosition / 100;
        paused = false; //  false beacuse its runing

        canvas.getChildren().add(ball); // adds the ball to  pane
        root.setCenter(canvas); // puts the animation pane in the center

        //slider things
        Label speedLabel = new Label("Speed:"); //label for the slider
        Slider speedSlider = new Slider(1, 20, 7); // slider for changing speed fcrom 1-20 starts 7

        Button pauseResumeButton = new Button("Pause"); // button to pause resume animation

        //slidewr logic oldval =oldvalue obs (java check this ) new val =new value
        speedSlider.valueProperty().addListener((obs, oldVal, newVal) -> {
            double direction;//direction value
            //saves directio
                 if (ballSpeed < 0) {
                direction = -1;
                 } else {
                direction = 1;
                 }
            ballSpeed = newVal.doubleValue() * direction; // aplies speed wtih out messing direvtion
        });


        //pause logic
        pauseResumeButton.setOnAction(e -> {
            if (paused) {

                timeline.play();
                pauseResumeButton.setText("Pause");
                paused = false;
            } else {

                timeline.pause();
                pauseResumeButton.setText("Resume");
                paused = true;
            }
        });
        //displays things
        HBox controls = new HBox(10, speedLabel, speedSlider, pauseResumeButton); // holds the pause adn slider
        controls.setAlignment(Pos.CENTER); // centers the pause adn slider
        root.setBottom(controls); // puts them at boittom

        stage.setTitle("Animated Ball"); // window title
        stage.setScene(scene); // adss scene to widnw
        stage.show(); // displays it

        //calls run every 16ms so it repats process all the time , keyframe is what happends each 16 tick

        timeline = new Timeline(new KeyFrame(Duration.millis(16), ae -> run())); // calls run() every 16ms
        timeline.setCycleCount(Timeline.INDEFINITE); // runs for ever
        timeline.play(); // starts it
    }

    /**
     * updates ball position if hit wall changes direction
     * also changes color per 100 pixels
     */
    public void run() {
        ballPosition += ballSpeed; // adds speed to postion thsi moves ball

        //hiting wall logic
        if (ballPosition >= 920) { //right bumper logic
            ballPosition = 920;
            ballSpeed = -ballSpeed;
        } else if (ballPosition <= 80) {//left bumper
            ballPosition = 80;
            ballSpeed = -ballSpeed;
        }

        ball.setLayoutX(ballPosition); // updats ball visible position

        int currentZone = (int) ballPosition / 100; // currnt zone

        //zone-color changing logic
        if (currentZone != lastColorZone) { // cheks
            colorIndex = (colorIndex + 1) % 3; //moves to next color logic down

            //logic for color change 0=blue, 1=chathertuese , 2 =chocolate
            if (colorIndex == 0) {
                ball.setFill(Color.CADETBLUE);
            } else if (colorIndex == 1) {
                ball.setFill(Color.CHARTREUSE);
            } else {
                ball.setFill(Color.CHOCOLATE);
            }

            lastColorZone = currentZone; // update  last zone so doesnt change color in same zone
        }
    }
}