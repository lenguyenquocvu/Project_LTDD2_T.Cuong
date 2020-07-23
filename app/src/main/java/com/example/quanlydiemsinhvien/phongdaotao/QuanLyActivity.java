package com.example.quanlydiemsinhvien.phongdaotao;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.quanlydiemsinhvien.R;

public class QuanLyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quan_ly_layout);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
    }
}