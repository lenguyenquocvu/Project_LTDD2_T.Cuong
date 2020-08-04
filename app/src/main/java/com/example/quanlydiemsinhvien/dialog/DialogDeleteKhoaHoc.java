package com.example.quanlydiemsinhvien.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import com.example.quanlydiemsinhvien.adapters.KhoaHocAdapter;
import com.example.quanlydiemsinhvien.data_models.KhoaHoc;
import com.example.quanlydiemsinhvien.interfaces.OnItemClickToDeleteListener;

public class DialogDeleteKhoaHoc extends DialogFragment {
    private OnItemClickToDeleteListener listener;
    private KhoaHoc khoaHoc;
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Xóa khóa học");
        builder.setMessage("Bạn có muốn xóa không?");
        builder.setNegativeButton(DialogAddOrEditKhoahoc.CANCEL_STRING, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        builder.setPositiveButton(DialogAddOrEditKhoahoc.OK_STRING, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(getArguments() != null){
                    khoaHoc = (KhoaHoc) getArguments().getSerializable(KhoaHocAdapter.DELETE_KHOAHOC);

                    int position = getArguments().getInt(KhoaHocAdapter.POSITION_STRING);
                    getArguments().clear();

                    listener.deleteObject(khoaHoc, position);
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
