package com.example.ashley.battleship;

import android.app.Application;

/**
 * Created by Ashley on 4/13/2016.
 */

//Globals class is essential for loading up a previously started game. Must simply make a call for the information here
public class Globals extends Application {
        private boolean volume = true;
        private boolean colorGraphic = true;
        private int difficulty = 1; //0 for easy
        private int turn = 0, playerMoves = 0, playerHits = 0; //0 for player, 1 for AI
        private Ship [] player_Ships;
        private String winner;

        final private int [] SHIPLIST = {2, 3, 3, 4, 4}; //types of ships both player and CPU have



    public boolean getVolume(){
        return this.volume;
    }

    public boolean getGraphics(){
        return this.colorGraphic;
    }

    public int getDifficulty(){
        return this.difficulty;
    }

    public int getTurn(){
        return this.turn;
    }

    public int getPlayerMoves(){
        return this.playerMoves;
    }

    public int getPlayerHits(){
        return this.playerHits;
    }

    public int[] getSHIPLIST(){
        return this.SHIPLIST;
    }

    public Ship[] getPlayerShips() {
        return player_Ships;
    }


    public String getWinner() {
        return winner;
    }


    public void setVolumeOn(boolean setV){
        this.volume = setV;
    }

    public void setColorGraphics(boolean setG){
        this.colorGraphic = setG;
    }

    public void setDifficulty(int setD){
        this.difficulty = setD;
    }

    public void setTurn(int setT){
        this.turn = setT;
    }

    public void setPlayerMoves(int setPM){
        this.playerMoves = setPM;
    }

    public void setPlayerHits(int setPH){
        this.playerMoves = setPH;
    }

    public void setPlayerShips(Ship[] playerShipsR) {
        this.player_Ships = playerShipsR;
    }

    public void setWinner(String winner) {
        this.winner = winner;
    }




}
