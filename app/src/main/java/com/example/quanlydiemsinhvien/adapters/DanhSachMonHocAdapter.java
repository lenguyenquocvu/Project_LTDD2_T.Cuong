package com.example.quanlydiemsinhvien.adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.daimajia.swipe.SwipeLayout;
import com.daimajia.swipe.adapters.RecyclerSwipeAdapter;
import com.example.quanlydiemsinhvien.R;
import com.example.quanlydiemsinhvien.activities.DanhSachLopHPTheoMHActivity;
import com.example.quanlydiemsinhvien.activities.LoginActivity;
import com.example.quanlydiemsinhvien.data_models.MonHoc;
import com.example.quanlydiemsinhvien.dialogs.DialogEditMonHoc;

import java.util.ArrayList;

public class DanhSachMonHocAdapter extends RecyclerSwipeAdapter<DanhSachMonHocAdapter.MonHocViewHolder> {

    private ArrayList<MonHoc> dsMonhocs;
    private Context context;

    public DanhSachMonHocAdapter(Context context, ArrayList<MonHoc> dsMonhocs) {
        this.dsMonhocs = dsMonhocs;
        this.context = context;
    }


    @Override
    public MonHocViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.item_monhoc_layout, parent, false);


        MonHocViewHolder monHocViewHolder = new MonHocViewHolder(itemView);


        return monHocViewHolder;
    }

    @Override
    public void onBindViewHolder(MonHocViewHolder holder, final int position) {
        final MonHoc monHoc = dsMonhocs.get(position);
        holder.txtMaMH.setText(monHoc.getMaMH());
        holder.txtTenMH.setText(monHoc.getTenMH());
        holder.soTinChi.setText(monHoc.getSoTinChi() + "");
        holder.maNganh.setText(monHoc.getMaNganh());

        holder.swipeLayout.setShowMode(SwipeLayout.ShowMode.PullOut);
        // Drag from right
        holder.swipeLayout.addDrag(SwipeLayout.DragEdge.Right, holder.swipeLayout.findViewById(R.id.bottom_wrapper));


        holder.swipeLayout.getSurfaceView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context ct = v.getContext();
                Intent intent = LoginActivity.intent;
                intent.setClass(ct, DanhSachLopHPTheoMHActivity.class);
                intent.putExtra("tenMH", dsMonhocs.get(position).getTenMH());
                intent.putExtra("maMH", dsMonhocs.get(position).getMaMH());
                ct.startActivity(intent);
            }
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


        holder.tvEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                Bundle bundle = new Bundle();
                bundle.putSerializable("mon hoc", dsMonhocs.get(position));
                DialogEditMonHoc editMonHoc = new DialogEditMonHoc();
                editMonHoc.setArguments(bundle);
                editMonHoc.show(((AppCompatActivity) context).getSupportFragmentManager(), "Sửa môn học");
            }
        });
        mItemManger.bindView(holder.itemView, position);
    }


    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public int getItemCount() {
        return dsMonhocs.size();
    }

    @Override
    public int getSwipeLayoutResourceId(int position) {
        return R.id.swipe;
    }

    public static class MonHocViewHolder extends RecyclerView.ViewHolder {
        protected TextView txtMaMH;
        protected TextView txtTenMH;
        protected TextView soTinChi;
        protected TextView maNganh;
        protected SwipeLayout swipeLayout;
        protected TextView tvEdit;

        //        protected TextView edit;
        public MonHocViewHolder(@NonNull View itemView) {
            super(itemView);
            txtMaMH = (TextView) itemView.findViewById(R.id.edtMaMH);
            txtTenMH = (TextView) itemView.findViewById(R.id.edtTenMH);
            soTinChi = (TextView) itemView.findViewById(R.id.SoTinChi);
            maNganh = itemView.findViewById(R.id.maNganh);
            swipeLayout = itemView.findViewById(R.id.swipe);
            tvEdit = itemView.findViewById(R.id.tvEdit);
//            edit = itemView.findViewById(R.id.edit);
        }
    }
}
