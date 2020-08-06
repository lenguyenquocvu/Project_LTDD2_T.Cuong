package com.example.quanlydiemsinhvien.activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.daimajia.swipe.util.Attributes;
import com.example.quanlydiemsinhvien.R;
import com.example.quanlydiemsinhvien.adapters.DSLopAdapter;
import com.example.quanlydiemsinhvien.adapters.DSSinhVienAdapter;

import com.example.quanlydiemsinhvien.adapters.LopHocPhanTheoMonAdapter;
import com.example.quanlydiemsinhvien.data_models.DanhSachSinhVien;
import com.example.quanlydiemsinhvien.data_models.SinhVien;
import com.example.quanlydiemsinhvien.dialogs.Dialog_Add_SV;
import com.example.quanlydiemsinhvien.divider.DividerItemDecoration;
import com.example.quanlydiemsinhvien.interfaces.AddSV;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import static com.example.quanlydiemsinhvien.adapters.LopHocPhanTheoMonAdapter.MALOP;

//import com.example.quanlydiemsinhvien.firebase_data.DSSVienDatabase;

public class DSSinhVienActivity extends AppCompatActivity implements AddSV {
//    public static final String SINHVIENTAG = "SinhVien";
//    public static ArrayList<DanhSachSinhVien> dataSinhVien = new ArrayList<DanhSachSinhVien>();
//    public static DSSinhVienAdapter dsSinhVienAdapter;
//
//    public static Intent intent;
//    private Context context = DSSinhVienActivity.this;
//    private RecyclerView recyclerView;
//    private RecyclerView.LayoutManager layoutManager;


    RecyclerView recycler_dssinhvien;
    private ArrayList<DanhSachSinhVien> dsSV = new ArrayList<DanhSachSinhVien>();
    private RecyclerView.LayoutManager layoutManager;
    TextView txtMaSV;
    TextView txtTenSV;
    TextView txtDiem;
    TextView txtMaLHP;
    public Intent intent;
    private DSSinhVienAdapter dsSinhVienAdapter;
    public static String maLop ="";
    private String tenLop ="";
    FirebaseDatabase rootNode;
    DatabaseReference reference, referenceSV;

    //Properties of Firebase
//    DSSVienDatabase mDatabase = new DSSVienDatabase();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.danhsachsinhvien_layout);
        setTitle("Danh Sách Sinh Viên");

        intent = getIntent();
        maLop = intent.getStringExtra(LopHocPhanTheoMonAdapter.MALOP);
        tenLop=intent.getStringExtra(LopHocPhanTheoMonAdapter.TENLOP);

        setTitle(maLop);


        recycler_dssinhvien = (RecyclerView) findViewById(R.id.recycler_dssinhvien);
        txtMaSV = findViewById(R.id.txtMaSV);
        txtTenSV = findViewById(R.id.txtTenSV);
        txtDiem = findViewById(R.id.txtDiemSV);
        txtMaLHP = findViewById(R.id.txtMaLHP);

        txtMaLHP.setText(tenLop);

        rootNode = FirebaseDatabase.getInstance();
        referenceSV = rootNode.getReference("SinhVien");
        reference = rootNode.getReference("DSSVMotLop").child(maLop);
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    dsSV.clear();
                    for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                        final DanhSachSinhVien sVien = new DanhSachSinhVien();
                        final String maSV = dataSnapshot.getKey();
                        double diemSv = dataSnapshot.child("diem").getValue(double.class);
                        int lanHoc = dataSnapshot.child("lanHoc").getValue(int.class);
                        sVien.setMaSV(maSV);
                        sVien.setDiem(diemSv);
                        sVien.setLanHoc(lanHoc);
                        referenceSV.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                if(snapshot.exists()){
                                    for(DataSnapshot dataSnapshot1 : snapshot.getChildren()){
                                        if(maSV.equals(dataSnapshot1.getKey())){
                                            String hoSV = dataSnapshot1.getValue(SinhVien.class).getHoSV();
                                            String tenSV = dataSnapshot1.getValue(SinhVien.class).getTenSV();
                                            sVien.setTenSV(hoSV +  " " + tenSV);
                                            dsSV.add(sVien);
                                        }
                                    }
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });
                        dsSinhVienAdapter = new DSSinhVienAdapter(dsSV, DSSinhVienActivity.this);
                        recycler_dssinhvien.setAdapter(dsSinhVienAdapter);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        recycler_dssinhvien.setLayoutManager(linearLayoutManager);
        // Item decoration
        recycler_dssinhvien.addItemDecoration(new DividerItemDecoration(getResources().getDrawable(R.drawable.divider, getTheme())));

        DSSinhVienAdapter dssvAdapter = new DSSinhVienAdapter(dsSV, this);
        ((DSSinhVienAdapter) dssvAdapter).setMode(Attributes.Mode.Single);
        recycler_dssinhvien.setAdapter(dssvAdapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.option_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(final MenuItem item) {
        switch (item.getItemId()) {
            case R.id.them_item: {
                showAddSVDialog();
                return true;
            }
            case R.id.thoat_item: {
                // do your code
                AlertDialog.Builder builder = new AlertDialog.Builder(DSSinhVienActivity.this);
                builder.setTitle("Thoát chương trình");
                builder.setMessage("Bạn có muốn thoát khỏi chương trình?");
                builder.setNegativeButton("Đóng", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.setPositiveButton("Thoát", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        intent.setClass(getApplicationContext(), LoginActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
//                finishAffinity();
//                System.exit(0);
                return true;
            }
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void showAddSVDialog() {
        DialogFragment svDialog = new Dialog_Add_SV();
        svDialog.show(getSupportFragmentManager(), "addsinhvien");
    }

    @Override
    public void applySV(final DanhSachSinhVien danhSachSinhVien) {
        referenceSV.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                        String maSV = dataSnapshot.getKey();
                        if(maSV.equals(danhSachSinhVien.getMaSV())) {
                            danhSachSinhVien.setDiem(0.0);
                            danhSachSinhVien.setLanHoc(1);
                            danhSachSinhVien.setTenSV(dataSnapshot.getValue(SinhVien.class).getHoSV()
                                    + " " + dataSnapshot.getValue(SinhVien.class).getHoSV());
//                            reference.child(maSV).child("diem").setValue(danhSachSinhVien.getDiem());
//                            referenceSV.child(maSV).child("lanHoc").setValue(danhSachSinhVien.getLanHoc());
                            reference.child(maSV).setValue(danhSachSinhVien.toMap());

                            dsSinhVienAdapter.notifyDataSetChanged();
                            break;
                        }
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        dsSV.add(danhSachSinhVien);
        dsSinhVienAdapter.notifyDataSetChanged();
    }

}
