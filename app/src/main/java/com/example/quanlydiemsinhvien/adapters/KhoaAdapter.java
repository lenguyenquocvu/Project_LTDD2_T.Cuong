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
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.daimajia.swipe.SwipeLayout;
import com.daimajia.swipe.adapters.RecyclerSwipeAdapter;
import com.example.quanlydiemsinhvien.R;
import com.example.quanlydiemsinhvien.activities.KhoaActivity;
import com.example.quanlydiemsinhvien.activities.NganhActivity;
import com.example.quanlydiemsinhvien.data_models.Khoa;
import com.example.quanlydiemsinhvien.firebase_data.KhoaDatabase;

import java.util.ArrayList;

public class KhoaAdapter extends RecyclerSwipeAdapter<KhoaAdapter.KhoaViewHolder> {
    public static final String KEY_MAKHOA = "ma_khoa";
    public static final String KEY_TENKHOA = "ten_khoa";
    public static final String KEY_DATA = "data";
    ArrayList<Khoa> listKhoa = new ArrayList<Khoa>();

    EditText edtMaKhoa;
    EditText edtTenKhoa;
    DatePicker dpNgayThanhLap;

    private Context context;
    public static Intent intent;
    private Bundle bundle;

    KhoaDatabase mKhoaDatabase = new KhoaDatabase();

    public KhoaAdapter(ArrayList<Khoa> listKhoa, Context context) {
        this.listKhoa = listKhoa;
        this.context = context;
    }

    @Override
    public int getSwipeLayoutResourceId(int position) {
        return R.id.khoa_swipe;
    }

    static class KhoaViewHolder extends RecyclerView.ViewHolder {
        private LinearLayout itemKhoa;
        TextView tvTenKhoa;
        TextView tvMaKhoa;
        TextView tvNgayThanhLap;
        SwipeLayout swipeLayout;
        TextView tvEdit;
        TextView tvDelete;

        public KhoaViewHolder(View itemView) {
            super(itemView);
            tvMaKhoa = itemView.findViewById(R.id.tvMaKhoa);
            tvTenKhoa = itemView.findViewById(R.id.tvTenKhoa);
            tvNgayThanhLap = itemView.findViewById(R.id.tvNgayThanhLap);
            swipeLayout = itemView.findViewById(R.id.khoa_swipe);
            tvEdit = itemView.findViewById(R.id.tvEdit);
            tvDelete = itemView.findViewById(R.id.tvDelete);
        }
    }

