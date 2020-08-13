package com.example.quanlydiemsinhvien.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.DialogFragment;

import com.example.quanlydiemsinhvien.R;
import com.example.quanlydiemsinhvien.data_models.Khoa;
import com.example.quanlydiemsinhvien.firebase_data.KhoaDatabase;


public class AddKhoaDialog extends DialogFragment {

    private EditText edtMaKhoa;
    private EditText edtTenKhoa;
    private DatePicker dtpNgayThanhLap;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        // Get layout inflater
        final LayoutInflater inflater = requireActivity().getLayoutInflater();

        // Set title for dialog them khoa moi
        builder.setTitle(R.string.titleAddKhoa);

        // Set dialog layout
        builder.setView(inflater.inflate(R.layout.dialog_them_khoa, null));

        // Set button Them
        builder.setPositiveButton(DialogAddOrEditKhoahoc.OK_STRING, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Dialog dialog = Dialog.class.cast(dialogInterface);

                // Get view from layout
                edtMaKhoa = dialog.findViewById(R.id.edtMaKhoa);
                edtTenKhoa = dialog.findViewById(R.id.edtTenKhoa);
                dtpNgayThanhLap = dialog.findViewById(R.id.dtpNgayThanhLap);

                int day = dtpNgayThanhLap.getDayOfMonth();
                int month = dtpNgayThanhLap.getMonth() + 1;
                int year = dtpNgayThanhLap.getYear();

                String maKhoa = edtMaKhoa.getText().toString();
                String tenKhoa = edtTenKhoa.getText().toString();
                String ngayThanhLap = checkDigit(day) + "/" + checkDigit(month) + "/" + year;

                Khoa khoa = new Khoa(maKhoa, tenKhoa, ngayThanhLap);

                // Add new khoa to data base
                if (maKhoa.isEmpty() || tenKhoa.isEmpty()) {
                    Toast.makeText(getActivity(), "Mã Khoa hoặc Tên Khoa bị rỗng!", Toast.LENGTH_SHORT).show();
                } else {
                    KhoaDatabase mDatabase = new KhoaDatabase();
                    mDatabase.writeNewKhoa(khoa.getMaKhoa(), khoa.getTenKhoa(), khoa.getNgayThanhLap());
                    Toast.makeText(getActivity(), " Thêm Khoa " + khoa.getMaKhoa() + " thành công!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Set Cancel button
        builder.setNegativeButton(DialogAddOrEditKhoahoc.THOAT_STRING, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Log.d("test", "The Cancel button on Dialog is tapped!");
                AddKhoaDialog.this.getDialog().cancel();
            }
        });

        return builder.create();
    }

    // Add zero number
    public String checkDigit(int number) {
        return number <= 9 ? "0" + number : number + "";
    }
}
