package com.example.quanlydiemsinhvien.adapters;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.daimajia.swipe.SwipeLayout;
import com.daimajia.swipe.adapters.RecyclerSwipeAdapter;
import com.example.quanlydiemsinhvien.R;
import com.example.quanlydiemsinhvien.data_models.GiangVienModel;
import com.example.quanlydiemsinhvien.data_models.NganhModel;
import com.example.quanlydiemsinhvien.dialogs.DialogAddOrEditGiangVien;
import com.example.quanlydiemsinhvien.dialogs.DialogDeleteGiangVien;
import com.example.quanlydiemsinhvien.interfaces.OnItemClickToEditGiangVienListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.Serializable;
import java.util.ArrayList;

public class GiangVienSwipeRecyclerViewAdapter extends RecyclerSwipeAdapter<GiangVienSwipeRecyclerViewAdapter.SimpleViewHolder> {
    private Context mContext;
    private ArrayList<GiangVienModel> giangVienList;

    public static String KEY_GIANGVIEN = "GiangVien";
    public static String KEY_POSITION = "position";

    public GiangVienSwipeRecyclerViewAdapter(Context context, ArrayList<GiangVienModel> objects) {
        this.mContext = context;
        this.giangVienList = objects;
    }

    @Override
    public SimpleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_giang_vien_layout, parent, false);
        return new SimpleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final SimpleViewHolder viewHolder, final int position) {
        final GiangVienModel giangVien = giangVienList.get(position);

        viewHolder.tvName.setText((giangVien.getHoGV() + " " + giangVien.getTenGV()));
        viewHolder.tvId.setText(giangVien.getMaGV());


        viewHolder.swipeLayout.setShowMode(SwipeLayout.ShowMode.PullOut);



        // Drag From Right
        viewHolder.swipeLayout.addDrag(SwipeLayout.DragEdge.Right, viewHolder.swipeLayout.findViewById(R.id.bottom_wrapper));

        viewHolder.swipeLayout.getSurfaceView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Context context = v.getContext();
//                Intent intent = DanhSachGiangVienActivity.intent;
//                intent.setClass(context, DanhSachGiangVienActivity.class);
//                intent.putExtra(KEY_MAGV, DanhSachGiangVienActivity.)
            }
        });


        viewHolder.tvEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final GiangVienModel giangVien = giangVienList.get(position);
                Bundle bundle = new Bundle();
                bundle.putSerializable(KEY_GIANGVIEN, giangVien);
                bundle.putInt(KEY_POSITION, position);
                DialogAddOrEditGiangVien dialogAddOrEditGiangVien = new DialogAddOrEditGiangVien();
                dialogAddOrEditGiangVien.setArguments(bundle);
                dialogAddOrEditGiangVien.show(((AppCompatActivity)mContext).getSupportFragmentManager(), "Edit");
                Toast.makeText(v.getContext(), "Clicked on Edit  " + viewHolder.tvName.getText().toString(), Toast.LENGTH_SHORT).show();
            }
        });


        viewHolder.tvDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putSerializable(KEY_GIANGVIEN, giangVien);
                DialogDeleteGiangVien deleteGiangVien = new DialogDeleteGiangVien();
                deleteGiangVien.setArguments(bundle);
                deleteGiangVien.show(((AppCompatActivity)mContext).getSupportFragmentManager(), "Delete");
//                mItemManger.removeShownLayouts(viewHolder.swipeLayout);
//                giangVienList.remove(position);
//                notifyItemRemoved(position);
//                notifyItemRangeChanged(position, giangVienList.size());
//                mItemManger.closeAllItems();
                Toast.makeText(view.getContext(), "Deleted " + viewHolder.tvName.getText().toString(), Toast.LENGTH_SHORT).show();
            }
        });

        // mItemManger is member in RecyclerSwipeAdapter Class
        mItemManger.bindView(viewHolder.itemView, position);

    }


    @Override
    public int getItemCount() {
        return giangVienList.size();
    }

    @Override
    public int getSwipeLayoutResourceId(int position) {
        return R.id.swipe;
    }
    //  ViewHolder Class

    public static class SimpleViewHolder extends RecyclerView.ViewHolder {
        SwipeLayout swipeLayout;
        TextView tvName;
        TextView tvId;
        TextView tvDelete;
        TextView tvEdit;

        public SimpleViewHolder(View itemView) {
            super(itemView);
            swipeLayout = (SwipeLayout) itemView.findViewById(R.id.swipe);
            tvName = (TextView) itemView.findViewById(R.id.txtTenGV);
            tvId = (TextView) itemView.findViewById(R.id.txtMaGV);
            tvDelete = (TextView) itemView.findViewById(R.id.tvDelete);
            tvEdit = (TextView) itemView.findViewById(R.id.tvEdit);
        }
    }
}
