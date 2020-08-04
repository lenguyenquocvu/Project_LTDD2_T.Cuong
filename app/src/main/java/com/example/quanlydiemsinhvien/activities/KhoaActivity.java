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
import com.example.quanlydiemsinhvien.adapters.KhoaAdapter;
import com.example.quanlydiemsinhvien.data_models.Khoa;
import com.example.quanlydiemsinhvien.dialogs.AddKhoaDialog;
import com.example.quanlydiemsinhvien.divider.DividerItemDecoration;
import com.example.quanlydiemsinhvien.firebase_data.KhoaDatabase;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class KhoaActivity extends AppCompatActivity {
    public static final String KHOATAG = "Khoa";
    public static ArrayList<Khoa> dataKhoa = new ArrayList<Khoa>();
    public static KhoaAdapter khoaAdapter;

    public static Intent intent;
    private Context context = KhoaActivity.this;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;

    // Properties of Firebase
    KhoaDatabase mDatabase = new KhoaDatabase();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.khoa_recyclerview_layout);
        setTitle(R.string.actionBarTitle);

        intent = getIntent();

        // Get View from layout
        recyclerView = findViewById(R.id.khoa_recylerview);

        // Improve performance if changes in content do not change the layout size of the RecyclerView
        recyclerView.setHasFixedSize(true);

        // Item decoration
        recyclerView.addItemDecoration(new DividerItemDecoration(getResources().getDrawable(R.drawable.divider, getTheme())));

        // Get all data from firebase
        mDatabase.getmDataRef().child("Khoa").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                dataKhoa.clear();

                for (DataSnapshot node : snapshot.getChildren()) {
                    Khoa khoa = node.getValue(Khoa.class);
                    dataKhoa.add(khoa);
                }

                // Update after changed data
                mDatabase.getmDataRef().keepSynced(true);

                // Specify an adapter
                khoaAdapter = new KhoaAdapter(dataKhoa, context);
                recyclerView.setAdapter(khoaAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.d(KHOATAG, "Load data error: ", error.toException());
            }
        });

        // Use linear layout manager
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

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
