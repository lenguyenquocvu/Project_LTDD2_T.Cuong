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
import com.example.quanlydiemsinhvien.data_models.ChuongTrinhDaoTao;

import java.util.ArrayList;

public class ChuongTrinhDaoTaoAdapter extends RecyclerView.Adapter<ChuongTrinhDaoTaoAdapter.ChuongTrinhDaoTaoViewHolder>{
    private ArrayList<ChuongTrinhDaoTao> dsCTDT;
    private Context context;
    private AlertDialog.Builder myDialog;

    public ChuongTrinhDaoTaoAdapter(Context context, ArrayList<ChuongTrinhDaoTao> dsCTDT) {
        this.dsCTDT = dsCTDT;
        this.context = context;
    }

    @NonNull
    @Override
    public ChuongTrinhDaoTaoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.item_chuong_trinh_dao_tao_layout, parent, false);

        ChuongTrinhDaoTaoViewHolder chuongTrinhDaoTaoViewHolder = new ChuongTrinhDaoTaoViewHolder(itemView);


        return chuongTrinhDaoTaoViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ChuongTrinhDaoTaoViewHolder holder, int position) {
        final ChuongTrinhDaoTao chuongTrinhDaoTao = dsCTDT.get(position);
        holder.txtMaMH.setText(chuongTrinhDaoTao.getMaMH());
        holder.txtTenMH.setText(chuongTrinhDaoTao.getTenMH());
        holder.soTinChi.setText(chuongTrinhDaoTao.getSoTinChi() + "");

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return dsCTDT.size();
    }

    public class ChuongTrinhDaoTaoViewHolder extends RecyclerView.ViewHolder {
        protected TextView txtMaMH;
        protected TextView txtTenMH;
        protected TextView soTinChi;

        public ChuongTrinhDaoTaoViewHolder(@NonNull View itemView) {
            super(itemView);
            txtMaMH = (TextView) itemView.findViewById(R.id.edtMaMH);
            txtTenMH = (TextView) itemView.findViewById(R.id.edtTenMH);
            soTinChi = (TextView) itemView.findViewById(R.id.SoTinChi);
        }
    }
}
