package com.example.quanlydiemsinhvien.adapters;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.daimajia.swipe.SwipeLayout;
import com.daimajia.swipe.adapters.RecyclerSwipeAdapter;
import com.example.quanlydiemsinhvien.R;
import com.example.quanlydiemsinhvien.data_models.SinhVienModel;
import com.example.quanlydiemsinhvien.dialogs.DialogAddOrEditSinhVien;
import com.example.quanlydiemsinhvien.dialogs.DialogDeleteSinhVien;

import java.util.ArrayList;

public class SinhVienSwipeRecyclerViewAdapter extends RecyclerSwipeAdapter<SinhVienSwipeRecyclerViewAdapter.SimpleViewHolder> {
    private Context mContext;
    private ArrayList<SinhVienModel> sinhVienList;

    public static String KEY_SINHVIEN = "SinhVien";
    public static String KEY_POSITION = "position";

    public SinhVienSwipeRecyclerViewAdapter(Context context, ArrayList<SinhVienModel> objects) {
        this.mContext = context;
        this.sinhVienList = objects;
    }

    @Override
    public SimpleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_sinh_vien_layout, parent, false);
        return new SimpleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final SimpleViewHolder viewHolder, final int position) {
        final SinhVienModel sinhVien = sinhVienList.get(position);

        viewHolder.tvTenSV.setText((sinhVien.getHoSV()) + " " + sinhVien.getTenSV());
        viewHolder.tvMaSV.setText(sinhVien.getMaSV());


        viewHolder.swipeLayout.setShowMode(SwipeLayout.ShowMode.PullOut);



        // Drag From Right
        viewHolder.swipeLayout.addDrag(SwipeLayout.DragEdge.Right, viewHolder.swipeLayout.findViewById(R.id.bottom_wrapper));


        // Handling different events when swiping
        viewHolder.swipeLayout.addSwipeListener(new SwipeLayout.SwipeListener() {
            @Override
            public void onClose(SwipeLayout layout) {
                //when the SurfaceView totally cover the BottomView.
            }

            @Override
            public void onUpdate(SwipeLayout layout, int leftOffset, int topOffset) {
                //you are swiping.
            }

            @Override
            public void onStartOpen(SwipeLayout layout) {

            }

            @Override
            public void onOpen(SwipeLayout layout) {
                //when the BottomView totally show.
            }

            @Override
            public void onStartClose(SwipeLayout layout) {

            }

            @Override
            public void onHandRelease(SwipeLayout layout, float xvel, float yvel) {
                //when user's hand released.
            }
        });

        /*viewHolder.swipeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if ((((SwipeLayout) v).getOpenStatus() == SwipeLayout.Status.Close)) {
                    //Start your activity

                    Toast.makeText(mContext, " onClick : " + item.getName() + " \n" + item.getEmailId(), Toast.LENGTH_SHORT).show();
                }

            }
        });*/

        viewHolder.swipeLayout.getSurfaceView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, " onClick : " + sinhVien.getTenSV() + " \n" + sinhVien.getMaSV(), Toast.LENGTH_SHORT).show();
            }
        });


        viewHolder.tvEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final SinhVienModel sinhVien = sinhVienList.get(position);
                Bundle bundle = new Bundle();
                bundle.putSerializable(KEY_SINHVIEN, sinhVien);
                bundle.putInt(KEY_POSITION, position);
                DialogAddOrEditSinhVien dialogAddOrEditSinhVien = new DialogAddOrEditSinhVien();
                dialogAddOrEditSinhVien.setArguments(bundle);
                dialogAddOrEditSinhVien.show(((AppCompatActivity)mContext).getSupportFragmentManager(), "Edit");
                Toast.makeText(view.getContext(), "Clicked on Edit  " + viewHolder.tvTenSV.getText().toString(), Toast.LENGTH_SHORT).show();
            }
        });


        viewHolder.tvDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putSerializable(KEY_SINHVIEN, sinhVien);
                DialogDeleteSinhVien dialogDeleteSinhVien = new DialogDeleteSinhVien();
                dialogDeleteSinhVien.setArguments(bundle);
                dialogDeleteSinhVien.show(((AppCompatActivity)mContext).getSupportFragmentManager(), "Delete");
//                mItemManger.removeShownLayouts(viewHolder.swipeLayout);
//                sinhVienList.remove(position);
//                notifyItemRemoved(position);
//                notifyItemRangeChanged(position, sinhVienList.size());
//                mItemManger.closeAllItems();
                Toast.makeText(view.getContext(), "Deleted " + viewHolder.tvTenSV.getText().toString(), Toast.LENGTH_SHORT).show();
            }
        });

        // mItemManger is member in RecyclerSwipeAdapter Class
        mItemManger.bindView(viewHolder.itemView, position);

    }

    @Override
    public int getItemCount() {
        return sinhVienList.size();
    }

    @Override
    public int getSwipeLayoutResourceId(int position) {
        return R.id.swipe;
    }
    //  ViewHolder Class

    public static class SimpleViewHolder extends RecyclerView.ViewHolder {
        SwipeLayout swipeLayout;
        TextView tvTenSV;
        TextView tvMaSV;
        TextView tvDelete;
        TextView tvEdit;

        public SimpleViewHolder(View itemView) {
            super(itemView);
            swipeLayout = (SwipeLayout) itemView.findViewById(R.id.swipe);
            tvTenSV = (TextView) itemView.findViewById(R.id.txtTenSV);
            tvMaSV = (TextView) itemView.findViewById(R.id.txtMaSV);
            tvDelete = (TextView) itemView.findViewById(R.id.tvDelete);
            tvEdit = (TextView) itemView.findViewById(R.id.tvEdit);
        }
    }
}
