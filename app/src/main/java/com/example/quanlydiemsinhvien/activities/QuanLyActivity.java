package com.example.quanlydiemsinhvien.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.quanlydiemsinhvien.R;

public class QuanLyActivity extends AppCompatActivity {
    Button btnKhoa, btnGiangVien, btnSinhVien;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quan_ly_layout);
        setTitle("Phòng Đào Tạo");

        btnKhoa = findViewById(R.id.btnKhoa);
        btnGiangVien = findViewById(R.id.btnGiangVien);
        btnSinhVien = findViewById(R.id.btnSinhVien);

        btnGiangVien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(QuanLyActivity.this, DanhSachGiangVienActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(intent);
            }
        });

        btnSinhVien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(QuanLyActivity.this, DanhSachSinhVienActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(intent);
            }
        });
    }
}