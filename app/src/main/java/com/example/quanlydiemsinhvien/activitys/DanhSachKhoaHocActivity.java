package com.example.quanlydiemsinhvien.activitys;

import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import com.example.quanlydiemsinhvien.R;
import com.example.quanlydiemsinhvien.adapters.KhoaHocAdapter;
import com.example.quanlydiemsinhvien.data_models.KhoaHoc;
import com.example.quanlydiemsinhvien.helper.SwipeHelper;

public class DanhSachKhoaHocActivity extends AppCompatActivity {
    RecyclerView rvDsKhoaHoc;
    private ArrayList<KhoaHoc> dsKhoaHoc;
    TextView txtNganh;
    KhoaHocAdapter khoaHocAdapter;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.danh_sach_khoa_hoc_layout);

        rvDsKhoaHoc = (RecyclerView) findViewById(R.id.rvDanhsachKhoaHoc);
        txtNganh = findViewById(R.id.txtNganh);

        txtNganh.setText("Ngành 12345");

        dsKhoaHoc = new ArrayList<KhoaHoc>();
        dsKhoaHoc.add(new KhoaHoc("K16", "2016", "2019"));
        dsKhoaHoc.add(new KhoaHoc("K17", "2017", "2020"));
        dsKhoaHoc.add(new KhoaHoc("K18", "2018", "2021"));

        khoaHocAdapter = new KhoaHocAdapter(this, dsKhoaHoc);
        rvDsKhoaHoc.setAdapter(khoaHocAdapter);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        rvDsKhoaHoc.setLayoutManager(linearLayoutManager);

        SwipeHelper swipeHelper = new SwipeHelper(this, rvDsKhoaHoc) {
            @Override
            public void instantiateUnderlayButton(RecyclerView.ViewHolder viewHolder, List<UnderlayButton> underlayButtons) {
                underlayButtons.add(new SwipeHelper.UnderlayButton(getApplicationContext(),
                        "Delete",
                        30, 0,
                        Color.parseColor("#FF3C30"),
                        new SwipeHelper.UnderlayButtonClickListener() {
                            @Override
                            public void onClick(final int pos) {
                                Log.d("delete", pos+"");
                                AlertDialog.Builder builder = new AlertDialog.Builder(DanhSachKhoaHocActivity.this);
                                builder.setTitle("Xóa khóa học");
                                builder.setMessage("Bạn có muốn xóa không?");
                                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                    }
                                });

                                builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dsKhoaHoc.remove(pos);
                                        khoaHocAdapter.notifyDataSetChanged();
                                    }
                                });

                                AlertDialog alertDialog = builder.create();
                                alertDialog.show();
                            }
                        }
                ));

                underlayButtons.add(new SwipeHelper.UnderlayButton(getApplicationContext(),
                        "Edit",
                        30, 0,
                        Color.parseColor("#000000"),
                        new SwipeHelper.UnderlayButtonClickListener() {
                            @Override
                            public void onClick(int pos) {
                                final KhoaHoc khoaHoc = dsKhoaHoc.get(pos);

                                AlertDialog.Builder builder = new AlertDialog.Builder(DanhSachKhoaHocActivity.this);

                                View view = LayoutInflater.from(DanhSachKhoaHocActivity.this).inflate(R.layout.dialog_sua_khoahoc_layout, null);

                                EditText edtMaKH = view.findViewById(R.id.edtMaKHInfo);
                                final EditText edtBatdau = view.findViewById(R.id.edtKhBatDau);
                                final EditText edtKetthuc = view.findViewById(R.id.edtKhKetThuc);

                                edtMaKH.setText(khoaHoc.getMaKH());
                                edtBatdau.setText(khoaHoc.getBatDau());
                                edtKetthuc.setText(khoaHoc.getKetThuc());

                                builder.setView(view);

                                builder.setTitle("Cập nhật lại khóa học");

                                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                    }
                                });

                                builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        khoaHoc.setBatDau(edtBatdau.getText().toString());
                                        khoaHoc.setKetThuc(edtKetthuc.getText().toString());
                                        khoaHocAdapter.notifyDataSetChanged();
                                    }
                                });

                                AlertDialog alertDialog = builder.create();
                                alertDialog.show();
                            }
                        }
                ));
            }
        };
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_them, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.mnu_them:
            {
                final KhoaHoc khoaHoc = new KhoaHoc();

                AlertDialog.Builder builder = new AlertDialog.Builder(DanhSachKhoaHocActivity.this);

                View view = LayoutInflater.from(DanhSachKhoaHocActivity.this).inflate(R.layout.dialog_sua_khoahoc_layout, null);

                final EditText edtMaKH = view.findViewById(R.id.edtMaKHInfo);
                final EditText edtBatdau = view.findViewById(R.id.edtKhBatDau);
                final EditText edtKetthuc = view.findViewById(R.id.edtKhKetThuc);

                edtMaKH.setEnabled(true);

                builder.setView(view);

                builder.setTitle("Thêm khóa học");

                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        khoaHoc.setMaKH(edtMaKH.getText().toString());
                        khoaHoc.setBatDau(edtBatdau.getText().toString());
                        khoaHoc.setKetThuc(edtKetthuc.getText().toString());
                        dsKhoaHoc.add(khoaHoc);
                        khoaHocAdapter.notifyDataSetChanged();
                    }
                });

                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }break;
            default:break;
        }

        return super.onOptionsItemSelected(item);
    }
}
