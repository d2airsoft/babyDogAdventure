package com.mypussy.babydogadventure;

/**
 * Created by ray on 6/10/2015.
 */
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


public class battle4 extends ActionBarActivity {
    private FrameLayout die1, die2;
    private Button card1, card2, card3 ;
    private int check;
    int count=0;
    MediaPlayer battle1music,babydoghurt,babydogattack,enemyattack,enemyattack2,ddraw,jdraw,goodcard, recard;
    boolean check1=true;
    boolean check2=true;
    boolean check3=true;
    private int map,currentposition;

    @Override
    protected void onPause() {
        super.onPause();
        battle1music.release();
    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_battle4);
        goodcard = MediaPlayer.create(this,R.raw.up);
        recard = MediaPlayer.create(this,R.raw.boo);

        battle1music = MediaPlayer.create(this, R.raw.battle2);
        battle1music.setVolume(0.6f, 0.6f);
        battle1music.start();
        babydogattack = MediaPlayer.create(this,R.raw.babydogattack);
        babydoghurt = MediaPlayer.create(this,R.raw.babydoghurt);
        ddraw = MediaPlayer.create(this,R.raw.ddraw);
        jdraw = MediaPlayer.create(this, R.raw.jdraw);

        Intent intent = getIntent();
        currentposition = intent.getIntExtra("currentpos", 0);
        map = intent.getIntExtra("map", 0);

