package com.example.quanlydiemsinhvien.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.swipe.util.Attributes;
import com.example.quanlydiemsinhvien.R;
import com.example.quanlydiemsinhvien.adapters.GiangVienSwipeRecyclerViewAdapter;
import com.example.quanlydiemsinhvien.adapters.decorations.DividerItemDecoration;
import com.example.quanlydiemsinhvien.data_models.GiangVienModel;
import com.example.quanlydiemsinhvien.dialogs.DialogAddOrEditGiangVien;
import com.example.quanlydiemsinhvien.interfaces.OnItemClickToAddGiangVienListener;
import com.example.quanlydiemsinhvien.interfaces.OnItemClickToDeleteListener;
import com.example.quanlydiemsinhvien.interfaces.OnItemClickToDeleteListener_Huong;
import com.example.quanlydiemsinhvien.interfaces.OnItemClickToEditGiangVienListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

public class DanhSachGiangVienActivity extends AppCompatActivity implements OnItemClickToAddGiangVienListener, OnItemClickToEditGiangVienListener, OnItemClickToDeleteListener_Huong {
    public static Intent intent;
    public static ArrayList<GiangVienModel> dsGiangVien;

    private RecyclerView recyclerView;
    private TextView tvEmptyView;

    GiangVienSwipeRecyclerViewAdapter mAdapter;

    FirebaseDatabase rootNode;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.danh_sach_giang_vien_layout);
        setTitle("Quản lý giảng viên");

        intent = getIntent();

        dsGiangVien = new ArrayList<GiangVienModel>();
        rootNode = FirebaseDatabase.getInstance();
        reference = rootNode.getReference("GiangVien");

        tvEmptyView = (TextView) findViewById(R.id.empty_view);
        recyclerView = findViewById(R.id.list_teacher_recycler_view);


        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                dsGiangVien.clear();
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    GiangVienModel giangVien = new GiangVienModel();
                    giangVien = dataSnapshot.getValue(GiangVienModel.class);
                    dsGiangVien.add(giangVien);
                }
                mAdapter  = new GiangVienSwipeRecyclerViewAdapter(DanhSachGiangVienActivity.this, dsGiangVien);
                recyclerView.setHasFixedSize(true);
                recyclerView.setAdapter(mAdapter);
                ((GiangVienSwipeRecyclerViewAdapter) mAdapter).setMode(Attributes.Mode.Single);
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
        DialogAddOrEditGiangVien addOrEditGiangVien = new DialogAddOrEditGiangVien();
        addOrEditGiangVien.show(getSupportFragmentManager(), "Thêm giảng viên");
    }

    @Override
    public void applyGiangVien(GiangVienModel giangVien) {
        dsGiangVien.add(0,giangVien);
        reference.child(giangVien.getMaGV()).setValue(giangVien);
    }

    @Override
    public void delete(Object object) {
        GiangVienModel giangVien = (GiangVienModel) object;
        dsGiangVien.remove(object);

        reference.child(((GiangVienModel) object).getMaGV()).removeValue();

        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onItemClicked(GiangVienModel giangVien, int position) {
        for(GiangVienModel gv : dsGiangVien){
            if(gv.getMaGV().equals(giangVien.getMaGV())){
                gv.setMaGV(giangVien.getMaGV());
                gv.setTenGV(giangVien.getTenGV());
                gv.setHoGV(giangVien.getHoGV());
                gv.setNgaySinh(giangVien.getNgaySinh());
                gv.setSdt(giangVien.getSdt());
                gv.setEmail(giangVien.getEmail());
                gv.setMaNganh(giangVien.getMaNganh());

                HashMap<String, Object> map = new HashMap<>();
                map.put("maGV", gv.getMaGV());
                map.put("tenGV", gv.getTenGV());
                map.put("hoGV", gv.getHoGV());
                map.put("ngaySinh", gv.getNgaySinh());
                map.put("sdt", gv.getSdt());
                map.put("email", gv.getEmail());
                map.put("maNganh", gv.getMaNganh());

                HashMap<String, Object> mapLink = new HashMap<>();
                mapLink.put("/" + gv.getMaGV() + "/", map);
                reference.updateChildren(mapLink);
            }
            break;
        }
        mAdapter.notifyDataSetChanged();
    }

}