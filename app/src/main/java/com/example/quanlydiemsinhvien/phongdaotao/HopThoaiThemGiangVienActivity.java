package com.example.quanlydiemsinhvien.phongdaotao;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.quanlydiemsinhvien.R;

import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.List;

public class HopThoaiThemGiangVienActivity extends Dialog {
    String id;
    String name;

    EditText edtMaGV;
    EditText edtTenGV;

    Button btnThem;
    Button btnThoat;

    Intent intent;

    public HopThoaiThemGiangVienActivity(Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hop_thoai_them_giang_vien_layout);

        edtMaGV = findViewById(R.id.edtMaGV);
        edtTenGV = findViewById(R.id.edtTenGV);

        btnThem = findViewById(R.id.btnThemGV);
        btnThoat = findViewById(R.id.btnThoatGV);

        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        btnThoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HopThoaiThemGiangVienActivity.this.cancel();
            }
        });
    }
}
