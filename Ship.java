package com.example.ashley.battleship;

import android.graphics.Point;

/**
 * Created by Ashley on 4/18/2016.
 */
public class Ship {

    Point center;
    Point [] allCoord;
    int type; // 1, 2, 3, 4- cells
    int arrange; // 0 = horizontal, 1 = vertical
    Boolean sunk;

    //creating a new ship
    public Ship(int x, int y, boolean sink, int typing, int arrangement) {
        this.center.set(x,y);
        this.sunk = sink;
        this.type = typing;
        this.arrange = arrangement;
    }

    public void setCoordinates(Point[] newCoord){
        this.allCoord = newCoord;
    }

    //set new coordinates for center if, during creation, a spot is occupied
    public void setCenter(int newX, int newY){
            this.center.set(newX, newY);
    }

    public Point[] getCoordinates(){
        return this.allCoord;
    }
    public Point getCenter(){
        return this.center;
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
}


