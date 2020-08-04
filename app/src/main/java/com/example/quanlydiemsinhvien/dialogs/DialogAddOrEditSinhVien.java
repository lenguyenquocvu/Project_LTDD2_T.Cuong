package com.example.quanlydiemsinhvien.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import com.example.quanlydiemsinhvien.R;
import com.example.quanlydiemsinhvien.adapters.GiangVienSwipeRecyclerViewAdapter;
import com.example.quanlydiemsinhvien.adapters.SinhVienSwipeRecyclerViewAdapter;
import com.example.quanlydiemsinhvien.data_models.GiangVienModel;
import com.example.quanlydiemsinhvien.data_models.NganhModel;
import com.example.quanlydiemsinhvien.data_models.SinhVienModel;
import com.example.quanlydiemsinhvien.interfaces.OnItemClickToAddGiangVienListener;
import com.example.quanlydiemsinhvien.interfaces.OnItemClickToAddSinhVienListener;
import com.example.quanlydiemsinhvien.interfaces.OnItemClickToEditGiangVienListener;
import com.example.quanlydiemsinhvien.interfaces.OnItemClickToEditSinhVienListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class DialogAddOrEditSinhVien extends DialogFragment {
    private EditText edtMaSV;
    private EditText edtTenSV;
    private EditText edtHoSV;
    private EditText edtNgaySinh;
    private EditText edtDiaChi;
    private EditText edtEmail;
    private EditText edtSDT;
    private static Spinner spnMaNganh;

    public static ArrayList<NganhModel> spinnerItems = new ArrayList<NganhModel>();

    private OnItemClickToAddSinhVienListener addSinhVienListener;
    private SinhVienModel sinhVien;
    private OnItemClickToEditSinhVienListener editSinhVienListener;

    private DatabaseReference spinnerDatabase;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.hop_thoai_them_sinh_vien_layout, null);

        spinnerDatabase = FirebaseDatabase.getInstance().getReference("Nganh");

        edtMaSV = view.findViewById(R.id.edtMaSV);
        edtTenSV = view.findViewById(R.id.edtTenSV);
        edtHoSV = view.findViewById(R.id.edtHoSV);
        edtNgaySinh = view.findViewById(R.id.editTextDate);
        edtEmail = view.findViewById(R.id.edtEmailSV);
        edtSDT = view.findViewById(R.id.edtSdtSV);
        edtDiaChi = view.findViewById(R.id.edtDiaChiSV);
        spnMaNganh = view.findViewById(R.id.spinnerMaNganh);

        spinnerDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot areaSnapshot : snapshot.getChildren()) {
                    NganhModel nganh = new NganhModel();
                    nganh = areaSnapshot.getValue(NganhModel.class);
                    spinnerItems.add(nganh);
                }

                ArrayAdapter<NganhModel> arrayAdapter = new ArrayAdapter<NganhModel>(getActivity(), android.R.layout.simple_spinner_item, spinnerItems);
                arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spnMaNganh.setAdapter(arrayAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        builder.setView(view);

        if (getArguments() != null) {
            sinhVien = (SinhVienModel) getArguments().getSerializable(SinhVienSwipeRecyclerViewAdapter.KEY_SINHVIEN);
            edtMaSV.setEnabled(false);
            edtMaSV.setText(sinhVien.getMaSV());
            edtTenSV.setText(sinhVien.getTenSV());
            edtHoSV.setText(sinhVien.getHoSV());
            edtNgaySinh.setText(sinhVien.getNgaySinh());
            edtSDT.setText(sinhVien.getSdt() + "");
            edtDiaChi.setText(sinhVien.getDiaChi());
            edtEmail.setText(sinhVien.getEmail());
            spnMaNganh.setSelection(getPositionOfMaNganh(sinhVien.getMaNganh()));
            builder.setTitle("Chỉnh sửa sinh viên");
        } else {
            edtMaSV.setEnabled(true);
            builder.setTitle("Thêm sinh viên");
        }


        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (getArguments() == null) {
                    if(edtMaSV.getText().toString().isEmpty() ||
                            edtHoSV.getText().toString().isEmpty() ||
                            edtTenSV.getText().toString().isEmpty() ||
                            edtSDT.getText().toString().isEmpty() ||
                            edtNgaySinh.getText().toString().isEmpty() ||
                            edtDiaChi.getText().toString().isEmpty()){
                        Toast.makeText(getContext(), "Không thể thêm sinh viên! Vui lòng nhập đầy đủ thông tin!", Toast.LENGTH_LONG).show();
                    }else{

                        sinhVien = new SinhVienModel();

                        sinhVien.setMaSV(edtMaSV.getText().toString());
                        sinhVien.setTenSV(edtTenSV.getText().toString());
                        sinhVien.setHoSV(edtHoSV.getText().toString());
                        sinhVien.setNgaySinh(edtNgaySinh.getText().toString());
                        sinhVien.setSdt(edtSDT.getText().toString());
                        sinhVien.setDiaChi(edtDiaChi.getText().toString());
                        sinhVien.setEmail(edtEmail.getText().toString());
                        sinhVien.setMaNganh(getSelectedMaNganh());
                        addSinhVienListener.applySinhVien(sinhVien);
                    }
                } else {
                    if(edtMaSV.getText().toString().isEmpty() ||
                            edtHoSV.getText().toString().isEmpty() ||
                            edtTenSV.getText().toString().isEmpty() ||
                            edtSDT.getText().toString().isEmpty() ||
                            edtNgaySinh.getText().toString().isEmpty() ||
                            edtDiaChi.getText().toString().isEmpty()){
                        Toast.makeText(getContext(), "Không thể chỉnh sửa! Vui lòng nhập đầy đủ thông tin!", Toast.LENGTH_LONG).show();
                    }else{
                        sinhVien.setMaSV(edtMaSV.getText().toString());
                        sinhVien.setTenSV(edtTenSV.getText().toString());
                        sinhVien.setHoSV(edtHoSV.getText().toString());
                        sinhVien.setNgaySinh(edtNgaySinh.getText().toString());
                        sinhVien.setSdt(edtSDT.getText().toString());
                        sinhVien.setDiaChi(edtDiaChi.getText().toString());
                        sinhVien.setEmail(edtEmail.getText().toString());
                        sinhVien.setMaNganh(getSelectedMaNganh());
                        int position = getArguments().getInt(SinhVienSwipeRecyclerViewAdapter.KEY_POSITION);
                        editSinhVienListener = (OnItemClickToEditSinhVienListener) getActivity();
                        editSinhVienListener.onItemClicked(sinhVien, position);
                        getArguments().clear();
                    }

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
            addSinhVienListener = (OnItemClickToAddSinhVienListener ) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + "Error!");
        }
    }

    public static String getSelectedMaNganh(){
        String maNganh = "";
        for(int i = 0; i < spinnerItems.size(); i++){
            if(spnMaNganh.getSelectedItem().toString() == spinnerItems.get(i).getTenNganh()){
                maNganh = spinnerItems.get(i).getMaNganh();
            }
        }
        return maNganh;
    }
    public static int getPositionOfMaNganh(String maNganh){
        int position = 0;
        for(int i = 0; i < spinnerItems.size(); i++){
            if(maNganh == spinnerItems.get(i).getMaNganh()){
                position = i;
            }
        }
        return position;
    }
}
