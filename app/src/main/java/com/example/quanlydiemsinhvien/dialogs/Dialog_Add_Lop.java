package com.example.quanlydiemsinhvien.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import com.example.quanlydiemsinhvien.R;
import com.example.quanlydiemsinhvien.data_models.DanhSachLop;
import com.example.quanlydiemsinhvien.firebase_data.DSLopDatabase;
import com.example.quanlydiemsinhvien.interfaces.AddLop;

public class Dialog_Add_Lop extends DialogFragment {
    DanhSachLop danhSachLop = new DanhSachLop();
    private EditText edtMaLHP;
    private EditText edtTenLHP;
    private EditText edtMaGV;
    private EditText edtMaHK;
    private EditText edtMaNH;
    private EditText edtMaMH;
    private EditText edtKetThuc;
    private AddLop listener;
    DSLopDatabase myDatabase;
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        //Create a new dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        final LayoutInflater inflater = requireActivity().getLayoutInflater();
        //Set title dialog them lop
        builder.setTitle(R.string.titleLop);

        //Set dialog layout
        builder.setView(inflater.inflate(R.layout.dialog_addlop_layout, null));

        //Set button them lop
        builder.setPositiveButton(DialogAddOrEditKhoahoc.OK_STRING, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Dialog dialog = Dialog.class.cast(dialogInterface);

                // Get all view from layout
                edtMaLHP = dialog.findViewById(R.id.edtMaLHP);
                edtTenLHP = dialog.findViewById(R.id.edtTenLHP);
                edtMaGV = dialog.findViewById(R.id.edtMaGV);
                edtMaHK = dialog.findViewById(R.id.edtMaHK);
                edtMaNH = dialog.findViewById(R.id.edtMaNH);
                edtMaMH = dialog.findViewById(R.id.edtmaMH);
                edtKetThuc = dialog.findViewById(R.id.edtKetThuc);

                // lay tat cac thuoc tinh
                String maLHP = edtMaLHP.getText().toString();
                String tenLHP = edtTenLHP.getText().toString();
                String maGV = edtMaGV.getText().toString();
                String maHK = edtMaHK.getText().toString();
                String maMH = edtMaMH.getText().toString();
                String maNH = edtMaNH.getText().toString();
                boolean ketThuc = false;
                // Tao database
                myDatabase = new DSLopDatabase();
                myDatabase.writeNewLop(maLHP, tenLHP, maGV, maHK, maMH, maNH, ketThuc);


//                danhSachLop.setMaLHP(maLHP);
//                danhSachLop.setTenLHP(tenLHP);
                Toast.makeText(getActivity(), "Them lop hoc phan button is tapped!" + danhSachLop.toString(), Toast.LENGTH_SHORT).show();
            }
        });

        // Set Cancel button
        builder.setNegativeButton(DialogAddOrEditKhoahoc.THOAT_STRING, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(getActivity(), "Cancel on DSLop is tapped!", Toast.LENGTH_SHORT).show();
                Dialog_Add_Lop.this.getDialog().cancel();
            }
        });

        return builder.create();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try{
            listener = (AddLop) context;
        }catch(ClassCastException e){
            throw new ClassCastException(context.toString() + "Error!");
        }

    }
}
