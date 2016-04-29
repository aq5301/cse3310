package com.example.ashley.battleship;


import java.util.ArrayList;
import java.util.*;

/**
 * Created by Will on 4/18/2016.
 */
public class AI {

    private ArrayList<Ship> targetships = new ArrayList<>();
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
        Ship [] AIship = new Ship[0];


        return AIship;
    }

    public boolean makemove() {
        int tileHit = -1;
        boolean success = false;
        int i, j;
        if (targetships.isEmpty()) { // if there are no target ships decide move based on seek mode.
            if (gInfo.getDifficulty() == 0) {
                while (!success) {
                    tileHit = rand.nextInt((64-1) + 1) + 1;
                    success = gameState.AICheckMove(tileHit); //TILE HIT ID NEEDED;
                }
                for (i = 0; i < 5; i++) {
                    for (j = 0; j < gameState.playerShips[i].getType(); j++) {
                        if (tileHit == gameState.playerShips[i].getTiles()[j]) {
                            if (!targetships.contains(gameState.playerShips[i])) {
                                targetships.add(gameState.playerShips[i]);
                                return true;
                            }
                        }
                    }
                }
            } else {
                while (!success) {
                    int[] hitselection = {1, 3, 5, 7, 10, 12, 14, 16, 17, 19, 21, 23, 26, 28, 30, 32, 33, 35, 37, 39, 42, 44, 46, 48, 49, 51, 53, 55, 58, 60, 62, 64};
                    //Array creating a checker pattern on the board, could be made using control structures, but it is easier to hard code it due to board size.
                    tileHit = hitselection[rand.nextInt(32)];
                    success = gameState.AICheckMove(tileHit); //TILE HIT ID NEEDED;
                }
                for (i = 0; i < 5; i++) {
                    for (j = 0; j < gameState.playerShips[i].getType(); j++) {
                        if (tileHit == gameState.playerShips[i].getTiles()[j]) {
                            if (!targetships.contains(gameState.playerShips[i])) {
                                targetships.add(gameState.playerShips[i]);
                                return true;
                            }
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

                for (i = 0; i < target.numHits; i++)
                    shipseek.add(holder[i]);
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

                } else {
                    if (!shipKillWasReversed) {
                        if (!shipKillReverse) {
                            tileHit = ((shipseek.size() - 1) - (shipseek.size() - 2));
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
                            shipKillWasReversed = true;
                            success = gameState.AICheckMove(tileHit);
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
                    } else {
                        int[] hit = target.getHitTiles();
                        ArrayList<Integer> hitList = new ArrayList<>();
                        int[] tiles = target.getTiles();
                        ArrayList<Integer> tileList = new ArrayList<>();
                        for (i = 0; i < target.numHits; i++) {
                            hitList.add(hit[i]);
                        }
                        for (i = 0; i < target.numHits; i++) {
                            tileList.add(tiles[i]);
                        }
                        for (i = 0; i < target.numHits; i++) {
                            tileList.remove(hitList.get(i));
                        }
                        tileHit = tileList.get(0);
                        success = gameState.AICheckMove(tileHit);
                        for (i = 0; i < 5; i++) {
                            for (j = 0; j < gameState.playerShips[i].getType(); j++) {
                                if (tileHit == gameState.playerShips[i].getTiles()[j]) {
                                    if (!targetships.contains(gameState.playerShips[i])) {
                                        targetships.add(gameState.playerShips[i]);
                                        return true; //hit
                                    }
                                }
                            }
                            success = gameState.AICheckMove(tileHit);
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
                }
            }
        }
        return false; //a miss
    }
}








