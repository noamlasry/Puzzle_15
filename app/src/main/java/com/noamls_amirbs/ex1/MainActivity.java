package com.noamls_amirbs.ex1;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    final Context context = this;
    private Switch switchMusic;
    public static MediaPlayer gameMusic;
    public static Boolean musicActive;
    public Boolean switchState;
    Button startGame;

    protected void onCreate(final Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        gameMusic = MediaPlayer.create(this, R.raw.gamemusic);

        SharedPreferences sp =
                getSharedPreferences("MyPref", Context.MODE_PRIVATE);
        musicActive = sp.getBoolean("isActive",false);

        switchMusic = (Switch)findViewById(R.id.switchMusic) ;
        startGame = (Button)findViewById(R.id.start_game) ;
        switchMusic.setChecked(musicActive);



        if(musicActive)
            gameMusic.start();

        switchMusic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                switchState = switchMusic.isChecked();


                SharedPreferences sp =
                        getSharedPreferences("MyPref", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sp.edit();
                editor.putBoolean("isActive",switchState);
                editor.commit();

                musicActive = sp.getBoolean("isActive",false);

                if(musicActive)
                {
                    switchMusic.setChecked(true);
                    gameMusic.start();
                }
                else
                {
                    switchMusic.setChecked(false);
                    gameMusic.pause();
                }
            }
        });


        startGame.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v)
            {
                openGame();
            }
        });



    }
    public void openGame()
    {
        Intent in = new Intent(this,GameActivity.class);
        startActivity(in);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        super.onCreateOptionsMenu(menu);
        MenuItem menuItem1 = menu.add("About");
        MenuItem menuItem2 = menu.add("Exit");

        menuItem1.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item)
            {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                        context);

                alertDialogBuilder.setTitle("About Puzzle 15"); // set title

                // set dialog message
                alertDialogBuilder
                        .setMessage("This app implement the Game of Fifteen. By Amir & Noam")
                        .setCancelable(false)

                        .setNegativeButton("Cancel",new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                // if this button is clicked, just close
                                // the dialog box and do nothing
                                dialog.cancel();
                            }
                        });

                // create alert dialog
                AlertDialog alertDialog = alertDialogBuilder.create();

                // show it
                alertDialog.show();
                return false;
            }
        });

        menuItem2.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener()
        {
            public boolean onMenuItemClick(MenuItem item)
            {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                        context);

                alertDialogBuilder.setTitle("Exit Puzzle 15");// set title

                // set dialog message
                alertDialogBuilder
                        .setMessage("Do you really want to exit the app?")
                        .setCancelable(false)
                        .setPositiveButton("Yes",new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                // if this button is clicked, close
                                // current activity
                                MainActivity.this.finish();
                            }
                        })
                        .setNegativeButton("No",new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                // if this button is clicked, just close
                                // the dialog box and do nothing
                                dialog.cancel();
                            }
                        });

                // create alert dialog
                AlertDialog alertDialog = alertDialogBuilder.create();

                // show it
                alertDialog.show();
                return true;
            }
        });
        return true;
    }
    public void onResume()
    {
        super.onResume();
        if(musicActive)
            gameMusic.start();



    }
    public void onPause()
    {
        super.onPause();
        if(musicActive)
            gameMusic.pause();

    }


}
