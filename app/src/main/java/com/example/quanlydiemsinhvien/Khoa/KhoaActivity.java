package com.example.quanlydiemsinhvien.Khoa;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quanlydiemsinhvien.Nganh.NganhActivity;
import com.example.quanlydiemsinhvien.R;

import java.util.Vector;

public class KhoaActivity extends AppCompatActivity {
    private Vector<Khoa> data = new Vector<Khoa>();
    private RecyclerView recyclerView;

    private KhoaAdapter khoaAdapter;
    private RecyclerView.LayoutManager layoutManager;

    public static Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.khoa_recyclerview_layout);
        setTitle(R.string.actionBarTitle);

        // Get View from layout
        recyclerView = findViewById(R.id.khoa_recylerview);

        // improve performance if changes in content do not change the layout size of the RecyclerView
        recyclerView.setHasFixedSize(true);

        //AddKhoaDialog addKhoaDialog = new AddKhoaDialog();

        // Day du lieu gia
        data.add(new Khoa("HK01", "Cong Nghe Thong Tin", "12/11/2019"));
        data.add(new Khoa("HK02", "Kinh Te", "12/01/2019"));
        data.add(new Khoa("HK03", "Du Lich", "11/11/2019"));
        data.add(new Khoa("HK04", "Quan Tri Kinh Doanh", "01/11/2019"));
        data.add(new Khoa("HK05", "Ke Toan", "02/01/2019"));
        data.add(new Khoa("HK06", "Tieng Han", "03/11/2019"));
        //data.add(addKhoaDialog.getTheKhoa());

        // Use linear layout manager
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        // Specify an adapter
        khoaAdapter = new KhoaAdapter(data);
        recyclerView.setAdapter(khoaAdapter);

    }

    // Create a menu on action bar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_of_khoa, menu);
        return true;
    }

    // Event for item menu
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.item_menu_them) {
            Log.d("test", "Item menu Them");

            // Switch intent
            intent = new Intent();
            intent.setClass(KhoaActivity.this, NganhActivity.class);
            startActivity(intent);

            AddKhoaDialog theDialog = new AddKhoaDialog();
            showAddKhoaDialog();
        }
        return true;
    }

    // Show add Khoa dialog
    public void showAddKhoaDialog() {
        DialogFragment newFragment = new AddKhoaDialog();
        newFragment.show(getSupportFragmentManager(), "addKhoa");
    }
}
