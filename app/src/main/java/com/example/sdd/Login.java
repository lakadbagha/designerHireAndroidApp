package com.example.sdd;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {

    private TextView signupp, frgt;
    private EditText email, password;
    private Button login;
    private RadioGroup radioGroup;
    private FirebaseAuth mAuth;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        signupp = findViewById(R.id.signupp);
        signupp.setOnClickListener(v -> {
            Intent intent = new Intent(Login.this, Register.class);
            startActivity(intent);
        });
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        login = findViewById(R.id.login);
        radioGroup = findViewById(R.id.radio);
        mAuth = FirebaseAuth.getInstance();
        progressBar = findViewById(R.id.progressBar);
        frgt = findViewById(R.id.frgt);
        login.setOnClickListener(v -> validateMyLogin());
    }

    private void validateMyLogin() {
        String email1 = email.getText().toString().trim();
        String pass = password.getText().toString().trim();
        int selectedId = radioGroup.getCheckedRadioButtonId();
        RadioButton radio = findViewById(selectedId);
        String userType = radio.getText().toString().trim();
        if (email1.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email1).matches()) {
            email.setError("Please enter a valid email");
            email.requestFocus();
            return;
        }
        if (pass.isEmpty() || pass.length() < 6) {
            password.setError("Please enter a valid Password");
            password.requestFocus();
            return;
        }
        login.setVisibility(View.GONE);
        progressBar.setVisibility(View.VISIBLE);
        mAuth.signInWithEmailAndPassword(email1, pass).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                if (userType.equals("Designer")) {
                    loginAsDesigner();
                } else {
                    customerLogin();
                }
            } else {
                progressBar.setVisibility(View.GONE);
                login.setVisibility(View.VISIBLE);
                Toast.makeText(Login.this, "Invalid credentials", Toast.LENGTH_LONG).show();
            }
        });
    }

    public void customerLogin() {
        Intent i = new Intent(this, HomeActivity.class);
        startActivity(i);
        finish();
    }


    public void loginAsDesigner() {
        Intent i = new Intent(this, Developerprofile.class);
        startActivity(i);
        finish();
    }

    public void resetPassword(View view){
        Intent i = new Intent(this, ForgetPassword.class);
        startActivity(i);
        finish();
    }

    public void registerUser(View view){
        Intent i = new Intent(this, Register.class);
        startActivity(i);
        finish();
    }
}