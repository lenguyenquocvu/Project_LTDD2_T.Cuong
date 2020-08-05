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
import com.example.quanlydiemsinhvien.data_models.ChuongTrinhDaoTao;
import com.example.quanlydiemsinhvien.interfaces.OnItemClickToAddListener;
import com.example.quanlydiemsinhvien.interfaces.OnItemClickToEditListener;

public class DialogAddChuongTrinhDaoTao extends DialogFragment {
    private OnItemClickToAddListener addListener;
    private OnItemClickToEditListener editListener;
    private ChuongTrinhDaoTao monHoc;
    private String maMH;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_sua_mon_hoc__ctdt_layout, null);

        final EditText edtMaMH = view.findViewById(R.id.edtMaMH);
        final EditText edtTenMH = view.findViewById(R.id.edtTenMH);
        final EditText edtSoTinChi = view.findViewById(R.id.edtSoTinChi);

        edtMaMH.setEnabled(true);
        edtTenMH.setVisibility(View.GONE);
        edtSoTinChi.setVisibility(View.GONE);
        builder.setTitle("Thêm môn học");


        builder.setView(view);


        builder.setNegativeButton(DialogAddOrEditKhoahoc.CANCEL_STRING, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        builder.setPositiveButton(DialogAddOrEditKhoahoc.OK_STRING, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                maMH = edtMaMH.getText().toString();
                addListener = (OnItemClickToAddListener) getActivity();
                addListener.applyObject(maMH);
            }
        });

        return builder.create();
    }
}
