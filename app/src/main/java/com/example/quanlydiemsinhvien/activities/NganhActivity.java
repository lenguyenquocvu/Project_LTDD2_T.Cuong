package com.example.quanlydiemsinhvien.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quanlydiemsinhvien.R;
import com.example.quanlydiemsinhvien.adapters.NganhAdapter;
import com.example.quanlydiemsinhvien.data_models.Nganh;
import com.example.quanlydiemsinhvien.dialogs.AddNganhDialog;
import com.example.quanlydiemsinhvien.divider.DividerItemDecoration;
import com.example.quanlydiemsinhvien.firebase_data.KhoaDatabase;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import static com.example.quanlydiemsinhvien.adapters.KhoaAdapter.KEY_DATA;
import static com.example.quanlydiemsinhvien.adapters.KhoaAdapter.KEY_MAKHOA;
import static com.example.quanlydiemsinhvien.adapters.KhoaAdapter.KEY_TENKHOA;

public class NganhActivity extends AppCompatActivity {
    public static final String NGANHTAG = "Nganh";
    public static ArrayList<Nganh> dataNganh = new ArrayList<Nganh>();
    private RecyclerView recyclerView;
    public static Context context;

    public static NganhAdapter adapterNganh;
    private RecyclerView.LayoutManager layoutManager;

    Intent intent;
    Bundle data;
    public static String getMaKhoaCuaNganh = "";
    public static String getTenKhoa = "";

    // Properties of Firebase
    KhoaDatabase mDatabase = new KhoaDatabase();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nganh_recycleview_layout);

        // Get data from intent
        intent = KhoaActivity.intent;
        data = intent.getBundleExtra(KEY_DATA);
        getMaKhoaCuaNganh = data.getString(KEY_MAKHOA);
        getTenKhoa = data.getString(KEY_TENKHOA);

        // Set title for action Nganh
        setTitle(getTenKhoa);

        // Get all from view
        recyclerView = findViewById(R.id.nganh_recylerview);

        // Item decoration
        recyclerView.addItemDecoration(new DividerItemDecoration(getResources().getDrawable(R.drawable.divider, getTheme())));

        // Get Nganh data from firebase server
        mDatabase.getmDataRef().child(NGANHTAG).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                dataNganh.clear();
                for (DataSnapshot node : snapshot.getChildren()) {
                    Nganh nganh = node.getValue(Nganh.class);
                    Log.d("nganhcuakhoa", nganh.toString());

                    if (nganh.getMaKhoa().equals(getMaKhoaCuaNganh)) {
                        dataNganh.add(nganh);
                    }
                }

                // Auto sync data
                mDatabase.getmDataRef().keepSynced(true);

                //Special an adapter
                adapterNganh = new NganhAdapter(dataNganh, NganhActivity.this);
                recyclerView.setAdapter(adapterNganh);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.d(NGANHTAG, "Load data Nganh error: ", error.toException());
            }
        });

        // Use the linear layout manager
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        // Specify an adapter
        adapterNganh = new NganhAdapter(dataNganh, this);
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
