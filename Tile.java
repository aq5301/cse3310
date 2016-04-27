package com.example.ashley.battleship;

import android.app.Fragment;
import android.view.View;


public class Tile extends Fragment {
    Boolean hasShipMid = false;
    Boolean hasShipEnd = false;
    Boolean hasBeenHit = false;

    private static final int EMPTY = 0;
    private static final int HIT  = 1;
    private static final int HAS_SHIPMID = 2;
    private static final int HAS_SHIPEND = 3;

    private View tileView;

    public View getView() {
        return tileView;
    }

    public void setView(View view) {
        this.tileView = view;
    }

    public Boolean gethasBeenHit() {
        return hasBeenHit;
    }

    public void hitTile() {
        this.hasBeenHit = true;
    }

    public void resettile() {
        this.hasBeenHit = false;
    }

    public Boolean gethasShipMid() {
        return hasShipMid;
    }

    /* Places the ship based on this tile's id whether it is first, a direction and remaining length og the ship
    * Returns True on success and false on failure.*/
    public boolean placeShip(int myID, Boolean isFirst, int direction, int remaininglength) {
        boolean canplace = true;

        switch (direction) { /*Checks to see if the ship can be placed*/
            case 0:
                if ((myID + remaininglength) < 64/*TEMP NEED FINAL TILES ID*/) {
                    canplace = true;
                } else {
                    canplace = false;
                }
                break;
            case 1:
                if ((myID + (8 * remaininglength)) < 64/*TEMP NEED FINAL TILES ID*/) {
                    canplace = true;
                } else {
                    canplace = false;
                }
                break;
            case 2:
                if ((myID - remaininglength) < 64/*TEMP NEED FINAL TILES ID*/) {
                    canplace = true;
                } else {
                    canplace = false;
                }
                break;
            case 3:
                if ((myID - (8 * remaininglength)) < 64/*TEMP NEED FINAL TILES ID*/) {
                    canplace = true;
                } else {
                    canplace = false;
                }
                break;
        }
            if (canplace) {


                if (isFirst) {
                    this.hasShipEnd = true;
                    switch (direction) {
                        case 0:
                            placeShip(myID + 1, false, 0, remaininglength - 1);
                            break;
                        case 1:
                            placeShip(myID + 8, false, 1, remaininglength - 1);
                            break;
                        case 2:
                            placeShip(myID - 1, false, 2, remaininglength - 1);
                            break;
                        case 3:
                            placeShip(myID - 8, false, 3, remaininglength - 1);
                            break;
                    }
                    return true;
                } else if (remaininglength == 1) {
                    this.hasShipEnd = true;
                    return true;
                } else {
                    this.hasShipMid = true;
                }
                switch (direction) {
                    case 0:
                        placeShip(myID + 1, false, 0, remaininglength - 1);
                        break;
                    case 1:
                        placeShip(myID + 8, false, 1, remaininglength - 1);
                        break;
                    case 2:
                        placeShip(myID - 1, false, 2, remaininglength - 1);
                        break;
                    case 3:
                        placeShip(myID - 8, false, 3, remaininglength - 1);
                        break;
                }
            }else{
        return false;
    }
       return true;
    }

    public Boolean gethasShipEnd() {
        return hasShipEnd;
    }


}


