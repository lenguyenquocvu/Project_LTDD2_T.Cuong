package com.example.quanlydiemsinhvien.Nganh;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quanlydiemsinhvien.R;

import java.util.Vector;

public class NganhAdapter extends RecyclerView.Adapter<NganhAdapter.NganhViewHolder> {
    private Vector<Nganh> listCarNganh;

    public NganhAdapter(Vector<Nganh> listCarNganh) {
        this.listCarNganh = listCarNganh;
    }

    static class NganhViewHolder extends RecyclerView.ViewHolder {
        TextView tvMaNganh;
        TextView tvTenNganh;

        public NganhViewHolder(View itemView) {
            super(itemView);
            tvMaNganh = itemView.findViewById(R.id.tvMaNganh);
            tvTenNganh = itemView.findViewById(R.id.tvTenNganh);
        }
    }

    @NonNull
    @Override
    public NganhViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CardView cardView;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        cardView = (CardView) inflater.inflate(R.layout.item_nganh_recycleview_layout, parent, false);
        return new NganhViewHolder(cardView);
    }

    @Override
    public void onBindViewHolder(@NonNull NganhViewHolder holder, int position) {
        Nganh theNganh = listCarNganh.get(position);
        holder.tvMaNganh.setText(theNganh.getMaNganh());
        holder.tvTenNganh.setText(theNganh.getTenNganh());
    }

    @Override
    public int getItemCount() {
        return listCarNganh.size();
    }


}
