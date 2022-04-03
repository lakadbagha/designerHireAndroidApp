package com.example.sdd;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.example.sdd.adapter.BillingAdapter;
import com.example.sdd.adapter.HireDesignerAdapter;
import com.example.sdd.dto.BillsDTO;
import com.example.sdd.dto.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class HireDesigner extends AppCompatActivity {
    private HireDesignerAdapter hireDesignerAdapter;
    private List<User> list;
    private DatabaseReference mFirebaseRef;
    private static final String TAG = HireDesigner.class.getName();
    private RecyclerView mRecyclerView;
    private ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hire_designer);
        mRecyclerView = findViewById(R.id.rvHire);
        list = new ArrayList<>();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        hireDesignerAdapter = new HireDesignerAdapter(list, this);
        mRecyclerView.setAdapter(hireDesignerAdapter);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        mFirebaseRef = database.getReference("Users");
        back = findViewById(R.id.back);

        mFirebaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot != null && dataSnapshot.getValue() != null) {
                    for (DataSnapshot child : dataSnapshot.getChildren()) {
                        try {
                            User model = child.getValue(User.class);
                            if (model.getUserType().equals("Designer")) {
                                list.add(model);
                                mRecyclerView.scrollToPosition(list.size() - 1);
                                hireDesignerAdapter.notifyItemInserted(list.size() - 1);
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
        back.setOnClickListener(v -> finish());
    }


}