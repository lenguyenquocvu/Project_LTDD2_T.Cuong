package com.example.quanlydiemsinhvien.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import com.daimajia.swipe.util.Attributes;
import com.example.quanlydiemsinhvien.R;
import com.example.quanlydiemsinhvien.adapters.KhoaHocAdapter;
import com.example.quanlydiemsinhvien.adapters.decorations.DividerItemDecoration;
import com.example.quanlydiemsinhvien.data_models.KhoaHoc;
import com.example.quanlydiemsinhvien.dialog.DialogAddOrEditKhoahoc;
import com.example.quanlydiemsinhvien.interfaces.OnItemClickToAddKhoahocListener;
import com.example.quanlydiemsinhvien.interfaces.OnItemClickToDeleteListener;
import com.example.quanlydiemsinhvien.interfaces.OnItemClickToEditKhoaHocListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import jp.wasabeef.recyclerview.animators.FadeInLeftAnimator;

public class DanhSachKhoaHocActivity extends AppCompatActivity implements OnItemClickToAddKhoahocListener, OnItemClickToEditKhoaHocListener, OnItemClickToDeleteListener {
    RecyclerView rvDsKhoaHoc;
    private ArrayList<KhoaHoc> dsKhoaHoc;
    public static TextView txtNganh;
    KhoaHocAdapter khoaHocAdapter;
    public static Intent intent;
    FirebaseDatabase rootNode;
    DatabaseReference reference;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.danh_sach_khoa_hoc_layout);

        intent = getIntent();
        intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        dsKhoaHoc = new ArrayList<KhoaHoc>();
        rvDsKhoaHoc = (RecyclerView) findViewById(R.id.rvDanhsachKhoaHoc);
        rootNode = FirebaseDatabase.getInstance();
        reference = rootNode.getReference("KhoaHoc");

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                dsKhoaHoc.clear();
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    KhoaHoc khoaHoc = new KhoaHoc();
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

            }
        });



        txtNganh = findViewById(R.id.txtNganh);

        txtNganh.setText("Ngành 12345");

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        rvDsKhoaHoc.setLayoutManager(linearLayoutManager);
        // Item decoration
        rvDsKhoaHoc.addItemDecoration(new DividerItemDecoration(getResources().getDrawable(R.drawable.divider, getTheme())));
        // Item Animators
        rvDsKhoaHoc.setItemAnimator(new FadeInLeftAnimator());



        // Scroll listener
        rvDsKhoaHoc.addOnScrollListener(onScroll);
    }

    public RecyclerView.OnScrollListener onScroll = new RecyclerView.OnScrollListener() {
        @Override
        public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
        }

        @Override
        public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_them, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.mnu_them: {
                final KhoaHoc khoaHoc = new KhoaHoc();
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
    public void applyKhoaHoc(KhoaHoc khoaHoc) {
        dsKhoaHoc.add(0, khoaHoc);
        ///
        khoaHocAdapter.notifyItemInserted(0);
        rvDsKhoaHoc.scrollToPosition(0);
        khoaHocAdapter.notifyItemRangeChanged(0, dsKhoaHoc.size());

        reference.child(khoaHoc.getMaKH()).setValue(khoaHoc);
    }

    // Sửa thông tin khóa học
    @Override
    public void onItemClicked(KhoaHoc khoaHoc, int position) {
        for(KhoaHoc kh : dsKhoaHoc){
            if(kh.getMaKH().equals(khoaHoc.getMaKH())){
                kh.setBatDau(khoaHoc.getBatDau());
                kh.setKetThuc(khoaHoc.getKetThuc());

                reference.child(kh.getMaKH()).child("batDau").setValue(kh.getBatDau());
                reference.child(kh.getMaKH()).child("ketThuc").setValue(kh.getKetThuc());
            }
            break;
        }
//        khoaHocAdapter.mItemManger.bindView(rvDsKhoaHoc.getChildAt(position), position);
        khoaHocAdapter.notifyDataSetChanged();
    }

    @Override
    public void deleteKhoaHoc(Object object) {
        KhoaHoc khoaHoc = (KhoaHoc) object;
        dsKhoaHoc.remove(object);

        reference.child(((KhoaHoc) object).getMaKH()).removeValue();

        khoaHocAdapter.notifyDataSetChanged();
    }
}
