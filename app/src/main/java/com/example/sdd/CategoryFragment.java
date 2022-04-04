package com.example.sdd;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;


public class CategoryFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_category, container, false);
        Button cat1 = view.findViewById(R.id.cat1);
        Button cat2 = view.findViewById(R.id.cat2);
        Button cat3 = view.findViewById(R.id.cat3);
        Button cat4 = view.findViewById(R.id.cat4);
        Button cat5 = view.findViewById(R.id.cat5);
        Button cat6 = view.findViewById(R.id.cat6);
        Button cat7 = view.findViewById(R.id.cat7);
        Button cat8 = view.findViewById(R.id.cat8);
        Button cat9 = view.findViewById(R.id.cat9);
        Button cat10 = view.findViewById(R.id.cat10);
        cat1.setOnClickListener(v -> viewCategory(v));
        cat2.setOnClickListener(v -> viewCategory(v));
        cat3.setOnClickListener(v -> viewCategory(v));
        cat4.setOnClickListener(v -> viewCategory(v));
        cat5.setOnClickListener(v -> viewCategory(v));
        cat6.setOnClickListener(v -> viewCategory(v));
        cat7.setOnClickListener(v -> viewCategory(v));
        cat8.setOnClickListener(v -> viewCategory(v));
        cat9.setOnClickListener(v -> viewCategory(v));
        cat10.setOnClickListener(v -> viewCategory(v));
        return view;
    }

    public void viewCategory(View v) {
        Intent i = new Intent(getActivity(), HireDesigner.class);
        startActivity(i);
    }
}