package com.example.ashley.battleship;

import android.graphics.Point;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Ashley on 4/18/2016.
 */
public class AI {
    Random ranPlace = new Random();
    final Globals global = new Globals();

    public int[][] placeShips(int[][] AIgrid, ArrayList<Ship> ships){
        int coordx, coordy, type, arrange;
        Ship current;
        Point newLoc;
        Point [] allLoc = new Point[5];

        for(int run = 0; run < 5; run++){
            coordx = ranPlace.nextInt(10);
            coordy = ranPlace.nextInt(10);
            type = ranPlace.nextInt((4-2) + 2) + 2;
            arrange = ranPlace.nextInt(1);
            //ships.add(new Ship(coordx, coordy, false, type, arrange));
        }


        return AIgrid;
    }

    //makes move and returns modified playerGrid
    public int[][] makeMove(int[][] playerGrid){
            if(global.getDifficulty() == 1){ //normal mode

            }
            else{ //easy mode

            }

            return playerGrid;
    }

    public Point newCoord(int [][]grid, int x, int y){
        Point newCoord = new Point();

        while(grid[x][y] == 4){
            x = ranPlace.nextInt(10);
            y = ranPlace.nextInt(10);
        }

        newCoord.set(x,y);
        return newCoord;
    }

}
