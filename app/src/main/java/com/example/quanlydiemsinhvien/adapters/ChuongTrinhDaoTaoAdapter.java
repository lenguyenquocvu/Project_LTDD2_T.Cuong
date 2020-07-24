package com.example.quanlydiemsinhvien.adapters;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
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
import com.example.quanlydiemsinhvien.data_models.ChuongTrinhDaoTao;

import java.util.ArrayList;

public class ChuongTrinhDaoTaoAdapter extends RecyclerSwipeAdapter<ChuongTrinhDaoTaoAdapter.ChuongTrinhDaoTaoViewHolder> {
    private ArrayList<ChuongTrinhDaoTao> dsCTDT;
    private Context context;
    private AlertDialog.Builder myDialog;

    private final int SHOW_MENU = 1;
    private final int HIDE_MENU = 0;

    public ChuongTrinhDaoTaoAdapter(Context context, ArrayList<ChuongTrinhDaoTao> dsCTDT) {
        this.dsCTDT = dsCTDT;
        this.context = context;
    }

    @NonNull
    @Override
    public ChuongTrinhDaoTaoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView;

        itemView = LayoutInflater.from(context).inflate(R.layout.item_chuong_trinh_dao_tao_layout, parent, false);


        ChuongTrinhDaoTaoViewHolder chuongTrinhDaoTaoViewHolder = new ChuongTrinhDaoTaoViewHolder(itemView);


        return chuongTrinhDaoTaoViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ChuongTrinhDaoTaoViewHolder holder, final int position) {
        final ChuongTrinhDaoTao chuongTrinhDaoTao = dsCTDT.get(position);
        holder.txtMaMH.setText(chuongTrinhDaoTao.getMaMH());
        holder.txtTenMH.setText(chuongTrinhDaoTao.getTenMH());
        holder.soTinChi.setText(chuongTrinhDaoTao.getSoTinChi() + "");

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
                intent.setClass(ct, DanhSachLopHPTheoMHActivity.class);
                intent.putExtra("tenMH", dsCTDT.get(position).getTenMH());
                intent.putExtra("maMH", dsCTDT.get(position).getMaMH());
                ct.startActivity(intent);
            }
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final ChuongTrinhDaoTao chuongTrinhDaoTao = dsCTDT.get(position);

                AlertDialog.Builder builder = new AlertDialog.Builder(context);

                View view = LayoutInflater.from(context).inflate(R.layout.dialog_sua_mon_hoc__ctdt_layout, null);

                EditText edtMaMH = view.findViewById(R.id.edtMaMH);
                EditText edtTenMH = view.findViewById(R.id.edtTenMH);
                EditText edtSoTinChi = view.findViewById(R.id.edtSoTinChi);

                edtMaMH.setText(chuongTrinhDaoTao.getMaMH());
                edtTenMH.setText(chuongTrinhDaoTao.getTenMH());
                edtSoTinChi.setText(chuongTrinhDaoTao.getSoTinChi() + "");

                builder.setView(view);

                builder.setTitle("Cập nhật lại môn học");

                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
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
                        dsCTDT.remove(position);
                        notifyItemRemoved(position);
                        notifyItemRangeChanged(position, dsCTDT.size());
                        mItemManger.closeAllItems();
                        Toast.makeText(v.getContext(), "Deleted !", Toast.LENGTH_SHORT).show();
                    }
                });

                AlertDialog alertDialog = builder.create();
                alertDialog.show();
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
        return dsCTDT.size();
    }

    @Override
    public int getSwipeLayoutResourceId(int position) {
        return R.id.swipe;
    }

    public class ChuongTrinhDaoTaoViewHolder extends RecyclerView.ViewHolder {
        protected TextView txtMaMH;
        protected TextView txtTenMH;
        protected TextView soTinChi;
        protected SwipeLayout swipeLayout;
        protected ImageButton ibtnDelete;
        protected TextView edit;
        public ChuongTrinhDaoTaoViewHolder(@NonNull View itemView) {
            super(itemView);
            txtMaMH = (TextView) itemView.findViewById(R.id.edtMaMH);
            txtTenMH = (TextView) itemView.findViewById(R.id.edtTenMH);
            soTinChi = (TextView) itemView.findViewById(R.id.SoTinChi);
            swipeLayout = itemView.findViewById(R.id.swipe);
            ibtnDelete = itemView.findViewById(R.id.ibtnDelete);
            edit = itemView.findViewById(R.id.edit);
        }
    }

}
