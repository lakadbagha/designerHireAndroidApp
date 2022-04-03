package com.example.sdd;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.example.sdd.adapter.BillingAdapter;
import com.example.sdd.dto.BillsDTO;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class BillingDetail extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private BillingAdapter billingAdapter;
    private DatabaseReference mFirebaseRef;
    private List<BillsDTO> list;
    private static final String TAG = BillingDetail.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_billing_detail);
        mRecyclerView = findViewById(R.id.rvBill);
        list = new ArrayList<>();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        billingAdapter = new BillingAdapter(list);
        mRecyclerView.setAdapter(billingAdapter);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        mFirebaseRef = database.getReference("Bills");

        mFirebaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot != null && dataSnapshot.getValue() != null) {
                    for (DataSnapshot child : dataSnapshot.getChildren()) {
                        try {
                            BillsDTO model = child.getValue(BillsDTO.class);
                            list.add(model);
                            mRecyclerView.scrollToPosition(list.size() - 1);
                            billingAdapter.notifyItemInserted(list.size() - 1);
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