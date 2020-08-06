package com.example.quanlydiemsinhvien.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import com.example.quanlydiemsinhvien.adapters.SinhVienSwipeRecyclerViewAdapter;
import com.example.quanlydiemsinhvien.data_models.SinhVien;
import com.example.quanlydiemsinhvien.interfaces.OnItemClickToDeleteListener;

public class DialogDeleteSinhVien extends DialogFragment {
    private OnItemClickToDeleteListener listener;
    private SinhVien sinhVien;
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Xóa sinh viên");
        builder.setMessage("Bạn có chắc muốn xóa?");
        builder.setNegativeButton("Đóng", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(getArguments() != null){
                    sinhVien = (SinhVien) getArguments().getSerializable(SinhVienSwipeRecyclerViewAdapter.KEY_SINHVIEN);
                    listener.delete(sinhVien);
                }
            }
        });

         return builder.create();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        try {
            listener = (OnItemClickToDeleteListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + "Error!");
        }
    }
}
