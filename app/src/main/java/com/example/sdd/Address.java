package com.example.sdd;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.sdd.dto.AddressDTO;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;

public class Address extends AppCompatActivity {
    private ImageView back;
    private TextInputEditText address1, address2, city, state, country, pincode;
    private ProgressBar progressBar;
    private FirebaseUser fUser;
    private DatabaseReference reference;
    private String userID;
    private LinearLayout showAddress;
    public static AddressDTO addressDTO = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address);
        back = findViewById(R.id.back);
        pincode = findViewById(R.id.pincode);
        country = findViewById(R.id.country);
        state = findViewById(R.id.state);
        city = findViewById(R.id.city);
        address2 = findViewById(R.id.address2);
        address1 = findViewById(R.id.address1);
        showAddress = findViewById(R.id.showAddress);
        progressBar = findViewById(R.id.progressBar);
        back.setOnClickListener(v -> finish());
        if (addressDTO != null) {
            pincode.setText(addressDTO.getPincode());
            address1.setText(addressDTO.getAddress1());
            address2.setText(addressDTO.getAddress2());
            city.setText(addressDTO.getCity());
            state.setText(addressDTO.getState());
            country.setText(addressDTO.getCountry());
        }
    }

    public void saveAddress(View view) {
        addressDTO = new AddressDTO();
        addressDTO.setAddress1(address1.getText().toString().trim());
        addressDTO.setAddress2(address2.getText().toString().trim());
        addressDTO.setPincode(pincode.getText().toString().trim());
        addressDTO.setCity(city.getText().toString().trim());
        addressDTO.setState(state.getText().toString().trim());
        addressDTO.setCountry(country.getText().toString().trim());
        Toast.makeText(Address.this, "Saved", Toast.LENGTH_LONG).show();
    }
}