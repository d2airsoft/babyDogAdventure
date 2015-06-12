package com.mypussy.babydogadventure;

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
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.os.Handler;
import android.os.Looper;

public class battle extends Activity  {
    private FrameLayout die1, die2, die3;
    private Button roll, hold;
    private int playerHP = 25;
    private int enemy1HP = 25;
    private int enemy2HP = 25;
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
    TextView enemyname1;
    TextView enemyname2 ;

    TextView rounds ;
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
    MediaPlayer battle1music,babydoghurt,babydogattack,enemyattack,enemyattack2,rollmusic;
    private int map;
    @Override
    protected void onPause() {
        super.onPause();
        battle1music.release();
    }

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_battle);

        battle1music = MediaPlayer.create(this,R.raw.battle3);
        battle1music.setVolume(0.7f, 0.7f);
        battle1music.start();
        rollmusic = MediaPlayer.create(this,R.raw.roll);


        babydogattack = MediaPlayer.create(this,R.raw.babydogattack);
        babydoghurt = MediaPlayer.create(this,R.raw.babydoghurt);
        enemyattack = MediaPlayer.create(this,R.raw.enemyattack);
        enemyattack.setVolume(0.3f, 0.3f);
        enemyattack2 = MediaPlayer.create(this, R.raw.enemyattack2);
        Intent intent = getIntent();
        currentposition = intent.getIntExtra("currentpos",0);
        map = intent.getIntExtra("map",0);
