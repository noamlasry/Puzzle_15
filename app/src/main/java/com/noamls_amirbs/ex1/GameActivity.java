package com.noamls_amirbs.ex1;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.TextView;
import android.widget.Toast;

public class GameActivity extends AppCompatActivity implements View.OnClickListener
{
    final Context context = this;
    int  count_Move = 0;
    private TextView buttons [][] = new TextView[4][4];
    int [][] temp = new int [4][4];

    private TextView textTimer;
    private long pauseOffset;
    private boolean running;
    Button startGame;
    Boolean theGameHasBeenStart = false,freezeBoard = true,inPause = false;
    TextView countMove;
    MainActivity m = new MainActivity();
    //=====================================

    private Handler handler;
    int second,minute,hours,milliscond;
    long milliseconfTime,startTime,TimeBuff,update=0L;

    //=========================================

    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        //=============================================//
        textTimer = (TextView)findViewById(R.id.textTimer);
        handler = new Handler();
        //===========================================//

        if(m.musicActive)
            m.gameMusic.start();


        countMove = (TextView)findViewById(R.id.count_up);
        startGame = (Button)findViewById(R.id.startGame);
        startGame.setOnClickListener(this);


        int count = 1;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                String buttonID = "but" + count;
                count++;
                int resID = getResources().getIdentifier(buttonID, "id", getPackageName());
                buttons[i][j] = findViewById(resID);
                buttons[i][j].setOnClickListener(this);
            }
        }
    }

    public void onClick(View v)
    {
        int count = 0,x,y;

        int [][] ifWinOrNot = new int [4][4];
        int  [] coordinateArray  = new int [2];
        int  [] moveOptionCordinate  = new int [4];

        GameBoard game = new GameBoard();

        if(v.getId() == R.id.startGame)  // start the game by mixing the board
        {
            theGameHasBeenStart = true;
            freezeBoard = true;
            count_Move = 0;
            countMove.setText("0000");
//===================================================//

            milliseconfTime = 0L ;
            startTime = 0L ;
            TimeBuff = 0L ;
            update = 0L ;
            second = 0 ;
            minute = 0 ;
            hours = 0 ;

            startTime = SystemClock.uptimeMillis();
            handler.postDelayed(updateTimerMethod,0);
//=====================================================//

            Toast.makeText(this, "The game has started!", Toast.LENGTH_LONG).show();
            temp = game.mixBoard(temp);


            int num = 1;
            for(int i = 0; i<4 ; i++)
            {
                for(int j = 0; j<4 ; j++)
                {
                    String str = Integer.toString(temp[i][j]);
                    buttons[i][j].setText(str);
                }
            }
            buttons[3][3].setText("");

        }

        for(int i = 0; i<4 ; i++)
        {
            for(int j = 0; j<4 ; j++)
            {
                count++;
                String buttonID = "but" + count;
                int resID = getResources().getIdentifier(buttonID, "id", getPackageName());
                buttons[i][j] = findViewById(resID);
                buttons[i][j].setOnClickListener(this);

                if(v.getId() == resID && theGameHasBeenStart && !game.winGame(ifWinOrNot) && freezeBoard)// one of the button has been click
                {
                    ifWinOrNot = temp;
                    coordinateArray = game.boradCoordinate(count);

                    x = coordinateArray[0];
                    y = coordinateArray[1];

                    moveOptionCordinate = game.moveOption(coordinateArray[0],coordinateArray[1]);


                    int [] getPossibleCordinate  = new int [2];

                    for(int k = 0; k<4; k++)
                    {
                        if(moveOptionCordinate[k] != -1)
                        {

                            getPossibleCordinate = game.boradCoordinate(moveOptionCordinate[k]);

                            if(buttons[getPossibleCordinate[0]][getPossibleCordinate[1]].getText() == "")
                            {
                                buttons[getPossibleCordinate[0]][getPossibleCordinate[1]].setText(buttons[x][y].getText());
                                buttons[x][y].setText("");

                                // ==================== update the board by any click that happen  ======================//
                                int value_1 = Integer.parseInt(buttons[getPossibleCordinate[0]][getPossibleCordinate[1]].getText().toString());
                                int value_2 = 0;
                                ifWinOrNot[getPossibleCordinate[0]][getPossibleCordinate[1]] = value_1;
                                ifWinOrNot[x][y] = value_2;

                                // =========== update the moves =========================//
                                count_Move++;
                                String st =  game.setMoves(count_Move);
                                String str = Integer.toString(count_Move);
                                countMove.setText(st);

                                // =========== check if the player win =========================//


                                if(game.winGame(ifWinOrNot))
                                {
                                    freezeBoard = false;
                                    Toast.makeText(this, "Game Over - you win!", Toast.LENGTH_LONG).show();
                                    count_Move = 0;
                                    str = Integer.toString(count_Move);
                                    countMove.setText(str);


                                }
                            }
                        }
                    }
                }
            }
        }
    }
    public void onPause()// override for music and timer function
    {
        super.onPause();
        inPause = true;
        TimeBuff+=milliseconfTime;
        handler.removeCallbacks(updateTimerMethod);

        m.gameMusic.pause();
    }

    public void onResume()
    {
        super.onResume();

        if(m.musicActive)
            m.gameMusic.start();
        if(theGameHasBeenStart)
        {
            startTime = SystemClock.uptimeMillis();
            handler.postDelayed(updateTimerMethod,0);
        }

    }

    private Runnable updateTimerMethod = new Runnable()
    {

    public void run() { // game timer , active than the screen on
        milliseconfTime = SystemClock.uptimeMillis() - startTime;


        update = TimeBuff + milliseconfTime;
        second = (int) (update / 1000);
        minute = second / 60;
        hours = minute / 60;
        second = second % 60;

        milliscond = (int)(update % 1000);

        textTimer.setText(String.format("%02d:%02d",minute,second));
        handler.postDelayed(this, 0);
    }

};



}
