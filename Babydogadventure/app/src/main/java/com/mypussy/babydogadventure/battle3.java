package com.mypussy.babydogadventure;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
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


public class battle3 extends ActionBarActivity {
    private FrameLayout die1, die2;
    private Button roll, hold;
    private int playerhp1=32;
    private int playerhp2=30;
    private int score;
    private int score2;
    private int round=1;
    private int roundScore=0;
    private int currentposition;
    private int map;
    int playerturn=1;
    TextView msg1;
    TextView turn;
    TextView textroundScore;
    TextView hp1;
    TextView hp2,enemyname1,enemyname2;
    boolean check=true;
    boolean changeturn=false;
    TextView roundNumber;
    MediaPlayer battle1music,babydoghurt,babydogattack,enemyattack,enemyattack2,ddraw,jdraw,victory,ohno;
    FrameLayout enemy1;
    FrameLayout enemy2;



    @Override
    protected void onPause() {
        super.onPause();
        battle1music.release();
        victory.release();
        ohno.release();
    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_battle3);
        victory = MediaPlayer.create(this, R.raw.victory);
        ohno = MediaPlayer.create(this, R.raw.ohno);
        battle1music = MediaPlayer.create(this, R.raw.finalbattle);
        battle1music.setVolume(0.8f, 0.8f);
        battle1music.start();
        babydogattack = MediaPlayer.create(this,R.raw.babydogattack);
        babydoghurt = MediaPlayer.create(this,R.raw.babydoghurt);
        ddraw = MediaPlayer.create(this,R.raw.ddraw);
        jdraw = MediaPlayer.create(this,R.raw.jdraw);


        enemyattack = MediaPlayer.create(this,R.raw.gunfire);
        enemyattack.setVolume(0.3f, 0.3f);
        enemyattack2 = MediaPlayer.create(this,R.raw.enemyattack2);
        enemy1 = (FrameLayout) findViewById(R.id.enemy1);
        enemy2 = (FrameLayout) findViewById(R.id.enemy2);



        Intent intent = getIntent();
        currentposition = intent.getIntExtra("currentpos", 0);
        map = intent.getIntExtra("map", 0);

        msg1 = (TextView) findViewById(R.id.msg1);

        roundNumber = (TextView) findViewById(R.id.round);
        roundNumber.setText("ROUND : " + Integer.toString(round));


        hp1 = (TextView) findViewById(R.id.enemyhp1);
        hp1.setText("HP 1 : " + Integer.toString(playerhp1));

        hp2 = (TextView) findViewById(R.id.enemyhp2);
        hp2.setText("HP 2 : " + Integer.toString( playerhp2 ));
        turn = (TextView) findViewById(R.id.turn);
        turn.setText("Player Turn");
        enemyname1 = (TextView) findViewById(R.id.enemyname1);
        enemyname2 = (TextView) findViewById(R.id.enemyname2);
        die1 = (FrameLayout) findViewById(R.id.die1);
        die2 = (FrameLayout) findViewById(R.id.die2);

