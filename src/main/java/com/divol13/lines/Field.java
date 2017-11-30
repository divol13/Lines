package com.divol13.lines;

import javafx.animation.Interpolator;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.control.Button;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class Field extends StackPane {

    private Tile[][] grid;
    private ArrayList<Tile> emptyTiles = new ArrayList<Tile>();
    static int COLORS = 1;
    static int IN_ROW = 5;
    static int GENERATE = 3;

    // maybe turn to enum ?
    private class Direction {
        int x;
        int y;

        public Direction(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }
    }

    private Direction[] horizontal = new Direction[]{
        new Direction(1, 0), // right
        new Direction(-1, 0) // left
    };

    private Direction[] vertical = new Direction[]{
        new Direction(0, 1), // up
        new Direction(0, -1) // down
    };

    private Direction[] diagonal1 = new Direction[]{
        new Direction(-1, -1),
        new Direction(1, 1)
    };

    private Direction[] diagonal2 = new Direction[]{
        new Direction(-1, 1),
        new Direction(1, -1)
    };

    public Tile getTile(int x, int y) {
        if(x<0 || y<0 || x>=10 || y>=10){
            return null;
        }
        return grid[x][y];
    }

    public Field(int width, int height) {
        // create a grid with tiles
        grid = new Tile[width][height];

        for (int x = 0; x < width ; x++) {
            for (int y = 0; y < height; y++) {
                Tile tile = new Tile(x,y);
                grid[x][y] = tile;
//                tile.grid_x = x;
//                tile.grid_y = y;
                getChildren().add(tile);
                emptyTiles.add(tile);
            }
        }

        //generate(3);
        this.setOnMouseClicked(this::mouseClick);
    }

    private void mouseClick(MouseEvent e) {
        MouseButton mb = e.getButton();

        System.out.println(e.getX() + "," + e.getY());

        int x = (int)(e.getX() / Tile.SIZE);
        int y = (int)(e.getY() / Tile.SIZE);

        System.out.println("clicked on x : " + x + ", y :" + y);

        Tile tile = getTile(x,y);

        if(mb == MouseButton.PRIMARY ){
            System.out.println("Primary mouse button");

            //int color = new Random().nextInt(COLORS);
            if(tile != null) {
                tile.setBall(new Ball());
            }

            ArrayList<Tile> tiles = new ArrayList<>();
            tiles.add(tile);
            check(tiles);

        } else if (mb == MouseButton.SECONDARY) {
            System.out.println("Secondary mouse button");
            ArrayList<Tile> newBalls = generate(GENERATE);
            check(newBalls);
        } else if (mb == MouseButton.MIDDLE) {
            System.out.println("Middle mouse button");
           // System.out.println(tile);
        }

    }

    private void check(ArrayList<Tile> newBalls) {
        Direction[][] directions = { horizontal, vertical, diagonal1, diagonal2 };
        ArrayList<Tile> toRemove = new ArrayList<Tile>();

        for (Tile tile : newBalls) {
            for (Direction[] dir :directions ) {
                ArrayList<Tile> line = checkLine(tile, dir);

                if(line != null){
                    toRemove.addAll(line);
                }
            }
        }

        remove(toRemove);
    }

    private void remove(ArrayList<Tile> toRemove) {
        for (Tile remove:toRemove ) {

            ScaleTransition anim = new ScaleTransition();
            anim.setNode(remove.getBall());

            anim.setToX(0);
            anim.setToY(0);

            anim.setInterpolator(Interpolator.LINEAR);
            anim.setDuration(new Duration(500));
            anim.play();

            // when animation is done
            anim.setOnFinished(e -> {
                remove.removeBall();
            });
        }
    }

    // it checks two directions
    private ArrayList<Tile> checkLine(Tile start, Direction[] dirs) {
        ArrayList<Tile> result = new ArrayList<Tile>();

        for ( Direction dir : dirs ) {
            ArrayList<Tile> temp = checkDirection(start, dir);
            if( temp != null ){
                result.addAll(temp);
            }
        }

        if(!result.isEmpty()) {
            result.add(start);
        }

        return result.size() >= IN_ROW ? result : null;
    }

    // it checks one direction
    private ArrayList<Tile> checkDirection(Tile start, Direction dir) {
        int k = 1;
        ArrayList<Tile> tiles = new ArrayList();

        if(start.getBall() == null) {
            return null;
        }

        while(true) {

            int x = start.grid_x + dir.getX() * k;
            int y = start.grid_y + dir.getY() * k;

            System.out.println("    let's check tile[x:" + x + " , y:" + y + "]");
            Tile tile = getTile(x, y);

            if(tile == null) {
                break;
            }

            if(tile.getBall() == null) {
                break;
            }

            if(tile.getBall().getColor() != start.getBall().getColor()) {
                break;
            }

            if(tiles.contains(tile)) {
                break;
            }

            System.out.println("        ok add it to the list the sames");
            tiles.add(tile);
            k += 1;
        }

        System.out.println("    tiles size : " + tiles.size());

        return tiles.isEmpty() ? null : tiles;
    }

    private ArrayList<Tile> generate(int num){
        // true if no empty space for new balls
        int realNum = Math.min(emptyTiles.size(), num);
        ArrayList<Tile> tiles = new ArrayList<Tile>();

        for (int i = 0; i < realNum; i++) {
            Random random = new Random();
            int randomInt = random.nextInt(emptyTiles.size());
            System.out.println("randomInt = " + randomInt);

            Tile tile = emptyTiles.remove(randomInt);

            tile.setBall(new Ball());
            System.out.println("i = " + i + " empties.size = " + emptyTiles.size());

            tiles.add(tile);
        }

        return tiles;
    }

}
