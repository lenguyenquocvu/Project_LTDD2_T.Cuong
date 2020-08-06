package com.example.quanlydiemsinhvien.dialogs;

import android.app.Dialog;
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
import com.example.quanlydiemsinhvien.data_models.MonHoc;
import com.example.quanlydiemsinhvien.interfaces.OnItemClickToAddListener;

public class DialogThemMonHoc extends DialogFragment {
    private OnItemClickToAddListener addListener;
    private MonHoc monHoc = new MonHoc();
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = LayoutInflater.from(getContext());
        View view = inflater.inflate(R.layout.dialog_them_mon_hoc_layout, null);

        final EditText edtMaMH = view.findViewById(R.id.edtMaMH);
        final EditText edtTenMH = view.findViewById(R.id.edtTenMH);
        final EditText edtSoTinChi = view.findViewById(R.id.edtSoTinChi);
        final EditText edtMaNganh = view.findViewById(R.id.edtMaNganh);

        builder.setTitle("Thêm môn học");
        builder.setView(view);
        builder.setNegativeButton(DialogAddOrEditKhoahoc.CANCEL_STRING, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        builder.setPositiveButton("Thêm", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                monHoc.setMaMH(edtMaMH.getText().toString());
                monHoc.setTenMH(edtTenMH.getText().toString());
                monHoc.setSoTinChi(Integer.parseInt(edtSoTinChi.getText().toString()));
                monHoc.setMaNganh(edtMaNganh.getText().toString());

                addListener = (OnItemClickToAddListener) getActivity();
                addListener.applyObject(monHoc);
            }
        });
        return builder.create();
    }
}
