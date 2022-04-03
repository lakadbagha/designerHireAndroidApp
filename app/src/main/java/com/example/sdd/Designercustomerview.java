package com.example.sdd;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sdd.dto.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Designercustomerview extends AppCompatActivity {
    private String email;
    private DatabaseReference mFirebaseRef;
    private static final String TAG = Designercustomerview.class.getName();
    private TextView profilename, designer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_designercustomerview);
        Bundle bundle = getIntent().getExtras();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        mFirebaseRef = database.getReference("Users");
        profilename = findViewById(R.id.profilename);
        designer = findViewById(R.id.designer);
        if (bundle != null) {
            email = bundle.getString("useremail");
            System.out.println("email "+email);
            mFirebaseRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    if (dataSnapshot != null && dataSnapshot.getValue() != null) {
                        for (DataSnapshot child : dataSnapshot.getChildren()) {
                            try {
                                User model = child.getValue(User.class);
                                if (model.getEmail().equals(email)) {
                                    designer.setText("phoneNumber: " + model.getPhoneNumber());
                                    profilename.setText("name: "+model.getName());
                                }
                            } catch (Exception ex) {
                                Log.e(TAG, ex.getMessage());
                            }
                        }
                    } else {
                        System.out.println("oops error occur");
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }
    }

    public void showChats(View view){
        startActivity(new Intent(this,ChatActivity.class));
    }

    public void showFollowSuccess(View view){
        Toast.makeText(this,"Great! you are successfully following now.",Toast.LENGTH_LONG).show();
    }
}