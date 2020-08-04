package com.example.quanlydiemsinhvien.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import com.example.quanlydiemsinhvien.R;
import com.example.quanlydiemsinhvien.data_models.DanhSachSinhVien;
import com.example.quanlydiemsinhvien.interfaces.AddSV;

public class Dialog_Add_SV extends DialogFragment {
    DanhSachSinhVien danhSachSinhVien = new DanhSachSinhVien();
    private EditText edtMaSV;
    private EditText edtTenSV;
    private AddSV listener;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        final LayoutInflater inflater = requireActivity().getLayoutInflater();
        //Set title dialog them sinh vien
        builder.setTitle(R.string.titleSinhVien);

        //Set dialog layout
        builder.setView(inflater.inflate(R.layout.dialog_addsv_layout, null));

        // Set them button
        builder.setPositiveButton(R.string.btnThemSV, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Dialog dialog = Dialog.class.cast(dialogInterface);

                // Get all view from layout dialog_them_nganh
                edtMaSV = dialog.findViewById(R.id.edtMaSV);
                edtTenSV = dialog.findViewById(R.id.edtTenSV);

                String maSV = edtMaSV.getText().toString();
                String tenSV = edtTenSV.getText().toString();

                danhSachSinhVien.setMaSV(maSV);
                danhSachSinhVien.setTenSV(tenSV);

                listener.applySV(danhSachSinhVien);
                Toast.makeText(getActivity(), "Them ma sinh vien button is tapped!" + danhSachSinhVien.toString(), Toast.LENGTH_SHORT).show();

            }
        });

        // Set Cancel button
        builder.setNegativeButton(R.string.btnCancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(getActivity(), "Cancel on DSSV is tapped!", Toast.LENGTH_SHORT).show();
                Dialog_Add_SV.this.getDialog().cancel();
            }
        });

        return builder.create();

    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try{
            listener = (AddSV) context;
        }catch(ClassCastException e){
            throw new ClassCastException(context.toString() + "Error!");
        }
    }
}
