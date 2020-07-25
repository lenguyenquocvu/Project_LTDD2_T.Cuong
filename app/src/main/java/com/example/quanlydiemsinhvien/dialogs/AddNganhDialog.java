package com.example.quanlydiemsinhvien.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.quanlydiemsinhvien.activities.NganhActivity;
import com.example.quanlydiemsinhvien.data_models.Nganh;
import com.example.quanlydiemsinhvien.R;

public class AddNganhDialog extends DialogFragment {
    // Properties of view
    private EditText edtMaNganh;
    private EditText edtTenNganh;
    private Spinner spnMaKhoa;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        // Create a new dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        /* Set content for dialog */
        // Set title for Dialog
        builder.setTitle(R.string.titleAddNganh);

        // Set Dialog layout
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        builder.setView(inflater.inflate(R.layout.dialog_them_nganh, null));

        // Set Them Nganh button
        builder.setPositiveButton(R.string.btnThem, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Dialog dialog = Dialog.class.cast(dialogInterface);

                edtMaNganh = dialog.findViewById(R.id.edtMaNganh);
                edtTenNganh = dialog.findViewById(R.id.edtTenNganh);

                String maNganh = edtMaNganh.getText().toString();
                String tenNganh = edtTenNganh.getText().toString();

                NganhActivity.dataNganh.add(new Nganh(maNganh, tenNganh));
                NganhActivity.adapterNganh.notifyDataSetChanged();

                Toast.makeText(getActivity(), "Thêm thành công! ", Toast.LENGTH_SHORT).show();
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


