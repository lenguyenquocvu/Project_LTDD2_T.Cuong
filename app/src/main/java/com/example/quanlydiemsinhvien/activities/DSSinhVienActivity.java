package com.example.quanlydiemsinhvien.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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
import com.example.quanlydiemsinhvien.adapters.DSSinhVienAdapter;
import com.example.quanlydiemsinhvien.adapters.decorations.DividerItemDecoration;
import com.example.quanlydiemsinhvien.data_models.DanhSachLop;
import com.example.quanlydiemsinhvien.data_models.DanhSachSinhVien;
import com.example.quanlydiemsinhvien.dialogs.Dialog_Add_SV;
//import com.example.quanlydiemsinhvien.firebase_data.DSSVienDatabase;
import com.example.quanlydiemsinhvien.interfaces.AddSV;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import static com.example.quanlydiemsinhvien.activities.DSLopActivity.LOPTAG;

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
    private ArrayList<DanhSachSinhVien> danhsachsinhvien;
    private RecyclerView.LayoutManager layoutManager;
    TextView txtMaSV;
    TextView txtTenSV;
    TextView txtDiem;
    public static Intent intent;
    private DSSinhVienAdapter dsSinhVienAdapter;


    //Properties of Firebase
//    DSSVienDatabase mDatabase = new DSSVienDatabase();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.danhsachsinhvien_layout);
        setTitle("Danh Sách Sinh Viên");

        intent = getIntent();

        //Get View from layout
//        recyclerView = (RecyclerView) findViewById(R.id.recycler_dssinhvien);

        //Improve performace if changes in content do not change the layout size of the RecyclerView
//        recyclerView.setHasFixedSize(true);

        //Item decoration
        //recyclerView.addItemDecoration(new DividerItemDecoration(getResources().getDrawable(R.drawable.divider, getTheme()));

        // Get all data from firebase
//        mDatabase.getmDataRef().child("DanhSachSinhVien").addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                dataSinhVien.clear();
//
//                for (DataSnapshot node : snapshot.getChildren()) {
//                    DanhSachSinhVien dssv = node.getValue(DanhSachSinhVien.class);
//                    dataSinhVien.add(dssv);
//                }
//
//                // Update after changed data
//                mDatabase.getmDataRef().keepSynced(true);
//
//                // Specify an adapter
//                dsSinhVienAdapter = new DSSinhVienAdapter(dataSinhVien, context);
//                recyclerView.setAdapter(dsSinhVienAdapter);
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//                Log.d(LOPTAG, "Load data error: ", error.toException());
//            }
//        });

        // Use linear layout manager
//        layoutManager = new LinearLayoutManager(this);
//        recyclerView.setLayoutManager(layoutManager);


        recycler_dssinhvien = (RecyclerView) findViewById(R.id.recycler_dssinhvien);
        txtMaSV = findViewById(R.id.txtMaSV);
        txtTenSV = findViewById(R.id.txtTenSV);
        txtDiem = findViewById(R.id.txtDiemSV);

        danhsachsinhvien = new ArrayList<DanhSachSinhVien>();
        danhsachsinhvien.add(new DanhSachSinhVien("17211TT345", "Le Nguyen Quoc Vu", 8));
        danhsachsinhvien.add(new DanhSachSinhVien("17211TT789", "Bui Van Hieu", 7));
        danhsachsinhvien.add(new DanhSachSinhVien("17211TT123", "Nguyen Dinh Trieu", 6));
        danhsachsinhvien.add(new DanhSachSinhVien("17211TT345", "Le Nguyen Quoc Vu", 8));
        danhsachsinhvien.add(new DanhSachSinhVien("17211TT789", "Bui Van Hieu", 7));
        danhsachsinhvien.add(new DanhSachSinhVien("17211TT123", "Nguyen Dinh Trieu", 6));


        dsSinhVienAdapter = new DSSinhVienAdapter(danhsachsinhvien, this);
        recycler_dssinhvien.setAdapter(dsSinhVienAdapter);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        recycler_dssinhvien.setLayoutManager(linearLayoutManager);
        // Item decoration
        recycler_dssinhvien.addItemDecoration(new DividerItemDecoration(getResources().getDrawable(R.drawable.divider, getTheme())));

        DSSinhVienAdapter dssvAdapter = new DSSinhVienAdapter(danhsachsinhvien, this);
        ((DSSinhVienAdapter) dssvAdapter).setMode(Attributes.Mode.Single);
        recycler_dssinhvien.setAdapter(dssvAdapter);

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
            showAddSVDialog();
        }
        return super.onOptionsItemSelected(item);
    }

    public void showAddSVDialog() {
        DialogFragment svDialog = new Dialog_Add_SV();
        svDialog.show(getSupportFragmentManager(), "addsinhvien");
    }

    @Override
    public void applySV(DanhSachSinhVien danhSachSinhVien) {
        danhsachsinhvien.add(danhSachSinhVien);
        dsSinhVienAdapter.notifyDataSetChanged();
    }

}
