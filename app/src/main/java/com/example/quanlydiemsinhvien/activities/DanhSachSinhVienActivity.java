package com.example.quanlydiemsinhvien.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.daimajia.swipe.util.Attributes;
import com.example.quanlydiemsinhvien.R;
import com.example.quanlydiemsinhvien.adapters.GiangVienSwipeRecyclerViewAdapter;
import com.example.quanlydiemsinhvien.adapters.SinhVienSwipeRecyclerViewAdapter;
import com.example.quanlydiemsinhvien.data_models.GiangVienModel;
import com.example.quanlydiemsinhvien.data_models.SinhVienModel;
import com.example.quanlydiemsinhvien.decorations.DividerItemDecoration;
import com.example.quanlydiemsinhvien.dialogs.DialogAddOrEditGiangVien;
import com.example.quanlydiemsinhvien.dialogs.DialogAddOrEditSinhVien;
import com.example.quanlydiemsinhvien.interfaces.OnItemClickToAddGiangVienListener;
import com.example.quanlydiemsinhvien.interfaces.OnItemClickToAddSinhVienListener;
import com.example.quanlydiemsinhvien.interfaces.OnItemClickToDeleteListener;
import com.example.quanlydiemsinhvien.interfaces.OnItemClickToEditGiangVienListener;
import com.example.quanlydiemsinhvien.interfaces.OnItemClickToEditSinhVienListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

public class DanhSachSinhVienActivity extends AppCompatActivity implements OnItemClickToAddSinhVienListener, OnItemClickToDeleteListener, OnItemClickToEditSinhVienListener {
    public static Intent intent;
    public static ArrayList<SinhVienModel> dsSinhVien;

    private RecyclerView recyclerView;
    private TextView tvEmptyView;

    SinhVienSwipeRecyclerViewAdapter mAdapter;

    FirebaseDatabase rootNode;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.danh_sach_sinh_vien_layout);
        setTitle("Quản lý sinh viên");

        dsSinhVien = new ArrayList<SinhVienModel>();
        intent = getIntent();
        rootNode = FirebaseDatabase.getInstance();
        reference = rootNode.getReference("SinhVien");

        tvEmptyView = (TextView) findViewById(R.id.empty_view);
        recyclerView = findViewById(R.id.list_student_recycler_view);


        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                dsSinhVien.clear();
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    SinhVienModel sinhVien = new SinhVienModel();
                    sinhVien = dataSnapshot.getValue(SinhVienModel.class);
                    dsSinhVien.add(sinhVien);
                }
                mAdapter  = new SinhVienSwipeRecyclerViewAdapter(DanhSachSinhVienActivity.this, dsSinhVien);
                recyclerView.setHasFixedSize(true);
                recyclerView.setAdapter(mAdapter);
                ((SinhVienSwipeRecyclerViewAdapter) mAdapter).setMode(Attributes.Mode.Single);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.w("Failed to read value.", error.toException());
            }
        });

        LinearLayoutManager layoutManager =new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        // Item decoration
        recyclerView.addItemDecoration(new DividerItemDecoration(getResources().getDrawable(R.drawable.divider, getTheme())));

        /* Scroll Listeners */
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                Log.e("RecyclerView", "onScrollStateChanged");
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.option_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Toast.makeText(this, "Selected Item: " +item.getTitle(), Toast.LENGTH_SHORT).show();
        switch (item.getItemId()) {
            case R.id.them_item:
                openDialog();
                return true;
            case R.id.thoat_item:
                // do your code
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    private void openDialog() {
        DialogAddOrEditSinhVien addOrEditSinhVien = new DialogAddOrEditSinhVien();
        addOrEditSinhVien.show(getSupportFragmentManager(), "Thêm sinh viên");
    }
    @Override
    public void delete(Object object) {
        SinhVienModel sinhVien = (SinhVienModel) object;
        dsSinhVien.remove(object);

        reference.child(((SinhVienModel) object).getMaSV()).removeValue();

        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void applySinhVien(SinhVienModel sinhVien) {
        dsSinhVien.add(0,sinhVien);
        reference.child(sinhVien.getMaSV()).setValue(sinhVien);
    }

    @Override
    public void onItemClicked(SinhVienModel sinhVien, int position) {
        for(SinhVienModel sv : dsSinhVien){
            if(sv.getMaSV().equals(sinhVien.getMaSV())){
                sv.setMaSV(sinhVien.getMaSV());
                sv.setTenSV(sinhVien.getTenSV());
                sv.setHoSV(sinhVien.getHoSV());
                sv.setNgaySinh(sinhVien.getNgaySinh());
                sv.setSdt(sinhVien.getSdt());
                sv.setDiaChi(sinhVien.getDiaChi());
                sv.setEmail(sinhVien.getEmail());
                sv.setMaNganh(sinhVien.getMaNganh());

                HashMap<String, Object> map = new HashMap<>();
                map.put("maSV", sv.getMaSV());
                map.put("tenSV", sv.getTenSV());
                map.put("hoSV", sv.getHoSV());
                map.put("ngaySinh", sv.getNgaySinh());
                map.put("sdt", sv.getSdt());
                map.put("diaChi", sv.getDiaChi());
                map.put("email", sv.getEmail());
                map.put("maNganh", sv.getMaNganh());

                HashMap<String, Object> mapLink = new HashMap<>();
                mapLink.put("/" + sv.getMaSV() + "/", map);
                reference.updateChildren(mapLink);
            }
            break;
        }
        mAdapter.notifyDataSetChanged();
    }
}