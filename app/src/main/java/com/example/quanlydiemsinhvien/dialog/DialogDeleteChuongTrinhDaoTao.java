package com.example.quanlydiemsinhvien.dialog;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import com.example.quanlydiemsinhvien.activities.DanhSachLopHPTheoMHActivity;
import com.example.quanlydiemsinhvien.adapters.KhoaHocAdapter;
import com.example.quanlydiemsinhvien.interfaces.OnItemClickToDeleteListener;

public class DialogDeleteChuongTrinhDaoTao extends DialogFragment {
    private OnItemClickToDeleteListener deleteListener;
    private String maMH;
    private int position;
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Xóa môn học");
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
                if(getArguments() != null) {
                    maMH = getArguments().getString(DanhSachLopHPTheoMHActivity.DB_MAMH_COL);
                    position = getArguments().getInt(KhoaHocAdapter.POSITION_STRING);
                    getArguments().clear();
                    deleteListener = (OnItemClickToDeleteListener) getActivity();
                    deleteListener.deleteObject(maMH, position);
                }
            }
        });

        return builder.create();
    }
}
