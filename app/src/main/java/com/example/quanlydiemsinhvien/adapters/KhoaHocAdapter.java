package com.example.quanlydiemsinhvien.adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.daimajia.swipe.SwipeLayout;
import com.daimajia.swipe.adapters.RecyclerSwipeAdapter;
import com.example.quanlydiemsinhvien.R;
import com.example.quanlydiemsinhvien.activities.DanhSachChuongTrinhDaoTaoActivity;
import com.example.quanlydiemsinhvien.activities.DanhSachKhoaHocActivity;
import com.example.quanlydiemsinhvien.data_models.KhoaHoc;
import com.example.quanlydiemsinhvien.dialog.DialogAddOrEditKhoahoc;
import com.example.quanlydiemsinhvien.dialog.DialogDeleteKhoaHoc;

import java.util.ArrayList;

public class KhoaHocAdapter extends RecyclerSwipeAdapter<KhoaHocAdapter.KhoaHocViewHolder> {
    private ArrayList<KhoaHoc> dsKhoaHoc;
    private Context context;
    public static final String EDIT_KHOAHOC = "khoa hoc";
    public static final String DELETE_KHOAHOC = "delete khoa hoc";
    public static final String POSITION_STRING = "position";
    public static final String MAKH_STRING = "maKH";
    public static final String MANGANH_STRING = "maNganh";
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
    public void onBindViewHolder(@NonNull final KhoaHocViewHolder holder, final int position) {
        final KhoaHoc khoaHoc = dsKhoaHoc.get(position);
        holder.txtKhoaHoc.setText(khoaHoc.getMaKH());
        holder.txtKhoaHocInfo.setText(khoaHoc.getBatDau() + " - " + khoaHoc.getKetThuc());

        holder.swipeLayout.setShowMode(SwipeLayout.ShowMode.PullOut);
        // Drag from right
        holder.swipeLayout.addDrag(SwipeLayout.DragEdge.Right, holder.swipeLayout.findViewById(R.id.right_wrapper));

        holder.swipeLayout.getSurfaceView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context ct = v.getContext();
                Intent intent = DanhSachKhoaHocActivity.intent;
                intent.setClass(ct, DanhSachChuongTrinhDaoTaoActivity.class);
                intent.putExtra(MANGANH_STRING, DanhSachKhoaHocActivity.txtNganh.getText().toString());
                intent.putExtra(MAKH_STRING, dsKhoaHoc.get(position).getMaKH());
                ct.startActivity(intent);
            }
        });



        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putSerializable(EDIT_KHOAHOC, khoaHoc);
                DialogAddOrEditKhoahoc editKhoahoc = new DialogAddOrEditKhoahoc();
                editKhoahoc.setArguments(bundle);
                editKhoahoc.show(((AppCompatActivity)context).getSupportFragmentManager(), "Edit");

            }
        });

        holder.ibtnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                Bundle bundle = new Bundle();
                bundle.putSerializable(DELETE_KHOAHOC, khoaHoc);
                bundle.putInt(POSITION_STRING, position);
                DialogDeleteKhoaHoc deleteKhoaHoc = new DialogDeleteKhoaHoc();
                deleteKhoaHoc.setArguments(bundle);
                deleteKhoaHoc.show(((AppCompatActivity)context).getSupportFragmentManager(), "Delete khóa học");

            }
        });
        mItemManger.bindView(holder.itemView, position);

    }



    @Override
    public int getItemCount() {
        return dsKhoaHoc.size();
    }

    @Override
    public int getSwipeLayoutResourceId(int position) {
        return R.id.swipe;
    }



    public static class KhoaHocViewHolder extends RecyclerView.ViewHolder {
        protected TextView txtKhoaHoc;
        protected TextView txtKhoaHocInfo;
        protected ImageButton ibtnDelete;
        protected TextView edit;
        protected SwipeLayout swipeLayout;
        public KhoaHocViewHolder(@NonNull View itemView) {
            super(itemView);
            swipeLayout = itemView.findViewById(R.id.swipe);
            txtKhoaHoc = (TextView) itemView.findViewById(R.id.txtKhoaKhoc);
            txtKhoaHocInfo = (TextView) itemView.findViewById(R.id.txtKhoaHocInfo);
            ibtnDelete = itemView.findViewById(R.id.ibtnDelete);
            edit = itemView.findViewById(R.id.edit);
        }
    }
}
