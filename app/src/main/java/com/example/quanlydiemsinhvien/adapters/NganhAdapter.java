package com.example.quanlydiemsinhvien.adapters;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.daimajia.swipe.SwipeLayout;
import com.daimajia.swipe.adapters.RecyclerSwipeAdapter;
import com.example.quanlydiemsinhvien.R;
import com.example.quanlydiemsinhvien.activities.NganhActivity;
import com.example.quanlydiemsinhvien.data_models.Nganh;

import java.util.Vector;

public class NganhAdapter extends RecyclerSwipeAdapter<NganhAdapter.NganhViewHolder> {
    EditText edtMaNganh;
    EditText edtTenNganh;

    Context context;
    Intent intent;
    private Vector<Nganh> listNganh;

    public NganhAdapter(Vector<Nganh> listNganh, Context context) {
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
        Nganh theNganh = listNganh.get(position);
        holder.tvMaNganh.setText(theNganh.getMaNganh());
        holder.tvTenNganh.setText(theNganh.getTenNganh());

        // Set mode swipe
        holder.swipeLayout.setShowMode(SwipeLayout.ShowMode.PullOut);

        // Drag from right to left
        holder.swipeLayout.addDrag(SwipeLayout.DragEdge.Right, holder.swipeLayout.findViewById(R.id.dragToLeft));

        holder.tvEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(), "Edit Nganh button is tapped!", Toast.LENGTH_SHORT).show();
                Nganh recentNganh = NganhActivity.dataNganh.get(position);
                showEditNganhDialog(position, recentNganh, view);
            }
        });

        holder.tvDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDeleteNganhDialog(position);
                Toast.makeText(view.getContext(), "Delete Nganh button is tapped!", Toast.LENGTH_SHORT).show();
            }
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get context
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

        // Set titile Edit Nganh Dialog
        builder.setTitle(R.string.titileEditNganh);

        // Get layout
        view = LayoutInflater.from(context).inflate(R.layout.dialog_them_nganh, null);

        edtMaNganh = view.findViewById(R.id.edtMaNganh);
        edtTenNganh = view.findViewById(R.id.edtTenNganh);

        edtMaNganh.setText(recentNganh.getMaNganh());
        edtTenNganh.setText(recentNganh.getTenNganh());

        builder.setView(view);

        builder.setPositiveButton(R.string.btnEdit, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(context, "Edit Nganh button is tapped!", Toast.LENGTH_SHORT).show();
                Dialog dialog = Dialog.class.cast(dialogInterface);

                edtMaNganh = dialog.findViewById(R.id.edtMaNganh);
                edtTenNganh = dialog.findViewById(R.id.edtTenNganh);

                String maNganh = edtMaNganh.getText().toString();
                String tenNganh = edtTenNganh.getText().toString();

                recentNganh.setMaNganh(maNganh);
                recentNganh.setTenNganh(tenNganh);

                notifyItemChanged(position);
                NganhActivity.adapterNganh.notifyItemChanged(position);
            }
        });

        builder.setNegativeButton(R.string.btnCancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(context, "Cancel edit Nganh button is tapped!", Toast.LENGTH_SHORT).show();
                dialogInterface.dismiss();

            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    // Show delete Nganh dialog
    public void showDeleteNganhDialog(final int position) {
        Nganh nganh = NganhActivity.dataNganh.get(position);

        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        // Set titile for dialog
        builder.setTitle(R.string.titileDeleteNganh).setMessage(R.string.contentRemoveNganh);

        // Create Delete Khoa button
        builder.setPositiveButton(R.string.btnXoa, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                removeTheNganh(position);
                Toast.makeText(context, "Delete Nganh button is tapped", Toast.LENGTH_SHORT).show();
            }
        });

        // Create Cancel button
        builder.setNegativeButton(R.string.btnCancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(context, "Cancel Nganh button is tapped", Toast.LENGTH_SHORT).show();
            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    // Remove the Nganh
    public void removeTheNganh(int position) {
        NganhActivity.dataNganh.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, NganhActivity.dataNganh.size());
    }
}
