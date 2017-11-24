package com.divol13.lines;

import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Tile extends StackPane {
    public Rectangle rect = new Rectangle();
    static int SIZE = 79;
    private Ball ball;
    public int grid_x;
    public int grid_y;

    public Tile(int x, int y) {
        rect.setWidth(SIZE);
        rect.setHeight(SIZE);

        rect.setFill(null);
        rect.setStroke(Color.BLACK);
        rect.setStrokeWidth(2.5);

        grid_x = x * SIZE;
        grid_y = y * SIZE;

        rect.setTranslateX(grid_x);
        rect.setTranslateY(grid_y);

        getChildren().add(rect);
    }

    public void setBall(Ball ball) {
        this.ball = ball;
        getChildren().add(ball);
    }

    public Ball getBall() {
        return ball;
    }

    @Override
    public String toString() {
        return "[" + grid_x + "," + grid_y + "]";
    }
}
