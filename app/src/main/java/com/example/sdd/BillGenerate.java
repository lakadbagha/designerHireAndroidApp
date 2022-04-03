package com.example.sdd;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sdd.dto.BillsDTO;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

public class BillGenerate extends AppCompatActivity {
    private EditText enterBill;
    private Button submit;
    private TextView enterDate;
    private int mYear, mMonth, mDay;
    private DatabaseReference mFirebaseRef;
    private String email;
    private ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill_generate);
        submit = findViewById(R.id.submit);
        enterBill = findViewById(R.id.enterBill);
        enterDate = findViewById(R.id.enterDate);
        enterDate.setOnClickListener(v -> {
            final Calendar c = Calendar.getInstance();
            mYear = c.get(Calendar.YEAR);
            mMonth = c.get(Calendar.MONTH);
            mDay = c.get(Calendar.DAY_OF_MONTH);
            DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                    (view, year, monthOfYear, dayOfMonth) ->
                            enterDate.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year), mYear, mMonth, mDay);
            datePickerDialog.show();
        });
        email = FirebaseAuth.getInstance().getCurrentUser().getEmail();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        mFirebaseRef = database.getReference("Bills");
        submit.setOnClickListener(v -> {
            String date = enterDate.getText().toString().trim();
            if (enterBill.getText().toString().trim().isEmpty()) {
                enterBill.setError("Please enter a valid amount");
                enterBill.requestFocus();
                return;
            }
            String amount = enterBill.getText().toString().trim();
            if (date.isEmpty()) {
                enterDate.setError("Please enter a valid date");
                enterDate.requestFocus();
                return;
            }
            mFirebaseRef.push().setValue(new BillsDTO(amount, date, email));
            enterDate.setText("");
            enterBill.setText("");
            Toast.makeText(BillGenerate.this, "Bill Generated successfully", Toast.LENGTH_LONG).show();
        });
        back= findViewById(R.id.back);
        back.setOnClickListener(v -> finish());
    }


}