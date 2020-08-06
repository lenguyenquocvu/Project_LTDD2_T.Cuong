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
import com.example.quanlydiemsinhvien.interfaces.OnItemClickToEditListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DialogEditMonHoc extends DialogFragment {
    private OnItemClickToEditListener editListener;
    private MonHoc monHoc = new MonHoc();
    FirebaseDatabase rootNode;
    DatabaseReference reference;
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

        rootNode = FirebaseDatabase.getInstance();
        reference = rootNode.getReference("MonHoc");

        if(!getArguments().isEmpty()){

            MonHoc mh2 = (MonHoc) getArguments().getSerializable("mon hoc");
            String key = mh2.getMaMH();

            reference.child(key).removeValue();

            monHoc = (MonHoc) getArguments().getSerializable("mon hoc");
            edtMaMH.setText(monHoc.getMaMH());
            edtTenMH.setText(monHoc.getTenMH());
            edtSoTinChi.setText(monHoc.getSoTinChi() + "");
            edtMaNganh.setText(monHoc.getMaNganh());
        }

        builder.setTitle("Thêm môn học");
        builder.setView(view);
        builder.setNegativeButton(DialogAddOrEditKhoahoc.CANCEL_STRING, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        builder.setPositiveButton("Sửa", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                monHoc.setMaMH(edtMaMH.getText().toString());
                monHoc.setTenMH(edtTenMH.getText().toString());
                monHoc.setSoTinChi(Integer.parseInt(edtSoTinChi.getText().toString()));
                monHoc.setMaNganh(edtMaNganh.getText().toString());

                editListener = (OnItemClickToEditListener) getActivity();
                editListener.editObject(monHoc);
            }
        });
        return builder.create();
    }
}