    @Override
    public KhoaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_khoa_recyclerview_layout, parent, false);
        return new KhoaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final KhoaViewHolder holder, final int position) {
        final Khoa theKhoa = listKhoa.get(position);
        holder.tvMaKhoa.setText(theKhoa.getMaKhoa());
        holder.tvTenKhoa.setText(theKhoa.getTenKhoa());
        holder.tvNgayThanhLap.setText(theKhoa.getNgayThanhLap());

        // Set mode swipe
        holder.swipeLayout.setShowMode(SwipeLayout.ShowMode.PullOut);

        // Drag from right to left
        holder.swipeLayout.addDrag(SwipeLayout.DragEdge.Right, holder.swipeLayout.findViewById(R.id.dragToLeft));

        // Action when swiping
        holder.swipeLayout.addSwipeListener(new SwipeLayout.SwipeListener() {
            @Override
            public void onStartOpen(SwipeLayout layout) {

            }

            @Override
            public void onOpen(SwipeLayout layout) {

            }

            @Override
            public void onStartClose(SwipeLayout layout) {

            }

            @Override
            public void onClose(SwipeLayout layout) {

            }

            @Override
            public void onUpdate(SwipeLayout layout, int leftOffset, int topOffset) {

            }

            @Override
            public void onHandRelease(SwipeLayout layout, float xvel, float yvel) {

            }
        });

        // Event for Edit button
        holder.tvEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialogEditKhoa(theKhoa, view, position);
            }
        });

        //Event for Delete Button
        holder.tvDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "Delete" + position + "is tapped!", Toast.LENGTH_SHORT).show();
                showDialogRemoveKhoa(theKhoa);
            }
        });

        // Event when click to the item on RecyclerView
        holder.swipeLayout.getSurfaceView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Khoa recentKhoa = KhoaActivity.dataKhoa.get(position);
                Context context = view.getContext();

                bundle = new Bundle();
                bundle.putString(KEY_MAKHOA, recentKhoa.getMaKhoa());
                bundle.putString(KEY_TENKHOA, recentKhoa.getTenKhoa());

                intent = KhoaActivity.intent;
                intent.setClass(context, NganhActivity.class);
                intent.putExtra(KEY_DATA, bundle);

                context.startActivity(intent);

                Log.d("test", "position: " + position);
            }
        });

        mItemManger.bindView(holder.itemView, position);
    }

    @Override
    public int getItemCount() {
        return listKhoa.size();
    }

    // Show dialog Remove the Khoa
    public void showDialogRemoveKhoa(final Khoa theKhoa) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        builder.setTitle(R.string.titleRemoveKhoa).setMessage(R.string.contentRemove);

        builder.setPositiveButton(R.string.btnXoa, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(context, "Xoa Khoa " + theKhoa.getMaKhoa() + "is tapped!", Toast.LENGTH_SHORT).show();

                // Delete A Khoa to Firebase server
                mKhoaDatabase.deleteAKhoa(theKhoa.getMaKhoa());
            }
        });

        builder.setNegativeButton(R.string.btnCancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(context, "Cancel Khoa", Toast.LENGTH_SHORT).show();
            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    // Show dialog Edit the dialog
    public void showDialogEditKhoa(final Khoa recentKhoa, View view, final int position) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(context);

        // Set title for Dialog
        builder.setTitle(R.string.titleEditKhoa);

        // Get view layout dialog
        view = LayoutInflater.from(context).inflate(R.layout.dialog_them_khoa, null);

        // Get all view from layout
        edtMaKhoa = view.findViewById(R.id.edtMaKhoa);
        edtTenKhoa = view.findViewById(R.id.edtTenKhoa);
        dpNgayThanhLap = view.findViewById(R.id.dtpNgayThanhLap);

        // Set data to dialog
        disableEditText(edtMaKhoa);
        edtMaKhoa.setText(recentKhoa.getMaKhoa());
        edtTenKhoa.setText(recentKhoa.getTenKhoa());

        String ngayThanhLap = recentKhoa.getNgayThanhLap();
        String arrNgayThanhLap[] = ngayThanhLap.split("/");
        final int day = Integer.parseInt(arrNgayThanhLap[0]);
        int month = Integer.parseInt(arrNgayThanhLap[1]);
        int year = Integer.parseInt(arrNgayThanhLap[2]);
        dpNgayThanhLap.updateDate(year, month - 1, day);

        // Set button Edit Khoa
        builder.setPositiveButton(R.string.btnSua, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                String maKhoa = edtMaKhoa.getText().toString();
                String tenKhoa = edtTenKhoa.getText().toString();
                int day = dpNgayThanhLap.getDayOfMonth();
                int month = dpNgayThanhLap.getMonth() + 1;
                int year = dpNgayThanhLap.getYear();
                String ngayThanhLap = checkDigit(day) + "/" + checkDigit(month) + "/" + year;

                edtMaKhoa.setText(maKhoa);
                edtTenKhoa.setText(tenKhoa);
                dpNgayThanhLap.updateDate(year, month, day);

                // Update database A Khoa into Firebase database
                mKhoaDatabase.updateAKhoa(maKhoa, tenKhoa, ngayThanhLap);
            }
        });

        builder.setNegativeButton(R.string.btnCancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });

        builder.setView(view);

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    public String checkDigit(int number) {
        return number <= 9 ? "0" + number : number + "";
    }

    public void disableEditText(EditText editText) {
        editText.setFocusable(false);
        editText.setEnabled(false);
        editText.setCursorVisible(false);
        editText.setKeyListener(null);
    }

}
