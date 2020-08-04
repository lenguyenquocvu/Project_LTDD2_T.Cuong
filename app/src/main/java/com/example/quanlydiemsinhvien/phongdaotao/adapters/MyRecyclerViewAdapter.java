package com.example.quanlydiemsinhvien.phongdaotao.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;

import com.example.quanlydiemsinhvien.R;
import com.example.quanlydiemsinhvien.phongdaotao.data_models.CardViewModel;

import java.util.Vector;

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.MyViewHolder> {
    private Vector<CardViewModel> list;

    public MyRecyclerViewAdapter(Vector<CardViewModel> list) {
        this.list = list;
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView textViewMaGV;
        TextView textViewTenGV;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewMaGV = itemView.findViewById(R.id.txtMaGV);
            textViewTenGV = itemView.findViewById(R.id.txtTenGV);
        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        CardView cardView;
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        cardView = (CardView) inflater.inflate(viewType, viewGroup, false);
        return new MyViewHolder(cardView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder viewHolder, int position) {
        CardViewModel aCard = list.get(position);
        viewHolder.textViewMaGV.setText(aCard.getId());
        viewHolder.textViewTenGV.setText(aCard.getName());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    //Doi layout, khong dung cho tu chuyen dong va nam ngang
    @Override
    public int getItemViewType(int position) {
        if (position % 2 == 0) {
            return R.layout.card_view_layout;
        } else {
            return R.layout.card_view_layout_inver;
        }
    }
}
