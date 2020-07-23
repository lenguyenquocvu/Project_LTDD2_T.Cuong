package com.example.quanlydiemsinhvien.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quanlydiemsinhvien.R;
import com.example.quanlydiemsinhvien.data_models.KhoaHoc;

import java.util.ArrayList;

public class KhoaHocAdapter extends RecyclerView.Adapter<KhoaHocAdapter.KhoaHocViewHolder> {
    private ArrayList<KhoaHoc> dsKhoaHoc;
    private Context context;
    private AlertDialog.Builder myDialog;
    public KhoaHocAdapter(Context context, ArrayList<KhoaHoc> dsKhoaHoc) {
        this.dsKhoaHoc = dsKhoaHoc;
        this.context = context;
    }

    @NonNull
    @Override
    public KhoaHocViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.item_khoa_hoc_layout, parent, false);

        KhoaHocViewHolder khoaHocViewHolder = new KhoaHocViewHolder(itemView);


        return khoaHocViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull KhoaHocViewHolder holder, int position) {
        final KhoaHoc khoaHoc = dsKhoaHoc.get(position);
        holder.txtKhoaHoc.setText(khoaHoc.getMaKH());
        holder.txtKhoaHocInfo.setText(khoaHoc.getThoiGianKhoaHoc());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }


    @Override
    public int getItemCount() {
        return dsKhoaHoc.size();
    }


    public static class KhoaHocViewHolder extends RecyclerView.ViewHolder {
        protected TextView txtKhoaHoc;
        protected TextView txtKhoaHocInfo;

        public KhoaHocViewHolder(@NonNull View itemView) {
            super(itemView);
            txtKhoaHoc = (TextView) itemView.findViewById(R.id.txtKhoaKhoc);
            txtKhoaHocInfo = (TextView) itemView.findViewById(R.id.txtKhoaHocInfo);
        }
    }
}
