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
import com.example.quanlydiemsinhvien.activities.DanhSachKhoaHocActivity;
import com.example.quanlydiemsinhvien.activities.LoginActivity;
import com.example.quanlydiemsinhvien.data_models.LopHocPhan;
import com.example.quanlydiemsinhvien.dialogs.DialogAddOrEditLopHocPhan;
import com.example.quanlydiemsinhvien.dialogs.DialogDeleteLopHocPhan;

import java.util.ArrayList;

public class LopHocPhanTheoMonAdapter extends RecyclerSwipeAdapter<LopHocPhanTheoMonAdapter.DanhSachLopHocPhanTheoMonViewHolder> {
    private ArrayList<LopHocPhan> dsLopHP;
    private Context context;
    public static final String MALOP = "maLop";
    public static final String TENLOP = "tenLop";
    public static final String EDIT_LHP = "lop hoc phan";
    public static final String DELETE_LHP = "lop hoc phan delete";

    public LopHocPhanTheoMonAdapter(Context context, ArrayList<LopHocPhan> dsLopHP) {
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
    public void onBindViewHolder(@NonNull final DanhSachLopHocPhanTheoMonViewHolder holder, final int position) {
        final LopHocPhan chuongTrinhDaoTao = dsLopHP.get(position);
        holder.txtMaLop.setText(chuongTrinhDaoTao.getMaLHP());
        holder.txtTenLop.setText(chuongTrinhDaoTao.getTenLHP());
        holder.txtMaGV.setText(chuongTrinhDaoTao.getMaGV());

        holder.swipeLayout.setShowMode(SwipeLayout.ShowMode.PullOut);
        // Drag from right
        holder.swipeLayout.addDrag(SwipeLayout.DragEdge.Right, holder.swipeLayout.findViewById(R.id.right_wrapper));

        holder.swipeLayout.getSurfaceView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context ct = v.getContext();
                Intent intent = LoginActivity.intent;
                intent.setClass(ct, DanhSachKhoaHocActivity.class); // null <=> class danh sach sinh vien cua mot lop hoc phan
                intent.putExtra(MALOP, dsLopHP.get(position).getMaLHP());
                intent.putExtra(TENLOP, dsLopHP.get(position).getTenLHP());
                ct.startActivity(intent);
            }
        });

        holder.ibtnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                Bundle bundle = new Bundle();
                bundle.putSerializable(DELETE_LHP, chuongTrinhDaoTao);
                bundle.putInt(KhoaHocAdapter.POSITION_STRING, position);
                DialogDeleteLopHocPhan deleteLopHocPhan = new DialogDeleteLopHocPhan();
                deleteLopHocPhan.setArguments(bundle);
                deleteLopHocPhan.show(((AppCompatActivity) context).getSupportFragmentManager(), "Delete lớp học phần");
            }
        });

        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final LopHocPhan lopHocPhan = dsLopHP.get(position);

                //

                Bundle bundle = new Bundle();
                bundle.putSerializable(EDIT_LHP, lopHocPhan);
                DialogAddOrEditLopHocPhan editLopHocPhan = new DialogAddOrEditLopHocPhan();
                editLopHocPhan.setArguments(bundle);
                editLopHocPhan.show(((AppCompatActivity) context).getSupportFragmentManager(), "Edit Lop hoc phan");


            }
        });

        mItemManger.bindView(holder.itemView, position);

    }

    @Override
    public int getItemCount() {
        return dsLopHP.size();
    }

    @Override
    public int getSwipeLayoutResourceId(int position) {
        return R.id.swipe;
    }

    public class DanhSachLopHocPhanTheoMonViewHolder extends RecyclerView.ViewHolder {
        protected TextView txtMaLop;
        protected TextView txtTenLop;
        protected TextView txtMaGV;
        protected ImageButton ibtnDelete;
        protected TextView edit;
        protected SwipeLayout swipeLayout;

        public DanhSachLopHocPhanTheoMonViewHolder(@NonNull View itemView) {
            super(itemView);
            txtMaLop = (TextView) itemView.findViewById(R.id.edtMaLop);
            txtTenLop = (TextView) itemView.findViewById(R.id.edtTenLop);
            txtMaGV = (TextView) itemView.findViewById(R.id.txtMaGV);
            swipeLayout = itemView.findViewById(R.id.swipe);
            ibtnDelete = itemView.findViewById(R.id.ibtnDelete);
            edit = itemView.findViewById(R.id.edit);
        }
    }
}
