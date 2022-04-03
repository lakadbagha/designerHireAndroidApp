package com.example.sdd;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.PatternMatcher;
import android.provider.MediaStore;
import android.util.Patterns;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sdd.dao.UserDao;
import com.example.sdd.dto.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.util.regex.Pattern;

public class Register extends AppCompatActivity {

    private TextView frgt;
    private FirebaseAuth mAuth;
    private EditText name, email, password, phone;
    private ProgressBar progressBar;
    private Button register;
    private RadioGroup radioGroup;
    private Spinner dropdown;
    private String category = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        frgt = findViewById(R.id.frgt);
        frgt.setOnClickListener(v -> {
            Intent intent = new Intent(Register.this, Login.class);
            startActivity(intent);
        });
        mAuth = FirebaseAuth.getInstance();
        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        phone = findViewById(R.id.phone);
        progressBar = findViewById(R.id.progressBar);
        register = findViewById(R.id.register);
        radioGroup = findViewById(R.id.radio);
        dropdown = findViewById(R.id.spinner1);
        String[] items = new String[]{"bedrooms", "kitchen", "hotel", "restaurants", "offices", "drawingrooms", "lounge", "appartments", "wardrobe", "others"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        dropdown.setAdapter(adapter);
        radioGroup.setOnCheckedChangeListener((group, checkedId) -> {
            RadioButton radio = findViewById(checkedId);
            if (radio.getText().toString().trim().equals("Designer")) {
                dropdown.setVisibility(View.VISIBLE);
            } else {
                dropdown.setVisibility(View.GONE);
            }
        });
        dropdown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                category = parent.getItemAtPosition(position).toString().trim();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    // validate input field
    public void regiascus(View view) {

        String name1 = name.getText().toString().trim();
        String email1 = email.getText().toString().trim();
        String pass = password.getText().toString().trim();
        String phoneNumber = phone.getText().toString().trim();
        int selectedId = radioGroup.getCheckedRadioButtonId();
        RadioButton radio = findViewById(selectedId);
        String userType = radio.getText().toString().trim();
        if (name1.isEmpty()) {
            name.setError("Please enter valid name");
            name.requestFocus();
            return;
        }
        if (email1.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email1).matches()) {
            email.setError("Please enter a valid email");
            email.requestFocus();
            return;
        }
        if (pass.isEmpty() || pass.length() < 6) {
            password.setError("Please enter a valid password");
            password.requestFocus();
            return;
        }
        if (phoneNumber.isEmpty() || !Patterns.PHONE.matcher(phoneNumber).matches()) {
            phone.setError("Please enter a valid phone number");
            phone.requestFocus();
            return;
        }
        if (userType.equals("Designer")) {
            if (category.isEmpty() || category.equals("")) {
                Toast.makeText(Register.this, "Please select category", Toast.LENGTH_LONG).show();
                dropdown.requestFocus();
                return;
            }
        }
        register.setVisibility(View.GONE);
        progressBar.setVisibility(View.VISIBLE);
        mAuth.createUserWithEmailAndPassword(email1, pass)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        User user = new User(name1, pass, email1, phoneNumber, userType, category);
                        UserDao userDao = UserDao.getInstance();
                        userDao.add(user).addOnCompleteListener(task1 -> {
                            if (task1.isSuccessful()) {
                                progressBar.setVisibility(View.GONE);
                                register.setVisibility(View.VISIBLE);
                                mAuth.signOut();
                                Toast.makeText(Register.this, "Welcome to our app", Toast.LENGTH_LONG).show();
                                Intent i = new Intent(this, Login.class);
                                startActivity(i);
                                finish();
                            } else {
                                progressBar.setVisibility(View.GONE);
                                register.setVisibility(View.VISIBLE);
                                Toast.makeText(Register.this, "Failed to register please try again.", Toast.LENGTH_LONG).show();
                            }
                        });
                    } else {
                        progressBar.setVisibility(View.GONE);
                        register.setVisibility(View.VISIBLE);
                        System.out.println(task.getException());
                        Toast.makeText(Register.this, "Failed to register please try again", Toast.LENGTH_LONG).show();
                    }
                });
    }

    public void LoginUser(View view) {
        Intent i = new Intent(this, Login.class);
        startActivity(i);
        finish();
    }

}