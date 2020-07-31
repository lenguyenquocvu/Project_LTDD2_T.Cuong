package com.example.quanlydiemsinhvien.adapters;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.daimajia.swipe.SwipeLayout;
import com.daimajia.swipe.adapters.RecyclerSwipeAdapter;
import com.example.quanlydiemsinhvien.R;
import com.example.quanlydiemsinhvien.activities.DanhSachChuongTrinhDaoTaoActivity;
import com.example.quanlydiemsinhvien.activities.DanhSachKhoaHocActivity;
import com.example.quanlydiemsinhvien.activities.DanhSachLopHPTheoMHActivity;
import com.example.quanlydiemsinhvien.data_models.LopHocPhan;

import java.util.ArrayList;

public class DanhSachLopHocPhanTheoMonAdapter extends RecyclerSwipeAdapter<DanhSachLopHocPhanTheoMonAdapter.DanhSachLopHocPhanTheoMonViewHolder> {
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
                Intent intent = DanhSachKhoaHocActivity.intent;
                intent.setClass(ct, DanhSachKhoaHocActivity.class); // null <=> class danh sach sinh vien cua mot lop hoc phan
                intent.putExtra("maLop", dsLopHP.get(position).getMaLHP());
                intent.putExtra("tenLop", dsLopHP.get(position).getTenLHP());
                ct.startActivity(intent);
            }
        });

        holder.ibtnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Xóa khóa học");
                builder.setMessage("Bạn có muốn xóa không?");
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mItemManger.removeShownLayouts(holder.swipeLayout);
                        dsLopHP.remove(position);
                        notifyItemRemoved(position);
                        notifyItemRangeChanged(position, dsLopHP.size());
                        mItemManger.closeAllItems();
                        Toast.makeText(v.getContext(), "Deleted !", Toast.LENGTH_SHORT).show();
                    }
                });

                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });

        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final LopHocPhan lopHocPhan = dsLopHP.get(position);

                AlertDialog.Builder builder = new AlertDialog.Builder(context);

                View view = LayoutInflater.from(context).inflate(R.layout.dialog_sua_lop_hoc_phan_layout, null);

                EditText edtMaLop = view.findViewById(R.id.edtMaLop);
                EditText edtTenLop = view.findViewById(R.id.edtTenLop);
                final EditText edtMaGV = view.findViewById(R.id.edtMaGV);

                edtMaLop.setText(lopHocPhan.getMaLHP());
                edtTenLop.setText(lopHocPhan.getTenLHP());
                edtMaGV.setText(lopHocPhan.getMaGV());

                builder.setView(view);

                builder.setTitle("Cập nhật lại lớp học phần");

                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        lopHocPhan.setMaGV(edtMaGV.getText().toString());
                        notifyDataSetChanged();
                    }
                });

                AlertDialog alertDialog = builder.create();
                alertDialog.show();
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
