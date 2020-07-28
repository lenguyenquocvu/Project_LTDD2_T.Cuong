package com.example.quanlydiemsinhvien.activities;

import android.content.DialogInterface;
import android.os.Bundle;
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
import com.example.quanlydiemsinhvien.adapters.DanhSachLopHocPhanTheoMonAdapter;
import com.example.quanlydiemsinhvien.adapters.decorations.DividerItemDecoration;
import com.example.quanlydiemsinhvien.data_models.LopHocPhan;

import java.util.ArrayList;

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
        for (LopHocPhan lopHocPhan : dsLopHPTheoMH) {
            if (lopHocPhan.getMaMH().equals(DanhSachKhoaHocActivity.intent.getStringExtra("maMH"))) {
                dsLopHPTheoMH_after_click.add(lopHocPhan);
            }
        }

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        rvLopHPTheoMH.setLayoutManager(linearLayoutManager);
        // Item decoration
        rvLopHPTheoMH.addItemDecoration(new DividerItemDecoration(getResources().getDrawable(R.drawable.divider, getTheme())));
        lopHocPhanTheoMonAdapter = new DanhSachLopHocPhanTheoMonAdapter(this, dsLopHPTheoMH_after_click);
        rvLopHPTheoMH.setAdapter(lopHocPhanTheoMonAdapter);

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
