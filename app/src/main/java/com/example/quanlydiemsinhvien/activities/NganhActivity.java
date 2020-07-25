package com.example.quanlydiemsinhvien.activities;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quanlydiemsinhvien.data_models.Nganh;
import com.example.quanlydiemsinhvien.R;
import com.example.quanlydiemsinhvien.adapters.NganhAdapter;
import com.example.quanlydiemsinhvien.dialogs.AddNganhDialog;

import java.util.Vector;

public class NganhActivity extends AppCompatActivity {
    public static Vector<Nganh> dataNganh = new Vector<Nganh>();
    private RecyclerView recyclerView;

    public static NganhAdapter adapterNganh;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nganh_recycleview_layout);

        // Set title for action Nganh
        setTitle(R.string.titleForActionBarOfNganh);

        // Get all from view
        recyclerView = findViewById(R.id.nganh_recylerview);

        recyclerView.setHasFixedSize(true);

        // Set data
        duLieuNganhGia();

        // Use the linear layout manager
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        // Specify an adapter
        adapterNganh = new NganhAdapter(dataNganh, this);
        recyclerView.setAdapter(adapterNganh);
    }

    // Day du lieu gia
    public void duLieuNganhGia() {
        for (int i = 0; i < 10; i++) {
            dataNganh.add(new Nganh("Ma Nganh " + i, "Ten Nganh " + i));
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_of_khoa, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.item_menu_them) {
            Log.d("test", "Them button on Nganh is tapped");
            showAddNganhDialog();
        }
        return super.onOptionsItemSelected(item);
    }

    // Show add Nganh dialog
    public void showAddNganhDialog() {
        DialogFragment newFragment = new AddNganhDialog();
        newFragment.show(getSupportFragmentManager(), "addNganh");
    }
}
