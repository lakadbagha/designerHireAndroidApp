package com.example.sdd;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;

public class Developerprofile extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_developerprofile);
    }

    public void editdesprof(View view)
    {
        Intent i= new Intent(this, EditProfile.class);
        startActivity(i);
    }

    public void viewdesprof(View view)
    {
        Intent i= new Intent(this, DesignerProfileView.class);
        startActivity(i);


    }

    public void btnbill(View view)
    {
        Intent i= new Intent(this, Billing.class);
        startActivity(i);

    }

    public void imgclick(View view)
    {
        Intent i= new Intent(this, ImageEditDelete.class);
        startActivity(i);
    }

    public void logout(View view){
        FirebaseAuth.getInstance().signOut();
        Intent l = new Intent(this, Login.class);
        startActivity(l);
        finish();
    }
}