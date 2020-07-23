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
import com.example.quanlydiemsinhvien.data_models.LopHocPhan;

import java.util.ArrayList;

public class DanhSachLopHocPhanTheoMonAdapter extends RecyclerView.Adapter<DanhSachLopHocPhanTheoMonAdapter.DanhSachLopHocPhanTheoMonViewHolder>{
    private ArrayList<LopHocPhan> dsLopHP;
    private Context context;
    private AlertDialog.Builder myDialog;

    public DanhSachLopHocPhanTheoMonAdapter(Context context, ArrayList<LopHocPhan> dsLopHP) {
        this.dsLopHP = dsLopHP;
        this.context = context;
    }

    @NonNull
    @Override
    public DanhSachLopHocPhanTheoMonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.item_lop_hp_theo_mh_layout, parent, false);

        DanhSachLopHocPhanTheoMonViewHolder danhSachLopHocPhanTheoMonViewHolder = new DanhSachLopHocPhanTheoMonViewHolder(itemView);


        return danhSachLopHocPhanTheoMonViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull DanhSachLopHocPhanTheoMonViewHolder holder, int position) {
        final LopHocPhan chuongTrinhDaoTao = dsLopHP.get(position);
        holder.txtMaLop.setText(chuongTrinhDaoTao.getMaLop());
        holder.txtTenLop.setText(chuongTrinhDaoTao.getTenLop());
        holder.txtMaGV.setText(chuongTrinhDaoTao.getMaGV());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return dsLopHP.size();
    }

    public class DanhSachLopHocPhanTheoMonViewHolder extends RecyclerView.ViewHolder {
        protected TextView txtMaLop;
        protected TextView txtTenLop;
        protected TextView txtMaGV;

        public DanhSachLopHocPhanTheoMonViewHolder(@NonNull View itemView) {
            super(itemView);
            txtMaLop = (TextView) itemView.findViewById(R.id.edtMaLop);
            txtTenLop = (TextView) itemView.findViewById(R.id.edtTenLop);
            txtMaGV = (TextView) itemView.findViewById(R.id.txtMaGV);
        }
    }
}
