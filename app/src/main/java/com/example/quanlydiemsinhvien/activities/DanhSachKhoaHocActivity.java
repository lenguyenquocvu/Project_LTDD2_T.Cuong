package com.example.quanlydiemsinhvien.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import com.daimajia.swipe.util.Attributes;
import com.example.quanlydiemsinhvien.R;
import com.example.quanlydiemsinhvien.adapters.KhoaHocAdapter;
import com.example.quanlydiemsinhvien.adapters.decorations.DividerItemDecoration;
import com.example.quanlydiemsinhvien.data_models.KhoaHoc;
import com.example.quanlydiemsinhvien.helper.SwipeHelper;

import jp.wasabeef.recyclerview.animators.FadeInAnimator;
import jp.wasabeef.recyclerview.animators.FadeInLeftAnimator;
import jp.wasabeef.recyclerview.animators.SlideInUpAnimator;

public class DanhSachKhoaHocActivity extends AppCompatActivity {
    RecyclerView rvDsKhoaHoc;
    private ArrayList<KhoaHoc> dsKhoaHoc;
    public static TextView txtNganh;
    KhoaHocAdapter khoaHocAdapter;
    public static Intent intent;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.danh_sach_khoa_hoc_layout);


        intent = getIntent();
        intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);

        rvDsKhoaHoc = (RecyclerView) findViewById(R.id.rvDanhsachKhoaHoc);
        txtNganh = findViewById(R.id.txtNganh);

        txtNganh.setText("Ngành 12345");

        dsKhoaHoc = new ArrayList<KhoaHoc>();
        dsKhoaHoc.add(new KhoaHoc("K16", "2016", "2019"));
        dsKhoaHoc.add(new KhoaHoc("K17", "2017", "2020"));
        dsKhoaHoc.add(new KhoaHoc("K18", "2018", "2021"));
        dsKhoaHoc.add(new KhoaHoc("K16", "2016", "2019"));
        dsKhoaHoc.add(new KhoaHoc("K17", "2017", "2020"));
        dsKhoaHoc.add(new KhoaHoc("K18", "2018", "2021"));
        dsKhoaHoc.add(new KhoaHoc("K16", "2016", "2019"));
        dsKhoaHoc.add(new KhoaHoc("K17", "2017", "2020"));
        dsKhoaHoc.add(new KhoaHoc("K18", "2018", "2021"));
        dsKhoaHoc.add(new KhoaHoc("K16", "2016", "2019"));
        dsKhoaHoc.add(new KhoaHoc("K17", "2017", "2020"));
        dsKhoaHoc.add(new KhoaHoc("K18", "2018", "2021"));



        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        rvDsKhoaHoc.setLayoutManager(linearLayoutManager);
        // Item decoration
        rvDsKhoaHoc.addItemDecoration(new DividerItemDecoration(getResources().getDrawable(R.drawable.divider, getTheme())));
        // Item Animators
        rvDsKhoaHoc.setItemAnimator(new FadeInLeftAnimator());
        khoaHocAdapter = new KhoaHocAdapter(this, dsKhoaHoc);
        rvDsKhoaHoc.setHasFixedSize(true);
        rvDsKhoaHoc.setAdapter(khoaHocAdapter);
        ((KhoaHocAdapter) khoaHocAdapter).setMode(Attributes.Mode.Single);
        // Scroll listener
        rvDsKhoaHoc.addOnScrollListener(onScroll);
//        SwipeHelper swipeHelper = new SwipeHelper(this, rvDsKhoaHoc) {
//            @Override
//            public void instantiateUnderlayButton(RecyclerView.ViewHolder viewHolder, List<UnderlayButton> underlayButtons) {
//                underlayButtons.add(new SwipeHelper.UnderlayButton(getApplicationContext(),
//                        "Delete",
//                        30, 0,
//                        Color.parseColor("#FF3C30"),
//                        new SwipeHelper.UnderlayButtonClickListener() {
//                            @Override
//                            public void onClick(final int pos) {
//                                Log.d("delete", pos+"");
//                                AlertDialog.Builder builder = new AlertDialog.Builder(DanhSachKhoaHocActivity.this);
//                                builder.setTitle("Xóa khóa học");
//                                builder.setMessage("Bạn có muốn xóa không?");
//                                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
//                                    @Override
//                                    public void onClick(DialogInterface dialog, int which) {
//                                        dialog.dismiss();
//                                    }
//                                });
//
//                                builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
//                                    @Override
//                                    public void onClick(DialogInterface dialog, int which) {
//                                        dsKhoaHoc.remove(pos);
//                                        khoaHocAdapter.notifyDataSetChanged();
//                                    }
//                                });
//
//                                AlertDialog alertDialog = builder.create();
//                                alertDialog.show();
//                            }
//                        }
//                ));
//
//                underlayButtons.add(new SwipeHelper.UnderlayButton(getApplicationContext(),
//                        "Edit",
//                        30, 0,
//                        Color.parseColor("#000000"),
//                        new SwipeHelper.UnderlayButtonClickListener() {
//                            @Override
//                            public void onClick(int pos) {
//                                final KhoaHoc khoaHoc = dsKhoaHoc.get(pos);
//
//                                AlertDialog.Builder builder = new AlertDialog.Builder(DanhSachKhoaHocActivity.this);
//
//                                View view = LayoutInflater.from(DanhSachKhoaHocActivity.this).inflate(R.layout.dialog_sua_khoahoc_layout, null);
//
//                                EditText edtMaKH = view.findViewById(R.id.edtMaKHInfo);
//                                final EditText edtBatdau = view.findViewById(R.id.edtKhBatDau);
//                                final EditText edtKetthuc = view.findViewById(R.id.edtKhKetThuc);
//
//                                edtMaKH.setText(khoaHoc.getMaKH());
//                                edtBatdau.setText(khoaHoc.getBatDau());
//                                edtKetthuc.setText(khoaHoc.getKetThuc());
//
//                                builder.setView(view);
//
//                                builder.setTitle("Cập nhật lại khóa học");
//
//                                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
//                                    @Override
//                                    public void onClick(DialogInterface dialog, int which) {
//                                        dialog.dismiss();
//                                    }
//                                });
//
//                                builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
//                                    @Override
//                                    public void onClick(DialogInterface dialog, int which) {
//                                        khoaHoc.setBatDau(edtBatdau.getText().toString());
//                                        khoaHoc.setKetThuc(edtKetthuc.getText().toString());
//                                        khoaHocAdapter.notifyDataSetChanged();
//                                    }
//                                });
//
//                                AlertDialog alertDialog = builder.create();
//                                alertDialog.show();
//                            }
//                        }
//                ));
//            }
//        };
    }

    public RecyclerView.OnScrollListener onScroll = new RecyclerView.OnScrollListener() {
        @Override
        public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
        }

        @Override
        public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
        }
    };

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
                        dsKhoaHoc.add(0, khoaHoc);
                        khoaHocAdapter.notifyItemInserted(0);
                        rvDsKhoaHoc.scrollToPosition(0);
                        khoaHocAdapter.notifyItemRangeChanged(0, dsKhoaHoc.size());
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
