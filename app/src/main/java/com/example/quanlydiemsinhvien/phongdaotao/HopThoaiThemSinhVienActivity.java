package com.example.quanlydiemsinhvien.phongdaotao;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import com.example.quanlydiemsinhvien.R;

public class HopThoaiThemSinhVienActivity extends Dialog {
    Button btnThem;
    Button btnThoat;

    public HopThoaiThemSinhVienActivity(@NonNull Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hop_thoai_them_sinh_vien_layout);

        btnThem = findViewById(R.id.btnThemSV);
        btnThoat = findViewById(R.id.btnThoatSV);

        btnThoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HopThoaiThemSinhVienActivity.this.cancel();
            }
        });
    }
}
