package com.example.ashley.battleship;

import android.app.Application;

/**
 * Created by Ashley on 4/13/2016.
 */
public class Globals extends Application {
        private boolean volume = true;
        private boolean colorGraphic = true;
        private int difficulty = 1; //0 for easy

    public boolean getVolume(){
        return this.volume;
    }

    public boolean getGraphics(){
        return this.colorGraphic;
    }

    public int getDifficulty(){
        return this.difficulty;
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



}
