package com.divol13.lines;

import javafx.scene.layout.StackPane;

public class Field extends StackPane {

    private int[][] grid;

    public Field(int width, int height) {
        // create a grid with tiles
        grid = new int[width][height];
    }
}
