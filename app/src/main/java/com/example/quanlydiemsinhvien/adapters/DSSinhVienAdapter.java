package com.example.quanlydiemsinhvien.adapters;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
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
import com.example.quanlydiemsinhvien.activities.DSSinhVienActivity;
import com.example.quanlydiemsinhvien.data_models.DanhSachSinhVien;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

//import com.example.quanlydiemsinhvien.firebase_data.DSSVienDatabase;

public class DSSinhVienAdapter extends RecyclerSwipeAdapter<DSSinhVienAdapter.ViewHolder> {
    public static final String KEY_MASV = "ma_sv";
    public static final String KEY_TENSV = "ten_sv";
    public static final String KEY_DATA = "data";
    ArrayList<DanhSachSinhVien> listSV = new ArrayList<DanhSachSinhVien>();

    private ArrayList<DanhSachSinhVien> danhsachsinhvien;
    private Context context;
    public static Intent intent;
    private Bundle bundle;
    FirebaseDatabase rootNode;
    DatabaseReference reference;

//    private ArrayList<DanhSachSinhVien> danhsachsinhvien;
//    private Context context;
//    public static Intent intent;
//    private AlertDialog.Builder myDialog;


//    DSSVienDatabase mDsLopDatabase = new DSSVienDatabase();

    public DSSinhVienAdapter(ArrayList<DanhSachSinhVien> listSV, Context context) {
        this.listSV = listSV;
        this.context = context;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView mTxtMaSV;
        private TextView mTxtTenSV;
        private TextView mTxtDiemSV;
        private TextView edit;
        private ImageButton ibtnDelete;
        private SwipeLayout swipeLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mTxtMaSV = (TextView) itemView.findViewById(R.id.txtMaSV);
            mTxtTenSV = (TextView) itemView.findViewById(R.id.txtTenSV);
            mTxtDiemSV = (TextView) itemView.findViewById(R.id.txtDiemSV);
            edit = (TextView) itemView.findViewById(R.id.edit);
            ibtnDelete = (ImageButton) itemView.findViewById(R.id.ibtnDelete);
            swipeLayout = itemView.findViewById(R.id.swipe_dssv);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.item_dssv_layout, parent, false);
        ViewHolder viewHolder = new ViewHolder(itemView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        final DanhSachSinhVien aCard = listSV.get(position);
        holder.mTxtMaSV.setText(aCard.getMaSV());
        holder.mTxtTenSV.setText(aCard.getTenSV());
        holder.mTxtDiemSV.setText(aCard.getDiem() + "");

        //Set mode swipe
        holder.swipeLayout.setShowMode(SwipeLayout.ShowMode.PullOut);

        //Drag from right to left
        holder.swipeLayout.addDrag(SwipeLayout.DragEdge.Right, holder.swipeLayout.findViewById(R.id.right_wrapper));
        holder.swipeLayout.getSurfaceView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        holder.edit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(final View view) {
                final DanhSachSinhVien danhSachSinhVien = listSV.get(position);

                AlertDialog.Builder builder = new AlertDialog.Builder(context);

                View v = LayoutInflater.from(context).inflate(R.layout.suadiem_layout, null);

                final TextView maSV = v.findViewById(R.id.maSV);
                final TextView tenSV = v.findViewById(R.id.tenSV);
                final EditText edtDiemSV = v.findViewById(R.id.edtDiemSV);
                final EditText edtLanHoc = v.findViewById(R.id.edtLanHoc);

                edtDiemSV.setText(danhSachSinhVien.getDiem() + "");
                maSV.setText(danhSachSinhVien.getMaSV() + "");
                tenSV.setText(danhSachSinhVien.getTenSV() + "");
                edtLanHoc.setText(danhSachSinhVien.getLanHoc() + "");
                builder.setView(v);

                builder.setTitle("Cập nhật lại điểm sinh viên");

                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        rootNode = FirebaseDatabase.getInstance();
                        reference = rootNode.getReference("DSSVMotLop").child(DSSinhVienActivity.maLop);
                        DanhSachSinhVien sv = new DanhSachSinhVien();
                        sv.setDiem(Double.parseDouble(edtDiemSV.getText().toString()));
                        sv.setLanHoc(Integer.parseInt(edtLanHoc.getText().toString()));
                        reference.child(listSV.get(position).getMaSV()).setValue(sv.toMap());
                        notifyDataSetChanged();
                        Toast.makeText(view.getContext(), "Thêm thành công!", Toast.LENGTH_SHORT).show();
                    }
                });

                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });

        holder.ibtnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Xóa sinh viên");
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

                        DanhSachSinhVien sv = new DanhSachSinhVien();
                        sv = listSV.get(position);
                        rootNode = FirebaseDatabase.getInstance();
                        reference = rootNode.getReference("DSSVMotLop").child(DSSinhVienActivity.maLop);
                        reference.child(sv.getMaSV()).removeValue();
                        notifyDataSetChanged();
                        Toast.makeText(view.getContext(), "Xóa thành công !", Toast.LENGTH_SHORT).show();
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
        return listSV.size();
    }

    @Override
    public int getSwipeLayoutResourceId(int position) {
        return R.id.swipe_dssv;
    }


}
