package com.example.quanlydiemsinhvien.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.daimajia.swipe.util.Attributes;
import com.example.quanlydiemsinhvien.MainActivity;
import com.example.quanlydiemsinhvien.R;
import com.example.quanlydiemsinhvien.adapters.ChuongTrinhDaoTaoAdapter;
import com.example.quanlydiemsinhvien.adapters.decorations.DividerItemDecoration;
import com.example.quanlydiemsinhvien.data_models.ChuongTrinhDaoTao;
import com.example.quanlydiemsinhvien.data_models.MonHoc;
import com.example.quanlydiemsinhvien.data_models.Nganh;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import jp.wasabeef.recyclerview.animators.FadeInLeftAnimator;

public class DanhSachChuongTrinhDaoTaoActivity extends AppCompatActivity {
    RecyclerView rvChuongTrinhDaoTaos;
    private ArrayList<MonHoc> dsMonHocs = new ArrayList<MonHoc>();

    TextView txtNganh;
    private ChuongTrinhDaoTaoAdapter chuongTrinhDaoTaoAdapter;

    private Intent intent;

    private String maKH;
    private String maNganh;
    private MonHoc monHoc;

    private Map<String, Object> map;
    FirebaseDatabase rootNode;
    DatabaseReference reference;
    DatabaseReference referenceCTDT;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chuong_trinh_dao_tao_layout);

        rvChuongTrinhDaoTaos = findViewById(R.id.rvChuongtrinhdaotao);
        txtNganh = findViewById(R.id.txtNganh);
        dsMonHocs = new ArrayList<MonHoc>();
        intent = getIntent();
        maKH = intent.getStringExtra("maKH");
        maNganh = intent.getStringExtra("Nganh");
        txtNganh.setText(maNganh + " " + maKH);

        rootNode = FirebaseDatabase.getInstance();
        reference = rootNode.getReference("ChuongTrinhDaoTao").child(maNganh).child(maKH);
        referenceCTDT = rootNode.getReference("MonHoc");

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                dsMonHocs.clear();
                for (final DataSnapshot dataSnapshot : snapshot.getChildren()) {

                    referenceCTDT.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            for (DataSnapshot dataSnapshot1 : snapshot.getChildren()) {
                                if (dataSnapshot.getKey().equals(dataSnapshot1.getKey())) {
                                    monHoc = new MonHoc();
                                    monHoc = dataSnapshot1.getValue(MonHoc.class);
                                    Log.d("test", monHoc.toString());
                                    dsMonHocs.add(monHoc);
                                }
                            }
                            chuongTrinhDaoTaoAdapter = new ChuongTrinhDaoTaoAdapter(DanhSachChuongTrinhDaoTaoActivity.this, dsMonHocs);
                            rvChuongTrinhDaoTaos.setHasFixedSize(true);
                            rvChuongTrinhDaoTaos.setAdapter(chuongTrinhDaoTaoAdapter);
                            ((ChuongTrinhDaoTaoAdapter) chuongTrinhDaoTaoAdapter).setMode(Attributes.Mode.Single);
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

//        dsChuongTrinhDaoTaos_after_click = new  ArrayList<MonHoc>();
//        for(MonHoc monHoc : dsMonHocs){
//            String maKH = DanhSachKhoaHocActivity.intent.getStringExtra("maKH");
//            if(monHoc.getMaKH().equals(maKH)){
//                dsChuongTrinhDaoTaos_after_click.add(monHoc);
//            }
//        }
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        rvChuongTrinhDaoTaos.setLayoutManager(linearLayoutManager);
        // Item decoration
        rvChuongTrinhDaoTaos.addItemDecoration(new DividerItemDecoration(getResources().getDrawable(R.drawable.divider, getTheme())));
        // Item Animators
        rvChuongTrinhDaoTaos.setItemAnimator(new FadeInLeftAnimator());


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
                final MonHoc monHoc = new MonHoc();

                AlertDialog.Builder builder = new AlertDialog.Builder(DanhSachChuongTrinhDaoTaoActivity.this);

                View view = LayoutInflater.from(DanhSachChuongTrinhDaoTaoActivity.this).inflate(R.layout.chuong_trinh_dao_tao_layout, null);

                final EditText edtMaMH = view.findViewById(R.id.edtMaMH);
                final EditText edtTenMH = view.findViewById(R.id.edtTenMH);
                final EditText edtSoTinChi = view.findViewById(R.id.edtSoTinChi);

                edtMaMH.setEnabled(true);
                edtTenMH.setEnabled(true);
                edtSoTinChi.setEnabled(true);

                builder.setView(view);

                builder.setTitle("Thêm môn học");

                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        monHoc.setMaMH(edtMaMH.getText().toString());
                        monHoc.setTenMH(edtTenMH.getText().toString());
                        monHoc.setSoTinChi(Integer.parseInt(edtSoTinChi.getText().toString()));
                        dsMonHocs.add(0, monHoc);
                        chuongTrinhDaoTaoAdapter.notifyItemInserted(0);
                        rvChuongTrinhDaoTaos.scrollToPosition(0);
                        chuongTrinhDaoTaoAdapter.notifyItemRangeChanged(0, dsMonHocs.size());
                    }
                });

                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
            break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
