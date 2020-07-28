package com.example.quanlydiemsinhvien.dialog;

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
import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.fragment.app.DialogFragment;

import com.example.quanlydiemsinhvien.R;
import com.example.quanlydiemsinhvien.data_models.KhoaHoc;
import com.example.quanlydiemsinhvien.interfaces.OnItemClickToAddKhoahocListener;
import com.example.quanlydiemsinhvien.interfaces.OnItemClickToEditKhoaHocListener;

import java.util.ArrayList;

public class DialogAddOrEditKhoahoc extends DialogFragment {
    private EditText edtBatdau;
    private EditText edtKetthuc;
    private OnItemClickToAddKhoahocListener listener;
    EditText edtMaKH;
    KhoaHoc khoaHoc;
    private OnItemClickToEditKhoaHocListener editKhoaHocListener;

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
            khoaHoc = (KhoaHoc) getArguments().getSerializable("khoa hoc");
            edtMaKH.setEnabled(false);
            edtMaKH.setText(khoaHoc.getMaKH());
            edtBatdau.setText(khoaHoc.getBatDau() + "");
            edtKetthuc.setText(khoaHoc.getKetThuc() + "");
            builder.setTitle("Update Khóa học");
        } else {
            edtMaKH.setEnabled(true);
            builder.setTitle("Add Khóa học");
        }


        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (getArguments() == null) {
                    khoaHoc = new KhoaHoc();

                    khoaHoc.setMaKH(edtMaKH.getText().toString());
                    khoaHoc.setBatDau(Integer.parseInt(edtBatdau.getText().toString()));
                    khoaHoc.setKetThuc(Integer.parseInt(edtKetthuc.getText().toString()));

                    listener.applyKhoaHoc(khoaHoc);
                } else {
                    khoaHoc.setMaKH(edtMaKH.getText().toString());
                    khoaHoc.setBatDau(Integer.parseInt(edtBatdau.getText().toString()));
                    khoaHoc.setKetThuc(Integer.parseInt(edtKetthuc.getText().toString()));
                    int position = getArguments().getInt("position");
                    editKhoaHocListener = (OnItemClickToEditKhoaHocListener) getActivity();
                    editKhoaHocListener.onItemClicked(khoaHoc, position);
                    getArguments().clear();
                }
            }
        })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
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
            listener = (OnItemClickToAddKhoahocListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + "Error!");
        }
    }

}
