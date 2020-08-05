package com.example.quanlydiemsinhvien.phongdaotao;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.quanlydiemsinhvien.R;
import com.example.quanlydiemsinhvien.phongdaotao.adapters.MyRecyclerViewAdapter;
import com.example.quanlydiemsinhvien.phongdaotao.data_models.CardViewModel;

import java.util.Vector;

public class DanhSachGiangVienActivity extends AppCompatActivity {
    Intent intent;
    private Vector<CardViewModel> data = new Vector<CardViewModel>();
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.danh_sach_giang_vien_layout);
        setTitle("Quản lý giảng viên");

        data.add(new CardViewModel("001", "Nguyễn A"));
        data.add(new CardViewModel("002", "Nguyễn B"));
        data.add(new CardViewModel("003", "Nguyễn C"));
        data.add(new CardViewModel("004", "Nguyễn D"));
        data.add(new CardViewModel("005", "Nguyễn E"));
        data.add(new CardViewModel("006", "Nguyễn F"));
        data.add(new CardViewModel("007", "Nguyễn G"));
        data.add(new CardViewModel("007", "Nguyễn G"));
        data.add(new CardViewModel("007", "Nguyễn G"));
        data.add(new CardViewModel("007", "Nguyễn G"));
        data.add(new CardViewModel("007", "Nguyễn G"));
        data.add(new CardViewModel("007", "Nguyễn G"));

        recyclerView = findViewById(R.id.list_teacher_recycler_view);
        LinearLayoutManager layoutManager =new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        MyRecyclerViewAdapter myRecyclerViewAdapter = new MyRecyclerViewAdapter(data);
        recyclerView.setAdapter(myRecyclerViewAdapter);
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
                openDialog();
                return true;
            case R.id.thoat_item:
                // do your code
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    public void openDialog(){
        HopThoaiThemGiangVienActivity dialog = new HopThoaiThemGiangVienActivity();
        dialog.show(getSupportFragmentManager(), "missiles");
    }
}