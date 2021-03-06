package com.example.ashley.battleship;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

public class GameBoard extends AppCompatActivity {

    Globals globalInfo;

    final Handler AIhandler = new Handler();
    int turn, playerRemaining, AIRemaining;
    AI computer;
    int numPlayerMoves;
    int numPlayerHits;
    int AIhitTile;
    int [] AIhits;
    TextView currentTurn;
    Ship [] playerShips;
    Ship [] AIShips;
    boolean [] hitTiles;
    boolean [] hitTilesAI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_board);
        ////AI and globals
        globalInfo = (Globals) getApplication();
        computer = new AI(this, this.globalInfo);
        /////misc values
        turn = 0; hitTiles = new boolean[65]; AIhits = new int[5];
        hitTilesAI = new boolean[65];
        playerRemaining = 5; AIRemaining = 5;
        //////Player and AI ships
        playerShips = globalInfo.getPlayerShips();
        AIShips = computer.placeships();
        ////set up player moves, hits
        numPlayerMoves = 0;
        numPlayerHits = 0;
        ////interface
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

        if(!globalInfo.getGraphics()){ //if monochrome
            String tpName = "Tile", mPath = "drawable/emptym";; int r, id; Drawable imag;
            ImageView current;
            for(int i = 1; i < 65; i++){
                tpName = tpName + i;
                id = getResources().getIdentifier(tpName, "id", getPackageName());
                current = (ImageView) findViewById(id);
                r = getResources().getIdentifier(mPath, null, getPackageName());
                imag = getResources().getDrawable(r, null);
                current.setForeground(imag);

                tpName = tpName + "s";
                id = getResources().getIdentifier(tpName, "id", getPackageName());
                current = (ImageView) findViewById(id);
                r = getResources().getIdentifier(mPath, null, getPackageName());
                imag = getResources().getDrawable(r, null);
                current.setForeground(imag);

                tpName = tpName.substring(0,4);
            }
        }

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
                   tilePath = "drawable/shipendpiece";
                   if(!globalInfo.getGraphics()){
                       tilePath = tilePath + "m";
                   }
                   tileRe = getResources().getIdentifier(tilePath, null, getPackageName());
                   currentTile.setForeground(getResources().getDrawable(tileRe, null));
                   ///
                   tile = playerShips[run].getTiles()[1]; //provide second tile
                   tileS = "Tile" + tile + "s";
                   id = getResources().getIdentifier(tileS, "id", getPackageName());
                   currentTile = (ImageView) findViewById(id);
                   tilePath = "drawable/shipendpiece";
                   if(!globalInfo.getGraphics()){
                       tilePath = tilePath + "m";
                   }
                   tileRe = getResources().getIdentifier(tilePath, null, getPackageName());
                   currentTile.setForeground(getResources().getDrawable(tileRe, null));
                   currentTile.setScaleX(-1.0f);
               }else{
                   tile = playerShips[run].getTiles()[0]; //provide first tile
                   tileS = "Tile" + tile + "s";
                   id = getResources().getIdentifier(tileS, "id", getPackageName());
                   currentTile = (ImageView) findViewById(id);
                   tilePath = "drawable/shipendpiece";
                   if(!globalInfo.getGraphics()){
                       tilePath = tilePath + "m";
                   }
                   tileRe = getResources().getIdentifier(tilePath, null, getPackageName());
                   currentTile.setForeground(getResources().getDrawable(tileRe, null));
                   currentTile.setScaleX(-1.0f);
                   ///
                   tile = playerShips[run].getTiles()[1]; //provide second tile
                   tileS = "Tile" + tile + "s";
                   id = getResources().getIdentifier(tileS, "id", getPackageName());
                   currentTile = (ImageView) findViewById(id);
                   tilePath = "drawable/shipendpiece";
                   if(!globalInfo.getGraphics()){
                       tilePath = tilePath + "m";
                   }
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
                   tilePath = "drawable/shipmidsection";
                   if(!globalInfo.getGraphics()){
                       tilePath = tilePath + "m";
                   }
                   tileRe = getResources().getIdentifier(tilePath, null, getPackageName());
                   currentTile.setForeground(getResources().getDrawable(tileRe, null));
                   currentTile.setRotation(90f);
                   /////
                   tile = playerShips[run].getTiles()[1]; //provide second tile TOP
                   tileS = "Tile" + tile + "s";
                   id = getResources().getIdentifier(tileS, "id", getPackageName());
                   currentTile = (ImageView) findViewById(id);
                   tilePath = "drawable/shipendpiece";
                   if(!globalInfo.getGraphics()){
                       tilePath = tilePath + "m";
                   }
                   tileRe = getResources().getIdentifier(tilePath, null, getPackageName());
                   currentTile.setForeground(getResources().getDrawable(tileRe, null));
                   currentTile.setRotation(90f);
                   /////
                   tile = playerShips[run].getTiles()[2]; //provide third tile BOTTOM
                   tileS = "Tile" + tile + "s";
                   id = getResources().getIdentifier(tileS, "id", getPackageName());
                   currentTile = (ImageView) findViewById(id);
                   tilePath = "drawable/shipendpiece";
                   if(!globalInfo.getGraphics()){
                       tilePath = tilePath + "m";
                   }
                   tileRe = getResources().getIdentifier(tilePath, null, getPackageName());
                   currentTile.setForeground(getResources().getDrawable(tileRe, null));
                   currentTile.setScaleX(-1.0f);
                   currentTile.setRotation(90f);
               }else{ //horizontal
                   tile = playerShips[run].getTiles()[0]; //provide first tile CENTER
                   tileS = "Tile" + tile + "s";
                   id = getResources().getIdentifier(tileS, "id", getPackageName());
                   currentTile = (ImageView) findViewById(id);
                   tilePath = "drawable/shipmidsection";
                   if(!globalInfo.getGraphics()){
                       tilePath = tilePath + "m";
                   }
                   tileRe = getResources().getIdentifier(tilePath, null, getPackageName());
                   currentTile.setForeground(getResources().getDrawable(tileRe, null));
                   /////
                   tile = playerShips[run].getTiles()[1]; //provide second tile LEFT
                   tileS = "Tile" + tile + "s";
                   id = getResources().getIdentifier(tileS, "id", getPackageName());
                   currentTile = (ImageView) findViewById(id);
                   tilePath = "drawable/shipendpiece";
                   if(!globalInfo.getGraphics()){
                       tilePath = tilePath + "m";
                   }
                   tileRe = getResources().getIdentifier(tilePath, null, getPackageName());
                   currentTile.setForeground(getResources().getDrawable(tileRe, null));
                   /////
                   tile = playerShips[run].getTiles()[2]; //provide third tile RIGHT
                   tileS = "Tile" + tile + "s";
                   id = getResources().getIdentifier(tileS, "id", getPackageName());
                   currentTile = (ImageView) findViewById(id);
                   tilePath = "drawable/shipendpiece";
                   if(!globalInfo.getGraphics()){
                       tilePath = tilePath + "m";
                   }
                   tileRe = getResources().getIdentifier(tilePath, null, getPackageName());
                   currentTile.setForeground(getResources().getDrawable(tileRe, null));
                   currentTile.setScaleX(-1.0f);

               }

           }else if(playerShips[run].getType() == 4){
               if(playerShips[run].getArrange() == 1){ //vertical
                   tile = playerShips[run].getTiles()[0]; //provide first tile CENTER
                   tileS = "Tile" + tile + "s";
                   id = getResources().getIdentifier(tileS, "id", getPackageName());
                   currentTile = (ImageView) findViewById(id);
                   tilePath = "drawable/shipmidsection";
                   if(!globalInfo.getGraphics()){
                       tilePath = tilePath + "m";
                   }
                   tileRe = getResources().getIdentifier(tilePath, null, getPackageName());
                   currentTile.setForeground(getResources().getDrawable(tileRe, null));
                   currentTile.setRotation(90f);
                   /////
                   tile = playerShips[run].getTiles()[1]; //provide second tile LEFT
                   tileS = "Tile" + tile + "s";
                   id = getResources().getIdentifier(tileS, "id", getPackageName());
                   currentTile = (ImageView) findViewById(id);
                   tilePath = "drawable/shipendpiece";
                   if(!globalInfo.getGraphics()){
                       tilePath = tilePath + "m";
                   }
                   tileRe = getResources().getIdentifier(tilePath, null, getPackageName());
                   currentTile.setForeground(getResources().getDrawable(tileRe, null));
                   currentTile.setRotation(90f);
                   /////
                   tile = playerShips[run].getTiles()[2]; //provide third tile RIGHT
                   tileS = "Tile" + tile + "s";
                   id = getResources().getIdentifier(tileS, "id", getPackageName());
                   currentTile = (ImageView) findViewById(id);
                   tilePath = "drawable/shipmidsection";
                   if(!globalInfo.getGraphics()){
                       tilePath = tilePath + "m";
                   }
                   tileRe = getResources().getIdentifier(tilePath, null, getPackageName());
                   currentTile.setForeground(getResources().getDrawable(tileRe, null));
                   currentTile.setRotation(90f);
                   /////
                   tile = playerShips[run].getTiles()[3]; //provide third tile FAR RIGHT
                   tileS = "Tile" + tile + "s";
                   id = getResources().getIdentifier(tileS, "id", getPackageName());
                   currentTile = (ImageView) findViewById(id);
                   tilePath = "drawable/shipendpiece";
                   if(!globalInfo.getGraphics()){
                       tilePath = tilePath + "m";
                   }
                   tileRe = getResources().getIdentifier(tilePath, null, getPackageName());
                   currentTile.setForeground(getResources().getDrawable(tileRe, null));
                   currentTile.setScaleX(-1.0f);
                   currentTile.setRotation(90f);

               }else{ //horizontal
                   tile = playerShips[run].getTiles()[0]; //provide first tile CENTER
                   tileS = "Tile" + tile + "s";
                   id = getResources().getIdentifier(tileS, "id", getPackageName());
                   currentTile = (ImageView) findViewById(id);
                   tilePath = "drawable/shipmidsection";
                   if(!globalInfo.getGraphics()){
                       tilePath = tilePath + "m";
                   }
                   tileRe = getResources().getIdentifier(tilePath, null, getPackageName());
                   currentTile.setForeground(getResources().getDrawable(tileRe, null));

                   /////
                   tile = playerShips[run].getTiles()[1]; //provide second tile LEFT
                   tileS = "Tile" + tile + "s";
                   id = getResources().getIdentifier(tileS, "id", getPackageName());
                   currentTile = (ImageView) findViewById(id);
                   tilePath = "drawable/shipendpiece";
                   if(!globalInfo.getGraphics()){
                       tilePath = tilePath + "m";
                   }
                   tileRe = getResources().getIdentifier(tilePath, null, getPackageName());
                   currentTile.setForeground(getResources().getDrawable(tileRe, null));

                   /////
                   tile = playerShips[run].getTiles()[2]; //provide third tile RIGHT
                   tileS = "Tile" + tile + "s";
                   id = getResources().getIdentifier(tileS, "id", getPackageName());
                   currentTile = (ImageView) findViewById(id);
                   tilePath = "drawable/shipmidsection";
                   if(!globalInfo.getGraphics()){
                       tilePath = tilePath + "m";
                   }
                   tileRe = getResources().getIdentifier(tilePath, null, getPackageName());
                   currentTile.setForeground(getResources().getDrawable(tileRe, null));

                   /////
                   tile = playerShips[run].getTiles()[3]; //provide third tile FAR RIGHT
                   tileS = "Tile" + tile + "s";
                   id = getResources().getIdentifier(tileS, "id", getPackageName());
                   currentTile = (ImageView) findViewById(id);
                   tilePath = "drawable/shipendpiece";
                   if(!globalInfo.getGraphics()){
                       tilePath = tilePath + "m";
                   }
                   tileRe = getResources().getIdentifier(tilePath, null, getPackageName());
                   currentTile.setForeground(getResources().getDrawable(tileRe, null));
                   currentTile.setScaleX(-1.0f);

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
        boolean isHit = false; String path; int res;
        ImageView hitTile;
        int numberHits;
        numPlayerMoves++;

        //loop through the AIShip list; then loop through the Tile array of each individual ship
        for(int findTile = 0; findTile < 5; findTile++){
            for(int findTile2 = 0; findTile2 < AIShips[findTile].getTiles().length; findTile2++){
                if(tileNum == AIShips[findTile].getTiles()[findTile2]){
                    isHit = true;
                    numberHits = AIShips[findTile].getNumHits() + 1;
                    AIShips[findTile].setNumHits(numberHits);
                    if(AIShips[findTile].getNumHits() == AIShips[findTile].getType()){ //if #hits = ship type, it is sunk
                        AIShips[findTile].setSunk(true);
                        AIRemaining--;
                        updateScore(5 - AIRemaining);
                        //set # AI ships sunk on game board: increment number by 1
                    }

                }
            }
        }

        if(isHit){
            hitTile = (ImageView) findViewById(tileID);
            path = "drawable/ship1placeholder"; //CHANGE TO HIT GRAPHIC
            if(!globalInfo.getGraphics()){
                path = path + "m";
            }
            res = getResources().getIdentifier(path, null, getPackageName());
            hitTile.setForeground(getResources().getDrawable(res, null));
            //play sound
            if(AIRemaining <= 0){
                numPlayerHits++;
                endGame("You Win!");
            }
            numPlayerHits++;
            currentTurn.setText("Computer's Turn");
            turn = 1;
            AIMove();

        }
        else{ //a miss
            hitTile = (ImageView) findViewById(tileID);
            path = "drawable/miss";
            if(!globalInfo.getGraphics()){
                path = path + "m";
            }
            res = getResources().getIdentifier(path, null, getPackageName());
            hitTile.setForeground(getResources().getDrawable(res, null));
            //play sound
            currentTurn.setText("Computer's Turn");
            turn = 1;
            AIMove();
        }
    }

    public boolean AICheckMove(int targetTile){
        if(targetTile < 1|| targetTile > 64){ //check for out of bounds
            return false;
        }
        else if(hitTilesAI[targetTile]){ //tile cannot be selected more than once
            return false;
        }
        else
        {
            AIhitTile = targetTile;
            hitTilesAI[targetTile] = true;
            return true;
        }

    }

    public void AIMove() {
        boolean isHitAI;
        String path; int res;
        ImageView hitTile;
        isHitAI = computer.makemove();
        int tileNum = AIhitTile, tileID;
        String tilePiece = "Tile" + tileNum + "s";
        tileID = getResources().getIdentifier(tilePiece, "id", getPackageName());


        if(isHitAI){
            hitTile = (ImageView) findViewById(tileID);
            path = "drawable/ship1placeholder"; //CHANGE TO HIT GRAPHIC
            if(!globalInfo.getGraphics()){
                path = path + "m";
            }
            res = getResources().getIdentifier(path, null, getPackageName());
            hitTile.setForeground(getResources().getDrawable(res, null));
            //play sound

            for(int findTile1 = 0; findTile1 < 5; findTile1++){ //findTile1 = ship in shiplist index
                for(int findTile2 = 0; findTile2 < playerShips[findTile1].getTiles().length; findTile2++){
                    if(AIhitTile == playerShips[findTile1].getTiles()[findTile2]){ //findTile2 refers to a ship's chosen tile index
                        playerShips[findTile1].getHitTiles()[AIhits[findTile1]] = AIhitTile; //where the shot landed
                        AIhits[findTile1] = AIhits[findTile1]++; //increments the next index for current ship hit array
                        playerShips[findTile1].setNumHits(playerShips[findTile1].getNumHits() + 1);
                        if(playerShips[findTile1].getNumHits() == playerShips[findTile1].getType()){
                            playerShips[findTile1].setSunk(true);
                            computer.targetships.remove(playerShips[findTile1]);
                            playerRemaining--;
                        }
                    }
                }
            }
            if(playerRemaining <= 0){
                endGame("Computer Win!");
            }

        }
        else{ //a miss
            hitTile = (ImageView) findViewById(tileID);
            path = "drawable/miss";
            if(!globalInfo.getGraphics()){
                path = path + "m";
            }
            res = getResources().getIdentifier(path, null, getPackageName());
            hitTile.setForeground(getResources().getDrawable(res, null));
            //play sound
        }



        AIhandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                currentTurn.setText("Your Turn");
                turn = 0;

            }
        }, 1000);

    }


    public void updateScore(int score){
        TextView pScore = (TextView) findViewById(R.id.ssunk);
        pScore.setText("Ships Sunk: " + score);
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

        globalInfo.setPlayerMoves(numPlayerMoves);
        globalInfo.setPlayerHits(numPlayerHits);
        globalInfo.setTurn(turn);

        Intent earlyQuit = new Intent(GameBoard.this, MainMenu.class);
        GameBoard.this.startActivity(earlyQuit);
    }

}