//        Toast.makeText(this, "currentpos" + Integer.toString(currentposition), Toast.LENGTH_LONG).show();
        mHandler=new Handler();
        changeplayer = false;
         roundNumber = (TextView) findViewById(R.id.round);
          enemyHP1 = (TextView) findViewById(R.id.enemyhp1);
        enemyHP2 = (TextView) findViewById(R.id.enemyhp2);
        enemyname1 = (TextView) findViewById(R.id.enemyname1);
        enemyname2 = (TextView) findViewById(R.id.enemyname2);
         playerScore = (TextView) findViewById(R.id.bar2);
        msg1 = (TextView) findViewById(R.id.msg1);
         msg2 = (TextView) findViewById(R.id.msg2);
          msg3 = (TextView) findViewById(R.id.msg3);
        msg4 = (TextView) findViewById(R.id.msg4);
        turn= (TextView) findViewById(R.id.turn);
        rounds= (TextView) findViewById(R.id.round);
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
        enemy2.setBackground(getResources().getDrawable(R.drawable.d_normal));
        enemy1.setBackground(getResources().getDrawable(R.drawable.s_normal));
        Animation animationfade = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade);
        Animation animationmove3 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.move3);
        enemy1.startAnimation(animationfade);
        enemy2.startAnimation(animationfade);
        die1.startAnimation(animationmove3);
        die2.startAnimation(animationmove3);
        die3.startAnimation(animationmove3);
        enemyHP1.startAnimation(animationmove3);
        enemyHP2.startAnimation(animationmove3);
        enemyname1.startAnimation(animationmove3);
        enemyname2.startAnimation(animationmove3);
        turn.startAnimation(animationmove3);
        rounds.startAnimation(animationmove3);
        roll = (Button) findViewById(R.id.button);
        roll.setOnClickListener(new View.OnClickListener() {
            @Override
          public void onClick(View v) {
                final Handler handler = new Handler();

                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        rollDice();
                        changeplayer=true;

                    }
                }, 100);


                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run()
                            {
                                rollmusic.start();
                                Drawable pic = getResources().getDrawable(R.drawable.die_face_1);
                                Drawable pic2 = getResources().getDrawable(R.drawable.die_face_4);
                                Drawable pic3 = getResources().getDrawable(R.drawable.die_face_2);
                                die1.setBackground(pic);
                                die2.setBackground(pic2);
                                die3.setBackground(pic3);


                            }
                        }, 1200);
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run()
                            {
                                Drawable pic = getResources().getDrawable(R.drawable.die_face_3);
                                Drawable pic2 = getResources().getDrawable(R.drawable.die_face_1);
                                Drawable pic3 = getResources().getDrawable(R.drawable.die_face_6);
                                die1.setBackground(pic);
                                die2.setBackground(pic2);
                                die3.setBackground(pic3);


                            }
                        }, 1400);
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run()
                            {
                                Drawable pic = getResources().getDrawable(R.drawable.die_face_2);
                                Drawable pic2 = getResources().getDrawable(R.drawable.die_face_3);
                                Drawable pic3 = getResources().getDrawable(R.drawable.die_face_1);
                                die1.setBackground(pic);
                                die2.setBackground(pic2);
                                die3.setBackground(pic3);


                            }
                        }, 1600);
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run()
                            {
                                Drawable pic = getResources().getDrawable(R.drawable.die_face_5);
                                Drawable pic2 = getResources().getDrawable(R.drawable.die_face_6);
                                Drawable pic3 = getResources().getDrawable(R.drawable.die_face_1);
                                die1.setBackground(pic);
                                die2.setBackground(pic2);
                                die3.setBackground(pic3);


                            }
                        }, 1800);
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run()
                            {
                                Drawable pic = getResources().getDrawable(R.drawable.die_face_3);
                                Drawable pic2 = getResources().getDrawable(R.drawable.die_face_5);
                                Drawable pic3 = getResources().getDrawable(R.drawable.die_face_4);
                                die1.setBackground(pic);
                                die2.setBackground(pic2);
                                die3.setBackground(pic3);


                            }
                        }, 2000);
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                rollDice2();



                            }
                        }, 2500);
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                roundNumber.setText("ROUND : " + Integer.toString(round));
                                msg3.setText("Enemy turn score : " + Integer.toString(enemy1Turn));
                                msg1.setText("Player turn score : " + Integer.toString(playerTurn));
                                round = round + 1;
                                if (playerTurn > enemy1Turn){
                                    Animation animation2 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.move2);
                                    enemy1.startAnimation(animation2);
                                    Animation animation3 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.zoom);
                                    enemy2.startAnimation(animation3);
                                    enemy1HP=enemy1HP-playerTurn;
                                    babydogattack.start();
                                    msg2.setText("Player won Turn!! Enemy lost HP: " + Integer.toString(playerTurn));
                                    msg4.setText("" );
                                    enemyHP2.setText("HP : " + Integer.toString(playerHP));
                                    playerScore.setText("HP : " + Integer.toString(playerHP));
                                    enemyHP1.setText("HP : " + Integer.toString(enemy1HP));
                                    enemy1.setBackground(getResources().getDrawable(R.drawable.s_hurt));
                                    enemy2.setBackground(getResources().getDrawable(R.drawable.d_attack));

                                }

                                else if (enemy1Turn> playerTurn  ){
                                    Animation animation2 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.move);
                                    enemy2.startAnimation(animation2);
                                    Animation animation3 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.zoom);
                                    enemy1.startAnimation(animation3);

                                    playerHP=playerHP-enemy1Turn;
                                    enemyattack.start();

                                    msg4.setText("Enemy won Turn!! Player lost HP: " + Integer.toString(enemy1Turn));
                                    msg2.setText("" );
                                    enemyHP2.setText("HP : " + Integer.toString(playerHP));
                                    playerScore.setText("HP : " + Integer.toString(playerHP));
                                    enemy2.setBackground(getResources().getDrawable(R.drawable.d_hurt));
                                    enemy1.setBackground(getResources().getDrawable(R.drawable.s_attack));
                                    babydoghurt.start();

                                }
                                else {
                                    enemy2.setBackground(getResources().getDrawable(R.drawable.d_simle));
                                    enemy1.setBackground(getResources().getDrawable(R.drawable.s_draw));

                                    msg2.setText("Draw ! " );
                                    msg4.setText("Draw ! ");

                                }

                                if (playerHP <= 0 || enemy1HP <= 0)
                                {

                                    if (playerHP <=0)
                                    {
                                        enemy1.setBackground(getResources().getDrawable(R.drawable.s_draw));
                                        enemy2.setBackground(getResources().getDrawable(R.drawable.d_dead));
                                        roundNumber.setText("Player Lost  : " + Integer.toString(round));
                                        enemyHP2.setText("HP : Death" );
                                        playerScore.setText("HP : Death" );
                                        Animation animationfade2 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade2);
                                        enemy2.startAnimation(animationfade2);


                                    }
                                    else if(enemy1HP<=0)
                                    {
                                        enemy1.setBackground(getResources().getDrawable(R.drawable.s_dead));
                                        enemy2.setBackground(getResources().getDrawable(R.drawable.d_simle));
                                        Animation animationfade2 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade2);
                                        enemy1.startAnimation(animationfade2);

                                        playerHP=playerHP+ enemy2HP;
                                        roundNumber.setText("Enemy killed : HP gained " + Integer.toString(enemy2HP));
                                        enemyHP1.setText("HP : Death" );
                                        enemyHP2.setText("HP : " + Integer.toString(playerHP));
                                        playerScore.setText("HP : " + Integer.toString(playerHP));



                                    }
                                    if(map==1)
                                    {

                                        handler.postDelayed(new Runnable() {
                                            @Override
                                            public void run() {
                                                Intent intent = new Intent(battle.this, map.class);
                                                intent.putExtra("currentpos", currentposition);
                                                intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                                                startActivity(intent);


                                            }
                                        }, 4000);

                                    }
                                    else if(map ==2)
                                    {
                                        handler.postDelayed(new Runnable() {
                                            @Override
                                            public void run() {
                                                Intent intent = new Intent(battle.this, map2.class);
                                                intent.putExtra("currentpos", currentposition);
                                                intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                                                startActivity(intent);


                                            }
                                        }, 4000);
                                    }
                                    else if(map ==3)
                                    {
                                        handler.postDelayed(new Runnable() {
                                            @Override
                                            public void run() {
                                                Intent intent = new Intent(battle.this, map3.class);
                                                intent.putExtra("currentpos", currentposition);
                                                intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                                                startActivity(intent);


                                            }
                                        }, 4000);
                                    }


                                }
                                else
                                {
                                    handler.postDelayed(new Runnable() {
                                        @Override
                                        public void run() {
                                            enemy2.setBackground(getResources().getDrawable(R.drawable.d_normal));
                                            enemy1.setBackground(getResources().getDrawable(R.drawable.s_normal));
                                            turn.setText("Player turn");
                                            Animation animationmove4 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.move4);
                                            turn.startAnimation(animationmove4);

                                            roundNumber.startAnimation(animationmove4);


                                        }
                                    }, 2000);
                                }


                            }
                        }, 2500);






                    }
                }, 1000);





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


        int val1 = 1 + (int) (6 * Math.random());
        int val2 = 1 + (int) (6 * Math.random());
        int val3 = 1 + (int) (6 * Math.random());


        setDie(val1, die1);
        setDie(val2, die2);
        setDie(val3, die3);


            playerTurn = val1 + val2 + val3;
        turn.setText("Enemy Turn");
        Animation animationmove4 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.move4);
        turn.startAnimation(animationmove4);

    }



    public void rollDice2() {



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