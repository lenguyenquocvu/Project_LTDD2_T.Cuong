package com.example.quanlydiemsinhvien;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quanlydiemsinhvien.adapters.DSLopAdapter;
import com.example.quanlydiemsinhvien.adapters.DSSinhVienAdapter;
import com.example.quanlydiemsinhvien.data_models.DanhSachSinhVien;

import java.util.ArrayList;

public class DSSinhVienActivity extends AppCompatActivity {
    RecyclerView recycler_dssinhvien;
    private ArrayList<DanhSachSinhVien> danhsachsinhvien;
    private RecyclerView.LayoutManager layoutManager;
    TextView txtMaSV;
    TextView txtTenSV;
    TextView txtDiem;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.danhsachsinhvien_layout);

        recycler_dssinhvien = (RecyclerView) findViewById(R.id.recycler_dssinhvien);
        txtMaSV = findViewById(R.id.txtMaSV);
        txtTenSV = findViewById(R.id.txtTenSV);
        txtDiem = findViewById(R.id.txtDiemSV);

        layoutManager = new LinearLayoutManager(this);
        recycler_dssinhvien.setLayoutManager(layoutManager);

        danhsachsinhvien = new ArrayList<DanhSachSinhVien>();
        danhsachsinhvien.add(new DanhSachSinhVien("17211TT123", "Nguyen Dinh Trieu", 6));
        danhsachsinhvien.add(new DanhSachSinhVien("17211TT345","Le Nguyen Quoc Vu", 8));
        danhsachsinhvien.add(new DanhSachSinhVien("17211TT789","Bui Van Hieu", 7));

        DSSinhVienAdapter dssvAdapter = new DSSinhVienAdapter(danhsachsinhvien, this);
        recycler_dssinhvien.setAdapter(dssvAdapter);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,RecyclerView.VERTICAL,false);
        recycler_dssinhvien.setLayoutManager(linearLayoutManager);
    }
}
