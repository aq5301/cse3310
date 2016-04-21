package com.example.ashley.battleship;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

public class GameBoard extends AppCompatActivity {

    final Globals globalInfo = (Globals) getApplication();
    //carry on PLAYER and COMPUTER matrices

    int turn,// 0 for Player, 1 for AI
    playerMoves;
    Boolean ongoing = true; //true when a winner is declared


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_board);

        Button quit = (Button) findViewById(R.id.quit);

        while(ongoing)
        {
            playerMove();
            AIMove();
        }

        quit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                quitGame();
            }
        });
    }

    public void playerMove() {
        // check if any ships remain (no more 1's or 2's remain: if so, move to endGame- COMP WIN
        /*
            if(PLAYER.isEmpty()){
                endGame("Computer");
            }
            else . . .
         */

    }

    public void AIMove() {
        // check if any ships remain (no more 1's or 2's remain: if so, move to endGame- PLAYER WIN
        /*
            if(COMPUTER.isEmpty()){
                endGame("Player");
            }
            else . . .
         */
    }

    //endGame ends the game "properly" or when one of the players lose all their ships
    public void endGame(String winner){
        //MOVE TO RESULTS SCREEN
        ongoing = false;
        //store score;

        Intent Quit = new Intent(GameBoard.this, Results.class);
        GameBoard.this.startActivity(Quit);
    }

    //quitGame ends the game prematurely and will save the current state
    public void quitGame(){
        //SAVE BOTH PLAYER AND AI BOARD, CURRENT TURN, return to main menu
        //saveGame(PLAYERGRID, AIGRID, turn);
        Intent earlyQuit = new Intent(GameBoard.this, MainMenu.class);
        GameBoard.this.startActivity(earlyQuit);
    }

}
