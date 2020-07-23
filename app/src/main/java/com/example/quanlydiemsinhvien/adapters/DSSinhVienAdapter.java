package com.example.quanlydiemsinhvien.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quanlydiemsinhvien.R;
import com.example.quanlydiemsinhvien.data_models.DanhSachSinhVien;

import java.util.ArrayList;

public class DSSinhVienAdapter extends RecyclerView.Adapter<DSSinhVienAdapter.ViewHolder> {
    private ArrayList<DanhSachSinhVien> danhsachsinhvien;
    private Context context;

    public DSSinhVienAdapter(ArrayList<DanhSachSinhVien> danhsachsinhvien, Context context) {
        this.danhsachsinhvien = danhsachsinhvien;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.item_dssv_layout,parent,false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        DanhSachSinhVien aCard = danhsachsinhvien.get(position);
        holder.mTxtMaSV.setText(aCard.getMaSV());
        holder.mTxtTenSV.setText(aCard.getTenSV());
        holder.mTxtDiemSV.setText(aCard.getDiem()+"");
    }

    @Override
    public int getItemCount() {
        return danhsachsinhvien.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView mTxtMaSV;
        private TextView mTxtTenSV;
        private TextView mTxtDiemSV;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mTxtMaSV = (TextView) itemView.findViewById(R.id.txtMaSV);
            mTxtTenSV = (TextView) itemView.findViewById(R.id.txtTenSV);
            mTxtDiemSV = (TextView) itemView.findViewById(R.id.txtDiemSV);
        }
    }
}
