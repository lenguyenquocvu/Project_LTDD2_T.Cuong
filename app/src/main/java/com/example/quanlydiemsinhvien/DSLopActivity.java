package com.example.quanlydiemsinhvien;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Adapter;
import android.widget.EditText;
import android.widget.TextView;

import com.example.quanlydiemsinhvien.adapters.DSLopAdapter;
import com.example.quanlydiemsinhvien.data_models.Card_View_Model;

import java.util.ArrayList;

public class DSLopActivity extends AppCompatActivity {

    RecyclerView recycler_dslop;
    private ArrayList<Card_View_Model> danhsachlop;
    TextView txtMaLHP;
    TextView txtTenLHP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.danhsachlop_layout);

        recycler_dslop = (RecyclerView) findViewById(R.id.recycler_dslop);
        txtMaLHP = findViewById(R.id.txtMaLHP);
        txtTenLHP = findViewById(R.id.txtTenLHP);

        danhsachlop = new ArrayList<Card_View_Model>();
        danhsachlop.add(new Card_View_Model("LHP-01","Lop hoc phan 1"));
        danhsachlop.add(new Card_View_Model("LHP-02","Lop hoc phan 2"));
        danhsachlop.add(new Card_View_Model("LHP-03","Lop hoc phan 3"));
        DSLopAdapter dsLopAdapter = new DSLopAdapter(this, danhsachlop);
        recycler_dslop.setAdapter(dsLopAdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL,false);
        recycler_dslop.setLayoutManager(linearLayoutManager);
    }
}
