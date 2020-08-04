package com.example.quanlydiemsinhvien.firebase_db_helpers;

import androidx.annotation.NonNull;

import com.example.quanlydiemsinhvien.activities.DanhSachKhoaHocActivity;
import com.example.quanlydiemsinhvien.data_models.KhoaHoc;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class FirebaseKhoaHocHelper {
    static FirebaseDatabase rootNode = FirebaseDatabase.getInstance();;
    static DatabaseReference reference = rootNode.getReference(DanhSachKhoaHocActivity.DB_KHOAHOC_NAME);
    static KhoaHoc khoaHoc = new KhoaHoc();

    public static void getKhoaHocList(final ArrayList<KhoaHoc> dsKhoaHoc){
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                dsKhoaHoc.clear();
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    khoaHoc = new KhoaHoc();
                    khoaHoc = dataSnapshot.getValue(KhoaHoc.class);
                    dsKhoaHoc.add(khoaHoc);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
