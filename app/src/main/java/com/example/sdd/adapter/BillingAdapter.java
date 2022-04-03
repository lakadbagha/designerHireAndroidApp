package com.example.sdd.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sdd.R;
import com.example.sdd.dto.BillsDTO;

import java.util.List;

public class BillingAdapter extends RecyclerView.Adapter<BillingAdapter.MyViewHolder> {
    private List<BillsDTO> list;

    public BillingAdapter(List<BillsDTO> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public BillingAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.billing_info, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull BillingAdapter.MyViewHolder holder, int position) {
        BillsDTO billDTO = list.get(position);
        holder.bill.setText("Amount: "+billDTO.getAmount());
        holder.email.setText("Email: "+billDTO.getEmail());
        holder.date.setText("Due Date: "+billDTO.getDueDate());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView email, bill, date;

        MyViewHolder(View v) {
            super(v);
            email = itemView.findViewById(R.id.email);
            bill = itemView.findViewById(R.id.bill);
            date = itemView.findViewById(R.id.date);
        }
    }
}
