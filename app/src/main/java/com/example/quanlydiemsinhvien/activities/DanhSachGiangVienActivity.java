package com.example.quanlydiemsinhvien.activities;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.daimajia.swipe.util.Attributes;
import com.example.quanlydiemsinhvien.R;
import com.example.quanlydiemsinhvien.adapters.GiangVienSwipeRecyclerViewAdapter;
import com.example.quanlydiemsinhvien.data_models.AccountGiangVien;
import com.example.quanlydiemsinhvien.data_models.GiangVien;
import com.example.quanlydiemsinhvien.dialogs.DialogAddOrEditGiangVien;
import com.example.quanlydiemsinhvien.divider.DividerItemDecoration;
import com.example.quanlydiemsinhvien.interfaces.OnItemClickToAddGiangVienListener;
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
    public static ArrayList<GiangVien> dsGiangVien;

    private RecyclerView recyclerView;
    private TextView tvEmptyView;

    GiangVienSwipeRecyclerViewAdapter mAdapter;

    FirebaseDatabase rootNode;
    DatabaseReference reference;
    DatabaseReference accGVReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.danh_sach_giang_vien_layout);
        setTitle("Quản lý giảng viên");

        intent = getIntent();

        dsGiangVien = new ArrayList<GiangVien>();
        rootNode = FirebaseDatabase.getInstance();
        reference = rootNode.getReference("GiangVien");
        accGVReference = rootNode.getReference("accountGiangVien");

        tvEmptyView = (TextView) findViewById(R.id.empty_view);
        recyclerView = findViewById(R.id.list_teacher_recycler_view);


        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                dsGiangVien.clear();
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()) {

                        GiangVien giangVien = new GiangVien();
                        giangVien = dataSnapshot.getValue(GiangVien.class);
                        dsGiangVien.add(giangVien);
                    }
                    mAdapter = new GiangVienSwipeRecyclerViewAdapter(DanhSachGiangVienActivity.this, dsGiangVien);
                    recyclerView.setHasFixedSize(true);
                    recyclerView.setAdapter(mAdapter);
                    ((GiangVienSwipeRecyclerViewAdapter) mAdapter).setMode(Attributes.Mode.Single);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.w("Failed to read value.", error.toException());
            }
        });



        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
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

//        Toast.makeText(this, "Selected Item: " +item.getTitle(), Toast.LENGTH_SHORT).show();

        Toast.makeText(this, "Selected Item: " + item.getTitle(), Toast.LENGTH_SHORT).show();

        switch (item.getItemId()) {
            case R.id.them_item:
                openDialog();
                return true;
            case R.id.thoat_item:
                // do your code
                AlertDialog.Builder builder = new AlertDialog.Builder(DanhSachGiangVienActivity.this);
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
    public void applyGiangVien(GiangVien giangVien) {

        dsGiangVien.add(0, giangVien);

        reference.child(giangVien.getMaGV()).setValue(giangVien);

        accGVReference.child(giangVien.getMaGV()).setValue(new AccountGiangVien(giangVien.getMaGV(), giangVien.getMaGV()));
    }

    @Override
    public void delete(Object object) {
        GiangVien giangVien = (GiangVien) object;
        dsGiangVien.remove(object);

        reference.child(((GiangVien) object).getMaGV()).removeValue();

        accGVReference.child(((GiangVien) object).getMaGV()).removeValue();

        mAdapter.notifyDataSetChanged();
    }


    @Override
    public void onItemClicked(GiangVien giangVien, int position) {
        for (GiangVien gv : dsGiangVien) {
            if (gv.getMaGV().equals(giangVien.getMaGV())) {

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