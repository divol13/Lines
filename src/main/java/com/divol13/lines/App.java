package com.divol13.lines;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class App extends Application {

    // window size
    private static final int WINDOW_WIDTH = 800;
    private static final int WINDOW_HEIGHT = 800;

    // game field size
    private static final int WIDTH = 4;
    private static final int HEIGHT = 4;

    private Parent createContent() {
        Pane root = new Pane();
        root.setPrefSize(WINDOW_WIDTH,WINDOW_HEIGHT);

        // set tile size to fit all the tiles in the window
        //Tile.TILE_SIZE = WINDOW_WIDTH / WIDTH;

        // create a new game field using WIDTH and HEIGHT constants
        //Field gameField = new Field(WIDTH, HEIGHT);

        // add game field to the window
        //root.getChildren().add(gameField);

        return root;
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Lines");
        primaryStage.setScene( new Scene( createContent()));
        primaryStage.show();
    }

    // entry point of program
    public static void main(String[] args) {
        launch(args);
    }
}
