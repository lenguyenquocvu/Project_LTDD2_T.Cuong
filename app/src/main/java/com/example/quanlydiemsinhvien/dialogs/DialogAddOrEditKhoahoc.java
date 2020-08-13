package com.example.quanlydiemsinhvien.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import com.example.quanlydiemsinhvien.R;
import com.example.quanlydiemsinhvien.adapters.KhoaHocAdapter;
import com.example.quanlydiemsinhvien.data_models.KhoaHoc;
import com.example.quanlydiemsinhvien.interfaces.OnItemClickToAddListener;
import com.example.quanlydiemsinhvien.interfaces.OnItemClickToEditListener;

public class DialogAddOrEditKhoahoc extends DialogFragment {
    private EditText edtBatdau;
    private EditText edtKetthuc;
    private OnItemClickToAddListener listener;
    EditText edtMaKH;
    KhoaHoc khoaHoc;
    private OnItemClickToEditListener editKhoaHocListener;
    public static final String OK_STRING = "OK";
    public static final String THOAT_STRING = "THOÁT";

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_sua_khoahoc_layout, null);

        edtBatdau = view.findViewById(R.id.edtKhBatDau);
        edtKetthuc = view.findViewById(R.id.edtKhKetThuc);
        edtMaKH = view.findViewById(R.id.edtMaKHInfo);
        builder.setView(view);

        if (getArguments() != null) {
            khoaHoc = (KhoaHoc) getArguments().getSerializable(KhoaHocAdapter.EDIT_KHOAHOC);
            edtMaKH.setEnabled(false);
            edtMaKH.setText(khoaHoc.getMaKH());
            edtBatdau.setText(khoaHoc.getBatDau() + "");
            edtKetthuc.setText(khoaHoc.getKetThuc() + "");
            builder.setTitle("Cập nhật Khóa học");
        } else {
            edtMaKH.setEnabled(true);
            builder.setTitle("Thêm Khóa học");
        }


        builder.setPositiveButton(OK_STRING, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (getArguments() == null) {
                    khoaHoc = new KhoaHoc();

                    khoaHoc.setMaKH(edtMaKH.getText().toString());
                    khoaHoc.setBatDau(Integer.parseInt(edtBatdau.getText().toString()));
                    khoaHoc.setKetThuc(Integer.parseInt(edtKetthuc.getText().toString()));

                    listener.applyObject(khoaHoc);
                } else {
                    khoaHoc.setMaKH(edtMaKH.getText().toString());
                    khoaHoc.setBatDau(Integer.parseInt(edtBatdau.getText().toString()));
                    khoaHoc.setKetThuc(Integer.parseInt(edtKetthuc.getText().toString()));
                    getArguments().clear();
                    editKhoaHocListener = (OnItemClickToEditListener) getActivity();
                    editKhoaHocListener.editObject(khoaHoc);
                }
            }
        })
                .setNegativeButton(THOAT_STRING, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dismiss();
                    }
                });


        return builder.create();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            listener = (OnItemClickToAddListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + "Error!");
        }
    }

}
