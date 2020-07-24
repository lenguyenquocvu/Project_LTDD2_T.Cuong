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
import com.example.quanlydiemsinhvien.data_models.KhoaHoc;

import java.util.ArrayList;

public class KhoaHocAdapter extends RecyclerSwipeAdapter<KhoaHocAdapter.KhoaHocViewHolder> {
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
    public void onBindViewHolder(@NonNull final KhoaHocViewHolder holder, final int position) {
        final KhoaHoc khoaHoc = dsKhoaHoc.get(position);
        holder.txtKhoaHoc.setText(khoaHoc.getMaKH());
        holder.txtKhoaHocInfo.setText(khoaHoc.getThoiGianKhoaHoc());

        holder.swipeLayout.setShowMode(SwipeLayout.ShowMode.PullOut);
        // Drag from right
        holder.swipeLayout.addDrag(SwipeLayout.DragEdge.Right, holder.swipeLayout.findViewById(R.id.right_wrapper));
        //swipeListener of swipeLayout but not use now so input by comment above
//        holder.swipeLayout.addSwipeListener(new SwipeLayout.SwipeListener() {
//            @Override
//            public void onStartOpen(SwipeLayout layout) {
//
//            }
//
//            @Override
//            public void onOpen(SwipeLayout layout) {
//
//            }
//
//            @Override
//            public void onStartClose(SwipeLayout layout) {
//
//            }
//
//            @Override
//            public void onClose(SwipeLayout layout) {
//
//            }
//
//            @Override
//            public void onUpdate(SwipeLayout layout, int leftOffset, int topOffset) {
//
//            }
//
//            @Override
//            public void onHandRelease(SwipeLayout layout, float xvel, float yvel) {
//
//            }
//        });
        holder.swipeLayout.getSurfaceView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context ct = v.getContext();
                Intent intent = DanhSachKhoaHocActivity.intent;
                intent.setClass(ct, DanhSachChuongTrinhDaoTaoActivity.class);
                intent.putExtra("Nganh", DanhSachKhoaHocActivity.txtNganh.getText().toString());
                intent.putExtra("maKH", dsKhoaHoc.get(position).getMaKH());
                ct.startActivity(intent);
            }
        });



        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final KhoaHoc khoaHoc = dsKhoaHoc.get(position);

                AlertDialog.Builder builder = new AlertDialog.Builder(context);

                View view = LayoutInflater.from(context).inflate(R.layout.dialog_sua_khoahoc_layout, null);

                EditText edtMaKH = view.findViewById(R.id.edtMaKHInfo);
                final EditText edtBatdau = view.findViewById(R.id.edtKhBatDau);
                final EditText edtKetthuc = view.findViewById(R.id.edtKhKetThuc);

                edtMaKH.setText(khoaHoc.getMaKH());
                edtBatdau.setText(khoaHoc.getBatDau());
                edtKetthuc.setText(khoaHoc.getKetThuc());

                builder.setView(view);

                builder.setTitle("Cập nhật lại khóa học");

                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        khoaHoc.setBatDau(edtBatdau.getText().toString());
                        khoaHoc.setKetThuc(edtKetthuc.getText().toString());
                        notifyDataSetChanged();
                    }
                });

                AlertDialog alertDialog = builder.create();
                alertDialog.show();
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
                        dsKhoaHoc.remove(position);
                        notifyItemRemoved(position);
                        notifyItemRangeChanged(position, dsKhoaHoc.size());
                        mItemManger.closeAllItems();
                        Toast.makeText(v.getContext(), "Deleted !", Toast.LENGTH_SHORT).show();
                    }
                });

                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });
//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//
//            }
//        });
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
