package com.example.quanlydiemsinhvien.activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
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
import com.example.quanlydiemsinhvien.adapters.DanhSachMonHocAdapter;
import com.example.quanlydiemsinhvien.data_models.MonHoc;
import com.example.quanlydiemsinhvien.dialogs.DialogThemMonHoc;
import com.example.quanlydiemsinhvien.divider.DividerItemDecoration;
import com.example.quanlydiemsinhvien.interfaces.OnItemClickToAddListener;
import com.example.quanlydiemsinhvien.interfaces.OnItemClickToEditListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static com.example.quanlydiemsinhvien.activities.DanhSachKhoaHocActivity.EDIT_SUCCESS_NOTIFY;

public class DanhSachMonHocActivity extends AppCompatActivity implements OnItemClickToAddListener, OnItemClickToEditListener {
    public static Intent intent;
    public static ArrayList<MonHoc> dsMonHoc = new ArrayList<MonHoc>();

    private RecyclerView rvDSMonHoc;
    private TextView tvEmptyView;
    MonHoc monHoc = new MonHoc();
    private DanhSachMonHocAdapter mAdapter;

    FirebaseDatabase rootNode;
    DatabaseReference reference;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.danh_sach_mon_hoc_layout);

        rvDSMonHoc = findViewById(R.id.rvDSMonHoc);

        rootNode = FirebaseDatabase.getInstance();
        reference = rootNode.getReference("MonHoc");

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    dsMonHoc.clear();
                    for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                        monHoc = dataSnapshot.getValue(MonHoc.class);
                        dsMonHoc.add(monHoc);
                    }
                }
                mAdapter = new DanhSachMonHocAdapter(DanhSachMonHocActivity.this, dsMonHoc);
                rvDSMonHoc.setAdapter(mAdapter);
                ((DanhSachMonHocAdapter) mAdapter).setMode(Attributes.Mode.Single);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL,false);
        rvDSMonHoc.setLayoutManager(layoutManager);

        rvDSMonHoc.addItemDecoration(new DividerItemDecoration(getResources().getDrawable(R.drawable.divider, getTheme())));
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
            case R.id.them_item: {
                openDialog();
                return true;
            }
            case R.id.thoat_item: {
                // do your code
                AlertDialog.Builder builder = new AlertDialog.Builder(DanhSachMonHocActivity.this);
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

    private void openDialog() {
        DialogThemMonHoc themMonHoc = new DialogThemMonHoc();
        themMonHoc.show(getSupportFragmentManager(), "thêm môn học");
    }

    @Override
    public void applyObject(Object object) {
        MonHoc mh = (MonHoc) object;
        reference.child(mh.getMaMH()).setValue(mh);
        mAdapter.notifyDataSetChanged();
        Toast.makeText(this, "Thêm môn học thành công!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void editObject(Object object) {
        MonHoc mh = (MonHoc) object;
        Map<String, Object> monHocValues = mh.toMap();

        Map<String, Object> childUpdates = new HashMap<>();
        childUpdates.put(mh.getMaMH(), monHocValues);
        reference.updateChildren(childUpdates);
        mAdapter.notifyDataSetChanged();
        Toast.makeText(getApplicationContext(), EDIT_SUCCESS_NOTIFY, Toast.LENGTH_SHORT).show();
    }
}
