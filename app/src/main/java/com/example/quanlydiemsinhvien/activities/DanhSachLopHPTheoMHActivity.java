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
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quanlydiemsinhvien.R;
import com.example.quanlydiemsinhvien.adapters.DanhSachLopHocPhanTheoMonAdapter;
import com.example.quanlydiemsinhvien.adapters.decorations.DividerItemDecoration;
import com.example.quanlydiemsinhvien.data_models.LopHocPhan;
import com.example.quanlydiemsinhvien.helper.SwipeHelper;

import java.util.ArrayList;
import java.util.List;

public class DanhSachLopHPTheoMHActivity extends AppCompatActivity {
    RecyclerView rvLopHPTheoMH;
    private ArrayList<LopHocPhan> dsLopHPTheoMH;
    private ArrayList<LopHocPhan> dsLopHPTheoMH_after_click;
    TextView txtTenMH;
    DanhSachLopHocPhanTheoMonAdapter lopHocPhanTheoMonAdapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.danh_sach_lop_hoc_phan_theo_mon_layout);

        rvLopHPTheoMH = findViewById(R.id.rvDsLopHPTheoMH);
        txtTenMH = findViewById(R.id.edtTenMH);

        txtTenMH.setText(DanhSachKhoaHocActivity.intent.getStringExtra("tenMH"));

        dsLopHPTheoMH = new ArrayList<LopHocPhan>();
        dsLopHPTheoMH.add(new LopHocPhan("LTC#02", "Lập trình C#", "GV01", "LTC#"));
        dsLopHPTheoMH.add(new LopHocPhan("CSDL01", "Cơ sở dữ liệu", "GV02", "CSDL"));
        dsLopHPTheoMH.add(new LopHocPhan("java02", "Lập trình java", "GV03", "Java"));
        dsLopHPTheoMH.add(new LopHocPhan("LTDD2L02", "Lập trình di động 2", "GV03", "LTDD2"));

        dsLopHPTheoMH_after_click = new ArrayList<LopHocPhan>();
        for(LopHocPhan lopHocPhan:dsLopHPTheoMH){
            if(lopHocPhan.getMaMH().equals(DanhSachKhoaHocActivity.intent.getStringExtra("maMH"))){
                dsLopHPTheoMH_after_click.add(lopHocPhan);
            }
        }

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        rvLopHPTheoMH.setLayoutManager(linearLayoutManager);
        // Item decoration
        rvLopHPTheoMH.addItemDecoration(new DividerItemDecoration(getResources().getDrawable(R.drawable.divider, getTheme())));
        lopHocPhanTheoMonAdapter = new DanhSachLopHocPhanTheoMonAdapter(this, dsLopHPTheoMH_after_click);
        rvLopHPTheoMH.setAdapter(lopHocPhanTheoMonAdapter);

//        SwipeHelper swipeHelper = new SwipeHelper(this, rvLopHPTheoMH) {
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
//                                AlertDialog.Builder builder = new AlertDialog.Builder(DanhSachLopHPTheoMHActivity.this);
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
//                                        dsLopHPTheoMH_after_click.remove(pos);
//                                        lopHocPhanTheoMonAdapter.notifyDataSetChanged();
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
//                                final LopHocPhan lopHocPhan = dsLopHPTheoMH_after_click.get(pos);
//
//                                AlertDialog.Builder builder = new AlertDialog.Builder(DanhSachLopHPTheoMHActivity.this);
//
//                                View view = LayoutInflater.from(DanhSachLopHPTheoMHActivity.this).inflate(R.layout.dialog_sua_lop_hoc_phan_layout, null);
//
//                                EditText edtMaLop = view.findViewById(R.id.edtMaLop);
//                                EditText edtTenLop = view.findViewById(R.id.edtTenLop);
//                                final EditText edtMaGV = view.findViewById(R.id.edtMaGV);
//
//                                edtMaLop.setText(lopHocPhan.getMaLop());
//                                edtTenLop.setText(lopHocPhan.getTenLop());
//                                edtMaGV.setText(lopHocPhan.getMaGV());
//
//                                builder.setView(view);
//
//                                builder.setTitle("Cập nhật lại lớp học phần");
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
//                                        lopHocPhan.setMaGV(edtMaGV.getText().toString());
//                                        lopHocPhanTheoMonAdapter.notifyDataSetChanged();
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
            case R.id.mnu_them:
            {
                final LopHocPhan lopHocPhan = new LopHocPhan();

                AlertDialog.Builder builder = new AlertDialog.Builder(DanhSachLopHPTheoMHActivity.this);

                View view = LayoutInflater.from(DanhSachLopHPTheoMHActivity.this).inflate(R.layout.dialog_sua_lop_hoc_phan_layout, null);

                final EditText edtMaLop = view.findViewById(R.id.edtMaLop);
                final EditText edtTenLop = view.findViewById(R.id.edtTenLop);
                final EditText edtMaGV = view.findViewById(R.id.edtMaGV);

                edtMaLop.setEnabled(true);
                edtTenLop.setEnabled(true);

                builder.setView(view);

                builder.setTitle("Thêm lớp học phần");

                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        lopHocPhan.setMaLop(edtMaLop.getText().toString());
                        lopHocPhan.setTenLop(edtTenLop.getText().toString());
                        lopHocPhan.setMaGV(edtMaGV.getText().toString());
                        dsLopHPTheoMH_after_click.add(lopHocPhan);
                        lopHocPhanTheoMonAdapter.notifyItemInserted(0);
                        rvLopHPTheoMH.scrollToPosition(0);
                        lopHocPhanTheoMonAdapter.notifyItemRangeChanged(0, dsLopHPTheoMH_after_click.size());
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
