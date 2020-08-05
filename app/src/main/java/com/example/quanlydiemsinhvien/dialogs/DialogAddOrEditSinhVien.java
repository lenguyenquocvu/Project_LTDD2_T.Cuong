package com.example.quanlydiemsinhvien.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
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
import com.example.quanlydiemsinhvien.activities.DanhSachSinhVienActivity;
import com.example.quanlydiemsinhvien.adapters.SinhVienSwipeRecyclerViewAdapter;
import com.example.quanlydiemsinhvien.data_models.KhoaHoc;
import com.example.quanlydiemsinhvien.data_models.Nganh;
import com.example.quanlydiemsinhvien.data_models.SinhVien;
import com.example.quanlydiemsinhvien.interfaces.OnItemClickToAddSinhVienListener;
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
    private Spinner spnMaKH;
    private static Spinner spnMaNganh;

    public static ArrayList<Nganh> spinnerItems = new ArrayList<Nganh>();
    public ArrayList<String> spinnerKH = new ArrayList<>();
    public static ArrayAdapter<String> arrayAdapterKH;

    private OnItemClickToAddSinhVienListener addSinhVienListener;
    private SinhVien sinhVien;
    private OnItemClickToEditSinhVienListener editSinhVienListener;

    private DatabaseReference spinnerNganhDatabase;
    private DatabaseReference spinnerMaKHDatabase;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.hop_thoai_them_sinh_vien_layout, null);

        spinnerNganhDatabase = FirebaseDatabase.getInstance().getReference("Nganh");
        spinnerMaKHDatabase = FirebaseDatabase.getInstance().getReference("KhoaHoc");

        edtMaSV = view.findViewById(R.id.edtMaSV);
        edtTenSV = view.findViewById(R.id.edtTenSV);
        edtHoSV = view.findViewById(R.id.edtHoSV);
        edtNgaySinh = view.findViewById(R.id.editTextDate);
        edtEmail = view.findViewById(R.id.edtEmailSV);
        edtSDT = view.findViewById(R.id.edtSdtSV);
        edtDiaChi = view.findViewById(R.id.edtDiaChiSV);
        spnMaKH = view.findViewById(R.id.spinnerMaKhoaHoc);
        spnMaNganh = view.findViewById(R.id.spinnerMaNganh);

        spinnerNganhDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                spinnerItems.clear();
                for (DataSnapshot areaSnapshot : snapshot.getChildren()) {
                    Nganh nganh = new Nganh();
                    nganh = areaSnapshot.getValue(Nganh.class);
                    spinnerItems.add(nganh);
                }

                ArrayAdapter<Nganh> arrayAdapter = new ArrayAdapter<Nganh>(getActivity(), android.R.layout.simple_spinner_item, spinnerItems);
                arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spnMaNganh.setAdapter(arrayAdapter);

                if (getArguments() != null) {
                    sinhVien = (SinhVien) getArguments().getSerializable(SinhVienSwipeRecyclerViewAdapter.KEY_SINHVIEN);
                    spnMaNganh.setSelection(arrayAdapter.getPosition(getNganh(sinhVien.getMaNganh(), spinnerItems)));
//                    Log.d("Nganh", "" + getPositionOfMaNganh(sinhVien.getMaNganh(), spinnerItems));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        spinnerMaKHDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                spinnerKH.clear();
                for (DataSnapshot areaSnapshot : snapshot.getChildren()) {
                    KhoaHoc khoaHoc = new KhoaHoc();
                    khoaHoc = areaSnapshot.getValue(KhoaHoc.class);
                    spinnerKH.add(khoaHoc.getMaKH());
                }
//                Log.d("KH", "" + spinnerKH);
                arrayAdapterKH = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, spinnerKH);
                arrayAdapterKH.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spnMaKH.setAdapter(arrayAdapterKH);

                if (getArguments() != null) {
                    sinhVien = (SinhVien) getArguments().getSerializable(SinhVienSwipeRecyclerViewAdapter.KEY_SINHVIEN);
                    spnMaKH.setSelection(arrayAdapterKH.getPosition(sinhVien.getMaKH()));
//                    Log.d("KH", "" + arrayAdapterKH.getPosition(sinhVien.getMaKH()));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        if (getArguments() != null) {
            sinhVien = (SinhVien) getArguments().getSerializable(SinhVienSwipeRecyclerViewAdapter.KEY_SINHVIEN);
            edtMaSV.setEnabled(false);
            edtMaSV.setText(sinhVien.getMaSV());
            edtTenSV.setText(sinhVien.getTenSV());
            edtHoSV.setText(sinhVien.getHoSV());
            edtNgaySinh.setText(sinhVien.getNgaySinh());
            edtSDT.setText(sinhVien.getSdt() + "");
            edtDiaChi.setText(sinhVien.getDiaChi());
            edtEmail.setText(sinhVien.getEmail());
            builder.setTitle("Chỉnh sửa sinh viên");
        } else {
            edtMaSV.setEnabled(true);
            builder.setTitle("Thêm sinh viên");
        }
        builder.setView(view);




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
                    } else if (!checkMaSV(edtMaSV.getText().toString())) {
                        Toast.makeText(getContext(), "Thêm không thành công! Mã sinh viên đã tồn tại!", Toast.LENGTH_LONG).show();
                    } else if (!checkMaSVTheoKH(edtMaSV.getText().toString(), spnMaKH.getSelectedItem().toString())){
//                        Log.d("maSV", "" + edtMaSV.getText().toString().substring(0, 2) + " " + spnMaKH.getSelectedItem().toString().substring(1));
                        Toast.makeText(getContext(), "Thêm không thành công! Mã sinh viên phải thuộc khóa " + spnMaKH.getSelectedItem().toString() + "!", Toast.LENGTH_LONG).show();
                    } else{
                        sinhVien = new SinhVien();
                        sinhVien.setMaSV(edtMaSV.getText().toString());
                        sinhVien.setTenSV(edtTenSV.getText().toString());
                        sinhVien.setHoSV(edtHoSV.getText().toString());
                        sinhVien.setNgaySinh(edtNgaySinh.getText().toString());
                        sinhVien.setSdt(edtSDT.getText().toString());
                        sinhVien.setDiaChi(edtDiaChi.getText().toString());
                        sinhVien.setEmail(edtEmail.getText().toString());
                        sinhVien.setMaNganh(getSelectedMaNganh());
                        sinhVien.setMaKH(spnMaKH.getSelectedItem().toString());
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
                        sinhVien.setMaKH(spnMaKH.getSelectedItem().toString());
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
    public static Nganh getNganh(String maNganh, ArrayList<Nganh> dsNganh){
        Nganh nganh = null;
        for(Nganh nganh1 : dsNganh){
            if(maNganh.equals(nganh1.getMaNganh())){
                nganh = nganh1;
            }
        }
        return nganh;
    }
    public static boolean checkMaSV (String maSV) {
        for (SinhVien sv : DanhSachSinhVienActivity.dsSinhVien){
            if(maSV.equals(sv.getMaSV().toString())){
                return false;
            }
        }
        return true;
    }

    public static boolean checkMaSVTheoKH (String maSV, String maKH){
        if(maSV.substring(0, 2).equals(maKH.substring(1))){
            return true;
        } else {
            return false;
        }
    }

}
