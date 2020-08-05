package com.example.quanlydiemsinhvien.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
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
import com.example.quanlydiemsinhvien.activities.DanhSachGiangVienActivity;
import com.example.quanlydiemsinhvien.adapters.GiangVienSwipeRecyclerViewAdapter;
import com.example.quanlydiemsinhvien.data_models.GiangVien;
import com.example.quanlydiemsinhvien.data_models.Nganh;
import com.example.quanlydiemsinhvien.interfaces.OnItemClickToAddGiangVienListener;
import com.example.quanlydiemsinhvien.interfaces.OnItemClickToEditGiangVienListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class DialogAddOrEditGiangVien extends DialogFragment {
    private EditText edtMaGV;
    private EditText edtTenGV;
    private EditText edtHoGV;
    private EditText edtNgaySinh;
    private EditText edtEmail;
    private EditText edtSDT;
    private static Spinner spnMaNganh;

    static ArrayAdapter<Nganh> arrayAdapterNganh;

    public static ArrayList<Nganh> spinnerItems = new ArrayList<Nganh>();

    private OnItemClickToAddGiangVienListener addGiangVienListener;
    private GiangVien giangVien;
    private OnItemClickToEditGiangVienListener editGiangVienListener;

    private DatabaseReference spinnerDatabase;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.hop_thoai_them_giang_vien_layout, null);

        spinnerDatabase = FirebaseDatabase.getInstance().getReference("Nganh");

        edtMaGV = view.findViewById(R.id.edtMaGV);
        edtTenGV = view.findViewById(R.id.edtTenGV);
        edtHoGV = view.findViewById(R.id.edtHoGV);
        edtNgaySinh = view.findViewById(R.id.edtDate);
        edtEmail = view.findViewById(R.id.edtEmailGV);
        edtSDT = view.findViewById(R.id.edtSdtGV);
        spnMaNganh = view.findViewById(R.id.spinnerMaNganh);

//        edtMaGV.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                if (s.length() == 0) {
//                    edtMaGV.setError("Bạn bắt buộc phải nhập Mã giảng viên!");
//                } else {
//                    edtMaGV.setError(null);
//                }
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//
//            }
//        });

        spinnerDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                spinnerItems.clear();

                for (DataSnapshot areaSnapshot : snapshot.getChildren()) {
                    Nganh nganh = new Nganh();
                    nganh = areaSnapshot.getValue(Nganh.class);
                    spinnerItems.add(nganh);
                }

                arrayAdapterNganh = new ArrayAdapter<Nganh>(getActivity(), android.R.layout.simple_spinner_item, spinnerItems);
                arrayAdapterNganh.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spnMaNganh.setAdapter(arrayAdapterNganh);

//                Log.d("-->","Size of Nganh" + spinnerItems.size());
                if (getArguments() != null) {
                    giangVien = (GiangVien) getArguments().getSerializable(GiangVienSwipeRecyclerViewAdapter.KEY_GIANGVIEN);
                    spnMaNganh.setSelection(arrayAdapterNganh.getPosition(getNganh(giangVien.getMaNganh(), spinnerItems)));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }

        });

        builder.setView(view);

        if (getArguments() != null) {
            giangVien = (GiangVien) getArguments().getSerializable(GiangVienSwipeRecyclerViewAdapter.KEY_GIANGVIEN);
            edtMaGV.setEnabled(false);
            edtMaGV.setText(giangVien.getMaGV());
            edtTenGV.setText(giangVien.getTenGV());
            edtHoGV.setText(giangVien.getHoGV());
            edtNgaySinh.setText(giangVien.getNgaySinh());
            edtSDT.setText(giangVien.getSdt() + "");
            edtEmail.setText(giangVien.getEmail());
            //spnMaNganh.setSelection(getPositionOfNganh(giangVien.getMaNganh()));
            builder.setTitle("Chỉnh sửa giảng viên");
//            Log.d("position", "position " + getPositionOfMaNganh(giangVien.getMaNganh(), spinnerItems));
        } else {
            edtMaGV.setEnabled(true);
            builder.setTitle("Thêm giảng viên");
        }


        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (getArguments() == null) {
                    if(edtMaGV.getText().toString().isEmpty() ||
                            edtHoGV.getText().toString().isEmpty() ||
                            edtTenGV.getText().toString().isEmpty() ||
                            edtSDT.getText().toString().isEmpty() ||
                            edtNgaySinh.getText().toString().isEmpty()){
                        Toast.makeText(getContext(), "Không thể thêm giảng viên! Vui lòng nhập đầy đủ thông tin!", Toast.LENGTH_LONG).show();
                    } else if (!checkMaGV(edtMaGV.getText().toString())) {
                        Toast.makeText(getContext(), "Thêm không thành công! Mã giảng viên đã tồn tại!", Toast.LENGTH_LONG).show();
                    } else {
                        giangVien = new GiangVien();
                        giangVien.setMaGV(edtMaGV.getText().toString());
                        giangVien.setTenGV(edtTenGV.getText().toString());
                        giangVien.setHoGV(edtHoGV.getText().toString());
                        giangVien.setNgaySinh(edtNgaySinh.getText().toString());
                        giangVien.setSdt(edtSDT.getText().toString());
                        giangVien.setEmail(edtEmail.getText().toString());
                        giangVien.setMaNganh(getSelectedMaNganh());
                        addGiangVienListener.applyGiangVien(giangVien);
                    }
                } else {
                    if(edtMaGV.getText().toString().isEmpty() ||
                            edtHoGV.getText().toString().isEmpty() ||
                            edtTenGV.getText().toString().isEmpty() ||
                            edtSDT.getText().toString().isEmpty() ||
                            edtNgaySinh.getText().toString().isEmpty()){
                        Toast.makeText(getContext(), "Không thể chỉnh sửa thông tin! Vui lòng nhập đầy đủ thông tin!", Toast.LENGTH_LONG).show();
                    }else{
                        giangVien.setMaGV(edtMaGV.getText().toString());
                        giangVien.setTenGV(edtTenGV.getText().toString());
                        giangVien.setHoGV(edtHoGV.getText().toString());
                        giangVien.setNgaySinh(edtNgaySinh.getText().toString());
                        giangVien.setSdt(edtSDT.getText().toString());
                        giangVien.setEmail(edtEmail.getText().toString());
                        giangVien.setMaNganh(getSelectedMaNganh());
                        int position = getArguments().getInt(GiangVienSwipeRecyclerViewAdapter.KEY_POSITION);
                        editGiangVienListener = (OnItemClickToEditGiangVienListener) getActivity();
                        editGiangVienListener.onItemClicked(giangVien, position);
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
            addGiangVienListener = (OnItemClickToAddGiangVienListener ) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + "Error!");
        }
    }

    public static String getSelectedMaNganh(){
        String maNganh = "";
        for(int i = 0; i < spinnerItems.size(); i++){
            if(spnMaNganh.getSelectedItem().toString().equals(spinnerItems.get(i).getTenNganh())){
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

    public static boolean checkMaGV (String maGV) {
        for (GiangVien gv : DanhSachGiangVienActivity.dsGiangVien){
            if(maGV.equals(gv.getMaGV().toString())){
                return false;
            }
        }
        return true;
    }
}
