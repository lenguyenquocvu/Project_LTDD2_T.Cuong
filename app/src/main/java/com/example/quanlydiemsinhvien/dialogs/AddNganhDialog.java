package com.example.quanlydiemsinhvien.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.quanlydiemsinhvien.R;
import com.example.quanlydiemsinhvien.activities.NganhActivity;
import com.example.quanlydiemsinhvien.firebase_data.NganhDatabase;

public class AddNganhDialog extends DialogFragment {
    Context context;
    // Properties of view
    private EditText edtMaNganh;
    private EditText edtTenNganh;
    private TextView tvMaKhoa;
    private NganhDatabase myDatabase;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        // Create a new dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        /* Set content for dialog */
        // Set Dialog layout
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        final View view  = inflater.inflate(R.layout.dialog_them_nganh, null);
        builder.setView(view);

        // Set title for Dialog
        builder.setTitle(R.string.titleAddNganh);

        // Get view form layout
        edtMaNganh = view.findViewById(R.id.edtMaNganh);
        edtTenNganh = view.findViewById(R.id.edtTenNganh);
        tvMaKhoa = view.findViewById(R.id.tvMaKhoa);

        // Set Ma Khoa
        tvMaKhoa.setText(NganhActivity.getMaKhoaCuaNganh);

        // Set Them Nganh button
        builder.setPositiveButton(R.string.btnThem, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String maNganh = edtMaNganh.getText().toString();
                String tenNganh = edtTenNganh.getText().toString();
                String maKhoaCuaNganh = tvMaKhoa.getText().toString();

                // Write to firebase server
                myDatabase = new NganhDatabase();
                myDatabase.writeNewNganh(maKhoaCuaNganh, maNganh, tenNganh);

                Toast.makeText(getActivity(), "Thêm Ngành " + tenNganh + " thành công! ", Toast.LENGTH_SHORT).show();
            }
        });

        // Set Cancel button
        builder.setNegativeButton(R.string.btnCancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(getActivity(), "Cancel on Nganh is tapped!", Toast.LENGTH_SHORT).show();
                AddNganhDialog.this.dismiss();
            }
        });

        return builder.create();
    }
}


