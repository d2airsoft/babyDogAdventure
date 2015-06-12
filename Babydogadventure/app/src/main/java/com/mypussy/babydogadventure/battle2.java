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

public class battle2 extends Activity  {
    private TextView die1, die2;
    private Button roll,roll2, roll3, hold;
    private int playerHP = 30;
    private int enemy1HP = 30;
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
    TextView turn,enemyname1,enemyname2,rounds;
    FrameLayout enemy1;
    FrameLayout player;
    FrameLayout enemy2;
    private int currentposition;
    private int map;
    MediaPlayer battle1music,babydoghurt,babydogattack,enemyattack,enemyattack2;
    @Override
    protected void onPause() {
        super.onPause();
        battle1music.release();
    }
    @Override

    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_battle2);
        battle1music = MediaPlayer.create(this,R.raw.battle1);
        battle1music.setVolume(0.7f, 0.7f);
        battle1music.start();
        babydogattack = MediaPlayer.create(this,R.raw.babydogattack);
        babydoghurt = MediaPlayer.create(this,R.raw.babydoghurt);
        enemyattack = MediaPlayer.create(this, R.raw.enemyattack);


        enemyattack.setVolume(0.3f, 0.3f);
        enemyattack2 = MediaPlayer.create(this, R.raw.enemyattack2);
        Intent intent = getIntent();
        currentposition = intent.getIntExtra("currentpos",0);
        map = intent.getIntExtra("map", 0);

        mHandler=new Handler();

        roundNumber = (TextView) findViewById(R.id.round);
        enemyHP1 = (TextView) findViewById(R.id.enemyhp1);
        enemyHP2 = (TextView) findViewById(R.id.enemyhp2);
        playerScore = (TextView) findViewById(R.id.bar2);
        msg1 = (TextView) findViewById(R.id.msg1);

        turn= (TextView) findViewById(R.id.turn);
        enemyname1 = (TextView) findViewById(R.id.enemyname1);
        enemyname2 = (TextView) findViewById(R.id.enemyname2);


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
        enemy1.setBackground(getResources().getDrawable(R.drawable.r_normal));
        enemy2.setBackground(getResources().getDrawable(R.drawable.d_normal));
        rounds= (TextView) findViewById(R.id.round);

        Animation animationbattle2open = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.battle2open);
        enemy1.startAnimation(animationbattle2open);
        enemy2.startAnimation(animationbattle2open);
        Animation animationbattl2open2 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.battle2open2);
        enemyHP1.startAnimation(animationbattl2open2);
        enemyHP2.startAnimation(animationbattl2open2);
        enemyname1.startAnimation(animationbattl2open2);
        enemyname2.startAnimation(animationbattl2open2);
        turn.startAnimation(animationbattl2open2);
        rounds.startAnimation(animationbattl2open2);

        roll = (Button) findViewById(R.id.rock);
        roll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                turn.setText("Enemy Turn");
                Animation animationmove4 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.move4);
                turn.startAnimation(animationmove4);

                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {


                        rollDice2();
                        die2.setText("");
                        roundNumber.setText("ROUND : " + Integer.toString(round));
                        die2.setText("Player : ROCK");
                        round = round + 1;
                        enemy2.setBackground(getResources().getDrawable(R.drawable.d_r));


                        if ( enemy1Turn==3){

                            enemy1.setBackground(getResources().getDrawable(R.drawable.r_s));
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    enemy1HP=enemy1HP-25;

                                    msg1.setText("Player won Turn!! Enemy lost HP: " + Integer.toString(25));
                                    playerScore.setText("HP : " + Integer.toString(playerHP));
                                    enemyHP1.setText("HP : " + Integer.toString(enemy1HP));
                                    enemyHP2.setText("HP : " + Integer.toString(playerHP));
                                    babydogattack.start();
                                    enemy1.setBackground(getResources().getDrawable(R.drawable.r_hurt));
                                    enemy2.setBackground(getResources().getDrawable(R.drawable.d_attack));
                                    Animation animation2 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.move2);
                                    enemy1.startAnimation(animation2);
                                    Animation animation3 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.zoom);
                                    enemy2.startAnimation(animation3);
                                    if (playerHP <= 0 || enemy1HP <= 0) {
                                        handler.postDelayed(new Runnable() {
                                            @Override
                                            public void run() {
                                                if (playerHP <=0) {
                                                    enemy1.setBackground(getResources().getDrawable(R.drawable.r_draw));
                                                    enemy2.setBackground(getResources().getDrawable(R.drawable.d_dead));
                                                    Animation animationfade2 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade2);
                                                    enemy2.startAnimation(animationfade2);
                                                    roundNumber.setText("Player Lost !!! Game Over " );
                                                    playerScore.setText("HP : Death" );
                                                    enemyHP2.setText("HP : Death" );



                                                } else {
                                                    enemy1.setBackground(getResources().getDrawable(R.drawable.r_dead));
                                                    enemy2.setBackground(getResources().getDrawable(R.drawable.d_simle));
                                                    Animation animationfade2 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade2);
                                                    enemy1.startAnimation(animationfade2);
                                                    playerHP=playerHP+ 50;
                                                    roundNumber.setText("Enemy killed : HP gained " + Integer.toString(50));
                                                    playerScore.setText("HP : " + Integer.toString(playerHP));
                                                    enemyHP1.setText("HP : Death " );
                                                    enemyHP2.setText("HP : " + Integer.toString(playerHP));

                                                }
                                                if(map==1)
                                                {
                                                    handler.postDelayed(new Runnable() {
                                                        @Override
                                                        public void run() {
                                                            Intent intent = new Intent(battle2.this, map.class);
                                                            intent.putExtra("currentpos", currentposition);
                                                            intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                                                            startActivity(intent);


                                                        }
                                                    }, 3500);
                                                }
                                                else if(map ==2)
                                                {
                                                    handler.postDelayed(new Runnable() {
                                                        @Override
                                                        public void run() {
                                                            Intent intent = new Intent(battle2.this, map2.class);
                                                            intent.putExtra("currentpos", currentposition);
                                                            intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                                                            startActivity(intent);


                                                        }
                                                    }, 3500);
                                                }
                                                else if(map ==3)
                                                {
                                                    handler.postDelayed(new Runnable() {
                                                        @Override
                                                        public void run() {
                                                            Intent intent = new Intent(battle2.this, map3.class);
                                                            intent.putExtra("currentpos", currentposition);
                                                            intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                                                            startActivity(intent);


                                                        }
                                                    },3500);
                                                }


                                            }
                                        }, 800);



                                    }
                                    else
                                    {
                                        handler.postDelayed(new Runnable() {
                                            @Override
                                            public void run() {
                                                enemy2.setBackground(getResources().getDrawable(R.drawable.d_normal));
                                                enemy1.setBackground(getResources().getDrawable(R.drawable.r_normal));
                                                playerScore.setText("HP : " + Integer.toString(playerHP));
                                                turn.setText("Player turn");
                                                Animation animationmove4 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.move4);
                                                turn.startAnimation(animationmove4);
                                                roundNumber.startAnimation(animationmove4);

                                                die1.setText(" ");
                                                die2.setText(" ");

                                            }
                                        }, 1800);
                                    }


                                }
                            }, 2000);


                        }

                        else if ( enemy1Turn==2){



                            enemy1.setBackground(getResources().getDrawable(R.drawable.r_p));
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    playerHP=playerHP-25;
                                    msg1.setText("Enemy won Turn!! Player lost HP: " + Integer.toString(25));

                                    playerScore.setText("HP : " + Integer.toString(playerHP));
                                    enemyHP1.setText("HP : " + Integer.toString(enemy1HP));
                                    enemyHP2.setText("HP : " + Integer.toString(playerHP));
                                    enemyattack.start();
                                    enemy2.setBackground(getResources().getDrawable(R.drawable.d_hurt));
                                    enemy1.setBackground(getResources().getDrawable(R.drawable.r_attack));
                                    Animation animation2 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.move);
                                    enemy2.startAnimation(animation2);
                                    Animation animation3 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.zoom);
                                    enemy1.startAnimation(animation3);

                                    if (playerHP <= 0 || enemy1HP <= 0) {
                                        handler.postDelayed(new Runnable() {
                                            @Override
                                            public void run() {
                                                if (playerHP <=0) {
                                                    enemy1.setBackground(getResources().getDrawable(R.drawable.r_draw));
                                                    enemy2.setBackground(getResources().getDrawable(R.drawable.d_dead));
                                                    Animation animationfade2 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade2);
                                                    enemy2.startAnimation(animationfade2);
                                                    roundNumber.setText("Player Lost !!! Game Over ");
                                                    playerScore.setText("HP : Death");
                                                    enemyHP2.setText("HP : Death" );




                                                } else {
                                                    enemy1.setBackground(getResources().getDrawable(R.drawable.r_dead));
                                                    enemy2.setBackground(getResources().getDrawable(R.drawable.d_simle));
                                                    Animation animationfade2 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade2);
                                                    enemy1.startAnimation(animationfade2);
                                                    playerHP=playerHP+ 50;
                                                    roundNumber.setText("Enemy killed : HP gained " + Integer.toString(50));
                                                    playerScore.setText("HP : " + Integer.toString(playerHP));
                                                    enemyHP1.setText("HP : Death " );
                                                    enemyHP2.setText("HP : " + Integer.toString(playerHP));


                                                }
                                                if(map==1)
                                                {
                                                    handler.postDelayed(new Runnable() {
                                                        @Override
                                                        public void run() {
                                                            Intent intent = new Intent(battle2.this, map.class);
                                                            intent.putExtra("currentpos", currentposition);
                                                            intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                                                            startActivity(intent);


                                                        }
                                                    }, 3500);
                                                }
                                                else if(map ==2)
                                                {
                                                    handler.postDelayed(new Runnable() {
                                                        @Override
                                                        public void run() {
                                                            Intent intent = new Intent(battle2.this, map2.class);
                                                            intent.putExtra("currentpos", currentposition);
                                                            intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                                                            startActivity(intent);


                                                        }
                                                    }, 3500);
                                                }
                                                else if(map ==3)
                                                {
                                                    handler.postDelayed(new Runnable() {
                                                        @Override
                                                        public void run() {
                                                            Intent intent = new Intent(battle2.this, map3.class);
                                                            intent.putExtra("currentpos", currentposition);
                                                            intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                                                            startActivity(intent);


                                                        }
                                                    }, 3500);
                                                }


                                            }
                                        }, 800);



                                    }
                                    else
                                    {
                                        handler.postDelayed(new Runnable() {
                                            @Override
                                            public void run() {
                                                enemy2.setBackground(getResources().getDrawable(R.drawable.d_normal));
                                                enemy1.setBackground(getResources().getDrawable(R.drawable.r_normal));
                                                playerScore.setText("HP : " + Integer.toString(playerHP));
                                                turn.setText("Player turn");
                                                Animation animationmove4 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.move4);
                                                turn.startAnimation(animationmove4);
                                                roundNumber.startAnimation(animationmove4);

                                                die1.setText(" ");
                                                die2.setText(" ");

                                            }
                                        }, 1800);
                                    }



                                }
                            }, 2000);


                        }
                        else {
                            msg1.setText("Draw ! " );
                            enemy1.setBackground(getResources().getDrawable(R.drawable.r_r));

                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {

                                    enemy2.setBackground(getResources().getDrawable(R.drawable.d_simle));
                                    enemy1.setBackground(getResources().getDrawable(R.drawable.r_draw));
                                    if (playerHP <= 0 || enemy1HP <= 0) {
                                        handler.postDelayed(new Runnable() {
                                            @Override
                                            public void run() {
                                                if (playerHP <=0) {
                                                    enemy1.setBackground(getResources().getDrawable(R.drawable.r_attack));
                                                    enemy2.setBackground(getResources().getDrawable(R.drawable.d_dead));
                                                    Animation animationfade2 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade2);
                                                    enemy2.startAnimation(animationfade2);
                                                    roundNumber.setText("Player Lost !!! Game Over ");
                                                    playerScore.setText("HP : Death" );
                                                    enemyHP2.setText("HP : Death" );




                                                } else {
                                                    enemy1.setBackground(getResources().getDrawable(R.drawable.r_hurt));
                                                    enemy2.setBackground(getResources().getDrawable(R.drawable.d_attack));
                                                    Animation animationfade2 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade2);
                                                    enemy1.startAnimation(animationfade2);
                                                    playerHP=playerHP+ 50;
                                                    roundNumber.setText("Enemy killed : HP gained " + Integer.toString(50));
                                                    playerScore.setText("HP : " + Integer.toString(playerHP));
                                                    enemyHP1.setText("HP : Death " );
                                                    enemyHP2.setText("HP : " + Integer.toString(playerHP));


                                                }
                                                if(map==1)
                                                {
                                                    handler.postDelayed(new Runnable() {
                                                        @Override
                                                        public void run() {
                                                            Intent intent = new Intent(battle2.this, map.class);
                                                            intent.putExtra("currentpos", currentposition);
                                                            intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                                                            startActivity(intent);


                                                        }
                                                    },3500);
                                                }
                                                else if(map ==2)
                                                {
                                                    handler.postDelayed(new Runnable() {
                                                        @Override
                                                        public void run() {
                                                            Intent intent = new Intent(battle2.this, map2.class);
                                                            intent.putExtra("currentpos", currentposition);
                                                            intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                                                            startActivity(intent);


                                                        }
                                                    }, 3500);
                                                }
                                                else if(map ==3)
                                                {
                                                    handler.postDelayed(new Runnable() {
                                                        @Override
                                                        public void run() {
                                                            Intent intent = new Intent(battle2.this, map3.class);
                                                            intent.putExtra("currentpos", currentposition);
                                                            intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                                                            startActivity(intent);


                                                        }
                                                    }, 3500);
                                                }


                                            }
                                        }, 800);



                                    }
                                    else
                                    {
                                        handler.postDelayed(new Runnable() {
                                            @Override
                                            public void run() {
                                                enemy2.setBackground(getResources().getDrawable(R.drawable.d_normal));
                                                enemy1.setBackground(getResources().getDrawable(R.drawable.r_normal));
                                                playerScore.setText("HP : " + Integer.toString(playerHP));
                                                turn.setText("Player turn");
                                                Animation animationmove4 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.move4);
                                                turn.startAnimation(animationmove4);
                                                roundNumber.startAnimation(animationmove4);

                                                die1.setText(" ");
                                                die2.setText(" ");

                                            }
                                        }, 1800);
                                    }



                                }
                            }, 2000);




                        }


                    }
                }, 600);

            }
        });

        roll2 = (Button) findViewById(R.id.paper);
        roll2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                turn.setText("Enemy Turn");
                Animation animationmove4 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.move4);
                turn.startAnimation(animationmove4);

                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        // Do something after 5s = 5000ms
                        rollDice2();
                        die1.setText("Player : Paper");
                        enemy2.setBackground(getResources().getDrawable(R.drawable.d_p));

                        roundNumber.setText("ROUND : " + Integer.toString(round));



                        round = round + 1;


                        if ( enemy1Turn==1){




                            enemy1.setBackground(getResources().getDrawable(R.drawable.r_r));
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    enemy1HP=enemy1HP-25;
                                    playerScore.setText("HP : " + Integer.toString(playerHP));
                                    msg1.setText("Player won Turn!! Enemy lost HP: " + Integer.toString(25));

                                    enemyHP2.setText("HP : " + Integer.toString(playerHP));
                                    enemyHP1.setText("HP : " + Integer.toString(enemy1HP));
                                    babydogattack.start();

                                    enemy1.setBackground(getResources().getDrawable(R.drawable.r_hurt));
                                    enemy2.setBackground(getResources().getDrawable(R.drawable.d_attack));
                                    Animation animation2 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.move2);
                                    enemy1.startAnimation(animation2);
                                    Animation animation3 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.zoom);
                                    enemy2.startAnimation(animation3);
                                    if (playerHP <= 0 || enemy1HP <= 0) {
                                        handler.postDelayed(new Runnable() {
                                            @Override
                                            public void run() {
                                                if (playerHP <=0) {
                                                    enemy1.setBackground(getResources().getDrawable(R.drawable.r_draw));
                                                    enemy2.setBackground(getResources().getDrawable(R.drawable.d_dead));
                                                    Animation animationfade2 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade2);
                                                    enemy2.startAnimation(animationfade2);
                                                    roundNumber.setText("Player Lost !!! Game Over ");
                                                    playerScore.setText("HP : Death" );
                                                    enemyHP2.setText("HP : Death" );




                                                } else {
                                                    enemy1.setBackground(getResources().getDrawable(R.drawable.r_dead));
                                                    enemy2.setBackground(getResources().getDrawable(R.drawable.d_simle));
                                                    Animation animationfade2 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade2);
                                                    enemy1.startAnimation(animationfade2);
                                                    playerHP=playerHP+ 50;
                                                    roundNumber.setText("Enemy killed : HP gained " + Integer.toString(50));
                                                    playerScore.setText("HP : " + Integer.toString(playerHP));
                                                    enemyHP1.setText("HP : Death " );
                                                    enemyHP2.setText("HP : " + Integer.toString(playerHP));


                                                }
                                                if(map==1)
                                                {
                                                    handler.postDelayed(new Runnable() {
                                                        @Override
                                                        public void run() {
                                                            Intent intent = new Intent(battle2.this, map.class);
                                                            intent.putExtra("currentpos", currentposition);
                                                            intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                                                            startActivity(intent);


                                                        }
                                                    }, 3500);
                                                }
                                                else if(map ==2)
                                                {
                                                    handler.postDelayed(new Runnable() {
                                                        @Override
                                                        public void run() {
                                                            Intent intent = new Intent(battle2.this, map2.class);
                                                            intent.putExtra("currentpos", currentposition);
                                                            intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                                                            startActivity(intent);


                                                        }
                                                    }, 3500);
                                                }
                                                else if(map ==3)
                                                {
                                                    handler.postDelayed(new Runnable() {
                                                        @Override
                                                        public void run() {
                                                            Intent intent = new Intent(battle2.this, map3.class);
                                                            intent.putExtra("currentpos", currentposition);
                                                            intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                                                            startActivity(intent);


                                                        }
                                                    }, 3500);
                                                }


                                            }
                                        }, 800);



                                    }
                                    else
                                    {
                                        handler.postDelayed(new Runnable() {
                                            @Override
                                            public void run() {
                                                enemy2.setBackground(getResources().getDrawable(R.drawable.d_normal));
                                                enemy1.setBackground(getResources().getDrawable(R.drawable.r_normal));
                                                playerScore.setText("HP : " + Integer.toString(playerHP));
                                                turn.setText("Player turn");
                                                Animation animationmove4 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.move4);
                                                turn.startAnimation(animationmove4);
                                                roundNumber.startAnimation(animationmove4);

                                                die1.setText(" ");
                                                die2.setText(" ");

                                            }
                                        }, 1800);
                                    }


                                }
                            }, 2000);


                        }

                        else if ( enemy1Turn==3){



                            enemy1.setBackground(getResources().getDrawable(R.drawable.r_s));
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    enemyattack.start();

                                    playerHP=playerHP-25;
                                    msg1.setText("Enemy won Turn!! Player lost HP: " + Integer.toString(25));

                                    enemyHP2.setText("HP : " + Integer.toString(playerHP));
                                    enemyHP1.setText("HP : " + Integer.toString(enemy1HP));
                                    playerScore.setText("HP : " + Integer.toString(playerHP));

                                    enemy2.setBackground(getResources().getDrawable(R.drawable.d_hurt));
                                    enemy1.setBackground(getResources().getDrawable(R.drawable.r_attack));
                                    Animation animation2 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.move);
                                    enemy2.startAnimation(animation2);
                                    Animation animation3 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.zoom);
                                    enemy1.startAnimation(animation3);

                                    if (playerHP <= 0 || enemy1HP <= 0) {
                                        handler.postDelayed(new Runnable() {
                                            @Override
                                            public void run() {
                                                if (playerHP <=0) {
                                                    enemy1.setBackground(getResources().getDrawable(R.drawable.r_draw));
                                                    enemy2.setBackground(getResources().getDrawable(R.drawable.d_dead));
                                                    Animation animationfade2 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade2);
                                                    enemy2.startAnimation(animationfade2);
                                                    roundNumber.setText("Player Lost !!! Game Over ");
                                                    playerScore.setText("HP : Death" );
                                                    enemyHP2.setText("HP : Death" );




                                                } else {
                                                    enemy1.setBackground(getResources().getDrawable(R.drawable.r_dead));
                                                    enemy2.setBackground(getResources().getDrawable(R.drawable.d_simle));
                                                    Animation animationfade2 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade2);
                                                    enemy1.startAnimation(animationfade2);
                                                    playerHP=playerHP+ 50;
                                                    roundNumber.setText("Enemy killed : HP gained " + Integer.toString(50));
                                                    playerScore.setText("HP : " + Integer.toString(playerHP));
                                                    enemyHP1.setText("HP : Death " );
                                                    enemyHP2.setText("HP : " + Integer.toString(playerHP));


                                                }
                                                if(map==1)
                                                {
                                                    handler.postDelayed(new Runnable() {
                                                        @Override
                                                        public void run() {
                                                            Intent intent = new Intent(battle2.this, map.class);
                                                            intent.putExtra("currentpos", currentposition);
                                                            intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                                                            startActivity(intent);


                                                        }
                                                    }, 3500);
                                                }
                                                else if(map ==2)
                                                {
                                                    handler.postDelayed(new Runnable() {
                                                        @Override
                                                        public void run() {
                                                            Intent intent = new Intent(battle2.this, map2.class);
                                                            intent.putExtra("currentpos", currentposition);
                                                            intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                                                            startActivity(intent);


                                                        }
                                                    }, 3500);
                                                }
                                                else if(map ==3)
                                                {
                                                    handler.postDelayed(new Runnable() {
                                                        @Override
                                                        public void run() {
                                                            Intent intent = new Intent(battle2.this, map3.class);
                                                            intent.putExtra("currentpos", currentposition);
                                                            intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                                                            startActivity(intent);


                                                        }
                                                    }, 3500);
                                                }


                                            }
                                        }, 800);



                                    }
                                    else
                                    {
                                        handler.postDelayed(new Runnable() {
                                            @Override
                                            public void run() {
                                                enemy2.setBackground(getResources().getDrawable(R.drawable.d_normal));
                                                enemy1.setBackground(getResources().getDrawable(R.drawable.r_normal));
                                                playerScore.setText("HP : " + Integer.toString(playerHP));
                                                turn.setText("Player turn");
                                                Animation animationmove4 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.move4);
                                                turn.startAnimation(animationmove4);
                                                roundNumber.startAnimation(animationmove4);

                                                die1.setText(" ");
                                                die2.setText(" ");

                                            }
                                        }, 1800);
                                    }

                                }
                            }, 2000);


                        }
                        else {

                            msg1.setText("Draw ! " );
                            enemy1.setBackground(getResources().getDrawable(R.drawable.r_p));

                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {

                                    enemy2.setBackground(getResources().getDrawable(R.drawable.d_simle));
                                    enemy1.setBackground(getResources().getDrawable(R.drawable.r_draw));
                                    if (playerHP <= 0 || enemy1HP <= 0) {
                                        handler.postDelayed(new Runnable() {
                                            @Override
                                            public void run() {
                                                if (playerHP <=0) {
                                                    enemy1.setBackground(getResources().getDrawable(R.drawable.r_attack));
                                                    enemy2.setBackground(getResources().getDrawable(R.drawable.d_dead));
                                                    Animation animationfade2 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade2);
                                                    enemy2.startAnimation(animationfade2);
                                                    roundNumber.setText("Player Lost !!! Game Over ");
                                                    playerScore.setText("HP : Death" );
                                                    enemyHP2.setText("HP : Death" );




                                                } else {
                                                    enemy1.setBackground(getResources().getDrawable(R.drawable.r_hurt));
                                                    enemy2.setBackground(getResources().getDrawable(R.drawable.d_attack));
                                                    Animation animationfade2 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade2);
                                                    enemy1.startAnimation(animationfade2);
                                                    playerHP=playerHP+ 50;
                                                    roundNumber.setText("Enemy killed : HP gained " + Integer.toString(50));
                                                    playerScore.setText("HP : " + Integer.toString(playerHP));
                                                    enemyHP1.setText("HP : Death " );
                                                    enemyHP2.setText("HP : " + Integer.toString(playerHP));


                                                }
                                                if(map==1)
                                                {
                                                    handler.postDelayed(new Runnable() {
                                                        @Override
                                                        public void run() {
                                                            Intent intent = new Intent(battle2.this, map.class);
                                                            intent.putExtra("currentpos", currentposition);
                                                            intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                                                            startActivity(intent);


                                                        }
                                                    }, 3500);
                                                }
                                                else if(map ==2)
                                                {
                                                    handler.postDelayed(new Runnable() {
                                                        @Override
                                                        public void run() {
                                                            Intent intent = new Intent(battle2.this, map2.class);
                                                            intent.putExtra("currentpos", currentposition);
                                                            intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                                                            startActivity(intent);


                                                        }
                                                    }, 3500);
                                                }
                                                else if(map ==3)
                                                {
                                                    handler.postDelayed(new Runnable() {
                                                        @Override
                                                        public void run() {
                                                            Intent intent = new Intent(battle2.this, map3.class);
                                                            intent.putExtra("currentpos", currentposition);
                                                            intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                                                            startActivity(intent);


                                                        }
                                                    }, 3500);
                                                }


                                            }
                                        }, 800);



                                    }
                                    else
                                    {
                                        handler.postDelayed(new Runnable() {
                                            @Override
                                            public void run() {
                                                enemy2.setBackground(getResources().getDrawable(R.drawable.d_normal));
                                                enemy1.setBackground(getResources().getDrawable(R.drawable.r_normal));
                                                playerScore.setText("HP : " + Integer.toString(playerHP));
                                                turn.setText("Player turn");
                                                Animation animationmove4 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.move4);
                                                turn.startAnimation(animationmove4);
                                                roundNumber.startAnimation(animationmove4);

                                                die1.setText(" ");
                                                die2.setText(" ");

                                            }
                                        }, 1800);
                                    }


                                }
                            }, 2000);


                        }


                    }
                }, 600);






            }
        });


        roll3 = (Button) findViewById(R.id.scissor);
        roll3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                turn.setText("Enemy Turn");
                Animation animationmove4 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.move4);
                turn.startAnimation(animationmove4);

                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        // Do something after 5s = 5000ms
                        rollDice2();
                        die2.setText("Player : Scissor");
                        enemy2.setBackground(getResources().getDrawable(R.drawable.d_s));

                        roundNumber.setText("ROUND : " + Integer.toString(round));



                        round = round + 1;


                        if ( enemy1Turn==2){




                            enemy1.setBackground(getResources().getDrawable(R.drawable.r_p));
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    babydogattack.start();
                                    enemy1HP=enemy1HP-25;
                                    msg1.setText("Player won Turn!! Enemy lost HP: " + Integer.toString(25));

                                    playerScore.setText("HP : " + Integer.toString(playerHP));
                                    enemyHP1.setText("HP : " + Integer.toString(enemy1HP));
                                    enemyHP2.setText("HP : " + Integer.toString(playerHP));

                                    enemy1.setBackground(getResources().getDrawable(R.drawable.r_hurt));
                                    enemy2.setBackground(getResources().getDrawable(R.drawable.d_attack));
                                    Animation animation2 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.move2);
                                    enemy1.startAnimation(animation2);
                                    Animation animation3 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.zoom);
                                    enemy2.startAnimation(animation3);
                                    if (playerHP <= 0 || enemy1HP <= 0) {
                                        handler.postDelayed(new Runnable() {
                                            @Override
                                            public void run() {
                                                if (playerHP <=0) {
                                                    enemy1.setBackground(getResources().getDrawable(R.drawable.r_draw));
                                                    enemy2.setBackground(getResources().getDrawable(R.drawable.d_dead));
                                                    Animation animationfade2 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade2);
                                                    enemy2.startAnimation(animationfade2);
                                                    roundNumber.setText("Player Lost !!! Game Over ");
                                                    playerScore.setText("HP : Death" );
                                                    enemyHP2.setText("HP : Death" );




                                                } else {
                                                    enemy1.setBackground(getResources().getDrawable(R.drawable.r_dead));
                                                    enemy2.setBackground(getResources().getDrawable(R.drawable.d_simle));
                                                    Animation animationfade2 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade2);
                                                    enemy1.startAnimation(animationfade2);
                                                    playerHP=playerHP+ 50;
                                                    roundNumber.setText("Enemy killed : HP gained " + Integer.toString(50));
                                                    playerScore.setText("HP : " + Integer.toString(playerHP));
                                                    enemyHP1.setText("HP : Death " );
                                                    enemyHP2.setText("HP : " + Integer.toString(playerHP));


                                                }
                                                if(map==1)
                                                {
                                                    handler.postDelayed(new Runnable() {
                                                        @Override
                                                        public void run() {
                                                            Intent intent = new Intent(battle2.this, map.class);
                                                            intent.putExtra("currentpos", currentposition);
                                                            intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                                                            startActivity(intent);


                                                        }
                                                    }, 3500);
                                                }
                                                else if(map ==2)
                                                {
                                                    handler.postDelayed(new Runnable() {
                                                        @Override
                                                        public void run() {
                                                            Intent intent = new Intent(battle2.this, map2.class);
                                                            intent.putExtra("currentpos", currentposition);
                                                            intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                                                            startActivity(intent);


                                                        }
                                                    }, 3500);
                                                }
                                                else if(map ==3)
                                                {
                                                    handler.postDelayed(new Runnable() {
                                                        @Override
                                                        public void run() {
                                                            Intent intent = new Intent(battle2.this, map3.class);
                                                            intent.putExtra("currentpos", currentposition);
                                                            intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                                                            startActivity(intent);


                                                        }
                                                    }, 3500);
                                                }


                                            }
                                        }, 800);



                                    }
                                    else
                                    {
                                        handler.postDelayed(new Runnable() {
                                            @Override
                                            public void run() {
                                                enemy2.setBackground(getResources().getDrawable(R.drawable.d_normal));
                                                enemy1.setBackground(getResources().getDrawable(R.drawable.r_normal));
                                                playerScore.setText("HP : " + Integer.toString(playerHP));
                                                turn.setText("Player turn");
                                                Animation animationmove4 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.move4);
                                                turn.startAnimation(animationmove4);
                                                roundNumber.startAnimation(animationmove4);

                                                die1.setText(" ");
                                                die2.setText(" ");

                                            }
                                        }, 1800);
                                    }


                                }
                            }, 2000);


                        }

                        else if ( enemy1Turn==1){



                            enemy1.setBackground(getResources().getDrawable(R.drawable.r_r));
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    enemyattack.start();
                                    playerHP=playerHP-25;
                                    msg1.setText("Enemy won Turn!! Player lost HP: " + Integer.toString(25));

                                    playerScore.setText("HP : " + Integer.toString(playerHP));
                                    enemyHP1.setText("HP : " + Integer.toString(enemy1HP));
                                    enemyHP2.setText("HP : " + Integer.toString(playerHP));

                                    enemy2.setBackground(getResources().getDrawable(R.drawable.d_hurt));
                                    enemy1.setBackground(getResources().getDrawable(R.drawable.r_attack));
                                    Animation animation2 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.move);
                                    enemy2.startAnimation(animation2);
                                    Animation animation3 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.zoom);
                                    enemy1.startAnimation(animation3);
                                    if (playerHP <= 0 || enemy1HP <= 0) {
                                        handler.postDelayed(new Runnable() {
                                            @Override
                                            public void run() {
                                                if (playerHP <=0) {
                                                    enemy1.setBackground(getResources().getDrawable(R.drawable.r_draw));
                                                    enemy2.setBackground(getResources().getDrawable(R.drawable.d_dead));
                                                    Animation animationfade2 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade2);
                                                    enemy2.startAnimation(animationfade2);
                                                    roundNumber.setText("Player Lost !!! Game Over ");
                                                    playerScore.setText("HP : Death" );
                                                    enemyHP2.setText("HP : Death" );




                                                } else {
                                                    enemy1.setBackground(getResources().getDrawable(R.drawable.r_dead));
                                                    enemy2.setBackground(getResources().getDrawable(R.drawable.d_simle));
                                                    Animation animationfade2 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade2);
                                                    enemy1.startAnimation(animationfade2);
                                                    playerHP=playerHP+ 50;
                                                    roundNumber.setText("Enemy killed : HP gained " + Integer.toString(50));
                                                    playerScore.setText("HP : " + Integer.toString(playerHP));
                                                    enemyHP1.setText("HP : Death " );
                                                    enemyHP2.setText("HP : " + Integer.toString(playerHP));


                                                }
                                                if(map==1)
                                                {
                                                    handler.postDelayed(new Runnable() {
                                                        @Override
                                                        public void run() {
                                                            Intent intent = new Intent(battle2.this, map.class);
                                                            intent.putExtra("currentpos", currentposition);
                                                            intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                                                            startActivity(intent);


                                                        }
                                                    }, 3500);
                                                }
                                                else if(map ==2)
                                                {
                                                    handler.postDelayed(new Runnable() {
                                                        @Override
                                                        public void run() {
                                                            Intent intent = new Intent(battle2.this, map2.class);
                                                            intent.putExtra("currentpos", currentposition);
                                                            intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                                                            startActivity(intent);


                                                        }
                                                    }, 3500);
                                                }
                                                else if(map ==3)
                                                {
                                                    handler.postDelayed(new Runnable() {
                                                        @Override
                                                        public void run() {
                                                            Intent intent = new Intent(battle2.this, map3.class);
                                                            intent.putExtra("currentpos", currentposition);
                                                            intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                                                            startActivity(intent);


                                                        }
                                                    }, 3500);
                                                }


                                            }
                                        }, 800);



                                    }
                                    else
                                    {
                                        handler.postDelayed(new Runnable() {
                                            @Override
                                            public void run() {
                                                enemy2.setBackground(getResources().getDrawable(R.drawable.d_normal));
                                                enemy1.setBackground(getResources().getDrawable(R.drawable.r_normal));
                                                playerScore.setText("HP : " + Integer.toString(playerHP));
                                                turn.setText("Player turn");
                                                Animation animationmove4 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.move4);
                                                turn.startAnimation(animationmove4);
                                                roundNumber.startAnimation(animationmove4);

                                                die1.setText(" ");
                                                die2.setText(" ");

                                            }
                                        }, 1800);
                                    }


                                }
                            }, 2000);


                        }
                        else {

                            msg1.setText("Draw ! " );
                            enemy1.setBackground(getResources().getDrawable(R.drawable.r_s));

                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {

                                    enemy2.setBackground(getResources().getDrawable(R.drawable.d_simle));
                                    enemy1.setBackground(getResources().getDrawable(R.drawable.r_draw));
                                    if (playerHP <= 0 || enemy1HP <= 0) {
                                        handler.postDelayed(new Runnable() {
                                            @Override
                                            public void run() {
                                                if (playerHP <=0) {
                                                    enemy1.setBackground(getResources().getDrawable(R.drawable.r_attack));
                                                    enemy2.setBackground(getResources().getDrawable(R.drawable.d_dead));
                                                    Animation animationfade2 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade2);
                                                    enemy2.startAnimation(animationfade2);
                                                    roundNumber.setText("Player Lost !!! Game Over ");
                                                    playerScore.setText("HP : Death" );
                                                    enemyHP2.setText("HP : Death" );




                                                } else {
                                                    enemy1.setBackground(getResources().getDrawable(R.drawable.r_hurt));
                                                    enemy2.setBackground(getResources().getDrawable(R.drawable.d_attack));
                                                    Animation animationfade2 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade2);
                                                    enemy1.startAnimation(animationfade2);
                                                    playerHP=playerHP+ 50;
                                                    roundNumber.setText("Enemy killed : HP gained " + Integer.toString(50));
                                                    playerScore.setText("HP : " + Integer.toString(playerHP));
                                                    enemyHP1.setText("HP : Death " );
                                                    enemyHP2.setText("HP : " + Integer.toString(playerHP));


                                                }
                                                if(map==1)
                                                {
                                                    handler.postDelayed(new Runnable() {
                                                        @Override
                                                        public void run() {
                                                            Intent intent = new Intent(battle2.this, map.class);
                                                            intent.putExtra("currentpos", currentposition);
                                                            intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                                                            startActivity(intent);


                                                        }
                                                    }, 3500);
                                                }
                                                else if(map ==2)
                                                {
                                                    handler.postDelayed(new Runnable() {
                                                        @Override
                                                        public void run() {
                                                            Intent intent = new Intent(battle2.this, map2.class);
                                                            intent.putExtra("currentpos", currentposition);
                                                            intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                                                            startActivity(intent);


                                                        }
                                                    }, 3500);
                                                }
                                                else if(map ==3)
                                                {
                                                    handler.postDelayed(new Runnable() {
                                                        @Override
                                                        public void run() {
                                                            Intent intent = new Intent(battle2.this, map3.class);
                                                            intent.putExtra("currentpos", currentposition);
                                                            intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                                                            startActivity(intent);


                                                        }
                                                    }, 3500);
                                                }


                                            }
                                        }, 800);



                                    }
                                    else
                                    {
                                        handler.postDelayed(new Runnable() {
                                            @Override
                                            public void run() {
                                                enemy2.setBackground(getResources().getDrawable(R.drawable.d_normal));
                                                enemy1.setBackground(getResources().getDrawable(R.drawable.r_normal));
                                                playerScore.setText("HP : " + Integer.toString(playerHP));
                                                turn.setText("Player turn");
                                                Animation animationmove4 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.move4);
                                                turn.startAnimation(animationmove4);
                                                roundNumber.startAnimation(animationmove4);

                                                die1.setText(" ");
                                                die2.setText(" ");

                                            }
                                        }, 1800);
                                    }


                                }
                            }, 2000);


                        }


                    }
                }, 600);






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