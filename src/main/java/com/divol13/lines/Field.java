package com.divol13.lines;

import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.Random;

public class Field extends StackPane {

    private Tile[][] grid;
    private ArrayList<Tile> emptyTiles = new ArrayList<Tile>();
    static int COLORS = 4;

    public Field(int width, int height) {
        // create a grid with tiles
        grid = new Tile[width][height];

        for (int x = 0; x < width ; x++) {
            for (int y = 0; y < height; y++) {
                Tile tile = new Tile(x,y);
                getChildren().add(tile);
                emptyTiles.add(tile);
            }
        }

        //generate(3);
        this.setOnMouseClicked(e -> {
            generate(3);
        });
    }

    private boolean generate(int num){
        // true if no empty space for new balls
        int realNum = Math.min(emptyTiles.size(), num);

        for (int i = 0; i < realNum; i++) {
            Random random = new Random();
            int randomInt = random.nextInt(emptyTiles.size());
            System.out.println("randomInt = " + randomInt);

            Tile tile = emptyTiles.remove(randomInt);
            System.out.println(tile);

            int color = random.nextInt(7);
            Ball ball = new Ball((int)tile.grid_x, (int)tile.grid_y, color, Tile.SIZE / 2 - 5);
            tile.setBall(ball);
            System.out.println("i = " + i + " empties.size = " + emptyTiles.size());
        }

        return false;
    }

}
