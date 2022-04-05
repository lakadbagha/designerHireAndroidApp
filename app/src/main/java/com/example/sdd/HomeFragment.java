package com.example.sdd;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;


public class HomeFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        Button viewprofile = (Button) view.findViewById(R.id.view_hire);
        viewprofile.setOnClickListener(v -> designerActivity());
        Button view_hire2 = view.findViewById(R.id.view_hire2);
        view_hire2.setOnClickListener(v -> designerActivity());
        Button view_hire3= view.findViewById(R.id.view_hire3);
        view_hire3.setOnClickListener(v -> designerActivity());
        ImageView like= view.findViewById(R.id.like);
        ImageView like2= view.findViewById(R.id.like2);
        ImageView like3= view.findViewById(R.id.like3);
        like.setOnClickListener(v -> showMessage());
        like2.setOnClickListener(v -> showMessage());
        like3.setOnClickListener(v -> showMessage());
        return view;
    }

    public void designerActivity() {
        Intent i = new Intent(getActivity(), Designercustomerview.class);
        startActivity(i);
    }
    public void showMessage(){
        Toast.makeText(getActivity(),"Yaay! Liked",Toast.LENGTH_LONG).show();
    }
}