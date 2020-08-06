package com.example.quanlydiemsinhvien.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
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
import com.example.quanlydiemsinhvien.activities.DanhSachLopHPTheoMHActivity;
import com.example.quanlydiemsinhvien.adapters.LopHocPhanTheoMonAdapter;
import com.example.quanlydiemsinhvien.data_models.LopHocPhan;
import com.example.quanlydiemsinhvien.data_models.MonHoc;
import com.example.quanlydiemsinhvien.interfaces.OnItemClickToAddListener;
import com.example.quanlydiemsinhvien.interfaces.OnItemClickToEditListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

public class DialogAddOrEditLopHocPhan extends DialogFragment {
    private LopHocPhan lopHocPhan;
    FirebaseDatabase rootNode;
    DatabaseReference referenceMH;
    DatabaseReference referenceGV;

    private ArrayList<String> dataDsTenMH = new ArrayList<String>();
    private HashMap<String, Object> mapMHMenu = new HashMap<String, Object>();

    private HashMap<String, Object> mapMHMenu2 = new HashMap<String, Object>();

    private MonHoc mhMenu;
    private MonHoc monHoc;

    private String beforeKey = ((Calendar.getInstance().get(Calendar.YEAR)) % 100) + "";

    private final int DEFAULT_POSITION = 0;

    public OnItemClickToAddListener addListener;
    public OnItemClickToEditListener editListener;

    private int positionSpinner = 0;

    private ArrayAdapter<String> adapterMH;

    int demSLLHP;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_sua_lop_hoc_phan_layout, null);

        final EditText edtMaLop = view.findViewById(R.id.edtMaLop);
        final EditText edtTenLop = view.findViewById(R.id.edtTenLop);
        final EditText edtMaGV = view.findViewById(R.id.edtMaGV);
        final Spinner spnMonHoc = view.findViewById(R.id.spnMonHoc);
        if (getArguments() != null) {
            if (!getArguments().containsKey(DanhSachLopHPTheoMHActivity.SIZE_DSLHP)) {
                lopHocPhan = (LopHocPhan) getArguments().getSerializable(LopHocPhanTheoMonAdapter.EDIT_LHP);

                edtMaLop.setEnabled(false);
                edtTenLop.setEnabled(false);
                spnMonHoc.setEnabled(false);

                edtMaGV.setText(lopHocPhan.getMaGV());
                edtMaLop.setText(lopHocPhan.getMaLHP());
                edtTenLop.setText(lopHocPhan.getTenLHP());


                builder.setTitle("Sửa thông tin lớp học phần");

            } else {
                builder.setTitle("Thêm lớp học phần");
            }
        }
        rootNode = FirebaseDatabase.getInstance();

        referenceMH = rootNode.getReference("MonHoc");
        referenceMH.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    if (dataSnapshot != null) {
                        mhMenu = new MonHoc();
                        mhMenu = dataSnapshot.getValue(MonHoc.class);
                        dataDsTenMH.add(mhMenu.getTenMH());
                        mapMHMenu.put(mhMenu.getTenMH(), mhMenu.getMaMH());
                        mapMHMenu2.put(mhMenu.getMaMH(), mhMenu.getTenMH());
                    }
                }
                adapterMH = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, dataDsTenMH);
                adapterMH.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spnMonHoc.setAdapter(adapterMH);
                if (getArguments() != null) {
                    if (getArguments().containsKey(DanhSachLopHPTheoMHActivity.SIZE_DSLHP)) {
                        demSLLHP = getArguments().getInt(DanhSachLopHPTheoMHActivity.SIZE_DSLHP);
                        spnMonHoc.setSelection(DEFAULT_POSITION);
                        spnMonHoc.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                String tenMH = spnMonHoc.getSelectedItem().toString();
                                String maMH = (String) mapMHMenu.get(tenMH);
                                edtMaLop.setText(beforeKey + maMH + ((demSLLHP + 1) + ""));
                                edtTenLop.setText(tenMH);
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {

                            }
                        });
                    } else {
                        String maMH = lopHocPhan.getMaMH();
                        String tenMH = (String) mapMHMenu2.get(maMH);
                        positionSpinner = adapterMH.getPosition(tenMH);
                        spnMonHoc.setSelection(positionSpinner);

                    }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        builder.setView(view);


        builder.setPositiveButton(DialogAddOrEditKhoahoc.OK_STRING, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (getArguments() != null) {
                    if (getArguments().containsKey(DanhSachLopHPTheoMHActivity.SIZE_DSLHP)) {
                        lopHocPhan = new LopHocPhan();
                        lopHocPhan.setMaLHP(edtMaLop.getText().toString());
                        lopHocPhan.setTenLHP(edtTenLop.getText().toString());
                        lopHocPhan.setMaGV(edtMaGV.getText().toString());
                        String tenMH = spnMonHoc.getSelectedItem().toString();
                        String maMH = (String) mapMHMenu.get(tenMH);
                        lopHocPhan.setMaMH(maMH);
                        lopHocPhan.setMaHK("HKI");
                        lopHocPhan.setMaNH("1819");
                        lopHocPhan.setKetThuc(false);

                        addListener.applyObject(lopHocPhan);
                    } else {
                        lopHocPhan.setMaGV(edtMaGV.getText().toString());
                        getArguments().clear();
                        editListener = (OnItemClickToEditListener) getActivity();
                        editListener.editObject(lopHocPhan);

                    }
                }
            }
        });
        builder.setNegativeButton(DialogAddOrEditKhoahoc.CANCEL_STRING, new DialogInterface.OnClickListener() {
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
            addListener = (OnItemClickToAddListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + "Error!");
        }
    }
}
