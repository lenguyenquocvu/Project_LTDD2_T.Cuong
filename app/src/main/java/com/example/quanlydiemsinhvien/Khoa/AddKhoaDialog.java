package com.example.quanlydiemsinhvien.Khoa;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.DialogFragment;

import com.example.quanlydiemsinhvien.R;

public class AddKhoaDialog extends DialogFragment {
    private Khoa theKhoa;
    private Activity context;
    private EditText edtMaKhoa;
    private EditText edtTenKhoa;
    private DatePicker dtpNgayThanhLap;
    private int day;
    private int month;
    private int year;

    public AddKhoaDialog() {
    }

    public Khoa getTheKhoa() {
        return theKhoa;
    }

    public void setTheKhoa(Khoa theKhoa) {
        this.theKhoa = theKhoa;
    }

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
        builder.setPositiveButton(R.string.btnThem, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Dialog dialog = Dialog.class.cast(dialogInterface);

                edtMaKhoa = dialog.findViewById(R.id.edtMaKhoa);
                edtTenKhoa = dialog.findViewById(R.id.edtTenKhoa);
                dtpNgayThanhLap = dialog.findViewById(R.id.dtpNgayThanhLap);

                day = dtpNgayThanhLap.getDayOfMonth();
                month = dtpNgayThanhLap.getMonth();
                year = dtpNgayThanhLap.getYear();

                String maKhoa = edtMaKhoa.getText().toString();
                String tenKhoa = edtTenKhoa.getText().toString();
                String ngayThanhLap = checkDigit(day + 1) + "/" + checkDigit(month) + "/" + year;

                setTheKhoa(new Khoa(maKhoa, tenKhoa, ngayThanhLap));

                Toast.makeText(getActivity(), theKhoa.getNgayThanhLap() + "", Toast.LENGTH_SHORT).show();
            }
        });

        // Set Cancel button
        builder.setNegativeButton(R.string.btnCancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Log.d("test", "The Cancel button on Dialog is tapped!");
                AddKhoaDialog.this.getDialog().cancel();
            }
        });

        return builder.create();
    }

/*    public Khoa getKhoaFromDialog(LayoutInflater inflater) {

        // Get all view form layout
        View view = null;
        view = inflater.inflate(R.layout.dialog_them_khoa, null);

        edtMaKhoa = view.findViewById(R.id.edtMaKhoa);
        edtTenKhoa = view.findViewById(R.id.edtTenKhoa);
        dtpNgayThanhLap = view.findViewById(R.id.dtpNgayThanhLap);

        day = dtpNgayThanhLap.getDayOfMonth();
        month = dtpNgayThanhLap.getMonth();
        year = dtpNgayThanhLap.getYear();

        String ngayThanhLap = checkDigit(day+1) + "/" + checkDigit(month) + "/" + year;

        return new Khoa(edtMaKhoa.getText()+"", edtTenKhoa.getText()+"", ngayThanhLap);
    }*/

    // Add zero number
    public String checkDigit(int number) {
        return number <= 9 ? "0" + number : number + "";
    }
}
