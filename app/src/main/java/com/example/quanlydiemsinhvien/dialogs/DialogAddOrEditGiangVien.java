package com.example.quanlydiemsinhvien.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import com.example.quanlydiemsinhvien.R;
import com.example.quanlydiemsinhvien.adapters.GiangVienSwipeRecyclerViewAdapter;
import com.example.quanlydiemsinhvien.data_models.GiangVienModel;
import com.example.quanlydiemsinhvien.data_models.NganhModel;
import com.example.quanlydiemsinhvien.interfaces.OnItemClickToAddGiangVienListener;
import com.example.quanlydiemsinhvien.interfaces.OnItemClickToEditGiangVienListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
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

    public static ArrayList<NganhModel> spinnerItems;

    private OnItemClickToAddGiangVienListener addGiangVienListener;
    private GiangVienModel giangVien;
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

        spinnerDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                spinnerItems = new ArrayList<NganhModel>();

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
            giangVien = (GiangVienModel) getArguments().getSerializable(GiangVienSwipeRecyclerViewAdapter.KEY_GIANGVIEN);
            edtMaGV.setEnabled(false);
            edtMaGV.setText(giangVien.getMaGV());
            edtTenGV.setText(giangVien.getTenGV());
            edtHoGV.setText(giangVien.getHoGV());
            edtNgaySinh.setText(giangVien.getNgaySinh());
            edtSDT.setText(giangVien.getSDT() + "");
            edtEmail.setText(giangVien.getEmail());
            //spnMaNganh.setSelection(getPositionOfMaNganh(giangVien.getMaNganh()));
            builder.setTitle("Chỉnh sửa giảng viên");
        } else {
            edtMaGV.setEnabled(true);
            builder.setTitle("Thêm giảng viên");
        }


        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (getArguments() == null) {
                    giangVien = new GiangVienModel();

                    giangVien.setMaGV(edtMaGV.getText().toString());
                    giangVien.setTenGV(edtTenGV.getText().toString());
                    giangVien.setHoGV(edtHoGV.getText().toString());
                    giangVien.setNgaySinh(edtNgaySinh.getText().toString());
                    giangVien.setSDT(Integer.parseInt(edtSDT.getText().toString()));
                    giangVien.setEmail(edtEmail.getText().toString());
                    giangVien.setMaNganh(getSelectedMaNganh());
                    addGiangVienListener.applyGiangVien(giangVien);
                } else {
                    giangVien.setMaGV(edtMaGV.getText().toString());
                    giangVien.setTenGV(edtTenGV.getText().toString());
                    giangVien.setHoGV(edtHoGV.getText().toString());
                    giangVien.setNgaySinh(edtNgaySinh.getText().toString());
                    giangVien.setSDT(Integer.parseInt(edtSDT.getText().toString()));
                    giangVien.setEmail(edtEmail.getText().toString());
                    giangVien.setMaNganh(getSelectedMaNganh());
                    int position = getArguments().getInt(GiangVienSwipeRecyclerViewAdapter.KEY_POSITION);
                    editGiangVienListener = (OnItemClickToEditGiangVienListener) getActivity();
                    editGiangVienListener.onItemClicked(giangVien, position);
                    getArguments().clear();
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
