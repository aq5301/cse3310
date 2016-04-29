package com.example.ashley.battleship;

import android.graphics.Point;

/**
 * Created by Ashley on 4/18/2016.
 */
public class Ship {

    int tiles[];
    int hitTiles[] = {0,0,0,0};
    int type; // 2, 3, 4- cells
    int arrange; // 0 = horizontal, 1 = vertical, or in case of 2-cell, the next tile goes to the right
    int numHits = 0;
    Boolean sunk;


    //creating a new ship
    public Ship(int [] allTiles, boolean sink, int typing, int arrangement) {
        this.tiles = allTiles;
        this.sunk = sink;
        this.type = typing;
        this.arrange = arrangement;
    }

    public int[] getTiles() {
        return tiles;
    }
    public int getType(){
        return this.type;
    }

    public boolean getSunk(){
        return this.sunk;
    }

    public int getArrange(){
        return this.arrange;
    }

    public int getNumHits() {
        return numHits;
    }

    public int[] getHitTiles() {
        return hitTiles;
    }


    public void setSunk(Boolean sunk){
        this.sunk = sunk;
    }

    public void setNumHits(int numHits) {
        this.numHits = numHits;
    }

    public void setHitTiles(int[] hitTiles) {
        this.hitTiles = hitTiles;
    }

}


