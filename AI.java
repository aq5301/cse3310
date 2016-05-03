package com.example.ashley.battleship;


import java.util.ArrayList;
import java.util.*;

/**
 * Created by Will on 4/18/2016.
 */
public class AI {

    public ArrayList<Ship> targetships = new ArrayList<>();
    private Random rand = new Random();
    private GameBoard gameState;
    private Globals gInfo;
    private boolean shipKillReverse = false, shipKillWasReversed = false;
    private int iterator = -1;

    public AI(GameBoard gamestate, Globals ginfo) {
        this.gameState = gamestate;
        this.gInfo = ginfo;
    }

    public Ship [] placeships() {
        Ship [] AIship = new Ship[5];
        int [] shipTypes = gInfo.getSHIPLIST();
        int [] allTiles;
        int typeShip;
        boolean[] checkShip = new boolean[65];
        int chooseTile, run = 0;
        
        while(run < 5){
            chooseTile = rand.nextInt((64-1) + 1) + 1;
            if(checkShip[chooseTile]){
                //do nothing
            }
            else{
                typeShip = shipTypes[run];
                if(typeShip == 2){
                    if(chooseTile % 8 == 0) { //check tile to the right for out of bounds
                        if ((chooseTile - 1) < 1) { //check tile to the left, if it's out of bounds = do nothing
                            
                        } else {

                            checkShip[chooseTile] = true;
                            checkShip[chooseTile - 1] = true;

                            allTiles = new int[2];
                            allTiles[0] = chooseTile;//right
                            allTiles[1] = chooseTile - 1; //left
                            AIship[run] = new Ship(allTiles, false, 2, 0);
                            run++;
                        }
                    }
                    else{
                        checkShip[chooseTile] = true;
                        checkShip[chooseTile + 1] = true;

                        allTiles = new int[2];
                        allTiles[0] = chooseTile; //left
                        allTiles[1] = chooseTile + 1; //right
                        AIship[run] = new Ship(allTiles, false, 2, 1); //1 for tile on right
                        run++;
                    }

                }
                else if (typeShip == 3){
                    if(run == 1){ //horiz
                        if((chooseTile + 1) > 64 || (chooseTile - 1) < 1){
                            //nothing
                        }
                        else if(checkShip[chooseTile - 1] || checkShip[chooseTile + 1] || chooseTile % 8 == 0 || (chooseTile - 1) % 8 == 0) {
                        //check left and right, do nothing

                        }
                        else{

                            checkShip[chooseTile] = true;
                            checkShip[chooseTile - 1] = true;
                            checkShip[chooseTile + 1] = true;

                            allTiles = new int[3];
                            allTiles[0] = chooseTile; //center
                            allTiles[1] = chooseTile - 1; //left
                            allTiles[2] = chooseTile + 1; //right
                            AIship[run] = new Ship(allTiles, false, 3, 0);
                            run++;
                        }

                    }
                    else{ //vertical
                        if(chooseTile - 8 <= 0 || chooseTile + 8 > 64) //if top or bottom go out of bounds
                        {

                        }
                        else if(checkShip[chooseTile - 8] || checkShip[chooseTile + 8] ) { //check top and bottom

                        }
                        else{

                            checkShip[chooseTile] = true;
                            checkShip[chooseTile - 8] = true;
                            checkShip[chooseTile + 8] = true;

                            allTiles = new int[3];
                            allTiles[0] = chooseTile; //center
                            allTiles[1] = chooseTile - 8; //top
                            allTiles[2] = chooseTile + 8; //bottom
                            AIship[run] = new Ship(allTiles, false, 3, 1);
                            run++;
                        }
                    }

                }
                else{ //4
                    if(run == 3){ //horiz
                        if(chooseTile % 8 == 0 || (chooseTile - 1) % 8 == 0 || (chooseTile + 1) % 8 == 0){ //check out of bounds

                        }
                        else if (checkShip[chooseTile - 1] || checkShip[chooseTile + 1] || checkShip[chooseTile + 2]){ //check to left, two spaces to right

                        }
                        else{

                            checkShip[chooseTile] = true;
                            checkShip[chooseTile- 1] = true;
                            checkShip[chooseTile + 1] = true;
                            checkShip[chooseTile + 2] = true;

                            allTiles = new int[4];
                            allTiles[0] = chooseTile; //center
                            allTiles[1] = chooseTile - 1; //left
                            allTiles[2] = chooseTile + 1; //right
                            allTiles[3] = chooseTile + 2; //far right
                            AIship[run] = new Ship(allTiles, false, 4, 0);
                            run++;
                        }

                    }
                    else { //vertical
                        if ((chooseTile - 8) <= 0 || (chooseTile + 8) > 64 || (chooseTile + 16) > 64) { //check top, bottom two

                        } else if (checkShip[chooseTile - 8] || checkShip[chooseTile + 8] || checkShip[chooseTile + 16]) { //check top, bottom two for ships

                        } else {
                            checkShip[chooseTile] = true;
                            checkShip[chooseTile - 8] = true;
                            checkShip[chooseTile + 8] = true;
                            checkShip[chooseTile + 16] = true;

                            allTiles = new int[4];
                            allTiles[0] = chooseTile; //center
                            allTiles[1] = chooseTile - 8; //top
                            allTiles[2] = chooseTile + 8; //bottom
                            allTiles[3] = chooseTile + 16; //far bottom
                            AIship[run] = new Ship(allTiles, false, 4, 1);
                            run++;
                        }
                    }
                }
            }
            
        }

        return AIship;
    }

