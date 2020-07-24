package com.example.quanlydiemsinhvien.activities;

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

import com.example.quanlydiemsinhvien.R;
import com.example.quanlydiemsinhvien.adapters.ChuongTrinhDaoTaoAdapter;
import com.example.quanlydiemsinhvien.adapters.decorations.DividerItemDecoration;
import com.example.quanlydiemsinhvien.data_models.ChuongTrinhDaoTao;
import com.example.quanlydiemsinhvien.helper.SwipeHelper;

import java.util.ArrayList;
import java.util.List;

import jp.wasabeef.recyclerview.animators.FadeInLeftAnimator;

public class DanhSachChuongTrinhDaoTaoActivity extends AppCompatActivity {
    RecyclerView rvChuongTrinhDaoTaos;
    private ArrayList<ChuongTrinhDaoTao> dsChuongTrinhDaoTaos;

    private ArrayList<ChuongTrinhDaoTao> dsChuongTrinhDaoTaos_after_click;
    TextView txtNganh;
    ChuongTrinhDaoTaoAdapter chuongTrinhDaoTaoAdapter;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chuong_trinh_dao_tao_layout);

        rvChuongTrinhDaoTaos = findViewById(R.id.rvChuongtrinhdaotao);
        txtNganh = findViewById(R.id.txtNganh);

        txtNganh.setText("Ngành 12345");

        dsChuongTrinhDaoTaos = new ArrayList<ChuongTrinhDaoTao>();
        dsChuongTrinhDaoTaos.add(new ChuongTrinhDaoTao("LTC#", "Lập trình C#", 75, "K17"));
        dsChuongTrinhDaoTaos.add(new ChuongTrinhDaoTao("CSDL", "Cơ sở dữ liệu", 75, "K17"));
        dsChuongTrinhDaoTaos.add(new ChuongTrinhDaoTao("LTDD2", "Lập trình di động 2", 75, "K18"));
        dsChuongTrinhDaoTaos.add(new ChuongTrinhDaoTao("Java", "Lập trình Java", 75, "K18"));

        dsChuongTrinhDaoTaos_after_click = new  ArrayList<ChuongTrinhDaoTao>();
        for(ChuongTrinhDaoTao chuongTrinhDaoTao:dsChuongTrinhDaoTaos){
            String maKH = DanhSachKhoaHocActivity.intent.getStringExtra("maKH");
            if(chuongTrinhDaoTao.getMaKH().equals(maKH)){
                dsChuongTrinhDaoTaos_after_click.add(chuongTrinhDaoTao);
            }
        }
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        rvChuongTrinhDaoTaos.setLayoutManager(linearLayoutManager);
        // Item decoration
        rvChuongTrinhDaoTaos.addItemDecoration(new DividerItemDecoration(getResources().getDrawable(R.drawable.divider, getTheme())));
        // Item Animators
        rvChuongTrinhDaoTaos.setItemAnimator(new FadeInLeftAnimator());
        chuongTrinhDaoTaoAdapter = new ChuongTrinhDaoTaoAdapter(this, dsChuongTrinhDaoTaos_after_click);
        rvChuongTrinhDaoTaos.setAdapter(chuongTrinhDaoTaoAdapter);



//        SwipeHelper swipeHelper = new SwipeHelper(this, rvChuongTrinhDaoTaos) {
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
//                                AlertDialog.Builder builder = new AlertDialog.Builder(DanhSachChuongTrinhDaoTaoActivity.this);
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
//                                        dsChuongTrinhDaoTaos_after_click.remove(pos);
//                                        chuongTrinhDaoTaoAdapter.notifyDataSetChanged();
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
//                                final ChuongTrinhDaoTao chuongTrinhDaoTao = dsChuongTrinhDaoTaos_after_click.get(pos);
//
//                                AlertDialog.Builder builder = new AlertDialog.Builder(DanhSachChuongTrinhDaoTaoActivity.this);
//
//                                View view = LayoutInflater.from(DanhSachChuongTrinhDaoTaoActivity.this).inflate(R.layout.dialog_sua_mon_hoc__ctdt_layout, null);
//
//                                EditText edtMaMH = view.findViewById(R.id.edtMaMH);
//                                EditText edtTenMH = view.findViewById(R.id.edtTenMH);
//                                EditText edtSoTinChi = view.findViewById(R.id.edtSoTinChi);
//
//                                edtMaMH.setText(chuongTrinhDaoTao.getMaMH());
//                                edtTenMH.setText(chuongTrinhDaoTao.getTenMH());
//                                edtSoTinChi.setText(chuongTrinhDaoTao.getSoTinChi() + "");
//
//                                builder.setView(view);
//
//                                builder.setTitle("Cập nhật lại môn học");
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
//                                        chuongTrinhDaoTaoAdapter.notifyDataSetChanged();
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_them, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.mnu_them:{
                final ChuongTrinhDaoTao chuongTrinhDaoTao = new ChuongTrinhDaoTao();

                AlertDialog.Builder builder = new AlertDialog.Builder(DanhSachChuongTrinhDaoTaoActivity.this);

                View view = LayoutInflater.from(DanhSachChuongTrinhDaoTaoActivity.this).inflate(R.layout.dialog_sua_mon_hoc__ctdt_layout, null);

                final EditText edtMaMH = view.findViewById(R.id.edtMaMH);
                final EditText edtTenMH = view.findViewById(R.id.edtTenMH);
                final EditText edtSoTinChi = view.findViewById(R.id.edtSoTinChi);

                edtMaMH.setEnabled(true);
                edtTenMH.setEnabled(true);
                edtSoTinChi.setEnabled(true);

                builder.setView(view);

                builder.setTitle("Thêm môn học");

                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        chuongTrinhDaoTao.setMaMH(edtMaMH.getText().toString());
                        chuongTrinhDaoTao.setTenMH(edtTenMH.getText().toString());
                        chuongTrinhDaoTao.setSoTinChi(Integer.parseInt(edtSoTinChi.getText().toString()));
                        dsChuongTrinhDaoTaos_after_click.add(0, chuongTrinhDaoTao);
                        chuongTrinhDaoTaoAdapter.notifyItemInserted(0);
                        rvChuongTrinhDaoTaos.scrollToPosition(0);
                        chuongTrinhDaoTaoAdapter.notifyItemRangeChanged(0, dsChuongTrinhDaoTaos_after_click.size());
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