        enemyattack = MediaPlayer.create(this, R.raw.gunfire);
        enemyattack.setVolume(0.3f, 0.3f);
        enemyattack2 = MediaPlayer.create(this,R.raw.enemyattack2);
        card1 = (Button) findViewById(R.id.card1);
        card2 = (Button) findViewById(R.id.card2);
        card3 = (Button) findViewById(R.id.card3);
        Animation animationbattle2open = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.battle2open);
        card1.startAnimation(animationbattle2open);
        card2.startAnimation(animationbattle2open);
        card3.startAnimation(animationbattle2open);


        final int val = 1 + (int) (3 * Math.random());

        check =val;
        Log.i("Error", "Check  = " + check);


        card1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (check1) {
                    if (check != 1) {
                        goodcard.start();
                        card1.setBackground(getResources().getDrawable(R.drawable.d_simle));
                        count = count + 1;
                        final Handler handler = new Handler();
                        check1=false;
                        goodcard.start();
                        if (count >= 2) {
                            check1=false;
                            check2=false;
                            check3=false;


                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    babydogattack.start();
                                    card1.setBackground(getResources().getDrawable(R.drawable.d_attack));
                                    card2.setBackground(getResources().getDrawable(R.drawable.d_attack));
                                    card3.setBackground(getResources().getDrawable(R.drawable.d_attack));
                                    //intent
                                    if(map==1)
                                    {

                                        handler.postDelayed(new Runnable() {
                                            @Override
                                            public void run() {
                                                Intent intent = new Intent(battle4.this, map.class);
                                                intent.putExtra("currentpos", currentposition);
                                                intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                                                startActivity(intent);


                                            }
                                        }, 2000);

                                    }
                                    else if(map ==2)
                                    {
                                        handler.postDelayed(new Runnable() {
                                            @Override
                                            public void run() {
                                                Intent intent = new Intent(battle4.this, map2.class);
                                                intent.putExtra("currentpos", currentposition);
                                                intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                                                startActivity(intent);


                                            }
                                        }, 2000);
                                    }
                                    else if(map ==3)
                                    {
                                        handler.postDelayed(new Runnable() {
                                            @Override
                                            public void run() {
                                                Intent intent = new Intent(battle4.this, map3.class);
                                                intent.putExtra("currentpos", currentposition);
                                                intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                                                startActivity(intent);


                                            }
                                        }, 2000);
                                    }
                                }
                            }, 1500);
                        } else {
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    card1.setBackground(getResources().getDrawable(R.drawable.d_normal2));

                                }
                            }, 1500);
                        }
                    }//if
                    else {
                        final Handler handler = new Handler();
                        check1=false;
                        check2=false;
                        check3=false;
                        enemyattack.start();
                        card1.setBackground(getResources().getDrawable(R.drawable.dogattack));
                        card2.setBackground(getResources().getDrawable(R.drawable.dogattack));
                        card3.setBackground(getResources().getDrawable(R.drawable.dogattack));
                        //intent
                        if(map==1)
                        {

                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    Intent intent = new Intent(battle4.this, map.class);
                                    intent.putExtra("currentpos", currentposition);
                                    intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                                    startActivity(intent);


                                }
                            }, 2000);

                        }
                        else if(map ==2)
                        {
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    Intent intent = new Intent(battle4.this, map2.class);
                                    intent.putExtra("currentpos", currentposition);
                                    intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                                    startActivity(intent);


                                }
                            }, 2000);
                        }
                        else if(map ==3)
                        {
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    Intent intent = new Intent(battle4.this, map3.class);
                                    intent.putExtra("currentpos", currentposition);
                                    intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                                    startActivity(intent);


                                }
                            }, 2000);
                        }
                    }

                } // check1

                else {
                    recard.start();
                }

            }
        });  /// card1 closed

        card2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (check2) {
                    if (check != 2) {

                        goodcard.start();
                        card2.setBackground(getResources().getDrawable(R.drawable.d_simle));
                        count = count + 1;
                        check2=false;
                        final Handler handler = new Handler();

                        if (count >= 2) {
                            check1=false;
                            check2=false;
                            check3=false;

                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    babydogattack.start();
                                    card1.setBackground(getResources().getDrawable(R.drawable.d_attack));
                                    card2.setBackground(getResources().getDrawable(R.drawable.d_attack));
                                    card3.setBackground(getResources().getDrawable(R.drawable.d_attack));
                                    final Handler handler = new Handler();
                                    //intent
                                    if(map==1)
                                    {

                                        handler.postDelayed(new Runnable() {
                                            @Override
                                            public void run() {
                                                Intent intent = new Intent(battle4.this, map.class);
                                                intent.putExtra("currentpos", currentposition);
                                                intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                                                startActivity(intent);


                                            }
                                        }, 2000);

                                    }
                                    else if(map ==2)
                                    {
                                        handler.postDelayed(new Runnable() {
                                            @Override
                                            public void run() {
                                                Intent intent = new Intent(battle4.this, map2.class);
                                                intent.putExtra("currentpos", currentposition);
                                                intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                                                startActivity(intent);


                                            }
                                        }, 2000);
                                    }
                                    else if(map ==3)
                                    {
                                        handler.postDelayed(new Runnable() {
                                            @Override
                                            public void run() {
                                                Intent intent = new Intent(battle4.this, map3.class);
                                                intent.putExtra("currentpos", currentposition);
                                                intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                                                startActivity(intent);


                                            }
                                        }, 2000);
                                    }
                                }
                            }, 1500);


                        } else {
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    card2.setBackground(getResources().getDrawable(R.drawable.d_normal2));

                                }
                            }, 1500);


                        }
                    }//if
                    else {
                        check1=false;
                        check2=false;
                        check3=false;
                        enemyattack.start();
                        card1.setBackground(getResources().getDrawable(R.drawable.dogattack));
                        card2.setBackground(getResources().getDrawable(R.drawable.dogattack));
                        card3.setBackground(getResources().getDrawable(R.drawable.dogattack));
                        final Handler handler = new Handler();

                        //intent
                        if(map==1)
                        {

                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    Intent intent = new Intent(battle4.this, map.class);
                                    intent.putExtra("currentpos", currentposition);
                                    intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                                    startActivity(intent);


                                }
                            }, 2000);

                        }
                        else if(map ==2)
                        {
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    Intent intent = new Intent(battle4.this, map2.class);
                                    intent.putExtra("currentpos", currentposition);
                                    intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                                    startActivity(intent);


                                }
                            }, 2000);
                        }
                        else if(map ==3)
                        {
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    Intent intent = new Intent(battle4.this, map3.class);
                                    intent.putExtra("currentpos", currentposition);
                                    intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                                    startActivity(intent);


                                }
                            }, 2000);
                        }
                    }

                }
                else {

                    recard.start();

                }



            }
        });
        card3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (check3) {
                    if (check != 3) {
                        goodcard.start();
                        card3.setBackground(getResources().getDrawable(R.drawable.d_simle));
                        count = count + 1;
                        final Handler handler = new Handler();
                        check3=false;

                        if (count >= 2) {
                            check1=false;
                            check2=false;
                            check3=false;

                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    babydogattack.start();
                                    card1.setBackground(getResources().getDrawable(R.drawable.d_attack));
                                    card2.setBackground(getResources().getDrawable(R.drawable.d_attack));
                                    card3.setBackground(getResources().getDrawable(R.drawable.d_attack));
                                    final Handler handler = new Handler();
                                    //intent
                                    if(map==1)
                                    {

                                        handler.postDelayed(new Runnable() {
                                            @Override
                                            public void run() {
                                                Intent intent = new Intent(battle4.this, map.class);
                                                intent.putExtra("currentpos", currentposition);
                                                intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                                                startActivity(intent);


                                            }
                                        }, 2000);

                                    }
                                    else if(map ==2)
                                    {
                                        handler.postDelayed(new Runnable() {
                                            @Override
                                            public void run() {
                                                Intent intent = new Intent(battle4.this, map2.class);
                                                intent.putExtra("currentpos", currentposition);
                                                intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                                                startActivity(intent);


                                            }
                                        }, 2000);
                                    }
                                    else if(map ==3)
                                    {
                                        handler.postDelayed(new Runnable() {
                                            @Override
                                            public void run() {
                                                Intent intent = new Intent(battle4.this, map3.class);
                                                intent.putExtra("currentpos", currentposition);
                                                intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                                                startActivity(intent);


                                            }
                                        }, 2000);
                                    }
                                }
                            }, 1500);
                        } else {
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    card3.setBackground(getResources().getDrawable(R.drawable.d_normal2));

                                }
                            }, 1000);
                        }
                    } //if
                    else {
                        check1=false;
                        check2=false;
                        check3=false;
                        enemyattack.start();
                        card1.setBackground(getResources().getDrawable(R.drawable.dogattack));
                        card2.setBackground(getResources().getDrawable(R.drawable.dogattack));
                        card3.setBackground(getResources().getDrawable(R.drawable.dogattack));
                        final Handler handler = new Handler();
                        //intent
                        if(map==1)
                        {

                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    Intent intent = new Intent(battle4.this, map.class);
                                    intent.putExtra("currentpos", currentposition);
                                    intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                                    startActivity(intent);


                                }
                            }, 2000);

                        }
                        else if(map ==2)
                        {
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    Intent intent = new Intent(battle4.this, map2.class);
                                    intent.putExtra("currentpos", currentposition);
                                    intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                                    startActivity(intent);


                                }
                            }, 2000);
                        }
                        else if(map ==3)
                        {
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    Intent intent = new Intent(battle4.this, map3.class);
                                    intent.putExtra("currentpos", currentposition);
                                    intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                                    startActivity(intent);


                                }
                            }, 2000);
                        }
                    }


                }

                else {
                    recard.start();

                }




            }
        });
    }

    //get two random ints between 1 and 6 inclusive


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