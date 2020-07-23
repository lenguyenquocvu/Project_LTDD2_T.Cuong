package com.example.quanlydiemsinhvien.Nganh;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quanlydiemsinhvien.R;

import java.util.Vector;

public class NganhActivity extends AppCompatActivity {
    private Vector<Nganh> listNganh = new Vector<Nganh>();
    private RecyclerView recyclerView;

    private NganhAdapter adapterNganh;
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
        listNganh.add(new Nganh("CNTT", "Cong Nghe Thong Tin"));
        listNganh.add(new Nganh("DL", "Du Lich"));
        listNganh.add(new Nganh("QTKD", "Quan Tri Kinh Doanh"));
        listNganh.add(new Nganh("TN", "Tieng Nhat"));
        listNganh.add(new Nganh("TH", "Tieng Han"));
        listNganh.add(new Nganh("KT", "Ke Toan"));
        listNganh.add(new Nganh("TA", "Tieng Anh"));

        // Use the linear layout manager
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        // Specify an adapter
        adapterNganh = new NganhAdapter(listNganh);
        recyclerView.setAdapter(adapterNganh);
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
