package com.example.quanlydiemsinhvien.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quanlydiemsinhvien.R;
import com.example.quanlydiemsinhvien.adapters.DSLopAdapter;
import com.example.quanlydiemsinhvien.data_models.DanhSachLop;
import com.example.quanlydiemsinhvien.dialogs.Dialog_Add_Lop;
import com.example.quanlydiemsinhvien.firebase_data.DSLopDatabase;
import com.example.quanlydiemsinhvien.interfaces.AddLop;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class DSLopActivity extends AppCompatActivity implements AddLop {
    public static final String LOPTAG = "LopHocPhan";
    public static ArrayList<DanhSachLop> dataLop = new ArrayList<DanhSachLop>();
    public static DSLopAdapter dsLopAdapter;

    public static Intent intent;
    private Context context = DSLopActivity.this;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;

//    RecyclerView recycler_dslop;
//    private ArrayList<DanhSachLop> danhsachlop;
//    TextView txtMaLHP;
//    TextView txtTenLHP;
//    private  DSLopAdapter dsLopAdapter;

    //Properties of Firebase
    DSLopDatabase mDatabase = new DSLopDatabase();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.danhsachlop_layout);
        setTitle("Danh Sách Lớp");

        intent = getIntent();

        //Get View from layout
        recyclerView = (RecyclerView) findViewById(R.id.recycler_dslop);


        //Item decoration
       // recyclerView.addItemDecoration(new DividerItemDecoration(getResources().getDrawable(R.drawable.divider, getTheme()));

        // Get all data from firebase
        mDatabase.getmDataRef().child(LOPTAG).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                dataLop.clear();

                for (DataSnapshot node : snapshot.getChildren()) {
                    DanhSachLop danhsachlop = node.getValue(DanhSachLop.class);
                    dataLop.add(danhsachlop);
                }

                // Update after changed data
                mDatabase.getmDataRef().keepSynced(true);

                // Specify an adapter
                dsLopAdapter = new DSLopAdapter(context, dataLop);
                recyclerView.setAdapter(dsLopAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.d(LOPTAG, "Load data error: ", error.toException());
            }
        });

        // Use linear layout manager
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

//        txtMaLHP = findViewById(R.id.txtMaLHP);
//        txtTenLHP = findViewById(R.id.txtTenLHP);
//        danhsachlop = new ArrayList<DanhSachLop>();
//        danhsachlop.add(new DanhSachLop("LHP-01","Lop hoc phan 1"));
//        danhsachlop.add(new DanhSachLop("LHP-02","Lop hoc phan 2"));
//        danhsachlop.add(new DanhSachLop("LHP-03","Lop hoc phan 3"));
//        dsLopAdapter = new DSLopAdapter(this, danhsachlop);
//        recycler_dslop.setAdapter(dsLopAdapter);
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL,false);
//        recycler_dslop.setLayoutManager(linearLayoutManager);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.add, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.item_menu_them) {
            Log.d("test", "Item menu Them");
            showAddLopDialog();
        }
        return true;
    }
    public void showAddLopDialog() {
        DialogFragment lopDialog = new Dialog_Add_Lop();
        lopDialog.show(getSupportFragmentManager(), "addlop");
    }

    @Override
    public void applyLop(DanhSachLop danhSachLop) {
        dataLop.add(danhSachLop);
        dsLopAdapter.notifyDataSetChanged();
    }
}
