package com.example.quanlydiemsinhvien.Khoa;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quanlydiemsinhvien.R;

import java.util.Vector;

public class KhoaAdapter extends RecyclerView.Adapter<KhoaAdapter.KhoaViewHolder> {
    private Vector<Khoa> listCarKhoa;

    public KhoaAdapter(Vector<Khoa> listCarKhoa) {
        this.listCarKhoa = listCarKhoa;
    }

    static class KhoaViewHolder extends RecyclerView.ViewHolder {
        TextView tvTenKhoa;
        TextView tvMaKhoa;
        TextView tvNgayThanhLap;

        public KhoaViewHolder(View itemView) {
            super(itemView);
            tvMaKhoa = itemView.findViewById(R.id.tvMaKhoa);
            tvTenKhoa = itemView.findViewById(R.id.tvTenKhoa);
            tvNgayThanhLap = itemView.findViewById(R.id.tvNgayThanhLap);
        }
    }

    @Override
    public KhoaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        CardView cardView;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        cardView = (CardView) inflater.inflate(R.layout.item_khoa_recyclerview_layout, parent, false);
        return new KhoaViewHolder(cardView);
    }

    @Override
    public void onBindViewHolder(@NonNull KhoaViewHolder holder, int position) {
        Khoa theKhoa = listCarKhoa.get(position);
        holder.tvMaKhoa.setText(theKhoa.getMaKhoa());
        holder.tvTenKhoa.setText(theKhoa.getTenKhoa());
        holder.tvNgayThanhLap.setText(theKhoa.getNgayThanhLap());
    }

    @Override
    public int getItemCount() {
        return listCarKhoa.size();
    }

}
