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

        rect.setTranslateX(x * SIZE);
        rect.setTranslateY(y * SIZE);

        grid_x = x;
        grid_y = y;

        getChildren().add(rect);
    }

    public void setBall(Ball ball) {
        if(this.ball != null) {
            System.out.println("Ball already exist in this tile!");
            return;
        }
        this.ball = ball;

        this.ball.setTranslateX(this.rect.getTranslateX());
        this.ball.setTranslateY(this.rect.getTranslateY());

        getChildren().add(ball);
    }

    public void removeBall() {
        getChildren().remove(this.ball);
        this.ball = null;
    }

    public Ball getBall() {
        return ball;
    }

    @Override
    public String toString() {
        return "[" + grid_x + "," + grid_y + "] = " + getBall() != null ? String.valueOf(getBall().getColor()) : "empty";
    }
}
