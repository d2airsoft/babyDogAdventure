package com.mypussy.rpggame;



import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;


public class map extends Activity
{
    private FrameLayout die1, die2,
            position;
    private Button roll, hold;

    private boolean changeplayer = false;
    private int currentposition;
    private int movement=0;
    private int mcount = 0;
    private int remainstep;
    MediaPlayer mymusic, babyturn;
    private boolean hitenemy =false, hitenemy2=false;

    @Override
    protected void onPause() {
        super.onPause();
        mymusic.release();
        babyturn.release();
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.map);
        mymusic = MediaPlayer.create(this,R.raw.monopoly);
        mymusic.setVolume(0.3f, 0.3f);
        mymusic.start();
        babyturn = MediaPlayer.create(this, R.raw.babyturn);



        Intent intent = getIntent();
        remainstep = intent.getIntExtra("stepleft", 0);
        currentposition =intent.getIntExtra("currentpos",0);
        if (currentposition ==0)
        {
            position = (FrameLayout) findViewById(R.id.stand_11);
        }
        else if(currentposition==1)
        {
            position = (FrameLayout) findViewById(R.id.stand_12);
        }
        else if(currentposition==2)
        {
            position = (FrameLayout) findViewById(R.id.stand_13);
        }
        else if(currentposition==3)
        {
            position = (FrameLayout) findViewById(R.id.stand_14);
        }
        else if(currentposition==4)
        {
            position = (FrameLayout) findViewById(R.id.stand_15);
        }
        else if(currentposition==5)
        {
            position = (FrameLayout) findViewById(R.id.stand_16);
        }
        else if(currentposition==6)
        {
            position = (FrameLayout) findViewById(R.id.stand_21);
        }
        else if(currentposition==7)
        {
            position = (FrameLayout) findViewById(R.id.stand_31);
        }
        else if(currentposition==8)
        {
            position = (FrameLayout) findViewById(R.id.stand_32);
        }
        else if(currentposition==9)
        {
            position = (FrameLayout) findViewById(R.id.stand_33);
        }
        else if(currentposition==10)
        {
            position = (FrameLayout) findViewById(R.id.stand_34);
        }
        else if(currentposition==11)
        {
            position = (FrameLayout) findViewById(R.id.stand_35);
        }
        else if(currentposition==12)
        {
            position = (FrameLayout) findViewById(R.id.stand_36);
        }
        else if(currentposition==13)
        {
            position = (FrameLayout) findViewById(R.id.stand_41);
        }
        else if(currentposition==14)
        {
            position = (FrameLayout) findViewById(R.id.stand_51);
        }
        else if(currentposition==15)
        {
            position = (FrameLayout) findViewById(R.id.stand_52);
        }
        else if(currentposition==16)
        {
            position = (FrameLayout) findViewById(R.id.stand_53);
        }

        else if(currentposition==17)
        {
            position = (FrameLayout) findViewById(R.id.stand_54);
        }
        else if(currentposition==18)
        {
            position = (FrameLayout) findViewById(R.id.stand_55);
        }
        else if(currentposition==19)
        {
            position = (FrameLayout) findViewById(R.id.stand_56);
        }

        setposition(position);
        Toast.makeText(this, "currentpos" + Integer.toString(currentposition), Toast.LENGTH_LONG).show();
        movement+=remainstep;



