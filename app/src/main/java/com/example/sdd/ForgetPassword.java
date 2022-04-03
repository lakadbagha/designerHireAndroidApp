package com.example.sdd;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ForgetPassword extends AppCompatActivity {

    private EditText email;
    private ProgressBar progressBar;
    private FirebaseAuth auth;
    private Button frgtPwsd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);
        email = findViewById(R.id.email);
        progressBar = findViewById(R.id.progressBar);
        auth = FirebaseAuth.getInstance();
        frgtPwsd = findViewById(R.id.frgtPwsd);
        frgtPwsd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetPassword();
            }
        });
    }

    private void resetPassword() {
        String email1 = email.getText().toString().trim();
        if (email1.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email1).matches()) {
            email.setError("Please enter valid email");
            email.requestFocus();
            return;
        }
        frgtPwsd.setVisibility(View.GONE);
        progressBar.setVisibility(View.VISIBLE);
        auth.sendPasswordResetEmail(email1).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(ForgetPassword.this, "Reset link sent on email. Check your email.", Toast.LENGTH_LONG).show();
                } else {
                    System.out.println(task.getException());
                    Toast.makeText(ForgetPassword.this, "Oops! something went wrong.", Toast.LENGTH_LONG).show();
                }
                frgtPwsd.setVisibility(View.VISIBLE);
                progressBar.setVisibility(View.GONE);
            }
        });
    }

    public void registerUser(View view) {
        Intent i = new Intent(this, Register.class);
        startActivity(i);
        finish();
    }

    public void LoginUser(View view) {
        Intent i = new Intent(this, Login.class);
        startActivity(i);
        finish();
    }
}