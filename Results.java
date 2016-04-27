package com.example.ashley.battleship;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class Results extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        final Globals endGlobal = new Globals();
        int moves = endGlobal.getPlayerMoves();
        int hits = endGlobal.getPlayerHits();
        double percentage = (hits/moves) * 100;

        Button newGame = (Button) findViewById(R.id.new_game);
        Button mainMenu = (Button) findViewById(R.id.main_menu);
        final TextView win = (TextView) findViewById(R.id.winner);
        final TextView totalHits = (TextView) findViewById(R.id.p_hits);
        final TextView totalMoves = (TextView) findViewById(R.id.p_moves);
        final TextView percent = (TextView) findViewById(R.id.percent);


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
