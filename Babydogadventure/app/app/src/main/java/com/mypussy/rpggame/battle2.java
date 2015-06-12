package com.mypussy.rpggame;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
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

public class battle2 extends Activity  {
    private TextView die1, die2;
    private Button roll,roll2, roll3, hold;
    private int playerHP = 20;
    private int enemy1HP = 20;
    private int enemy2HP = 20;
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
    FrameLayout player;
    FrameLayout enemy2;
    private int currentposition;
    @Override

    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_battle2);
        Intent intent = getIntent();
        currentposition = intent.getIntExtra("currentpos",0);
        mHandler=new Handler();

        roundNumber = (TextView) findViewById(R.id.round);
        enemyHP1 = (TextView) findViewById(R.id.enemyhp1);
        enemyHP2 = (TextView) findViewById(R.id.enemyhp2);
        playerScore = (TextView) findViewById(R.id.bar2);
        msg1 = (TextView) findViewById(R.id.msg1);

        turn= (TextView) findViewById(R.id.turn);


        roundNumber.setText("ROUND : " + Integer.toString(round));
        enemyHP1.setText("HP : " + Integer.toString(enemy1HP));
        enemyHP2.setText("HP : " + Integer.toString(playerHP));
        playerScore.setText("HP : " + Integer.toString(playerHP));
        msg1.setText("Player turn score : " + Integer.toString(playerTurn));




        die2 = (TextView) findViewById(R.id.die2);
        die1 = (TextView) findViewById(R.id.die1);
        player = (FrameLayout) findViewById(R.id.player);
        enemy1 = (FrameLayout) findViewById(R.id.enemy1);
        enemy2 = (FrameLayout) findViewById(R.id.enemy2);


        roll = (Button) findViewById(R.id.rock);
        roll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        // Do something after 5s = 5000ms
                        rollDice2();
                        die2.setText("");

                        roundNumber.setText("ROUND : " + Integer.toString(round));
                        die2.setText("Player : ROCK" );


                        round = round + 1;


                        if ( enemy1Turn==3){


                            enemy1HP=enemy1HP-25;
                            msg1.setText("Player won Turn!! Enemy lost HP: " + Integer.toString(25));
                            playerScore.setText("HP : " + Integer.toString(playerHP));
                            enemyHP1.setText("HP : " + Integer.toString(enemy1HP));
                            enemyHP2.setText("HP : " + Integer.toString(playerHP));
                            enemy1.setBackground(getResources().getDrawable(R.drawable.s_hurt));
                            enemy2.setBackground(getResources().getDrawable(R.drawable.r_attack));

                        }

                        else if ( enemy1Turn==2){

                            playerHP=playerHP-25;
                            msg1.setText("Enemy won Turn!! Player lost HP: " + Integer.toString(25));
                            playerScore.setText("HP : " + Integer.toString(playerHP));
                            enemyHP1.setText("HP : " + Integer.toString(enemy1HP));
                            enemyHP2.setText("HP : " + Integer.toString(playerHP));
                            enemy2.setBackground(getResources().getDrawable(R.drawable.r_hurt));
                            enemy1.setBackground(getResources().getDrawable(R.drawable.s_attack));

                        }
                        else {

                            msg1.setText("Draw ! " );


                        }

                        if (playerHP <= 0 || enemy1HP <= 0) {


                            if (playerHP <=0) {
                                roundNumber.setText("Player Lost !!! Game Over " );
                                playerScore.setText("HP : Death" );
                                enemyHP2.setText("HP : Death" );



                            } else {
                                playerHP=playerHP+ 50;
                                roundNumber.setText("Enemy killed : HP gained " + Integer.toString(50));
                                playerScore.setText("HP : " + Integer.toString(playerHP));
                                enemyHP1.setText("HP : Death " );
                                enemyHP2.setText("HP : " + Integer.toString(playerHP));

                            }
                            Intent intent = new Intent(battle2.this,map.class);
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
                        playerScore.setText("HP : " + Integer.toString(playerHP));

                        die1.setText(" " );
                        die2.setText(" " );

                   }
             }, 1800);


            }
        });

        roll2 = (Button) findViewById(R.id.paper);
        roll2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        // Do something after 5s = 5000ms
                        rollDice2();
                        die1.setText("Player : Paper" );

                        roundNumber.setText("ROUND : " + Integer.toString(round));



                        round = round + 1;


                        if ( enemy1Turn==1){


                            enemy1HP=enemy1HP-25;
                            playerScore.setText("HP : " + Integer.toString(playerHP));
                             msg1.setText("Player won Turn!! Enemy lost HP: " + Integer.toString(25));
                            enemyHP2.setText("HP : " + Integer.toString(playerHP));
                            enemyHP1.setText("HP : " + Integer.toString(enemy1HP));
                            enemy1.setBackground(getResources().getDrawable(R.drawable.s_hurt));
                            enemy2.setBackground(getResources().getDrawable(R.drawable.r_attack));

                        }

                        else if ( enemy1Turn==3){

                            playerHP=playerHP-25;
                            msg1.setText("Enemy won Turn!! Player lost HP: " + Integer.toString(25));
                            enemyHP2.setText("HP : " + Integer.toString(playerHP));
                            enemyHP1.setText("HP : " + Integer.toString(enemy1HP));
                            playerScore.setText("HP : " + Integer.toString(playerHP));
                            enemy2.setBackground(getResources().getDrawable(R.drawable.r_hurt));
                            enemy1.setBackground(getResources().getDrawable(R.drawable.s_attack));

                        }
                        else {

                            msg1.setText("Draw ! " );


                        }

                        if (playerHP <= 0 || enemy1HP <= 0) {

                            if (playerHP <=0) {
                                roundNumber.setText("Player Lost !!! Game Over " );
                                playerScore.setText("HP : Death" );
                                enemyHP2.setText("HP : Death" );


                            } else {
                                playerHP=playerHP+ 50;
                                roundNumber.setText("Enemy killed : HP gained " + Integer.toString(50));
                                playerScore.setText("HP : " + Integer.toString(playerHP));

                                enemyHP1.setText("HP : Death " );
                                enemyHP2.setText("HP : " + Integer.toString(playerHP));
                            }
                        }
                    }
                }, 600);



                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        enemy2.setBackground(getResources().getDrawable(R.drawable.fatboy));
                        enemy1.setBackground(getResources().getDrawable(R.drawable.s_normal));
                        die1.setText(" " );
                        die2.setText(" " );

                    }
                }, 1800);


            }
        });


        roll3 = (Button) findViewById(R.id.scissor);
        roll3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        // Do something after 5s = 5000ms
                        rollDice2();
                        die2.setText("Player : Scissor" );

                        roundNumber.setText("ROUND : " + Integer.toString(round));



                        round = round + 1;


                        if ( enemy1Turn==2){


                            enemy1HP=enemy1HP-25;
                            msg1.setText("Player won Turn!! Enemy lost HP: " + Integer.toString(25));
                            playerScore.setText("HP : " + Integer.toString(playerHP));
                            enemyHP1.setText("HP : " + Integer.toString(enemy1HP));
                            enemyHP2.setText("HP : " + Integer.toString(playerHP));
                            enemy1.setBackground(getResources().getDrawable(R.drawable.s_hurt));
                            enemy2.setBackground(getResources().getDrawable(R.drawable.r_attack));

                        }

                        else if ( enemy1Turn==1){

                            playerHP=playerHP-25;
                            msg1.setText("Enemy won Turn!! Player lost HP: " + Integer.toString(25));
                            playerScore.setText("HP : " + Integer.toString(playerHP));
                            enemyHP1.setText("HP : " + Integer.toString(enemy1HP));
                            enemyHP2.setText("HP : " + Integer.toString(playerHP));
                            enemy2.setBackground(getResources().getDrawable(R.drawable.r_hurt));
                            enemy1.setBackground(getResources().getDrawable(R.drawable.s_attack));

                        }
                        else {

                            msg1.setText("Draw ! " );


                        }

                        if (playerHP <= 0 || enemy1HP <= 0) {

                            if (playerHP <=0) {
                                roundNumber.setText("Player Lost !!! Game Over " );
                                playerScore.setText("HP : Death" );
                                enemyHP2.setText("HP : Death" );


                            } else {
                                playerHP=playerHP+ 50;
                                roundNumber.setText("Enemy killed : HP gained " + Integer.toString(50));
                                playerScore.setText("HP : " + Integer.toString(playerHP));

                                enemyHP1.setText("HP : Death " );
                                enemyHP2.setText("HP : " + Integer.toString(playerHP));
                            }
                        }
                    }
                }, 600);



                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        enemy2.setBackground(getResources().getDrawable(R.drawable.fatboy));
                        enemy1.setBackground(getResources().getDrawable(R.drawable.s_normal));
                        die1.setText(" " );
                        die2.setText(" " );

                    }
                }, 1800);


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





    public void rollDice2() {
        turn.setText("Enemy turn");

        int val1 = 1 + (int) (3 * Math.random());

       enemy1Turn=val1;
        setDie(val1, die1);

    }

    public void setDie(int value, TextView view) {
        Drawable pic = null;

        switch (value) {
            case 1:
                die1.setText("Enemy : ROCK" );


                break;
            case 2:
                die1.setText("Enemy : Paper" );


                break;
            case 3:
                die1.setText("Enemy : Scissor" );


                break;

        }

    }// oncreate close


}//main close