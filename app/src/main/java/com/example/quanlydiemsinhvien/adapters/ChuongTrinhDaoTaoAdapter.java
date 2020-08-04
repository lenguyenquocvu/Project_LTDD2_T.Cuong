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
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.daimajia.swipe.SwipeLayout;
import com.daimajia.swipe.adapters.RecyclerSwipeAdapter;
import com.example.quanlydiemsinhvien.R;
import com.example.quanlydiemsinhvien.activities.DanhSachKhoaHocActivity;
import com.example.quanlydiemsinhvien.activities.DanhSachLopHPTheoMHActivity;
import com.example.quanlydiemsinhvien.data_models.MonHoc;
import com.example.quanlydiemsinhvien.dialog.DialogDeleteChuongTrinhDaoTao;

import java.util.ArrayList;

import static com.example.quanlydiemsinhvien.adapters.KhoaHocAdapter.POSITION_STRING;

public class ChuongTrinhDaoTaoAdapter extends RecyclerSwipeAdapter<ChuongTrinhDaoTaoAdapter.ChuongTrinhDaoTaoViewHolder> {
    private ArrayList<MonHoc> dsCTDT;
    private Context context;
    private AlertDialog.Builder myDialog;


    public ChuongTrinhDaoTaoAdapter(Context context, ArrayList<MonHoc> dsCTDT) {
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
        final MonHoc monHoc = dsCTDT.get(position);
        holder.txtMaMH.setText(monHoc.getMaMH());
        holder.txtTenMH.setText(monHoc.getTenMH());
        holder.soTinChi.setText(monHoc.getSoTinChi() + "");

        holder.swipeLayout.setShowMode(SwipeLayout.ShowMode.PullOut);
        // Drag from right
        holder.swipeLayout.addDrag(SwipeLayout.DragEdge.Right, holder.swipeLayout.findViewById(R.id.right_wrapper));


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


        holder.ibtnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                Bundle bundle = new Bundle();
                bundle.putString("maMH", dsCTDT.get(position).getMaMH());
                bundle.putInt(POSITION_STRING, position);
                DialogDeleteChuongTrinhDaoTao deleteChuongTrinhDaoTao = new DialogDeleteChuongTrinhDaoTao();
                deleteChuongTrinhDaoTao.setArguments(bundle);
                deleteChuongTrinhDaoTao.show(((AppCompatActivity) context).getSupportFragmentManager(), "Xóa môn học");
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

        //        protected TextView edit;
        public ChuongTrinhDaoTaoViewHolder(@NonNull View itemView) {
            super(itemView);
            txtMaMH = (TextView) itemView.findViewById(R.id.edtMaMH);
            txtTenMH = (TextView) itemView.findViewById(R.id.edtTenMH);
            soTinChi = (TextView) itemView.findViewById(R.id.SoTinChi);
            swipeLayout = itemView.findViewById(R.id.swipe);
            ibtnDelete = itemView.findViewById(R.id.ibtnDelete);
//            edit = itemView.findViewById(R.id.edit);
        }
    }

}
