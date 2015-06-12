package com.mypussy.rpggame;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.os.Handler;
import android.os.Looper;

public class battle extends Activity  {
    private FrameLayout die1, die2, die3;
    private Button roll, hold;
    private int playerHP = 10;
    private int enemy1HP = 10;
    private int enemy2HP = 10;
    private int enemy1Turn = 0;
    private int enemy2Turn = 0;
    private int playerTurn = 0;
    private Handler mHandler;
    private final int TIME_DELAY_MS=10000;
    private int playerLV = 1;

    private int score;
    private int round = 1;
    private boolean changeplayer;
    TextView roundNumber ;
    TextView enemyHP1;
    TextView enemyHP2 ;
    TextView playerScore ;
    TextView msg1 ;
    TextView msg2;
    TextView msg3 ;
    TextView msg4;
    TextView turn;
    FrameLayout enemy1;
    FrameLayout enemy2;

    FrameLayout player;
    private int currentposition;
    MediaPlayer mymusic;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_battle);
        mymusic = MediaPlayer.create(this,R.raw.monopoly);
        mymusic.setVolume(0.3f, 0.3f);
        mymusic.start();

        Intent intent = getIntent();
        currentposition = intent.getIntExtra("currentpos",0);
//        Toast.makeText(this, "currentpos" + Integer.toString(currentposition), Toast.LENGTH_LONG).show();
        mHandler=new Handler();
        changeplayer = false;
         roundNumber = (TextView) findViewById(R.id.round);
          enemyHP1 = (TextView) findViewById(R.id.enemyhp1);
         enemyHP2 = (TextView) findViewById(R.id.enemyhp2);
         playerScore = (TextView) findViewById(R.id.bar2);
        msg1 = (TextView) findViewById(R.id.msg1);
         msg2 = (TextView) findViewById(R.id.msg2);
          msg3 = (TextView) findViewById(R.id.msg3);
        msg4 = (TextView) findViewById(R.id.msg4);
        turn= (TextView) findViewById(R.id.turn);



        roundNumber.setText("ROUND : " + Integer.toString(round));
        enemyHP1.setText("HP : " + Integer.toString(enemy1HP));
        enemyHP2.setText("HP : " + Integer.toString(playerHP));
        playerScore.setText("HP : " + Integer.toString(playerHP));
        msg1.setText("Player turn score : " + Integer.toString(playerTurn));
        msg3.setText("Enemy turn score : " + Integer.toString(enemy1Turn));



        die1 = (FrameLayout) findViewById(R.id.die1);
        die2 = (FrameLayout) findViewById(R.id.die2);
        die3 = (FrameLayout) findViewById(R.id.die3);
        player = (FrameLayout) findViewById(R.id.player);
        enemy1 = (FrameLayout) findViewById(R.id.enemy1);
        enemy2 = (FrameLayout) findViewById(R.id.enemy2);


        roll = (Button) findViewById(R.id.button);
       roll.setOnClickListener(new View.OnClickListener() {
            @Override
          public void onClick(View v) {


                rollDice();

                changeplayer=true;



                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        // Do something after 5s = 5000ms
                      rollDice2();

                        roundNumber.setText("ROUND : " + Integer.toString(round));
                        msg3.setText("Enemy turn score : " + Integer.toString(enemy1Turn));
                        msg1.setText("Player turn score : " + Integer.toString(playerTurn));

                        round = round + 1;


                        if (playerTurn > enemy1Turn){


                            enemy1HP=enemy1HP-playerTurn;
                            msg2.setText("Player won Turn!! Enemy lost HP: " + Integer.toString(playerTurn));
                            msg4.setText("" );
                            enemyHP2.setText("HP : " + Integer.toString(playerHP));
                            playerScore.setText("HP : " + Integer.toString(playerHP));
                            enemyHP1.setText("HP : " + Integer.toString(enemy1HP));
                            enemy1.setBackground(getResources().getDrawable(R.drawable.s_hurt));
                            enemy2.setBackground(getResources().getDrawable(R.drawable.r_attack));

                        }

                        else if (enemy1Turn> playerTurn  ){

                            playerHP=playerHP-enemy1Turn;
                            msg4.setText("Enemy won Turn!! Player lost HP: " + Integer.toString(enemy1Turn));
                            msg2.setText("" );
                            enemyHP2.setText("HP : " + Integer.toString(playerHP));
                            playerScore.setText("HP : " + Integer.toString(playerHP));
                            enemy2.setBackground(getResources().getDrawable(R.drawable.r_hurt));
                            enemy1.setBackground(getResources().getDrawable(R.drawable.s_attack));

                        }
                        else {

                            msg2.setText("Draw ! " );
                            msg4.setText("Draw ! ");

                        }

                        if (playerHP <= 0 || enemy1HP <= 0)
                        {

                            if (playerTurn <=0) {
                                roundNumber.setText("Player Lost  : " + Integer.toString(round));
                                enemyHP2.setText("HP : Death" );
                                playerScore.setText("HP : Death" );
                            } else {
                                playerHP=playerHP+ enemy2HP;
                                roundNumber.setText("Enemy killed : HP gained " + Integer.toString(enemy2HP));
                                enemyHP1.setText("HP : Death" );
                                enemyHP2.setText("HP : " + Integer.toString(playerHP));
                                playerScore.setText("HP : " + Integer.toString(playerHP));

                            }
                            Intent intent = new Intent(battle.this,map.class);
                            intent.putExtra("currentpos", currentposition);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                            startActivity(intent);


                        }

                    }
                }, 600);



                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        enemy2.setBackground(getResources().getDrawable(R.drawable.fatboy));
                        enemy1.setBackground(getResources().getDrawable(R.drawable.s_normal));
                        turn.setText("");

                    }
                }, 1700);

              stop();
           }
       });



    }
    Runnable rollDieRunnable = new Runnable() {
        @Override
        public void run() {
            mHandler.postDelayed(rollDieRunnable, TIME_DELAY_MS);
            threadRun();
        }
    };




    public void threadRun(){
        if(Looper.myLooper()== Looper.getMainLooper()) {
            new Thread(new Runnable(){
                @Override
                public void run() {



                    rollDice2();
                }
            }).start();
            return;
        }
    }



    public void start() {

        rollDieRunnable.run();
    }

    public void stop() {
        mHandler.removeCallbacks(rollDieRunnable);

    }




    public void rollDice() {
        turn.setText("Player turn");

        int val1 = 1 + (int) (6 * Math.random());
        int val2 = 1 + (int) (6 * Math.random());
        int val3 = 1 + (int) (6 * Math.random());


        setDie(val1, die1);
        setDie(val2, die2);
        setDie(val3, die3);


            playerTurn = val1 + val2 + val3;

    }



    public void rollDice2() {
        turn.setText("Enemy turn");

        int val1 = 1 + (int) (6 * Math.random());
        int val2 = 1 + (int) (6 * Math.random());
        int val3 = 1 + (int) (6 * Math.random());


        setDie(val1, die1);
        setDie(val2, die2);
        setDie(val3, die3);


       enemy1Turn= val1 + val2 + val3;

    }

    public void setDie(int value, FrameLayout layout) {
        Drawable pic = null;

        switch (value) {
            case 1:
                pic = getResources().getDrawable(R.drawable.die_face_1);

                break;
            case 2:
                pic = getResources().getDrawable(R.drawable.die_face_2);

                break;
            case 3:
                pic = getResources().getDrawable(R.drawable.die_face_3);


                break;
            case 4:
                pic = getResources().getDrawable(R.drawable.die_face_4);

                break;
            case 5:
                pic = getResources().getDrawable(R.drawable.die_face_5);

                break;
            case 6:
                pic = getResources().getDrawable(R.drawable.die_face_6);


                break;
        }
        layout.setBackground(pic);


    }

}