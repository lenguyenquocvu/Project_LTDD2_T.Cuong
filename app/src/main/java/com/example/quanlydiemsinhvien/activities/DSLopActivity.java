package com.example.quanlydiemsinhvien.activities;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
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

import static com.example.quanlydiemsinhvien.adapters.LopHocPhanTheoMonAdapter.MALOP;

public class DSLopActivity extends AppCompatActivity implements AddLop {
    public static final String LOPTAG = "LopHocPhan";
    public static ArrayList<DanhSachLop> dataLop = new ArrayList<DanhSachLop>();
    public static DSLopAdapter dsLopAdapter;

    public Intent intent;
    private Context context = DSLopActivity.this;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private String maMH;
    private String maGV;
    private TextView txtMaGV;
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

        //Get View from layout
        recyclerView = (RecyclerView) findViewById(R.id.recycler_dslop);
        txtMaGV = findViewById(R.id.maGV);


        intent = getIntent();
        maGV = intent.getStringExtra("id");
        txtMaGV.setText(maGV);
        //Item decoration
       // recyclerView.addItemDecoration(new DividerItemDecoration(getResources().getDrawable(R.drawable.divider, getTheme()));

        // Get all data from firebase
        mDatabase.getmDataRef().child(LOPTAG).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                dataLop.clear();

                for (DataSnapshot node : snapshot.getChildren()) {
                    DanhSachLop danhsachlop = node.getValue(DanhSachLop.class);
                    if(maGV.equals(danhsachlop.getMaGV())) {
                        dataLop.add(danhsachlop);
                    }
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
        inflater.inflate(R.menu.menu_thoat, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.thoat_item: {
                // do your code
                AlertDialog.Builder builder = new AlertDialog.Builder(DSLopActivity.this);
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