    public boolean makemove() {
        int tileHit = -1;
        boolean success = false;
        int i, j;
        if (targetships.isEmpty()) { // if there are no target ships decide move based on seek mode.
            if (gInfo.getDifficulty() == 0) { // easy
                while (!success) {
                    tileHit = rand.nextInt((64-1) + 1) + 1;
                    success = gameState.AICheckMove(tileHit);
                }
                for (i = 0; i < 5; i++) {
                    for (j = 0; j < gameState.playerShips[i].getType(); j++) {
                        if (tileHit == gameState.playerShips[i].getTiles()[j]) {
                            if (!targetships.contains(gameState.playerShips[i])) {
                                targetships.add(gameState.playerShips[i]);
                            }
                            return true;
                        }
                    }
                }
            } else {
                while (!success) {
                    int[] hitselection = {1, 3, 5, 7, 10, 12, 14, 16, 17, 19, 21, 23, 26, 28, 30, 32, 33, 35, 37, 39, 42, 44, 46, 48, 49, 51, 53, 55, 58, 60, 62, 64};
                    //Array creating a checker pattern on the board, could be made using control structures, but it is easier to hard code it due to board size.
                    tileHit = hitselection[rand.nextInt(32)];
                    success = gameState.AICheckMove(tileHit);
                }
                for (i = 0; i < 5; i++) {
                    for (j = 0; j < gameState.playerShips[i].getType(); j++) {
                        if (tileHit == gameState.playerShips[i].getTiles()[j]) {
                            if (!targetships.contains(gameState.playerShips[i])) {
                                targetships.add(gameState.playerShips[i]);
                            }
                            return true;
                        }
                    }
                }
            }
        } else { // if there is a target ship, decide move based on hunt mode
            while (!success) {
                ArrayList<Integer> shipseek;
                shipseek = new ArrayList<>();
                Ship target = targetships.get(0);
                int[] holder = target.getHitTiles();

                for (i = 0; i < target.getNumHits(); i++) {
                    if(holder[i] == 0){
                        //do nothing
                    }
                    else{
                        shipseek.add(holder[i]);
                    }
                }
                Collections.sort(shipseek);
                Collections.reverse(shipseek);


                if (!(shipseek.size() > 1)) {
                    if (iterator == -1) {
                        iterator = rand.nextInt(4);
                        iterator = iterator % 4;
                    } else {
                        iterator = ((iterator + 1) % 4);
                    }
                    switch (iterator) {
                        case 0:
                            tileHit = (shipseek.get(0) + 8);
                            break;

                        case 1:
                            tileHit = (shipseek.get(0) + 1);
                            break;

                        case 2:
                            tileHit = (shipseek.get(0) - 8);
                            break;

                        case 3:
                            tileHit = (shipseek.get(0) - 1);
                            break;
                    }
                    success = gameState.AICheckMove(tileHit);
                    if(success){
                        for (i = 0; i < 5; i++) {
                            for (j = 0; j < gameState.playerShips[i].getType(); j++) {
                                if (tileHit == gameState.playerShips[i].getTiles()[j]) {
                                    if (!targetships.contains(gameState.playerShips[i])) {
                                        targetships.add(gameState.playerShips[i]);
                                    }
                                    return true;
                                }
                            }
                        }
                        return false;
                    }
                } else {
                    if (!shipKillWasReversed) {
                        if (!shipKillReverse) {
                            tileHit = ((shipseek.get(shipseek.size() - 2)) - (shipseek.get(shipseek.size() - 1))) + shipseek.get(shipseek.size() - 2);
                            success = gameState.AICheckMove(tileHit);
                            if(success) {
                                for (i = 0; i < 5; i++) {
                                    for (j = 0; j < gameState.playerShips[i].getType(); j++) {
                                        if (tileHit == gameState.playerShips[i].getTiles()[j]) {
                                            if (!targetships.contains(gameState.playerShips[i])) {
                                                targetships.add(gameState.playerShips[i]);
                                            }
                                            return true;
                                        }
                                    }
                                }
                                shipKillReverse = true;
                                return false;
                            }
                            /*else {
                                tileHit = ((shipseek.get(shipseek.size() - 2)) - (shipseek.get(shipseek.size() - 1))) + shipseek.get(shipseek.size() - 2);
                                success = gameState.AICheckMove(tileHit);
                                if (success) {
                                    for (i = 0; i < 5; i++) {
                                        for (j = 0; j < gameState.playerShips[i].getType(); j++) {
                                            if (tileHit == gameState.playerShips[i].getTiles()[j]) {
                                                if (!targetships.contains(gameState.playerShips[i])) {
                                                    targetships.add(gameState.playerShips[i]);
                                                }
                                                return true;
                                            }
                                        }
                                    }
                                }
                            }*/
                        } else {
                            switch (iterator) {
                                case 0:
                                    tileHit = (shipseek.get(0) - 8);
                                    break;

                                case 1:
                                    tileHit = (shipseek.get(0) - 1);
                                    break;

                                case 2:
                                    tileHit = (shipseek.get(0) + 8);
                                    break;

                                case 3:
                                    tileHit = (shipseek.get(0) + 1);
                                    break;
                            }
                            shipKillReverse = false;
                            success = gameState.AICheckMove(tileHit);
                            if(success) {
                                for (i = 0; i < 5; i++) {
                                    for (j = 0; j < gameState.playerShips[i].getType(); j++) {
                                        if (tileHit == gameState.playerShips[i].getTiles()[j]) {
                                            if (!targetships.contains(gameState.playerShips[i])) {
                                                targetships.add(gameState.playerShips[i]);
                                            }
                                            return true; //hit
                                        }
                                    }
                                }
                                return false;
                            }
                        }
                    } else {
                        int[] hit = target.getHitTiles();
                        ArrayList<Integer> hitList = new ArrayList<>();
                        int[] tiles = target.getTiles();
                        ArrayList<Integer> tileList = new ArrayList<>();
                        for (i = 0; i < target.getNumHits(); i++) {
                            hitList.add(hit[i]);
                        }
                        for (i = 0; i < target.getNumHits(); i++) {
                            tileList.add(tiles[i]);
                        }
                        for (i = 0; i < target.getNumHits(); i++) {
                            tileList.remove(hitList.get(i));
                        }
                        tileHit = tileList.get(0);
                        success = gameState.AICheckMove(tileHit);
                        if(success) {
                            for (i = 0; i < 5; i++) {
                                for (j = 0; j < gameState.playerShips[i].getType(); j++) {
                                    if (tileHit == gameState.playerShips[i].getTiles()[j]) {
                                        if (!targetships.contains(gameState.playerShips[i])) {
                                            targetships.add(gameState.playerShips[i]);
                                            return true; //hit
                                        }
                                    }
                                }
                                for (i = 0; i < 5; i++) {
                                    for (j = 0; j < gameState.playerShips[i].getType(); j++) {
                                        if (tileHit == gameState.playerShips[i].getTiles()[j]) {
                                            if (!targetships.contains(gameState.playerShips[i])) {
                                                targetships.add(gameState.playerShips[i]);
                                                return true; //hit
                                            }
                                        }
                                    }
                                }
                            }
                        }
                        return false;
                    }
                }
            }
        }
        return false; //miss or invalid
    }
}








