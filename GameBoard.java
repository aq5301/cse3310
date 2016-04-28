package com.example.ashley.battleship;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class GameBoard extends AppCompatActivity {

    Globals globalInfo;

    final Handler AIhandler = new Handler();
    int turn, playerRemaining, AIRemaining;
    int numPlayerMoves;
    int numPlayerHits;
    TextView currentTurn;
    Ship [] playerShips;
    Ship [] AIShips;
    Boolean [] hitTiles;
    Ship selectedShip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_board);

        globalInfo = (Globals) getApplication();
        turn = 0; hitTiles = new Boolean[65];
        playerRemaining = 5; AIRemaining = 5;
        playerShips = globalInfo.getPlayerShips();
        AIShips = globalInfo.getAIships();

        numPlayerMoves = globalInfo.getPlayerMoves();
        numPlayerHits = globalInfo.getPlayerHits();

        drawPlayerGrid();

        currentTurn = (TextView) findViewById(R.id.ongoing_turn);
        currentTurn.setText("Your Turn");

        Button quit = (Button) findViewById(R.id.quit);
        quit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(turn == 0){
                    quitGame();
                }

            }
        });
    }

    public void drawPlayerGrid(){
        int tile, id, tileRe; String tileS, tilePath;
        ImageView currentTile;
        for(int run = 0; run < 5; run++){
           if(playerShips[run].getType() == 2){
               //if getarrange == 1, then the next tile is to the right, looks like ""}""
               if(playerShips[run].getArrange() == 1) {
                   tile = playerShips[run].getTiles()[0]; //provide first tile
                   tileS = "Tile" + tile + "s";
                   id = getResources().getIdentifier(tileS, "id", getPackageName());
                   currentTile = (ImageView) findViewById(id);
                   tilePath = "drawable/ship1placeholder";
                   tileRe = getResources().getIdentifier(tilePath, null, getPackageName());
                   currentTile.setForeground(getResources().getDrawable(tileRe, null));
                   ///
                   tile = playerShips[run].getTiles()[1]; //provide second tile
                   tileS = "Tile" + tile + "s";
                   id = getResources().getIdentifier(tileS, "id", getPackageName());
                   currentTile = (ImageView) findViewById(id);
                   tilePath = "drawable/ship2placeholder";
                   tileRe = getResources().getIdentifier(tilePath, null, getPackageName());
                   currentTile.setForeground(getResources().getDrawable(tileRe, null));
               }else{
                   tile = playerShips[run].getTiles()[0]; //provide first tile
                   tileS = "Tile" + tile + "s";
                   id = getResources().getIdentifier(tileS, "id", getPackageName());
                   currentTile = (ImageView) findViewById(id);
                   tilePath = "drawable/ship2placeholder";
                   tileRe = getResources().getIdentifier(tilePath, null, getPackageName());
                   currentTile.setForeground(getResources().getDrawable(tileRe, null));
                   ///
                   tile = playerShips[run].getTiles()[1]; //provide second tile
                   tileS = "Tile" + tile + "s";
                   id = getResources().getIdentifier(tileS, "id", getPackageName());
                   currentTile = (ImageView) findViewById(id);
                   tilePath = "drawable/ship1placeholder";
                   tileRe = getResources().getIdentifier(tilePath, null, getPackageName());
                   currentTile.setForeground(getResources().getDrawable(tileRe, null));

               }
               ///////////////////////////////
           }else if(playerShips[run].getType() == 3){
               if(playerShips[run].getArrange() == 1){ //vertical
                   tile = playerShips[run].getTiles()[0]; //provide first tile CENTER
                   tileS = "Tile" + tile + "s";
                   id = getResources().getIdentifier(tileS, "id", getPackageName());
                   currentTile = (ImageView) findViewById(id);
                   tilePath = "drawable/ship1placeholder";
                   tileRe = getResources().getIdentifier(tilePath, null, getPackageName());
                   currentTile.setForeground(getResources().getDrawable(tileRe, null));
                   /////
                   tile = playerShips[run].getTiles()[1]; //provide second tile TOP
                   tileS = "Tile" + tile + "s";
                   id = getResources().getIdentifier(tileS, "id", getPackageName());
                   currentTile = (ImageView) findViewById(id);
                   tilePath = "drawable/ship2placeholder";
                   tileRe = getResources().getIdentifier(tilePath, null, getPackageName());
                   currentTile.setForeground(getResources().getDrawable(tileRe, null));
                   /////
                   tile = playerShips[run].getTiles()[2]; //provide third tile BOTTOM
                   tileS = "Tile" + tile + "s";
                   id = getResources().getIdentifier(tileS, "id", getPackageName());
                   currentTile = (ImageView) findViewById(id);
                   tilePath = "drawable/ship3placeholder";
                   tileRe = getResources().getIdentifier(tilePath, null, getPackageName());
                   currentTile.setForeground(getResources().getDrawable(tileRe, null));
               }else{ //horizontal
                   tile = playerShips[run].getTiles()[0]; //provide first tile CENTER
                   tileS = "Tile" + tile + "s";
                   id = getResources().getIdentifier(tileS, "id", getPackageName());
                   currentTile = (ImageView) findViewById(id);
                   tilePath = "drawable/ship1placeholder";
                   tileRe = getResources().getIdentifier(tilePath, null, getPackageName());
                   currentTile.setForeground(getResources().getDrawable(tileRe, null));
                   /////
                   tile = playerShips[run].getTiles()[1]; //provide second tile LEFT
                   tileS = "Tile" + tile + "s";
                   id = getResources().getIdentifier(tileS, "id", getPackageName());
                   currentTile = (ImageView) findViewById(id);
                   tilePath = "drawable/ship2placeholder";
                   tileRe = getResources().getIdentifier(tilePath, null, getPackageName());
                   currentTile.setForeground(getResources().getDrawable(tileRe, null));
                   /////
                   tile = playerShips[run].getTiles()[2]; //provide third tile RIGHT
                   tileS = "Tile" + tile + "s";
                   id = getResources().getIdentifier(tileS, "id", getPackageName());
                   currentTile = (ImageView) findViewById(id);
                   tilePath = "drawable/ship3placeholder";
                   tileRe = getResources().getIdentifier(tilePath, null, getPackageName());
                   currentTile.setForeground(getResources().getDrawable(tileRe, null));

               }

           }else if(playerShips[run].getType() == 4){
               if(playerShips[run].getArrange() == 1){ //vertical
                   tile = playerShips[run].getTiles()[0]; //provide first tile CENTER
                   tileS = "Tile" + tile + "s";
                   id = getResources().getIdentifier(tileS, "id", getPackageName());
                   currentTile = (ImageView) findViewById(id);
                   tilePath = "drawable/ship1placeholder";
                   tileRe = getResources().getIdentifier(tilePath, null, getPackageName());
                   currentTile.setForeground(getResources().getDrawable(tileRe, null));
                   /////
                   tile = playerShips[run].getTiles()[1]; //provide second tile LEFT
                   tileS = "Tile" + tile + "s";
                   id = getResources().getIdentifier(tileS, "id", getPackageName());
                   currentTile = (ImageView) findViewById(id);
                   tilePath = "drawable/ship2placeholder";
                   tileRe = getResources().getIdentifier(tilePath, null, getPackageName());
                   currentTile.setForeground(getResources().getDrawable(tileRe, null));
                   /////
                   tile = playerShips[run].getTiles()[2]; //provide third tile RIGHT
                   tileS = "Tile" + tile + "s";
                   id = getResources().getIdentifier(tileS, "id", getPackageName());
                   currentTile = (ImageView) findViewById(id);
                   tilePath = "drawable/ship3placeholder";
                   tileRe = getResources().getIdentifier(tilePath, null, getPackageName());
                   currentTile.setForeground(getResources().getDrawable(tileRe, null));
                   /////
                   tile = playerShips[run].getTiles()[3]; //provide third tile FAR RIGHT
                   tileS = "Tile" + tile + "s";
                   id = getResources().getIdentifier(tileS, "id", getPackageName());
                   currentTile = (ImageView) findViewById(id);
                   tilePath = "drawable/ship3placeholder";
                   tileRe = getResources().getIdentifier(tilePath, null, getPackageName());
                   currentTile.setForeground(getResources().getDrawable(tileRe, null));

               }else{ //horizontal
                   tile = playerShips[run].getTiles()[0]; //provide first tile CENTER
                   tileS = "Tile" + tile + "s";
                   id = getResources().getIdentifier(tileS, "id", getPackageName());
                   currentTile = (ImageView) findViewById(id);
                   tilePath = "drawable/ship1placeholder";
                   tileRe = getResources().getIdentifier(tilePath, null, getPackageName());
                   currentTile.setForeground(getResources().getDrawable(tileRe, null));
                   /////
                   tile = playerShips[run].getTiles()[1]; //provide second tile TOP
                   tileS = "Tile" + tile + "s";
                   id = getResources().getIdentifier(tileS, "id", getPackageName());
                   currentTile = (ImageView) findViewById(id);
                   tilePath = "drawable/ship2placeholder";
                   tileRe = getResources().getIdentifier(tilePath, null, getPackageName());
                   currentTile.setForeground(getResources().getDrawable(tileRe, null));
                   /////
                   tile = playerShips[run].getTiles()[2]; //provide third tile BOTTOM
                   tileS = "Tile" + tile + "s";
                   id = getResources().getIdentifier(tileS, "id", getPackageName());
                   currentTile = (ImageView) findViewById(id);
                   tilePath = "drawable/ship3placeholder";
                   tileRe = getResources().getIdentifier(tilePath, null, getPackageName());
                   currentTile.setForeground(getResources().getDrawable(tileRe, null));
                   /////
                   tile = playerShips[run].getTiles()[3]; //provide third tile FAR BOTTOM
                   tileS = "Tile" + tile + "s";
                   id = getResources().getIdentifier(tileS, "id", getPackageName());
                   currentTile = (ImageView) findViewById(id);
                   tilePath = "drawable/ship3placeholder";
                   tileRe = getResources().getIdentifier(tilePath, null, getPackageName());
                   currentTile.setForeground(getResources().getDrawable(tileRe, null));
               }

           }else{
               System.out.print("Error");
           }

        }
    }

    //listeners for all 64 grid cells
    public void OnClick(View v) {
        if(turn == 0) {
            switch (v.getId()) {
                case R.id.Tile1:
                    playerMove(1, R.id.Tile1);
                    break;

                case R.id.Tile2:
                    playerMove(2, R.id.Tile2);
                    break;

                case R.id.Tile3:
                    playerMove(3, R.id.Tile3);
                    break;
                case R.id.Tile4:
                    playerMove(4, R.id.Tile4);
                    break;

                case R.id.Tile5:
                    playerMove(5, R.id.Tile5);
                    break;

                case R.id.Tile6:
                    playerMove(6, R.id.Tile6);
                    break;
                case R.id.Tile7:
                    playerMove(7, R.id.Tile7);
                    break;

                case R.id.Tile8:
                    playerMove(8, R.id.Tile8);
                    break;

                case R.id.Tile9:
                    playerMove(9, R.id.Tile9);
                    break;
                case R.id.Tile10:
                    playerMove(10, R.id.Tile10);
                    break;

                case R.id.Tile11:
                    playerMove(11, R.id.Tile11);
                    break;

                case R.id.Tile12:
                    playerMove(12, R.id.Tile12);
                    break;
                case R.id.Tile13:
                    playerMove(13, R.id.Tile13);
                    break;

                case R.id.Tile14:
                    playerMove(14, R.id.Tile14);
                    break;

                case R.id.Tile15:
                    playerMove(15, R.id.Tile15);
                    break;
                case R.id.Tile16:
                    playerMove(16, R.id.Tile16);
                    break;

                case R.id.Tile17:
                    playerMove(17, R.id.Tile17);
                    break;

                case R.id.Tile18:
                    playerMove(18, R.id.Tile18);
                    break;
                case R.id.Tile19:
                    playerMove(19, R.id.Tile19);
                    break;

                case R.id.Tile20:
                    playerMove(20, R.id.Tile20);
                    break;

                case R.id.Tile21:
                    playerMove(21, R.id.Tile21);
                    break;
                case R.id.Tile22:
                    playerMove(22, R.id.Tile22);
                    break;

                case R.id.Tile23:
                    playerMove(23, R.id.Tile23);
                    break;

                case R.id.Tile24:
                    playerMove(24, R.id.Tile24);
                    break;
                case R.id.Tile25:
                    playerMove(25, R.id.Tile25);
                    break;

                case R.id.Tile26:
                    playerMove(26, R.id.Tile26);
                    break;

                case R.id.Tile27:
                    playerMove(27, R.id.Tile27);
                    break;
                case R.id.Tile28:
                    playerMove(28, R.id.Tile28);
                    break;

                case R.id.Tile29:
                    playerMove(29, R.id.Tile29);
                    break;

                case R.id.Tile30:
                    playerMove(30, R.id.Tile30);
                    break;
                case R.id.Tile31:
                    playerMove(31, R.id.Tile31);
                    break;

                case R.id.Tile32:
                    playerMove(32, R.id.Tile32);
                    break;

                case R.id.Tile33:
                    playerMove(33, R.id.Tile33);
                    break;
                case R.id.Tile34:
                    playerMove(34, R.id.Tile34);
                    break;

                case R.id.Tile35:
                    playerMove(35, R.id.Tile35);
                    break;

                case R.id.Tile36:
                    playerMove(36, R.id.Tile36);
                    break;
                case R.id.Tile37:
                    playerMove(37, R.id.Tile37);
                    break;

                case R.id.Tile38:
                    playerMove(38, R.id.Tile38);
                    break;

                case R.id.Tile39:
                    playerMove(39, R.id.Tile39);
                    break;
                case R.id.Tile40:
                    playerMove(40, R.id.Tile40);
                    break;

                case R.id.Tile41:
                    playerMove(41, R.id.Tile41);
                    break;

                case R.id.Tile42:
                    playerMove(42, R.id.Tile42);
                    break;
                case R.id.Tile43:
                    playerMove(43, R.id.Tile43);
                    break;

                case R.id.Tile44:
                    playerMove(44, R.id.Tile44);
                    break;

                case R.id.Tile45:
                    playerMove(45, R.id.Tile45);
                    break;
                case R.id.Tile46:
                    playerMove(46, R.id.Tile46);
                    break;

                case R.id.Tile47:
                    playerMove(47, R.id.Tile47);
                    break;

                case R.id.Tile48:
                    playerMove(48, R.id.Tile48);
                    break;
                case R.id.Tile49:
                    playerMove(49, R.id.Tile49);
                    break;

                case R.id.Tile50:
                    playerMove(50, R.id.Tile50);
                    break;

                case R.id.Tile51:
                    playerMove(51, R.id.Tile51);
                    break;
                case R.id.Tile52:
                    playerMove(52, R.id.Tile52);
                    break;

                case R.id.Tile53:
                    playerMove(53, R.id.Tile53);
                    break;

                case R.id.Tile54:
                    playerMove(54, R.id.Tile54);
                    break;
                case R.id.Tile55:
                    playerMove(55, R.id.Tile55);
                    break;

                case R.id.Tile56:
                    playerMove(56, R.id.Tile56);
                    break;

                case R.id.Tile57:
                    playerMove(57, R.id.Tile57);
                    break;
                case R.id.Tile58:
                    playerMove(58, R.id.Tile58);
                    break;

                case R.id.Tile59:
                    playerMove(59, R.id.Tile59);
                    break;

                case R.id.Tile60:
                    playerMove(60, R.id.Tile60);
                    break;
                case R.id.Tile61:
                    playerMove(61, R.id.Tile61);
                    break;

                case R.id.Tile62:
                    playerMove(62, R.id.Tile62);
                    break;

                case R.id.Tile63:
                    playerMove(63, R.id.Tile63);
                    break;

                case R.id.Tile64:
                    playerMove(64, R.id.Tile64);
                    break;

                default:
                    break;

            }
        }
    }


    public void playerMove(int tileNum, int tileID) {

        if(hitTiles[tileNum]){ //tile cannot be selected more than once
            return;
        }
        hitTiles[tileNum] = true;

        //by default, this is a miss; the for loops will check if it is a hit
        Boolean isHit = false, sink = false; String path; int res;
        ImageView hitTile;
        int numberHits;
        numPlayerMoves = numPlayerMoves + 1;

        //loop through the AIShip list; then loop through the Tile array of each individual ship
        for(int findTile = 0; findTile < 5; findTile++){
            selectedShip = AIShips[findTile];
            for(int findTile2 = 0; findTile2 < AIShips[findTile].getTiles().length; findTile2++){
                if(tileNum == AIShips[findTile].getTiles()[findTile2]){
                    isHit = true;
                    numberHits = AIShips[findTile].getNumHits() + 1;
                    AIShips[findTile].setNumHits(numberHits);
                    if(AIShips[findTile].getNumHits() == AIShips[findTile].getType()){ //if #hits = ship type, it is sunk
                        AIShips[findTile].setSunk(true);
                        sink = true;
                        AIRemaining--;
                        //set # AI ships sunk on game board: increment number by 1
                    }
                    continue;
                }
            }
            if(isHit){
                numPlayerHits = numPlayerHits + 1;
                continue;

            }
        }

        if(isHit){
            hitTile = (ImageView) findViewById(tileID);
            path = "drawable/threeship"; //CHANGE TO HIT GRAPHIC
            res = getResources().getIdentifier(path, null, getPackageName());
            hitTile.setBackground(getResources().getDrawable(res, null));
            //play sound
            if(AIRemaining <= 0){
                endGame("You Win!");
            }
            else if(sink){
                currentTurn.setText("Computer's Turn");
                turn = 1;
                AIMove();
            }

        }
        else{ //a miss
            hitTile = (ImageView) findViewById(tileID);
            path = "drawable/miss";
            res = getResources().getIdentifier(path, null, getPackageName());
            hitTile.setBackground(getResources().getDrawable(res, null));
            //play sound
            currentTurn.setText("Computer's Turn");
            turn = 1;
            AIMove();
        }
    }

    public void AIMove() {






        AIhandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                currentTurn.setText("Your Turn");
                turn = 0;

            }
        }, 1000);

    }

    //endGame ends the game "properly" or when one of the players lose all their ships
    public void endGame(String winner){
        globalInfo.setWinner(winner);
        globalInfo.setPlayerMoves(numPlayerMoves);
        globalInfo.setPlayerHits(numPlayerHits);

        Intent Quit = new Intent(GameBoard.this, Results.class);
        GameBoard.this.startActivity(Quit);
    }

    //quitGame ends the game prematurely and will save the current state
    public void quitGame(){
        //SAVE BOTH PLAYER AND AI BOARD, CURRENT TURN, return to main menu
        //saveGame(PLAYERGRID, AIGRID, turn);

        globalInfo.setPlayerMoves(numPlayerMoves);
        globalInfo.setPlayerHits(numPlayerHits);
        globalInfo.setTurn(turn);

        Intent earlyQuit = new Intent(GameBoard.this, MainMenu.class);
        GameBoard.this.startActivity(earlyQuit);
    }

}