        roll = (Button) findViewById(R.id.button);
        roll.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                babyturn.start();
                rollDice();



                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        if (mcount < movement) {
                            updateposition(position);
                            setposition(position);
                            if (currentposition == 19) {
                                Intent intent = new Intent(map.this, map2.class);
                                intent.putExtra("stepleft", remainstep);
                                intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                                startActivity(intent);

                            }
                            mcount++;
                            handler.postDelayed(this, 500);
                            if (currentposition >=1 && currentposition<=6)
                            {
                                hitenemy = true;
                            }
                            if (currentposition >=7 && currentposition<=12)
                            {
                                hitenemy2 = true;
                            }


                        }
                        if (hitenemy==true && mcount>=movement)
                        {
                            Intent intent = new Intent(map.this, battle.class);
                            intent.putExtra("currentpos", currentposition);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                            startActivity(intent);
                        }
                        if (hitenemy2==true && mcount>=movement)
                        {
                            Intent intent = new Intent(map.this, battle2.class);
                            intent.putExtra("currentpos", currentposition);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                            startActivity(intent);
                        }



                    }
                }, 500);




            }
        });
        die1 = (FrameLayout) findViewById(R.id.dice1);
        position = (FrameLayout) findViewById(R.id.stand_31);
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (mcount<remainstep)
                {
                    updateposition(position);
                    setposition(position);
                    mcount++;
                    handler.postDelayed(this, 500);
                }

            }
        }, 500);

    }
    public void rollDice()
    {

        int val1 = 1 + (int) (6 * Math.random());
        setDie(val1, die1);
        Toast.makeText(this, "currentpos" + Integer.toString(currentposition), Toast.LENGTH_LONG).show();



    }
    public void setDie(int value, FrameLayout layout) {
        Drawable pic = null;
        switch (value) {
            case 1:
                pic = getResources().getDrawable(R.drawable.die_face_1);
                movement +=1;
                break;
            case 2:
                pic = getResources().getDrawable(R.drawable.die_face_2);
                movement +=2;
                break;
            case 3:
                pic = getResources().getDrawable(R.drawable.die_face_3);
                movement +=3;
                break;
            case 4:
                pic = getResources().getDrawable(R.drawable.die_face_4);
                movement +=4;
                break;
            case 5:
                pic = getResources().getDrawable(R.drawable.die_face_5);
                movement +=5;
                break;
            case 6:
                pic = getResources().getDrawable(R.drawable.die_face_6);
                movement +=6;
                break;
        }
        if (movement>=19)
        {
            remainstep = movement-18;

        }
        else
        {
            remainstep =0;
        }
        layout.setBackground(pic);
    }
    public void removecurrentfatboy(FrameLayout layout)
    {
        Drawable pic = null;
        pic = getResources().getDrawable(R.drawable.stand);
        layout.setBackground(pic);
    }
    public void setposition(FrameLayout layout)
    {
        Drawable pic = null;
        pic = getResources().getDrawable(R.drawable.fatboy);
        layout.setBackground(pic);

    }
    public void updateposition(FrameLayout layout)
    {

        if(currentposition==19)
        {
            Drawable pic = null;
            pic = getResources().getDrawable(R.drawable.next);
            layout.setBackground(pic);

        }
        else
        {
            Drawable pic = null;
            pic = getResources().getDrawable(R.drawable.stand);
            layout.setBackground(pic);
        }

        currentposition++;
        currentposition = currentposition%20;
        if (currentposition ==0)
        {
            position = (FrameLayout) findViewById(R.id.stand_11);
        }
        else if(currentposition==1)
        {
            position = (FrameLayout) findViewById(R.id.stand_12);
        }
        else if(currentposition==2)
        {
            position = (FrameLayout) findViewById(R.id.stand_13);
        }
        else if(currentposition==3)
        {
            position = (FrameLayout) findViewById(R.id.stand_14);
        }
        else if(currentposition==4)
        {
            position = (FrameLayout) findViewById(R.id.stand_15);
        }
        else if(currentposition==5)
        {
            position = (FrameLayout) findViewById(R.id.stand_16);
        }
        else if(currentposition==6)
        {
            position = (FrameLayout) findViewById(R.id.stand_21);
        }
        else if(currentposition==7)
        {
            position = (FrameLayout) findViewById(R.id.stand_31);
        }
        else if(currentposition==8)
        {
            position = (FrameLayout) findViewById(R.id.stand_32);
        }
        else if(currentposition==9)
        {
            position = (FrameLayout) findViewById(R.id.stand_33);
        }
        else if(currentposition==10)
        {
            position = (FrameLayout) findViewById(R.id.stand_34);
        }
        else if(currentposition==11)
        {
            position = (FrameLayout) findViewById(R.id.stand_35);
        }
        else if(currentposition==12)
        {
            position = (FrameLayout) findViewById(R.id.stand_36);
        }
        else if(currentposition==13)
        {
            position = (FrameLayout) findViewById(R.id.stand_41);
        }
        else if(currentposition==14)
        {
            position = (FrameLayout) findViewById(R.id.stand_51);
        }
        else if(currentposition==15)
        {
            position = (FrameLayout) findViewById(R.id.stand_52);
        }
        else if(currentposition==16)
        {
            position = (FrameLayout) findViewById(R.id.stand_53);
        }

        else if(currentposition==17)
        {
            position = (FrameLayout) findViewById(R.id.stand_54);
        }
        else if(currentposition==18)
        {
            position = (FrameLayout) findViewById(R.id.stand_55);
        }
        else if(currentposition==19)
        {
            position = (FrameLayout) findViewById(R.id.stand_56);
        }

    }




}
