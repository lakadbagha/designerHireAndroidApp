package com.example.sdd;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

public class Billing extends AppCompatActivity {
    private ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_billing);
        back = findViewById(R.id.back);
        back.setOnClickListener(v -> finish());
    }


    public void btnbill(View view) {
        Intent i = new Intent(this, Billing.class);
        startActivity(i);

    }

    public void genbill(View view) {
        Intent i = new Intent(this, BillGenerate.class);
        startActivity(i);

    }
}