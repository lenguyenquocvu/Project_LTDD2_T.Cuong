package com.example.quanlydiemsinhvien.adapters;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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
import com.example.quanlydiemsinhvien.activities.DSLopActivity;
import com.example.quanlydiemsinhvien.activities.DSSinhVienActivity;
import com.example.quanlydiemsinhvien.data_models.DanhSachLop;
import com.example.quanlydiemsinhvien.data_models.DanhSachSinhVien;
import com.example.quanlydiemsinhvien.firebase_data.DSLopDatabase;

import java.util.ArrayList;

public class DSLopAdapter extends RecyclerSwipeAdapter<DSLopAdapter.DSLopViewHolder> {
    public static final String KEY_MALOP = "ma_lop";
    public static final String KEY_TENLOP = "ten_lop";
    public static final String KEY_DATA = "data";
    ArrayList<DanhSachLop> listLop = new ArrayList<DanhSachLop>();

    //    private ArrayList<DanhSachLop> danhsachlop;
    private Context context;
    public static Intent intent;
    private Bundle bundle;
//    private AlertDialog.Builder myDialog;

    DSLopDatabase mDsLopDatabase = new DSLopDatabase();

    public DSLopAdapter(Context context, ArrayList<DanhSachLop> listLop) {
        this.listLop = listLop;
        this.context = context;
    }

    static class DSLopViewHolder extends RecyclerView.ViewHolder {
        private TextView mTxtMaLHP;
        private TextView mTxtTenLHP;
        private ImageButton ibtnDelete;
        private SwipeLayout swipeLayout;

        public DSLopViewHolder(@NonNull View itemView) {
            super(itemView);
            mTxtMaLHP = (TextView) itemView.findViewById(R.id.txtMaLHP);
            mTxtTenLHP = (TextView) itemView.findViewById(R.id.txtTenLHP);
            ibtnDelete = (ImageButton) itemView.findViewById(R.id.ibtnDelete);
            swipeLayout = itemView.findViewById(R.id.swipe_dslop);
        }
    }

    @NonNull
    @Override
    public DSLopViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_dslop_layout, parent, false);
        return new DSLopViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final DSLopViewHolder holder, final int position) {

        final DanhSachLop aCard = listLop.get(position);
        holder.mTxtTenLHP.setText(aCard.getTenLHP());
        holder.mTxtMaLHP.setText(aCard.getMaLHP());

        //Set mode swipe
        holder.swipeLayout.setShowMode(SwipeLayout.ShowMode.PullOut);

        //Drag from right to left
        holder.swipeLayout.addDrag(SwipeLayout.DragEdge.Right, holder.swipeLayout.findViewById(R.id.right_wrapper));

        //Event when click to the item on recyclerView
        holder.swipeLayout.getSurfaceView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DanhSachLop recentLop = DSLopActivity.dataLop.get(position);
                Context context = view.getContext();

                bundle = new Bundle();
                bundle.putString(KEY_MALOP, recentLop.getMaLHP());
                bundle.putString(KEY_TENLOP, recentLop.getTenLHP());

                intent = DSLopActivity.intent;
                intent.setClass(context, DSSinhVienActivity.class);
                intent.putExtra(KEY_DATA, bundle);

                context.startActivity(intent);

                Log.d("test", "position: " + position);
            }
        });

        mItemManger.bindView(holder.itemView, position);


        holder.ibtnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Xóa lớp");
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

                        mDsLopDatabase.deleteLop(aCard.getMaLHP());

                        /*mItemManger.removeShownLayouts(holder.swipeLayout);*/
                        /*listLop.remove(position);*/
                        /*notifyItemRemoved(position);*/
                        /*notifyItemRangeChanged(position, listLop.size());*/
                        /*mItemManger.closeAllItems();*/
                        Toast.makeText(view.getContext(), "Deleted !", Toast.LENGTH_SHORT).show();
                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });
        mItemManger.bindView(holder.itemView, position);

//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Context context = view.getContext();
//                intent = new Intent();
//                intent.setClass(context, DSSinhVienActivity.class);
//                context.startActivity(intent);
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return listLop.size();
    }

    public int getSwipeLayoutResourceId(int position) {
        return R.id.swipe_dslop;
    }


}

