package com.example.sdd;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class HomeFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        Button viewprofile = (Button) view.findViewById(R.id.view_hire);
        viewprofile.setOnClickListener(v -> designerActivity());
        Button view_hire2 = view.findViewById(R.id.view_hire2);
        view_hire2.setOnClickListener(v -> designerActivity());
        return view;
    }

    public void designerActivity() {
        Intent i = new Intent(getActivity(), Designercustomerview.class);
        startActivity(i);
    }
}