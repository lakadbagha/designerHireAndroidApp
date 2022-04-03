package com.example.sdd;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.sdd.dao.UserDao;
import com.example.sdd.dto.User;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class EditProfile extends AppCompatActivity {


    private Spinner dropdown;
    private String category = "";
    private FirebaseUser fUser;
    private DatabaseReference reference;
    private String userID;
    private TextInputEditText name, email, phoneNumber;
    private ProgressBar progressBar;
    private LinearLayout showProfile;
    private ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        phoneNumber = findViewById(R.id.phone);
        progressBar = findViewById(R.id.progressBar);
        showProfile = findViewById(R.id.showProfile);
        back = findViewById(R.id.back);
        dropdown = findViewById(R.id.spinner1);
        fUser = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("Users");
        userID = fUser.getUid();
        String[] items = new String[]{"bedrooms", "kitchen", "hotel", "restaurants", "offices", "drawingrooms", "lounge", "appartments", "wardrobe", "others"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        dropdown.setAdapter(adapter);
        dropdown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                category = parent.getItemAtPosition(position).toString().trim();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        if (UserDao.getInstance().getUser() != null) {
            name.setText(UserDao.getInstance().getUser().getName());
            email.setText(UserDao.getInstance().getUser().getEmail());
            phoneNumber.setText(UserDao.getInstance().getUser().getPhoneNumber());
            progressBar.setVisibility(View.GONE);
            showProfile.setVisibility(View.VISIBLE);
            if (UserDao.getInstance().getUser().getCategory() != null) {
                int spinnerPosition = adapter.getPosition(UserDao.getInstance().getUser().getCategory());
                dropdown.setSelection(spinnerPosition);
            }
        } else {
            reference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    User user = snapshot.getValue(User.class);
                    if (user != null) {
                        UserDao.getInstance().setUser(user);
                        name.setText(user.getName());
                        email.setText(user.getEmail());
                        phoneNumber.setText(user.getPhoneNumber());
                        progressBar.setVisibility(View.GONE);
                        showProfile.setVisibility(View.VISIBLE);
                        if (user.getCategory() != null) {
                            int spinnerPosition = adapter.getPosition(user.getCategory());
                            dropdown.setSelection(spinnerPosition);
                        }
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Toast.makeText(EditProfile.this, "Something went wrong!", Toast.LENGTH_LONG).show();
                }
            });
        }
        back.setOnClickListener(v -> finish());
    }


    public void savebtn(View view) {
        UserDao userDao = UserDao.getInstance();
        HashMap<String, Object> map = new HashMap<>();
        String nam = name.getText().toString().trim();
        String mail = email.getText().toString().trim();
        String phoneNo = phoneNumber.getText().toString().trim();
        if (nam.isEmpty()) {
            name.setError("Please enter valid name");
            name.requestFocus();
            return;
        }
        if (mail.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(mail).matches()) {
            email.setError("Please enter a valid email");
            email.requestFocus();
            return;
        }
        if (phoneNo.isEmpty() || !Patterns.PHONE.matcher(phoneNo).matches()) {
            phoneNumber.setError("Please enter a valid phone number");
            phoneNumber.requestFocus();
            return;
        }
        map.put("name", nam);
        map.put("email", mail);
        map.put("phoneNumber", phoneNo);
        map.put("category", category);
        userDao.update(userID, map).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                Toast.makeText(EditProfile.this, "Account updated successfully", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(EditProfile.this, "Something went wrong! Please try again.", Toast.LENGTH_LONG).show();
            }
        });
    }


}