package com.example.quanlydiemsinhvien.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.daimajia.swipe.util.Attributes;
import com.example.quanlydiemsinhvien.R;
import com.example.quanlydiemsinhvien.adapters.KhoaHocAdapter;
import com.example.quanlydiemsinhvien.data_models.KhoaHoc;
import com.example.quanlydiemsinhvien.data_models.LopHocPhan;
import com.example.quanlydiemsinhvien.dialogs.DialogAddOrEditKhoahoc;
import com.example.quanlydiemsinhvien.divider.DividerItemDecoration;
import com.example.quanlydiemsinhvien.interfaces.OnItemClickToAddListener;
import com.example.quanlydiemsinhvien.interfaces.OnItemClickToDeleteListener;
import com.example.quanlydiemsinhvien.interfaces.OnItemClickToEditListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static com.example.quanlydiemsinhvien.activities.DanhSachChuongTrinhDaoTaoActivity.DB_CTDT_NAME;
import static com.example.quanlydiemsinhvien.activities.DanhSachLopHPTheoMHActivity.DB_LOPHOCPHAN_NAME;


public class DanhSachKhoaHocActivity extends AppCompatActivity implements OnItemClickToAddListener, OnItemClickToEditListener, OnItemClickToDeleteListener {
    RecyclerView rvDsKhoaHoc;
    private ArrayList<KhoaHoc> dsKhoaHoc;
    public static TextView txtNganh;
    KhoaHocAdapter khoaHocAdapter;
    public static Intent intent;
    FirebaseDatabase rootNode;
    DatabaseReference reference;
    private KhoaHoc khoaHoc;
    public static String DB_KHOAHOC_NAME = "KhoaHoc";
    public static final String ADD_SUCCESS_NOTIFY = "Thêm thành công!";
    public static final String EDIT_SUCCESS_NOTIFY = "Sửa thành công!";
    public static final String DELETE_SUCCESS_NOTIFY = "Xóa thành công!";
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.danh_sach_khoa_hoc_layout);

        intent = getIntent();
        intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        dsKhoaHoc = new ArrayList<KhoaHoc>();
        rvDsKhoaHoc = (RecyclerView) findViewById(R.id.rvDanhsachKhoaHoc);
        rootNode = FirebaseDatabase.getInstance();
        reference = rootNode.getReference(DB_KHOAHOC_NAME);

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                dsKhoaHoc.clear();
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    khoaHoc = new KhoaHoc();
                    khoaHoc = dataSnapshot.getValue(KhoaHoc.class);
                    dsKhoaHoc.add(khoaHoc);
                }
                khoaHocAdapter = new KhoaHocAdapter(DanhSachKhoaHocActivity.this, dsKhoaHoc);
                rvDsKhoaHoc.setHasFixedSize(true);
                rvDsKhoaHoc.setAdapter(khoaHocAdapter);
                ((KhoaHocAdapter) khoaHocAdapter).setMode(Attributes.Mode.Single);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e(DanhSachChuongTrinhDaoTaoActivity.TAG, DialogAddOrEditKhoahoc.CANCEL_STRING, error.toException());
            }
        });

        txtNganh = findViewById(R.id.txtNganh);

        txtNganh.setText("CNTT");

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        rvDsKhoaHoc.setLayoutManager(linearLayoutManager);
        // Item decoration
        rvDsKhoaHoc.addItemDecoration(new DividerItemDecoration(getResources().getDrawable(R.drawable.divider, getTheme())));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_them, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.mnu_them: {
                openDialogAddKhoaHoc();
            }
            break;
            default:
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    private void openDialogAddKhoaHoc() {
        DialogAddOrEditKhoahoc addOrEditKhoahocActivity = new DialogAddOrEditKhoahoc();
        addOrEditKhoahocActivity.show(getSupportFragmentManager(), "Thêm khóa học");
    }

    // Thêm khóa học
    @Override
    public void applyObject(Object object) {
        khoaHoc = new KhoaHoc();
        khoaHoc = (KhoaHoc) object;

        reference.child(khoaHoc.getMaKH()).setValue(khoaHoc);
        khoaHocAdapter.notifyDataSetChanged();
        Toast.makeText(DanhSachKhoaHocActivity.this, ADD_SUCCESS_NOTIFY, Toast.LENGTH_SHORT).show();
    }

    // Sửa thông tin khóa học
    @Override
    public void editObject(Object object) {
        khoaHoc = new KhoaHoc();
        khoaHoc = (KhoaHoc) object;

        String maKH = khoaHoc.getMaKH();
        Map<String, Object> khoaHocValues = khoaHoc.toMap();

        Map<String, Object> childUpdates = new HashMap<>();
        childUpdates.put(maKH, khoaHocValues);

        reference.updateChildren(childUpdates);
        khoaHocAdapter.notifyDataSetChanged();
        Toast.makeText(DanhSachKhoaHocActivity.this, EDIT_SUCCESS_NOTIFY, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void deleteObject(Object object, int position) {
        dsKhoaHoc.remove(object);
        khoaHoc = (KhoaHoc) object;

        String maNganh = txtNganh.getText().toString();
        final DatabaseReference referenceLHP = rootNode.getReference(DB_LOPHOCPHAN_NAME);

        DatabaseReference referenceCTDT = rootNode.getReference(DB_CTDT_NAME).child(maNganh).child(khoaHoc.getMaKH());
        referenceCTDT.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot!=null){
                    for(DataSnapshot dataSnapshot:snapshot.getChildren()){
                        final String key = dataSnapshot.getKey();
                        referenceLHP.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                if(snapshot != null){
                                    for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                                        if(dataSnapshot.getValue(LopHocPhan.class).getMaMH().equals(key)){
                                            dataSnapshot.getRef().removeValue();
                                        }
                                    }
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {
                                Log.e(DanhSachChuongTrinhDaoTaoActivity.TAG, DialogAddOrEditKhoahoc.CANCEL_STRING, error.toException());
                            }
                        });
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        referenceCTDT.removeValue();
//        reference.child(((KhoaHoc) object).getMaKH()).removeValue();
        rvDsKhoaHoc.removeViewAt(position);
        khoaHocAdapter.notifyDataSetChanged();
        Toast.makeText(DanhSachKhoaHocActivity.this, DELETE_SUCCESS_NOTIFY, Toast.LENGTH_SHORT).show();
    }
}
