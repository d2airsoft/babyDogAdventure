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


public class map3 extends Activity
{
    private FrameLayout die1, die2,
            position;
    private Button roll, hold;

    private boolean changeplayer = false;
    private int currentposition =0;
    private int movement=0;
    private int mcount = 0;
    private int remainstep;
    MediaPlayer mymusic, babyturn;
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
        setContentView(R.layout.map3);
        mymusic = MediaPlayer.create(this,R.raw.monopoly);
        mymusic.setVolume(0.3f,0.3f);
        mymusic.start();
        babyturn = MediaPlayer.create(this,R.raw.babyturn);
        Intent intent = getIntent();
        remainstep = intent.getIntExtra("stepleft", 0);
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

                        if (mcount<movement)
                        {
                            updateposition(position);
                            setposition(position);
                            if(currentposition==21)
                            {
                                Intent intent = new Intent(map3.this,map.class);
                                intent.putExtra("stepleft", remainstep);
                                intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                                startActivity(intent);

                            }
                            mcount++;
                            handler.postDelayed(this, 500);

                        }


                    }
                }, 500);




            }
        });
        die1 = (FrameLayout) findViewById(R.id.dice31);
        position = (FrameLayout) findViewById(R.id.stand_311);

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
        if (movement>=21)
        {
            remainstep = movement-20;

        }
        else
        {
            remainstep =0;
        }
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
        if(currentposition==21)
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
        currentposition = currentposition%22;
        if (currentposition ==0)
        {
            position = (FrameLayout) findViewById(R.id.stand_311);
        }
        else if(currentposition==1)
        {
            position = (FrameLayout) findViewById(R.id.stand_312);
        }
        else if(currentposition==2)
        {
            position = (FrameLayout) findViewById(R.id.stand_313);
        }
        else if(currentposition==3)
        {
            position = (FrameLayout) findViewById(R.id.stand_323);
        }
        else if(currentposition==4)
        {
            position = (FrameLayout) findViewById(R.id.stand_333);
        }
        else if(currentposition==5)
        {
            position = (FrameLayout) findViewById(R.id.stand_332);
        }
        else if(currentposition==6)
        {
            position = (FrameLayout) findViewById(R.id.stand_331);
        }
        else if(currentposition==7)
        {
            position = (FrameLayout) findViewById(R.id.stand_341);
        }
        else if(currentposition==8)
        {
            position = (FrameLayout) findViewById(R.id.stand_351);
        }
        else if(currentposition==9)
        {
            position = (FrameLayout) findViewById(R.id.stand_352);
        }
        else if(currentposition==10)
        {
            position = (FrameLayout) findViewById(R.id.stand_353);
        }
        else if(currentposition==11)
        {
            position = (FrameLayout) findViewById(R.id.stand_354);
        }
        else if(currentposition==12)
        {
            position = (FrameLayout) findViewById(R.id.stand_355);
        }
        else if(currentposition==13)
        {
            position = (FrameLayout) findViewById(R.id.stand_356);
        }
        else if(currentposition==14)
        {
            position = (FrameLayout) findViewById(R.id.stand_346);
        }
        else if(currentposition==15)
        {
            position = (FrameLayout) findViewById(R.id.stand_336);
        }
        else if(currentposition==16)
        {
            position = (FrameLayout) findViewById(R.id.stand_335);
        }

        else if(currentposition==17)
        {
            position = (FrameLayout) findViewById(R.id.stand_334);
        }
        else if(currentposition==18)
        {
            position = (FrameLayout) findViewById(R.id.stand_324);
        }
        else if(currentposition==19)
        {
            position = (FrameLayout) findViewById(R.id.stand_314);
        }

        else if(currentposition==20)
        {
            position = (FrameLayout) findViewById(R.id.stand_315);
        }
        else if(currentposition==21)
        {
            position = (FrameLayout) findViewById(R.id.stand_316);
        }



    }




}
