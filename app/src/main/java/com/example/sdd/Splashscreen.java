package com.example.sdd;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class Splashscreen extends AppCompatActivity {
    public ImageView Logo;
    public TextView title;
    Animation textanimation;
    Animation animate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);
        Logo =findViewById(R.id.logo);
        title =findViewById(R.id.appname);
        textanimation= AnimationUtils.loadAnimation(this,R.anim.textanimation );
        animate= AnimationUtils.loadAnimation(this,R.anim.animate );
        Logo.setAnimation(textanimation);
        title.setAnimation(animate);
        int SPLASH_SCREEN = 4300;
        new Handler().postDelayed(new Runnable()
        {


            @Override
            public void run()
            {

                Intent i= new Intent(Splashscreen.this, MainActivity.class);
                startActivity(i);
                finish();
            }
        },SPLASH_SCREEN);



    }
    }
