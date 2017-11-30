package com.divol13.lines;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.util.Random;


public class Ball extends Circle {
    private int color;

    public Ball(int x, int y, int color, int radius) {
        setTranslateX(x);
        setTranslateY(y);
        setColor(color);
        setRadius(radius);
        setStroke(Color.BLACK);
        setStrokeWidth(1.0);
    }

    public Ball() {
        setStroke(Color.BLACK);
        setStrokeWidth(1.0);
        setRadius(Tile.SIZE / 2 - 5);
        setColor();
    }

    public void setColor(int color) {
        this.color = color;
        Color c;

        switch (color) {
            case 0 : c = Color.BROWN; break;
            case 1 : c = Color.RED; break;
            case 2 : c = Color.BLUE; break;
            case 3 : c = Color.VIOLET; break;
            case 4 : c = Color.YELLOW; break;
            case 5 : c = Color.GREEN; break;
            case 6 : c = Color.GOLD; break;
            case 7 : c = Color.ORANGE; break;
            default: c = Color.BLACK;
        }

        setFill(c);
    }

    public void setColor(){
        Random random = new Random();
        setColor( random.nextInt(Field.COLORS) );
    }

    public int getColor() {
        return color;
    }
}