        textroundScore=(TextView) findViewById(R.id.roundScore);
        Animation animationbattle3open = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.battle3open);

        Animation animationbattle3open2 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.battle3open2);
        Animation animationbattle3open3 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.battle3open3);
        enemy1.startAnimation(animationbattle3open2);
        enemy2.startAnimation(animationbattle3open);
        hp1.startAnimation(animationbattle3open3);
        hp2.startAnimation(animationbattle3open3);
        enemyname1.startAnimation(animationbattle3open3);
        enemyname2.startAnimation(animationbattle3open3);
        turn.startAnimation(animationbattle3open3);
        roundNumber.startAnimation(animationbattle3open3);
        die1.startAnimation(animationbattle3open3);
        die2.startAnimation(animationbattle3open3);
        textroundScore.startAnimation(animationbattle3open3);


        roll = (Button) findViewById(R.id.button);
        roll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                if (check==true & playerturn==1){
                    Log.i("Error", "check T  , turn 1 ");
                    rollDice();
                    textroundScore.setText(Integer.toString(roundScore));

                }
                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run()
                    {
                        if (playerturn == 2& check ==false) {
                            final int val2 = 1 + (int) (3 * Math.random());
                            if (val2==1|| val2==2){
                                playerhp2 = playerhp2 - 8;
                                hp2.setText("HP 2 : " + Integer.toString(playerhp2));
                                enemyattack.start();
                                textroundScore.setText(Integer.toString(roundScore));
                                playerturn = 1;
                                textroundScore.setText("CPU ATTACK : player -   8" );
                                roundScore = 0;
                                turn.setText("Player Turn");
                                roundNumber.setText("ROUND : " + Integer.toString(round ));
                                Animation animation2 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.move2);
                                enemy2.startAnimation(animation2);
                                Animation animation3 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.zoom);
                                enemy1.startAnimation(animation3);
                                enemy2.setBackground(getResources().getDrawable(R.drawable.d_dead));
                                enemy1.setBackground(getResources().getDrawable(R.drawable.j_attack));
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        enemy2.setBackground(getResources().getDrawable(R.drawable.d_normal));
                                        enemy1.setBackground(getResources().getDrawable(R.drawable.j_normal));

                                    }
                                }, 1500);

                                check =true;

                            }

                            else {
                                textroundScore.setText(Integer.toString(roundScore));
                                playerturn = 1;
                                roundScore = 0;
                                turn.setText("Player Turn");
                                roundNumber.setText("ROUND : " + Integer.toString(round ));
                                Animation animation2 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate2);
                                enemy1.startAnimation(animation2);
                                Animation animation3 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate2);
                                enemy2.startAnimation(animation3);
                                enemy2.setBackground(getResources().getDrawable(R.drawable.d_normal));
                                enemy1.setBackground(getResources().getDrawable(R.drawable.j_attack2));
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        enemy2.setBackground(getResources().getDrawable(R.drawable.d_normal));
                                        enemy1.setBackground(getResources().getDrawable(R.drawable.j_normal));

                                    }
                                }, 1500);

                                check=true;
                            }



                        }


                        if ( (playerhp1>0) ||  (playerhp2>0) ) {
                            textroundScore.setText("Round Score : " + Integer.toString(roundScore));
                        }
                        if (playerhp1<=0 || playerhp2<=0)
                        {
                            final Handler handlerdead = new Handler();
                            if (playerhp1 <=0){
                                textroundScore.setText("Super Jonathan Death");
                                ohno.start();
                                handlerdead.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        victory.start();
                                        battle1music.stop();


                                    }
                                }, 2000);


                                Animation animationfade2 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.battle3open4);
                                enemy1.startAnimation(animationfade2);
                                if(map==1 )
                                {
                                    handlerdead.postDelayed(new Runnable() {
                                        @Override
                                        public void run() {
                                            Intent intent = new Intent(battle3.this, map.class);
                                            intent.putExtra("currentpos", currentposition);
                                            intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                                            startActivity(intent);


                                        }
                                    }, 14000);
                                }
                                else if(map ==2)
                                {
                                    handlerdead.postDelayed(new Runnable() {
                                        @Override
                                        public void run() {
                                            Intent intent = new Intent(battle3.this, map2.class);
                                            intent.putExtra("currentpos", currentposition);
                                            intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                                            startActivity(intent);


                                        }
                                    }, 14000);
                                }
                                else if(map ==3)
                                {
                                    handlerdead.postDelayed(new Runnable() {
                                        @Override
                                        public void run() {
                                            Intent intent = new Intent(battle3.this, map3.class);
                                            intent.putExtra("currentpos", currentposition);
                                            intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                                            startActivity(intent);


                                        }
                                    }, 14000);
                                }

                            }

                            else if (playerhp2<=0){
                                textroundScore.setText("Player 1 Death");
                                Animation animationfade2 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade2);
                                enemy2.startAnimation(animationfade2);
                                if(map==1 )
                                {
                                    handlerdead.postDelayed(new Runnable() {
                                        @Override
                                        public void run() {
                                            Intent intent = new Intent(battle3.this, map.class);
                                            intent.putExtra("currentpos", currentposition);
                                            intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                                            startActivity(intent);


                                        }
                                    }, 2000);
                                }
                                else if(map ==2)
                                {
                                    handlerdead.postDelayed(new Runnable() {
                                        @Override
                                        public void run() {
                                            Intent intent = new Intent(battle3.this, map2.class);
                                            intent.putExtra("currentpos", currentposition);
                                            intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                                            startActivity(intent);


                                        }
                                    }, 2000);
                                }
                                else if(map ==3)
                                {
                                    handlerdead.postDelayed(new Runnable() {
                                        @Override
                                        public void run() {
                                            Intent intent = new Intent(battle3.this, map3.class);
                                            intent.putExtra("currentpos", currentposition);
                                            intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                                            startActivity(intent);


                                        }
                                    }, 2000);
                                }

                            }



                        }

                    }
                }, 2000);








            }

        });

        hold = (Button)findViewById(R.id.hold);
        hold.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (roundScore>0) {
                    round = round + 1;
                    playerhp1 = playerhp1 - roundScore;
                    hp1.setText("HP 1 : " + Integer.toString(playerhp1));
                    roundScore = 0;
                    babydogattack.start();

                    textroundScore.setText("Round Score : " + Integer.toString(roundScore));
                    turn.setText("CPU Turn");
                    Animation animation2 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.move2);
                    enemy1.startAnimation(animation2);
                    Animation animation3 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.zoom);
                    enemy2.startAnimation(animation3);
                    enemy2.setBackground(getResources().getDrawable(R.drawable.d_attack));
                    enemy1.setBackground(getResources().getDrawable(R.drawable.j_hurt));


                    final int val2 = 1 + (int) (3 * Math.random());

                    if (playerhp1>0) {
                        if (val2 == 1 || val2 == 2) {
                            playerhp2 = playerhp2 - 8;
                            hp2.setText("HP 2 : " + Integer.toString(playerhp2));
                            textroundScore.setText(Integer.toString(roundScore));
                            playerturn = 1;
                            textroundScore.setText("CPU ATTACK : player - 8 " );
                            roundScore = 0;
                            turn.setText("Player Turn");
                            roundNumber.setText("ROUND : " + Integer.toString(round));
                            final Handler handler = new Handler();
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    enemyattack.start();
                                    Animation animation2 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.move2);
                                    enemy2.startAnimation(animation2);
                                    Animation animation3 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.zoom);
                                    enemy1.startAnimation(animation3);

                                    enemy2.setBackground(getResources().getDrawable(R.drawable.d_dead));
                                    enemy1.setBackground(getResources().getDrawable(R.drawable.j_attack));
                                    handler.postDelayed(new Runnable() {
                                        @Override
                                        public void run() {
                                            enemy2.setBackground(getResources().getDrawable(R.drawable.d_normal));
                                            enemy1.setBackground(getResources().getDrawable(R.drawable.j_normal));

                                        }
                                    }, 1500);
                                }
                            }, 2000);


                            check = true;

                        } else {
                            textroundScore.setText(Integer.toString(roundScore));
                            playerturn = 1;
                            textroundScore.setText("CPU ATTACK : player - " + Integer.toString(roundScore));
                            roundScore = 0;
                            turn.setText("Player Turn");
                            roundNumber.setText("ROUND : " + Integer.toString(round));
                            final Handler handler = new Handler();

                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    jdraw.start();
                                    Animation animation2 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate2);
                                    enemy1.startAnimation(animation2);
                                    Animation animation3 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate2);
                                    enemy2.startAnimation(animation3);
                                    enemy2.setBackground(getResources().getDrawable(R.drawable.d_normal));
                                    enemy1.setBackground(getResources().getDrawable(R.drawable.j_attack2));
                                    handler.postDelayed(new Runnable() {
                                        @Override
                                        public void run() {

                                            enemy2.setBackground(getResources().getDrawable(R.drawable.d_normal));
                                            enemy1.setBackground(getResources().getDrawable(R.drawable.j_normal));


                                        }
                                    }, 1500);
                                }
                            }, 2000);


                            check = true;
                        }
                    }




                    if(playerhp1<=0 ||playerhp2<=0)
                    {

                        if (playerhp1 <= 0)
                        {
                            final Handler handlerdead = new Handler();
                            textroundScore.setText("Super Jonathan Death");
                            ohno.start();
                            handlerdead.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    victory.start();
                                    battle1music.stop();


                                }
                            }, 2000);

                            Animation animationfade2 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.battle3open4);
                            enemy1.startAnimation(animationfade2);
                            if(map==1)
                            {
                                handlerdead.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        Intent intent = new Intent(battle3.this, map.class);
                                        intent.putExtra("currentpos", currentposition);
                                        intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                                        startActivity(intent);


                                    }
                                }, 14000);
                            }
                            else if(map ==2)
                            {
                                handlerdead.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        Intent intent = new Intent(battle3.this, map2.class);
                                        intent.putExtra("currentpos", currentposition);
                                        intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                                        startActivity(intent);


                                    }
                                }, 14000);
                            }
                            else if(map ==3)
                            {
                                handlerdead.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        Intent intent = new Intent(battle3.this, map3.class);
                                        intent.putExtra("currentpos", currentposition);
                                        intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                                        startActivity(intent);


                                    }
                                }, 14000);
                            }
                        }


                        }
                        else if (playerhp2 <= 0)
                        {
                            final Handler handlerdead = new Handler();
                            textroundScore.setText("Player 1 Death");
                            Animation animationfade2 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade2);
                            enemy2.startAnimation(animationfade2);
                            if(map==1)
                            {
                                handlerdead.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        Intent intent = new Intent(battle3.this, map.class);
                                        intent.putExtra("currentpos", currentposition);
                                        intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                                        startActivity(intent);


                                    }
                                }, 2000);
                            }
                            else if(map ==2)
                            {
                                handlerdead.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        Intent intent = new Intent(battle3.this, map2.class);
                                        intent.putExtra("currentpos", currentposition);
                                        intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                                        startActivity(intent);


                                    }
                                }, 2000);
                            }
                            else if(map ==3)
                            {
                                handlerdead.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        Intent intent = new Intent(battle3.this, map3.class);
                                        intent.putExtra("currentpos", currentposition);
                                        intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                                        startActivity(intent);


                                    }
                                }, 2000);
                            }
                        }





                }

                else {

                    textroundScore.setText("Player 1 Score =0 can't hold");


                }
            }
        });




    }

    //get two random ints between 1 and 6 inclusive






    public void rollDice() {
        final int val1 = 1 + (int) (6 * Math.random());
        final int val2 = 1 + (int) (6 * Math.random());


        Log.i("Error", "val1 = " + val1);
        Log.i("Error", "val2 = " + val2);


        setDie(val1, die1);
        setDie(val2, die2);



        if (val1==1 || val2==1) {
            round = round + 1;
            Log.i("Error", "change check to false ");
            playerturn = 2;
            textroundScore.setText("Player roll 1: CPU TURN");
            ddraw.start();
            turn.setText("CPU Turn");
            roundScore = 0;
            Animation animation2 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate2);
            enemy1.startAnimation(animation2);
            Animation animation3 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate2);
            enemy2.startAnimation(animation3);
            enemy2.setBackground(getResources().getDrawable(R.drawable.d_normal));
            enemy1.setBackground(getResources().getDrawable(R.drawable.j_attack2));
            check =false;
        }


        else {
            roundScore = roundScore + val1 + val2;
        }







    }

    //set the appropriate picture for each die per int
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
