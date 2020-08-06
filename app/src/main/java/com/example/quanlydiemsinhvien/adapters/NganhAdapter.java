package com.example.quanlydiemsinhvien.adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.daimajia.swipe.SwipeLayout;
import com.daimajia.swipe.adapters.RecyclerSwipeAdapter;
import com.example.quanlydiemsinhvien.R;
import com.example.quanlydiemsinhvien.activities.NganhActivity;
import com.example.quanlydiemsinhvien.data_models.Nganh;
import com.example.quanlydiemsinhvien.firebase_data.NganhDatabase;

import java.util.ArrayList;

public class NganhAdapter extends RecyclerSwipeAdapter<NganhAdapter.NganhViewHolder> {
    EditText edtMaNganh;
    EditText edtTenNganh;
    TextView tvMaKhoa;

    private Context context;
    Intent intent;
    Bundle data;
    private ArrayList<Nganh> listNganh;
    private NganhDatabase myDatabase;

    public NganhAdapter(ArrayList<Nganh> listNganh, Context context) {
        this.listNganh = listNganh;
        this.context = context;
    }

    @Override
    public int getSwipeLayoutResourceId(int position) {
        return R.id.nganh_swipe;
    }

    static class NganhViewHolder extends RecyclerView.ViewHolder {
        TextView tvMaNganh;
        TextView tvTenNganh;
        SwipeLayout swipeLayout;
        TextView tvEdit;
        TextView tvDelete;

        public NganhViewHolder(View itemView) {
            super(itemView);
            tvMaNganh = itemView.findViewById(R.id.tvMaNganh);
            tvTenNganh = itemView.findViewById(R.id.tvTenNganh);
            swipeLayout = itemView.findViewById(R.id.nganh_swipe);
            tvEdit = itemView.findViewById(R.id.tvEdit);
            tvDelete = itemView.findViewById(R.id.tvDelete);
        }
    }

    @NonNull
    @Override
    public NganhViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_nganh_recycleview_layout, parent, false);
        return new NganhViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NganhViewHolder holder, final int position) {
        final Nganh theNganh = listNganh.get(position);
        holder.tvMaNganh.setText(theNganh.getMaNganh());
        holder.tvTenNganh.setText(theNganh.getTenNganh());

        // Set mode swipe
        holder.swipeLayout.setShowMode(SwipeLayout.ShowMode.PullOut);

        // Drag from right to left
        holder.swipeLayout.addDrag(SwipeLayout.DragEdge.Right, holder.swipeLayout.findViewById(R.id.dragToLeft));

        holder.tvEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showEditNganhDialog(position, theNganh, view);
            }
        });

        holder.tvDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDeleteNganhDialog(position, theNganh);
            }
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Switch screen
                /*Context context = view.getContext();
                intent = new Intent();
                intent.setClass(context, );*/

                Log.d("test", "CLick a Nganh");
            }
        });

        mItemManger.bindView(holder.itemView, position);
    }

    @Override
    public int getItemCount() {
        return listNganh.size();
    }

    // Show edit Nganh dialog
    public void showEditNganhDialog(final int position, final Nganh recentNganh, View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        // Set title Edit Nganh Dialog
        builder.setTitle(R.string.titileEditNganh);

        // Get view from layout
        view = LayoutInflater.from(context).inflate(R.layout.dialog_them_nganh, null);
        builder.setView(view);

        edtMaNganh = view.findViewById(R.id.edtMaNganh);
        edtTenNganh = view.findViewById(R.id.edtTenNganh);
        tvMaKhoa = view.findViewById(R.id.tvMaKhoa);

        disableEditText(edtMaNganh);
        tvMaKhoa.setEnabled(false);

        edtMaNganh.setText(recentNganh.getMaNganh());
        edtTenNganh.setText(recentNganh.getTenNganh());
        tvMaKhoa.setText(NganhActivity.getMaKhoaCuaNganh);

        // Edit Button
        builder.setPositiveButton(R.string.btnEdit, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String maKhoaCuaNganh = tvMaKhoa.getText().toString();
                String maNganh = edtMaNganh.getText().toString();
                String tenNganh = edtTenNganh.getText().toString();

                // Write edit in the Firebase server
                myDatabase = new NganhDatabase();
                myDatabase.updateANganh(maKhoaCuaNganh, maNganh, tenNganh);
            }
        });

        // Cancel Button
        builder.setNegativeButton(R.string.btnCancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    // Show delete Nganh dialog
    public void showDeleteNganhDialog(final int position, final Nganh theNganh) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        // Set titile for dialog
        builder.setTitle(R.string.titileDeleteNganh).setMessage(R.string.contentRemoveNganh);

        // Create Delete Khoa button
        builder.setPositiveButton(R.string.btnXoa, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                myDatabase = new NganhDatabase();
                myDatabase.removeANganh(theNganh.getMaNganh());

            }
        });

        // Create Cancel button
        builder.setNegativeButton(R.string.btnCancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    public void disableEditText(EditText editText) {
        editText.setFocusable(false);
        editText.setEnabled(false);
        editText.setCursorVisible(false);
        editText.setKeyListener(null);
    }
}
