package com.example.ashley.battleship;

/**
 * Created by Ashley on 4/18/2016.
 */
public class Grid {
    /*
    0 = no ship
    1 = ship end
    2 = ship middle
    3 = destroyed ship
    4 = AI hidden part
    5 = miss
    */
    int[][] grid = new int[10][10];

    //return a new grid filled only with 0's (blank cells)
    public int[][] newGrid() {
        for (int r = 0; r < 10; r++) {
            for (int c = 0; c < 10; c++) {
                grid[r][c] = 0;
            }
        }
        return grid;
    }

    //check if all ships are sunk
    public boolean isEmpty(int[][] checkGrid) {
        for (int r = 0; r < 10; r++) {
            for (int c = 0; c < 10; c++) {
                if (checkGrid[r][c] == 1 || checkGrid[r][c] == 2 || checkGrid[r][c] == 4) {
                    return false;
                }
            }
        }
        return true;
    }
}
