package com.example.quanlydiemsinhvien.firebase_data;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.quanlydiemsinhvien.activities.KhoaActivity;
import com.example.quanlydiemsinhvien.adapters.KhoaAdapter;
import com.example.quanlydiemsinhvien.data_models.Khoa;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static com.example.quanlydiemsinhvien.activities.KhoaActivity.KHOATAG;
import static com.example.quanlydiemsinhvien.activities.KhoaActivity.khoaAdapter;

public class KhoaDatabase {
    DatabaseReference mDataRef;

    public KhoaDatabase() {
        mDataRef = FirebaseDatabase.getInstance().getReference();
    }

    public KhoaDatabase(DatabaseReference mDataRef) {
        this.mDataRef = mDataRef;
    }

    public DatabaseReference getmDataRef() {
        return mDataRef;
    }

    public void setmDataRef(DatabaseReference mDataRef) {
        this.mDataRef = mDataRef;
    }

    // Add new Khoa to Firebase server
    public void writeNewKhoa(String maKhoa, String tenKhoa, String ngayThanhLap) {
        Khoa newKhoa = new Khoa(maKhoa, tenKhoa, ngayThanhLap);
        mDataRef.child(KHOATAG).child(newKhoa.getMaKhoa()).setValue(newKhoa);
    }

    // Delete a Khoa
    public void deleteAKhoa(String removeKhoa) {
        mDataRef.child(KHOATAG).child(removeKhoa).removeValue();
    }

    // Update the Khoa
    public void updateAKhoa(String maKhoa, String tenKhoa, String ngayThanhLap) {
        String key = mDataRef.child(KHOATAG).push().getKey();

        Khoa updateKhoa = new Khoa(maKhoa, tenKhoa, ngayThanhLap);
        Map<String, Object> postValue = updateKhoa.toMap();

        Map<String, Object> childUpdates = new HashMap<>();
        childUpdates.put("/Khoa/" + key, postValue);

        mDataRef.updateChildren(childUpdates);

        Log.d(KHOATAG, key);

    }
}
