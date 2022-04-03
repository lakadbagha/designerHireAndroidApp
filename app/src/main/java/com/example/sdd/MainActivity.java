package com.example.sdd;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void loginmain(View view) {
        Intent i = new Intent(this, Login.class);
        startActivity(i);
        finish();
    }


    public void registermain(View view) {
        Intent i = new Intent(this, Register.class);
        startActivity(i);
        finish();
    }
}