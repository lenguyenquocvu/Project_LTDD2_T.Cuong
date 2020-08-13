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
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.daimajia.swipe.util.Attributes;
import com.example.quanlydiemsinhvien.R;
import com.example.quanlydiemsinhvien.adapters.LopHocPhanTheoMonAdapter;
import com.example.quanlydiemsinhvien.data_models.LopHocPhan;
import com.example.quanlydiemsinhvien.dialogs.DialogAddOrEditKhoahoc;
import com.example.quanlydiemsinhvien.dialogs.DialogAddOrEditLopHocPhan;
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

public class DanhSachLopHPTheoMHActivity extends AppCompatActivity implements OnItemClickToAddListener, OnItemClickToDeleteListener, OnItemClickToEditListener {
    RecyclerView rvLopHPTheoMH;
    private ArrayList<LopHocPhan> dsLopHPTheoMH;
    private ArrayList<LopHocPhan> dsLopHPTheoMH_after_click;
    private TextView txtTenMH;
    private LopHocPhanTheoMonAdapter lopHocPhanTheoMonAdapter;

    FirebaseDatabase rootNode;
    DatabaseReference reference;

    private Intent intent;
    private String maMH;
    private String tenMH;
    private LopHocPhan lopHocPhan;

    public static final String DB_LOPHOCPHAN_NAME = "LopHocPhan";
    public static final String DB_MAMH_COL = "maMH";
    public static final String DB_TENMH_COL = "tenMH";
    public static final String SIZE_DSLHP = "LHPSize";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.danh_sach_lop_hoc_phan_theo_mon_layout);

        rvLopHPTheoMH = findViewById(R.id.rvDsLopHPTheoMH);
        intent = getIntent();

        maMH = intent.getStringExtra(DB_MAMH_COL);
        tenMH = intent.getStringExtra(DB_TENMH_COL);

        txtTenMH = findViewById(R.id.edtTenMH);
        txtTenMH.setText(tenMH);

        dsLopHPTheoMH = new ArrayList<LopHocPhan>();


        dsLopHPTheoMH_after_click = new ArrayList<LopHocPhan>();

        rootNode = FirebaseDatabase.getInstance();
        reference = rootNode.getReference(DB_LOPHOCPHAN_NAME);

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                dsLopHPTheoMH_after_click.clear();
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    if(dataSnapshot.child(DB_MAMH_COL).getValue().equals(maMH)){
                        lopHocPhan = new LopHocPhan();
                        lopHocPhan = dataSnapshot.getValue(LopHocPhan.class);
                        dsLopHPTheoMH_after_click.add(lopHocPhan);
                    }
                }
                lopHocPhanTheoMonAdapter = new LopHocPhanTheoMonAdapter(DanhSachLopHPTheoMHActivity.this, dsLopHPTheoMH_after_click);
                rvLopHPTheoMH.setAdapter(lopHocPhanTheoMonAdapter);
                ((LopHocPhanTheoMonAdapter) lopHocPhanTheoMonAdapter).setMode(Attributes.Mode.Single);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e(DanhSachChuongTrinhDaoTaoActivity.TAG, DialogAddOrEditKhoahoc.THOAT_STRING, error.toException());
            }
        });

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        rvLopHPTheoMH.setLayoutManager(linearLayoutManager);
        // Item decoration
        rvLopHPTheoMH.addItemDecoration(new DividerItemDecoration(getResources().getDrawable(R.drawable.divider, getTheme())));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.option_menu, menu);
        return true;
    }


    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.them_item: {
                openDialogAddLopHocPhan();
                return true;
            }
            case R.id.thoat_item: {
                // do your code
                AlertDialog.Builder builder = new AlertDialog.Builder(DanhSachLopHPTheoMHActivity.this);
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

    private void openDialogAddLopHocPhan() {
        DialogAddOrEditLopHocPhan addLopHocPhan = new DialogAddOrEditLopHocPhan();
        Bundle bundle = new Bundle();
        bundle.putInt(SIZE_DSLHP, dsLopHPTheoMH_after_click.size());
        addLopHocPhan.setArguments(bundle);
        addLopHocPhan.show(getSupportFragmentManager(), "Thêm lớp học phần");

    }

    @Override
    public void applyObject(Object object) {
        lopHocPhan =  (LopHocPhan) object;
        reference.child(lopHocPhan.getMaLHP()).setValue(lopHocPhan);
        lopHocPhanTheoMonAdapter.notifyDataSetChanged();
        Toast.makeText(DanhSachLopHPTheoMHActivity.this, DanhSachKhoaHocActivity.ADD_SUCCESS_NOTIFY, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void deleteObject(Object object, int position) {
        dsLopHPTheoMH_after_click.remove(object);
        rvLopHPTheoMH.removeViewAt(position);
        reference.child(((LopHocPhan) object).getMaLHP()).removeValue();
        lopHocPhanTheoMonAdapter.notifyDataSetChanged();
        Toast.makeText(DanhSachLopHPTheoMHActivity.this, DanhSachKhoaHocActivity.DELETE_SUCCESS_NOTIFY, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void editObject(Object object) {
        lopHocPhan =  (LopHocPhan) object;
        String maLHP = lopHocPhan.getMaLHP();
        Map<String, Object> lopHocPhanValues = lopHocPhan.toMap();

        Map<String, Object> childUpdates = new HashMap<>();
        childUpdates.put(maLHP, lopHocPhanValues);

        reference.updateChildren(childUpdates);
        lopHocPhanTheoMonAdapter.notifyDataSetChanged();

        Toast.makeText(DanhSachLopHPTheoMHActivity.this, DanhSachKhoaHocActivity.EDIT_SUCCESS_NOTIFY, Toast.LENGTH_SHORT).show();
    }
}
