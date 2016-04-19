package com.example.ashley.battleship;

import android.graphics.Point;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Ashley on 4/18/2016.
 */
public class AI {
    Random ranPlace = new Random();

    public int[][] placeShips(int[][] AIgrid, ArrayList<Ship> ships){
        int coordx, coordy, type, arrange;
        Ship current;
        Point newLoc;
        for(int run = 0; run < 8; run++){
            coordx = ranPlace.nextInt(100);
            coordy = ranPlace.nextInt(100);
            type = ranPlace.nextInt((4-1) + 1) + 1;
            arrange = ranPlace.nextInt(1);
            ships.add(new Ship(coordx, coordy, false, type, arrange));
        }

        //vertical: for >1 space, one above and one/two below, or vice-versa
        //horizontal: for >1 space, one to the left and one/two to the right, or vice-versa
        //place down ships as "4" for AI hidden ship
        //ADD ERROR-CATCHING FOR OUT OF BOUNDS
        for(int run2 = 0; run2 < 8; run2++){
            current = ships.get(run2);
            //single cell ship does not care about arrangement
            if(current.getType() == 1){
               if(AIgrid[current.getCenter().x][current.getCenter().y] == 4) {
                    newLoc = newCoord(AIgrid, current.getCenter().x, current.getCenter().y);
                    current.setCenter(newLoc.x, newLoc.y);
               }
                AIgrid[current.getCenter().x][current.getCenter().y] = 4;
            }

            else
            //horizontal
            if(current.getArrange() == 0){
                if(current.getType() == 2){
                    if(AIgrid[current.getCenter().x][current.getCenter().y] == 4) {
                        newLoc = newCoord(AIgrid, current.getCenter().x, current.getCenter().y);
                        current.setCenter(newLoc.x, newLoc.y);
                    }
                }
                else if(current.getType() == 3){
                    if(AIgrid[current.getCenter().x][current.getCenter().y] == 4) {
                        newLoc = newCoord(AIgrid, current.getCenter().x, current.getCenter().y);
                        current.setCenter(newLoc.x, newLoc.y);
                    }
                }
                else if(current.getType() == 4){
                    if(AIgrid[current.getCenter().x][current.getCenter().y] == 4) {
                        newLoc = newCoord(AIgrid, current.getCenter().x, current.getCenter().y);
                        current.setCenter(newLoc.x, newLoc.y);
                    }
                }

            }

            //vertical
            else
            if(current.getArrange() == 1){
                if(current.getType() == 2){

                }
                else if(current.getType() == 3){

                }
                else if(current.getType() == 4){

                }

            }

        }
        return AIgrid;
    }

    //makes move and returns modified playerGrid
    public int[][] makeMove(int[][] playerGrid){


            return playerGrid;
    }

    public Point newCoord(int [][]grid, int x, int y){
        Point newCoord = new Point();

        while(grid[x][y] == 4){
            x = ranPlace.nextInt(100);
            y = ranPlace.nextInt(100);
        }

        newCoord.set(x,y);
        return newCoord;
    }

}
