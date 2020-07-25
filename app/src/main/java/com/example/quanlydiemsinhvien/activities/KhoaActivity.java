package com.example.quanlydiemsinhvien.activities;

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

import com.example.quanlydiemsinhvien.data_models.Khoa;
import com.example.quanlydiemsinhvien.R;
import com.example.quanlydiemsinhvien.adapters.KhoaAdapter;
import com.example.quanlydiemsinhvien.dialogs.AddKhoaDialog;
import com.example.quanlydiemsinhvien.divider.DividerItemDecoration;

import java.util.Vector;

public class KhoaActivity extends AppCompatActivity {
    public static Vector<Khoa> dataKhoa = new Vector<Khoa>();
    private RecyclerView recyclerView;

    public static KhoaAdapter khoaAdapter;
    private RecyclerView.LayoutManager layoutManager;

    public static Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.khoa_recyclerview_layout);
        setTitle(R.string.actionBarTitle);

        intent = getIntent();

        // Get View from layout
        recyclerView = findViewById(R.id.khoa_recylerview);

        // improve performance if changes in content do not change the layout size of the RecyclerView
        recyclerView.setHasFixedSize(true);

        // Item decoration
        recyclerView.addItemDecoration(new DividerItemDecoration(getResources().getDrawable(R.drawable.divider, getTheme())));

        // Day du lieu gia
        duLieuGia();

        // Use linear layout manager
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        // Specify an adapter
        khoaAdapter = new KhoaAdapter(this, dataKhoa);
        recyclerView.setAdapter(khoaAdapter);
    }

    // Day du lieu gia
    public void duLieuGia() {
        for (int i = 0; i < 20; i++) {
           dataKhoa.add(new Khoa("K" + i, "Khoa " + i, + i + "/07/2020"));
        }
    }

    // Create a menu on action bar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_of_khoa, menu);
        return true;
    }

    // Event for item menu on action bar
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.item_menu_them) {
            Log.d("test", "Item menu Them");
            showAddKhoaDialog();
        }
        return true;
    }

    // Show Add Khoa dialog
    public void showAddKhoaDialog() {
        DialogFragment khoaDialog = new AddKhoaDialog();
        khoaDialog.show(getSupportFragmentManager(), "addKhoa");
    }
}
