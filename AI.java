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
            ships.add(new Ship(coordx, coordy, false, type, arrange));
        }

        //vertical: for >1 space, one above and one/two below, or vice-versa
        //horizontal: for >1 space, one to the left and one/two to the right, or vice-versa
        //place down ships as 4(end), or 5 (middle) for AI hidden ship
        //ADD ERROR-CATCHING FOR OUT OF BOUNDS
        for(int run2 = 0; run2 < 5; run2++){
            current = ships.get(run2);
            current.setCoordinates(allLoc);


            //horizontal
            if(current.getArrange() == 0){
                if(current.getType() == 2){ //two-celled
                    if(AIgrid[current.getCenter().x][current.getCenter().y] == 4) {
                        newLoc = newCoord(AIgrid, current.getCenter().x, current.getCenter().y);
                        current.setCenter(newLoc.x, newLoc.y);
                    }
                    AIgrid[current.getCenter().x][current.getCenter().y] = 4;
                    if(AIgrid[current.getCenter().x + 1][current.getCenter().y] == 4 || (current.getCenter().x + 1) > 9) //check right
                    {
                        try{ //attempt to put the second half to the left
                            AIgrid[current.getCenter().x - 1][current.getCenter().y] = 4;
                            current.getCoordinates()[0] = current.getCenter();
                            current.getCoordinates()[1].set(current.getCenter().x - 1,current.getCenter().y);
                        }
                        catch(ArrayIndexOutOfBoundsException e) //if the half goes out of bounds
                        {
                            AIgrid[current.getCenter().x][current.getCenter().y] = 0;
                            while(AIgrid[current.getCenter().x - 1][current.getCenter().y] == 4 || //if left goes on occupied spot
                                    AIgrid[current.getCenter().x][current.getCenter().y] == 4  //if right/center goes on occupied spot
                                    || (current.getCenter().x) < 9){  //if right/center is out of bounds
                                current.getCenter().set(current.getCenter().x + 1, current.getCenter().y); //moves ship to the right
                            }
                            AIgrid[current.getCenter().x][current.getCenter().y] = 4;
                            AIgrid[current.getCenter().x - 1][current.getCenter().y] = 4;
                            current.getCoordinates()[0] = current.getCenter();
                            current.getCoordinates()[1].set(current.getCenter().x - 1,current.getCenter().y);
                        }

                    }
                    else{ //right is clear, put other part to the right
                        AIgrid[current.getCenter().x + 1][current.getCenter().y] = 4;
                        current.getCoordinates()[0] = current.getCenter();
                        current.getCoordinates()[1].set(current.getCenter().x + 1,current.getCenter().y);
                    }
                }
                else if(current.getType() == 3){ //three-celled
                    if(AIgrid[current.getCenter().x][current.getCenter().y] == 4) {
                        newLoc = newCoord(AIgrid, current.getCenter().x, current.getCenter().y);
                        current.setCenter(newLoc.x, newLoc.y);
                    }
                    AIgrid[current.getCenter().x][current.getCenter().y] = 4;
                    if(AIgrid[current.getCenter().x + 1][current.getCenter().y] == 4 ||
                            AIgrid[current.getCenter().x - 1][current.getCenter().y] == 4) { //check right & left for occupied
                            while(AIgrid[current.getCenter().x + 1][current.getCenter().y] == 4 ||
                                    AIgrid[current.getCenter().x - 1][current.getCenter().y] == 4 && ((current.getCenter().y) < 9
                                    || (current.getCenter().y) > 0)){ //move the ship up or down
                                current.getCenter().set(current.getCenter().x, current.getCenter().y - 1);
                            }

                    }

                }
                else if(current.getType() == 4){ //four-celled
                    if(AIgrid[current.getCenter().x][current.getCenter().y] == 4) {
                        newLoc = newCoord(AIgrid, current.getCenter().x, current.getCenter().y);
                        current.setCenter(newLoc.x, newLoc.y);
                    }
                    AIgrid[current.getCenter().x][current.getCenter().y] = 4;
                }

            }

            //vertical
            else
            if(current.getArrange() == 1){
                if(current.getType() == 2) {
                    if (AIgrid[current.getCenter().x][current.getCenter().y] == 4) {
                        newLoc = newCoord(AIgrid, current.getCenter().x, current.getCenter().y);
                        current.setCenter(newLoc.x, newLoc.y);
                    }
                    AIgrid[current.getCenter().x][current.getCenter().y] = 4;
                    if (AIgrid[current.getCenter().x][current.getCenter().y + 1] == 4 || (current.getCenter().y + 1) > 9) //check above
                    {
                        try {
                            AIgrid[current.getCenter().x][current.getCenter().y - 1] = 4;
                            current.getCoordinates()[0] = current.getCenter();
                            current.getCoordinates()[1].set(current.getCenter().x,current.getCenter().y - 1);
                        } catch (ArrayIndexOutOfBoundsException e) {
                            AIgrid[current.getCenter().x][current.getCenter().y] = 0;
                            while (AIgrid[current.getCenter().x][current.getCenter().y - 1] == 4 || //if bottom goes on occupied spot
                                    AIgrid[current.getCenter().x][current.getCenter().y] == 4  //if center/top goes on occupied spot
                                    || AIgrid[current.getCenter().x][current.getCenter().y] <= 9) {  //if center/top out of bounds
                                current.getCenter().set(current.getCenter().x, current.getCenter().y + 1);
                            }
                            AIgrid[current.getCenter().x][current.getCenter().y] = 4;
                            AIgrid[current.getCenter().x][current.getCenter().y - 1] = 4;
                            current.getCoordinates()[0] = current.getCenter();
                            current.getCoordinates()[1].set(current.getCenter().x, current.getCenter().y - 1);
                        }

                    }
                    else {
                        AIgrid[current.getCenter().x][current.getCenter().y + 1] = 4;
                        current.getCoordinates()[0] = current.getCenter();
                        current.getCoordinates()[1].set(current.getCenter().x,current.getCenter().y + 1);
                    }
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
