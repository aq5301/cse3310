package com.example.ashley.battleship;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.util.ArrayList;


public class ShipPlacement extends AppCompatActivity {


    Grid board = new Grid();
    AI comp = new AI();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ship_placement);

        int[][] AIgrid = makeComputerGrid();
        int [][] playerGrid = board.newGrid();

        /*
            Generate x ships of types: 1-square, 2-square, 3-square, 4-square
            loop --> x loops, player will select a cell for the center of the ship (right part for 2-, second from left for 4- square)
                IF ship would be out of bounds, no action, wait for valid spot to be chosen
            when all ships are placed, the AI will place its ships randomly (RNG)
            continue to the game, player grid is shrunk, blank AI grid is revealed
        */
    }

    public int[][] makeComputerGrid(){
        int grid[][] = board.newGrid();
        ArrayList<Ship> ships = new ArrayList<>();
        grid = comp.placeShips(grid, ships);

        return grid;
    }


}
