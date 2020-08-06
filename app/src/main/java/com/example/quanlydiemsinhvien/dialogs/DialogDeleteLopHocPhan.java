package com.example.quanlydiemsinhvien.dialogs;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import com.example.quanlydiemsinhvien.adapters.KhoaHocAdapter;
import com.example.quanlydiemsinhvien.adapters.LopHocPhanTheoMonAdapter;
import com.example.quanlydiemsinhvien.data_models.LopHocPhan;
import com.example.quanlydiemsinhvien.interfaces.OnItemClickToDeleteListener;

public class DialogDeleteLopHocPhan extends DialogFragment {
    private OnItemClickToDeleteListener deleteListener;
    private LopHocPhan lopHocPhan;
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Xóa lớp học phần");
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
                    lopHocPhan = (LopHocPhan) getArguments().getSerializable(LopHocPhanTheoMonAdapter.DELETE_LHP);
                    int position = getArguments().getInt(KhoaHocAdapter.POSITION_STRING);
                    getArguments().clear();
                    try {
                        deleteListener = (OnItemClickToDeleteListener) getActivity();
                    } catch (ClassCastException e) {
                        throw new ClassCastException(getActivity().toString() + "Error!");
                    }
                    deleteListener.deleteObject(lopHocPhan, position);

                }
            }
        });

        return builder.create();
    }
}
