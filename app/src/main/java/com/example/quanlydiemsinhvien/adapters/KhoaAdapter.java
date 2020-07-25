package com.example.quanlydiemsinhvien.adapters;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.provider.ContactsContract;
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

import java.util.Calendar;
import java.util.Vector;

public class KhoaAdapter extends RecyclerSwipeAdapter<KhoaAdapter.KhoaViewHolder> {
    EditText edtMaKhoa;
    EditText edtTenKhoa;
    DatePicker dpNgayThanhLap;

    private Context context;
    private Vector<Khoa> listCarKhoa;
    public static Intent intent;

    public KhoaAdapter(Context context, Vector<Khoa> listCarKhoa) {
        this.context = context;
        this.listCarKhoa = listCarKhoa;
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
        Khoa theKhoa = listCarKhoa.get(position);
        holder.tvMaKhoa.setText(theKhoa.getMaKhoa());
        holder.tvTenKhoa.setText(theKhoa.getTenKhoa());
        holder.tvNgayThanhLap.setText(theKhoa.getNgayThanhLap());

        // Set mode swipe
        holder.swipeLayout.setShowMode(SwipeLayout.ShowMode.PullOut);

        // Drag from right to left
        holder.swipeLayout.addDrag(SwipeLayout.DragEdge.Right, holder.swipeLayout.findViewById(R.id.dragToLeft));

        // Event when swiping
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
                Khoa recentKhoa = KhoaActivity.dataKhoa.get(position);
                showDialogEditKhoa(recentKhoa, view, position);
            }
        });

        //Event for Delete Button
        holder.tvDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "Delete" + position + "is tapped!", Toast.LENGTH_SHORT).show();
                showDialogRemoveKhoa(position);
            }
        });

        // Event when click to the item on RecyclerView
        holder.swipeLayout.getSurfaceView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = view.getContext();

                intent = KhoaActivity.intent;
                intent.setClass(context, NganhActivity.class);
                context.startActivity(intent);

                Log.d("test", "position: " + position);
            }
        });

        mItemManger.bindView(holder.itemView, position);
    }

    @Override
    public int getItemCount() {
        return listCarKhoa.size();
    }

    // Show dialog Remove the Khoa
    public void showDialogRemoveKhoa(final int position) {
        final Khoa recentKhoa = KhoaActivity.dataKhoa.get(position);

        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        builder.setTitle(R.string.titleRemoveKhoa).setMessage(R.string.contentRemove);

        builder.setPositiveButton(R.string.btnXoa, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(context, "Xoa Khoa " + recentKhoa.getMaKhoa() + "is tapped!", Toast.LENGTH_SHORT).show();
                removeTheKhoa(position);
            }
        });

        builder.setNegativeButton(R.string.btnCancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(context, "Cancel Khoa is tapped!", Toast.LENGTH_SHORT).show();

            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    // Remove the khoa
    public void removeTheKhoa(int position) {
        KhoaActivity.dataKhoa.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, KhoaActivity.dataKhoa.size());
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
        edtMaKhoa.setText(recentKhoa.getMaKhoa());
        edtTenKhoa.setText(recentKhoa.getTenKhoa());

        String ngayThanhLap = recentKhoa.getNgayThanhLap();
        String arrNgayThanhLap[] = ngayThanhLap.split("/");
        final int day = Integer.parseInt(arrNgayThanhLap[0]);
        int month = Integer.parseInt(arrNgayThanhLap[1]);
        int year = Integer.parseInt(arrNgayThanhLap[2]);
        dpNgayThanhLap.updateDate(year, month, day);


        // Set button Edit Khoa
        builder.setPositiveButton(R.string.btnSua, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Dialog dialog = Dialog.class.cast(dialogInterface);

                // Get view from layout
                edtMaKhoa = dialog.findViewById(R.id.edtMaKhoa);
                edtTenKhoa = dialog.findViewById(R.id.edtTenKhoa);
                dpNgayThanhLap = dialog.findViewById(R.id.dtpNgayThanhLap);

                String maKhoa = edtMaKhoa.getText().toString();
                String tenKhoa = edtTenKhoa.getText().toString();
                int day = dpNgayThanhLap.getDayOfMonth();
                int month = dpNgayThanhLap.getMonth() + 1;
                int year = dpNgayThanhLap.getYear();
                String ngayThanhLap = checkDigit(day) + "/" + checkDigit(month) + "/" + year;

                edtMaKhoa.setText(maKhoa);
                edtTenKhoa.setText(tenKhoa);
                dpNgayThanhLap.updateDate(year, month, day);

                recentKhoa.setMaKhoa(maKhoa);
                recentKhoa.setTenKhoa(tenKhoa);
                recentKhoa.setNgayThanhLap(ngayThanhLap);

                notifyItemChanged(position);
                KhoaActivity.khoaAdapter.notifyItemChanged(position);
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

}
