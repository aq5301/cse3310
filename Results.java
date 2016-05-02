package com.example.ashley.battleship;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Results extends AppCompatActivity {

    Globals endGlobal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        endGlobal = (Globals) getApplication();
        String win = endGlobal.getWinner();
        int moves = endGlobal.getPlayerMoves();
        String movesT = "" + moves;
        int hits = endGlobal.getPlayerHits();
        String hitsT = "" + hits;

        double percentage = (endGlobal.getPlayerHits()/endGlobal.getPlayerMoves()) * 100;
        String precT = String.format("%.2f", percentage) + "%";


        Button newGame = (Button) findViewById(R.id.new_game);
        Button mainMenu = (Button) findViewById(R.id.main_menu);
        TextView winnerText = (TextView) findViewById(R.id.winner);
        TextView moveText = (TextView) findViewById(R.id.p_moves);
        TextView hitText = (TextView) findViewById(R.id.p_hits);
        TextView percentText = (TextView) findViewById(R.id.percent);


        winnerText.setText(win);
        moveText.setText(movesT);
        hitText.setText(hitsT);
        percentText.setText(precT);



        newGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent startNewGame = new Intent(Results.this,ShipPlacement.class);
                Results.this.startActivity(startNewGame);
            }
        });

        mainMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent returnMM = new Intent(Results.this, MainMenu.class);
                Results.this.startActivity(returnMM);
            }
        });
    }

}
