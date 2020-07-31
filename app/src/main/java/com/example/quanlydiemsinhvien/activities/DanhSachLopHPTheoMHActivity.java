package com.example.quanlydiemsinhvien.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quanlydiemsinhvien.R;
import com.example.quanlydiemsinhvien.adapters.DanhSachLopHocPhanTheoMonAdapter;
import com.example.quanlydiemsinhvien.adapters.decorations.DividerItemDecoration;
import com.example.quanlydiemsinhvien.data_models.LopHocPhan;
import com.example.quanlydiemsinhvien.data_models.MonHoc;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class DanhSachLopHPTheoMHActivity extends AppCompatActivity {
    RecyclerView rvLopHPTheoMH;
    private ArrayList<LopHocPhan> dsLopHPTheoMH;
    private ArrayList<LopHocPhan> dsLopHPTheoMH_after_click;
    private TextView txtTenMH;
    private DanhSachLopHocPhanTheoMonAdapter lopHocPhanTheoMonAdapter;

    FirebaseDatabase rootNode;
    DatabaseReference reference;

    private MonHoc mhMenu;

    private Intent intent;
    private String maMH;
    private String tenMH;
    private LopHocPhan lopHocPhan;
    private Map<String, Object> mapMenu;
    private MonHoc monHoc = new MonHoc();

    private String beforeKey = ((Calendar.getInstance().get(Calendar.YEAR)) % 100) + "";

    private int demSLLHP;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.danh_sach_lop_hoc_phan_theo_mon_layout);

        rvLopHPTheoMH = findViewById(R.id.rvDsLopHPTheoMH);
        intent = getIntent();
        maMH = intent.getStringExtra("maMH");
        tenMH = intent.getStringExtra("tenMH");

        txtTenMH = findViewById(R.id.edtTenMH);
        txtTenMH.setText(tenMH);

        dsLopHPTheoMH = new ArrayList<LopHocPhan>();


        dsLopHPTheoMH_after_click = new ArrayList<LopHocPhan>();

        rootNode = FirebaseDatabase.getInstance();
        reference = rootNode.getReference("LopHocPhan");

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    if(dataSnapshot.child("maMH").getValue().equals(maMH)){
                        lopHocPhan = new LopHocPhan();
                        lopHocPhan = dataSnapshot.getValue(LopHocPhan.class);
                        dsLopHPTheoMH_after_click.add(lopHocPhan);
                        demSLLHP++;
                    }
                }
                lopHocPhanTheoMonAdapter = new DanhSachLopHocPhanTheoMonAdapter(DanhSachLopHPTheoMHActivity.this, dsLopHPTheoMH_after_click);
                rvLopHPTheoMH.setHasFixedSize(true);
                rvLopHPTheoMH.setAdapter(lopHocPhanTheoMonAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        rvLopHPTheoMH.setLayoutManager(linearLayoutManager);
        // Item decoration
        rvLopHPTheoMH.addItemDecoration(new DividerItemDecoration(getResources().getDrawable(R.drawable.divider, getTheme())));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_them, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.mnu_them:
            {

                final LopHocPhan lopHocPhan = new LopHocPhan();

                AlertDialog.Builder builder = new AlertDialog.Builder(DanhSachLopHPTheoMHActivity.this);

                View view = LayoutInflater.from(DanhSachLopHPTheoMHActivity.this).inflate(R.layout.dialog_sua_lop_hoc_phan_layout, null);

                final EditText edtMaLop = view.findViewById(R.id.edtMaLop);
                final EditText edtTenLop = view.findViewById(R.id.edtTenLop);
                final EditText edtMaGV = view.findViewById(R.id.edtMaGV);
                final Spinner spnMonHoc = view.findViewById(R.id.spnMonHoc);

                edtMaLop.setEnabled(true);
                edtTenLop.setEnabled(true);

                final ArrayList<MonHoc> dataDsMH = new ArrayList<MonHoc>();

                final ArrayList<String> dataDsTenMH = new ArrayList<String>();
                mapMenu = new HashMap<String, Object>();

                DatabaseReference referenceMH = rootNode.getReference("MonHoc");
                referenceMH.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                            if(dataSnapshot != null){
                                mhMenu = new MonHoc();
                                mhMenu = dataSnapshot.getValue(MonHoc.class);
                                dataDsMH.add(mhMenu);
                                dataDsTenMH.add(mhMenu.getTenMH());
                                mapMenu.put(mhMenu.getTenMH(), mhMenu);
                            }
                        }
                        ArrayAdapter<String> adapter = new ArrayAdapter<String>(DanhSachLopHPTheoMHActivity.this, android.R.layout.simple_spinner_item, dataDsTenMH);
                        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spnMonHoc.setAdapter(adapter);
                        spnMonHoc.setSelection(0);
                        spnMonHoc.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                String tenMH = spnMonHoc.getSelectedItem().toString();
                                monHoc = (MonHoc) mapMenu.get(tenMH);
                                edtMaLop.setText(beforeKey + monHoc.getMaMH() + ((demSLLHP + 1) + ""));
                                edtTenLop.setText(monHoc.getTenMH());
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {

                            }
                        });
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

                builder.setView(view);

                builder.setTitle("Thêm lớp học phần");

                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        lopHocPhan.setMaLHP(edtMaLop.getText().toString());
                        lopHocPhan.setTenLHP(edtTenLop.getText().toString());
                        lopHocPhan.setMaGV(edtMaGV.getText().toString());
                        dsLopHPTheoMH_after_click.add(lopHocPhan);
                        lopHocPhanTheoMonAdapter.notifyItemInserted(0);
                        rvLopHPTheoMH.scrollToPosition(0);
                        lopHocPhanTheoMonAdapter.notifyItemRangeChanged(0, dsLopHPTheoMH_after_click.size());
                    }
                });

                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }break;
            default:break;
        }
        return super.onOptionsItemSelected(item);
    }
}
