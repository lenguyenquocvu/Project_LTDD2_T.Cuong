package com.example.quanlydiemsinhvien.phongdaotao;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.daimajia.swipe.util.Attributes;
import com.example.quanlydiemsinhvien.R;
import com.example.quanlydiemsinhvien.phongdaotao.adapters.SinhVienSwipeRecyclerViewAdapter;
import com.example.quanlydiemsinhvien.phongdaotao.data_models.SinhVienModel;

import java.util.ArrayList;

public class DanhSachSinhVienActivity extends AppCompatActivity {
    Intent intent;
    private ArrayList<SinhVienModel> data;
    public static Context context;

    private Dialog dialog;

    private RecyclerView recyclerView;
    private TextView tvEmptyView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.danh_sach_sinh_vien_layout);
        setTitle("Quản lý sinh viên");

        dialog = new HopThoaiThemSinhVienActivity(DanhSachSinhVienActivity.this);

        data = new ArrayList<SinhVienModel>();
        data.add(new SinhVienModel("001", "Nguyễn A"));
        data.add(new SinhVienModel("002", "Nguyễn B"));
        data.add(new SinhVienModel("003", "Nguyễn C"));
        data.add(new SinhVienModel("004", "Nguyễn D"));
        data.add(new SinhVienModel("005", "Nguyễn E"));
        data.add(new SinhVienModel("006", "Nguyễn F"));
        data.add(new SinhVienModel("007", "Nguyễn G"));
        data.add(new SinhVienModel("007", "Nguyễn G"));
        data.add(new SinhVienModel("007", "Nguyễn G"));
        data.add(new SinhVienModel("007", "Nguyễn G"));
        data.add(new SinhVienModel("007", "Nguyễn G"));
        data.add(new SinhVienModel("007", "Nguyễn G"));

        tvEmptyView = (TextView) findViewById(R.id.empty_view);
        recyclerView = findViewById(R.id.list_student_recycler_view);
        LinearLayoutManager layoutManager =new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        SinhVienSwipeRecyclerViewAdapter mAdapter = new SinhVienSwipeRecyclerViewAdapter(this, data);

         

        if (data.isEmpty()) {
            recyclerView.setVisibility(View.GONE);
            tvEmptyView.setVisibility(View.VISIBLE);

        } else {
            recyclerView.setVisibility(View.VISIBLE);
            tvEmptyView.setVisibility(View.GONE);
        }
        ((SinhVienSwipeRecyclerViewAdapter) mAdapter).setMode(Attributes.Mode.Single);
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