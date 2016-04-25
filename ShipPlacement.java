package com.example.ashley.battleship;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;


public class ShipPlacement extends AppCompatActivity {

        boolean[] checkShip = new boolean[64];
        String path;
        Globals g = new Globals();
        int [] ships = g.getSHIPLIST();
        int cycleShip = 0; //cycles through the ship type list
        ImageView currentShip = (ImageView) findViewById(R.id.currentShip);
        Ship [] playerShips = new Ship [5];

        @Override
        protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_ship_placement);

                //first ship to put down is the 2-cell ship
                path = "/drawable/ship1placeholder";
                currentShip.setBackground(Drawable.createFromPath(path));

                //currently, all spaces are empty (true = has ship, false = no ship)
                for (int fillBool = 0; fillBool < 64; fillBool++) {
                    checkShip[fillBool] = false;
                }

        }

        //when all ships are placed down, the Start Game button can be pressed
        public void moveOn(){
            g.setPlayerShips(playerShips);
            Button startTheGame = (Button) findViewById(R.id.start);
            TextView confirmation = (TextView) findViewById(R.id.canStart);
            confirmation.setText("You may start");
            startTheGame.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent gameBoard = new Intent(ShipPlacement.this, Volume.class);
                    ShipPlacement.this.startActivity(gameBoard);
                }
            });
        }

        //draw the next ship in the queue
        public void drawInQueue(int currentType){
            if(currentType == 1){ //3-cell horiz
                path = "/drawable/ship2placeholder";
                currentShip.setBackground(Drawable.createFromPath(path));
            }
            else if(currentType == 2){ //3-cell vert
                path = "/drawable/ship3placeholder";
                currentShip.setBackground(Drawable.createFromPath(path));
            }
            else if(currentType == 3){ //4-cell horiz
                path = "/drawable/ship4placeholder";
                currentShip.setBackground(Drawable.createFromPath(path));
            }
            else if (currentType == 4){ //4-cell vert
                path = "/drawable/ship5placeholder";
                currentShip.setBackground(Drawable.createFromPath(path));
            }
            else{ //last ship put
                moveOn();
            }

        }

        //listeners for all 64 grid cells
        public void OnClick(View v) {
                switch (v.getId()) {
                        case R.id.Tile1:
                                shipPlacement(1, R.id.Tile1);
                                break;

                        case R.id.Tile2:
                                shipPlacement(2, R.id.Tile2);
                                break;

                        case R.id.Tile3:
                                shipPlacement(3, R.id.Tile3);
                                break;
                        case R.id.Tile4:
                                shipPlacement(4, R.id.Tile4);
                                break;

                        case R.id.Tile5:
                                shipPlacement(5, R.id.Tile5);
                                break;

                        case R.id.Tile6:
                                shipPlacement(6, R.id.Tile6);
                                break;
                        case R.id.Tile7:
                                shipPlacement(7, R.id.Tile7);
                                break;

                        case R.id.Tile8:
                                shipPlacement(8, R.id.Tile8);
                                break;

                        case R.id.Tile9:
                                shipPlacement(9, R.id.Tile9);
                                break;
                        case R.id.Tile10:
                                shipPlacement(10, R.id.Tile10);
                                break;

                        case R.id.Tile11:
                                shipPlacement(11, R.id.Tile11);
                                break;

                        case R.id.Tile12:
                                shipPlacement(12, R.id.Tile12);
                                break;
                        case R.id.Tile13:
                                shipPlacement(13, R.id.Tile13);
                                break;

                        case R.id.Tile14:
                                shipPlacement(14, R.id.Tile14);
                                break;

                        case R.id.Tile15:
                                shipPlacement(15, R.id.Tile15);
                                break;
                        case R.id.Tile16:
                                shipPlacement(16, R.id.Tile16);
                                break;

                        case R.id.Tile17:
                                shipPlacement(17, R.id.Tile17);
                                break;

                        case R.id.Tile18:
                                shipPlacement(18, R.id.Tile18);
                                break;
                        case R.id.Tile19:
                                shipPlacement(19, R.id.Tile19);
                                break;

                        case R.id.Tile20:
                                shipPlacement(20, R.id.Tile20);
                                break;

                        case R.id.Tile21:
                                shipPlacement(21, R.id.Tile21);
                                break;
                        case R.id.Tile22:
                                shipPlacement(22, R.id.Tile22);
                                break;

                        case R.id.Tile23:
                                shipPlacement(23, R.id.Tile23);
                                break;

                        case R.id.Tile24:
                                shipPlacement(24, R.id.Tile24);
                                break;
                        case R.id.Tile25:
                                shipPlacement(25, R.id.Tile25);
                                break;

                        case R.id.Tile26:
                                shipPlacement(26, R.id.Tile26);
                                break;

                        case R.id.Tile27:
                                shipPlacement(27, R.id.Tile27);
                                break;
                        case R.id.Tile28:
                                shipPlacement(28, R.id.Tile28);
                                break;

                        case R.id.Tile29:
                                shipPlacement(29, R.id.Tile29);
                                break;

                        case R.id.Tile30:
                                shipPlacement(30, R.id.Tile30);
                                break;
                        case R.id.Tile31:
                                shipPlacement(31, R.id.Tile31);
                                break;

                        case R.id.Tile32:
                                shipPlacement(32, R.id.Tile32);
                                break;

                        case R.id.Tile33:
                                shipPlacement(33, R.id.Tile33);
                                break;
                        case R.id.Tile34:
                                shipPlacement(34, R.id.Tile34);
                                break;

                        case R.id.Tile35:
                                shipPlacement(35, R.id.Tile35);
                                break;

                        case R.id.Tile36:
                                shipPlacement(36, R.id.Tile36);
                                break;
                        case R.id.Tile37:
                                shipPlacement(37, R.id.Tile37);
                                break;

                        case R.id.Tile38:
                                shipPlacement(38, R.id.Tile38);
                                break;

                        case R.id.Tile39:
                                shipPlacement(39, R.id.Tile39);
                                break;
                        case R.id.Tile40:
                                shipPlacement(40, R.id.Tile40);
                                break;

                        case R.id.Tile41:
                                shipPlacement(41, R.id.Tile41);
                                break;

                        case R.id.Tile42:
                                shipPlacement(42, R.id.Tile42);
                                break;
                        case R.id.Tile43:
                                shipPlacement(43, R.id.Tile43);
                                break;

                        case R.id.Tile44:
                                shipPlacement(44, R.id.Tile44);
                                break;

                        case R.id.Tile45:
                                shipPlacement(45, R.id.Tile45);
                                break;
                        case R.id.Tile46:
                                shipPlacement(46, R.id.Tile46);
                                break;

                        case R.id.Tile47:
                                shipPlacement(47, R.id.Tile47);
                                break;

                        case R.id.Tile48:
                                shipPlacement(48, R.id.Tile48);
                                break;
                        case R.id.Tile49:
                                shipPlacement(49, R.id.Tile49);
                                break;

                        case R.id.Tile50:
                                shipPlacement(50, R.id.Tile50);
                                break;

                        case R.id.Tile51:
                                shipPlacement(51, R.id.Tile51);
                                break;
                        case R.id.Tile52:
                                shipPlacement(52, R.id.Tile52);
                                break;

                        case R.id.Tile53:
                                shipPlacement(53, R.id.Tile53);
                                break;

                        case R.id.Tile54:
                                shipPlacement(54, R.id.Tile54);
                                break;
                        case R.id.Tile55:
                                shipPlacement(55, R.id.Tile55);
                                break;

                        case R.id.Tile56:
                                shipPlacement(56, R.id.Tile56);
                                break;

                        case R.id.Tile57:
                                shipPlacement(57, R.id.Tile57);
                                break;
                        case R.id.Tile58:
                                shipPlacement(58, R.id.Tile58);
                                break;

                        case R.id.Tile59:
                                shipPlacement(59, R.id.Tile59);
                                break;

                        case R.id.Tile60:
                                shipPlacement(60, R.id.Tile60);
                                break;
                        case R.id.Tile61:
                                shipPlacement(61, R.id.Tile61);
                                break;

                        case R.id.Tile62:
                                shipPlacement(62, R.id.Tile62);
                                break;

                        case R.id.Tile63:
                                shipPlacement(63, R.id.Tile63);
                                break;

                        case R.id.Tile64:
                                shipPlacement(64, R.id.Tile64);
                                break;

                        default:
                                break;

                }
        }

        public void shipPlacement(int tile, int id) {
                int typeShip;
                int [] allTiles;
                String tilePiece = "Tile";

                //for now: the 2-cell is horiz, and there is one horiz/verti each for the 3- and 4- cells
                if(cycleShip >= 5){
                    return;
                }
                else if(checkShip[tile - 1]) {
                    return;
                }
                else {
                    currentShip = (ImageView) findViewById(id);
                    checkShip[tile - 1] = true;
                    typeShip = ships[cycleShip];
                    if(typeShip == 2){
                        if(checkShip[tile + 2] || tile % 8 == 0) { //check tile to the right for another ship/out of bounds
                            if (checkShip[tile - 2]) { //check tile to the left for another ship; do nothing
                                return;
                            } else if ((tile - 1) < 1 || (tile - 1) % 8 == 0) { //check tile to the left, if it's out of bounds = do nothing
                                return;
                            } else {
                                //put other tile to the left
                                //currentShip.setBackground(end piece, oriented facing ""]"");
                                //TILE TO THE LEFT ORIENTED FACING ""[""
                                allTiles = new int[2];
                                allTiles[0] = tile;
                                allTiles[1] = tile - 1;
                                playerShips[cycleShip] = new Ship(allTiles, false, 2, 0);
                                cycleShip++;
                                drawInQueue(cycleShip);
                            }
                        }
                        else{
                            //put other tile to the right
                            //currentShip.setBackground(end piece, oriented facing ""["");
                            //TILE TO THE RIGHT ORIENTED FACING ""]""
                            allTiles = new int[2];
                            allTiles[0] = tile;
                            allTiles[1] = tile + 1;
                            playerShips[cycleShip] = new Ship(allTiles, false, 2, 0);
                            cycleShip++;
                            drawInQueue(cycleShip);
                        }

                    }
                    else if (typeShip == 3){
                        if(cycleShip == 1){ //horiz
                            if(checkShip[tile - 2] || checkShip[tile + 2] || tile % 8 == 0 || (tile - 1) % 8 == 0) { //check left and right
                                return;
                            }
                            else{
                                //TILE TO THE LEFT ORIENTED FACING ""[""
                                //currentShip.setBackground(middle piece, horiz);
                                //TILE TO THE RIGHT ORIENTED FACING ""]""
                                allTiles = new int[3];
                                allTiles[0] = tile; //center
                                allTiles[1] = tile - 1; //left
                                allTiles[2] = tile + 1; //right
                                playerShips[cycleShip] = new Ship(allTiles, false, 3, 0);
                                cycleShip++;
                                drawInQueue(cycleShip);
                                }

                        }
                        else{ //vertical
                            if(tile - 8 <= 0 || tile + 8 > 64) //if top or bottom go out of bounds
                            {
                                return;
                            }
                            else if(checkShip[tile - 9] || checkShip[tile + 9] ) { //check top and bottom
                                return;
                            }
                            else{
                                //TILE TO THE TOP ORIENTED FACING ""∩""
                                //currentShip.setBackground(middle piece, vertical);
                                //TILE TO THE RIGHT ORIENTED FACING ""U""
                                allTiles = new int[3];
                                allTiles[0] = tile; //center
                                allTiles[1] = tile - 8; //top
                                allTiles[2] = tile + 8; //bottom
                                playerShips[cycleShip] = new Ship(allTiles, false, 3, 1);
                                cycleShip++;
                                drawInQueue(cycleShip);
                            }
                        }

                    }
                    else{ //4
                        if(cycleShip == 3){ //horiz
                            if(tile % 8 == 0 || (tile - 1) % 8 == 0 || (tile + 1) % 8 == 0){ //check out of bounds
                                return;
                            }
                            else if (checkShip[tile - 2] || checkShip[tile + 2] || checkShip[tile + 3]){ //check to left, two spaces to right
                                return;
                            }
                            else{
                                //TILE TO THE LEFT ORIENTED ""[""
                                //currentShip.setBackground(middle piece, horiz);
                                //MIDDLE PIECE, HORIZ
                                //FAR RIGHT, ORIENTED""]""
                                allTiles = new int[4];
                                allTiles[0] = tile; //center
                                allTiles[1] = tile - 1; //left
                                allTiles[2] = tile + 1; //right
                                allTiles[3] = tile + 2; //far right
                                playerShips[cycleShip] = new Ship(allTiles, false, 4, 0);
                                cycleShip++;
                                drawInQueue(cycleShip);
                            }

                        }
                        else { //vertical
                            if ((tile - 8) <= 0 || (tile + 8) > 64 || (tile + 16) > 64) { //check top, bottom two
                                return;
                            } else if (checkShip[tile - 9] || checkShip[tile + 9] || checkShip[tile + 17]) { //check top, bottom two for ships
                                return;
                            } else {
                                //TILE TO THE TOP ORIENTED ""∩""
                                //currentShip.setBackground(middle piece, vertical);
                                //MIDDLE PIECE, VERTICAL
                                //FAR BOTTOM, ORIENTED""U""
                                allTiles = new int[4];
                                allTiles[0] = tile; //center
                                allTiles[1] = tile - 8; //top
                                allTiles[2] = tile + 8; //bottom
                                allTiles[3] = tile + 16; //far bottom
                                playerShips[cycleShip] = new Ship(allTiles, false, 4, 1);
                                cycleShip++;
                                drawInQueue(cycleShip);
                            }
                        }
                    }


                }

        }

}