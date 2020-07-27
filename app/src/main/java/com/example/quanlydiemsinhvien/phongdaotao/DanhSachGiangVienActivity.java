package com.example.quanlydiemsinhvien.phongdaotao;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.swipe.SwipeLayout;
import com.daimajia.swipe.util.Attributes;
import com.example.quanlydiemsinhvien.R;
import com.example.quanlydiemsinhvien.phongdaotao.adapters.GiangVienSwipeRecyclerViewAdapter;
import com.example.quanlydiemsinhvien.phongdaotao.data_models.GiangVienModel;

import java.util.ArrayList;

public class DanhSachGiangVienActivity extends AppCompatActivity {
    Intent intent;
    public static ArrayList<GiangVienModel> data;

    private Dialog dialog;

    String id;
    String name;

    EditText edtMaGV;
    EditText edtTenGV;

    private RecyclerView recyclerView;
    private TextView tvEmptyView;

    GiangVienSwipeRecyclerViewAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.danh_sach_giang_vien_layout);
        setTitle("Quản lý giảng viên");


        dialog = new HopThoaiThemGiangVienActivity(DanhSachGiangVienActivity.this);

        data = new ArrayList<GiangVienModel>();
        data.add(new GiangVienModel("001", "Nguyễn A"));
        data.add(new GiangVienModel("002", "Nguyễn B"));
        data.add(new GiangVienModel("003", "Nguyễn C"));
        data.add(new GiangVienModel("004", "Nguyễn D"));
        data.add(new GiangVienModel("005", "Nguyễn E"));
        data.add(new GiangVienModel("006", "Nguyễn F"));
        data.add(new GiangVienModel("007", "Nguyễn G"));
        data.add(new GiangVienModel("007", "Nguyễn G"));
        data.add(new GiangVienModel("007", "Nguyễn G"));
        data.add(new GiangVienModel("007", "Nguyễn G"));
        data.add(new GiangVienModel("007", "Nguyễn G"));
        data.add(new GiangVienModel("007", "Nguyễn G"));

        edtMaGV = findViewById(R.id.edtMaGV);
        edtTenGV = findViewById(R.id.edtTenGV);

        tvEmptyView = (TextView) findViewById(R.id.empty_view);
        recyclerView = findViewById(R.id.list_teacher_recycler_view);
        LinearLayoutManager layoutManager =new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        mAdapter = new GiangVienSwipeRecyclerViewAdapter(this, data);

        if (data.isEmpty()) {
            recyclerView.setVisibility(View.GONE);
            tvEmptyView.setVisibility(View.VISIBLE);

        } else {
            recyclerView.setVisibility(View.VISIBLE);
            tvEmptyView.setVisibility(View.GONE);
        }
        ((GiangVienSwipeRecyclerViewAdapter) mAdapter).setMode(Attributes.Mode.Single);
        recyclerView.setAdapter(mAdapter);

        /* Scroll Listeners */
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                Log.e("RecyclerView", "onScrollStateChanged");
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });
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
            case R.id.them_item:
                dialog.show();
                return true;
            case R.id.thoat_item:
                // do your code
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}