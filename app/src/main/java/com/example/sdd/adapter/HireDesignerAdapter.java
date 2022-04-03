package com.example.sdd.adapter;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sdd.Designercustomerview;
import com.example.sdd.R;
import com.example.sdd.dto.User;

import java.util.List;

public class HireDesignerAdapter extends RecyclerView.Adapter<HireDesignerAdapter.MyViewHolder> {
    private List<User> users;
    private Activity activity;

    public HireDesignerAdapter(List<User> users, Activity activity) {
        this.users = users;
        this.activity = activity;
    }

    @NonNull
    @Override
    public HireDesignerAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new HireDesignerAdapter.MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.hired_me, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull HireDesignerAdapter.MyViewHolder holder, int position) {
        User user = users.get(position);
        holder.email.setText(user.getEmail());
        holder.name.setText(user.getName());
        holder.phoneno.setText(user.getPhoneNumber());
        holder.hire.setOnClickListener(v -> {
            Intent i = new Intent(activity, Designercustomerview.class);
            i.putExtra("useremail", user.getEmail());
            activity.startActivity(i);
        });
    }

    @Override
    public int getItemCount() {
        return users.size();
    }


    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView email, name, phoneno;
        private Button hire;

        MyViewHolder(View v) {
            super(v);
            email = itemView.findViewById(R.id.email);
            name = itemView.findViewById(R.id.name);
            phoneno = itemView.findViewById(R.id.phoneno);
            hire = itemView.findViewById(R.id.hire);
        }
    }
}
