package com.example.quanlydiemsinhvien.firebase_db_helpers;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FirebaseLopHocPhanHelper {
    private FirebaseDatabase rootNode;
    private DatabaseReference reference;

    public FirebaseLopHocPhanHelper() {
        rootNode = FirebaseDatabase.getInstance();
        reference = rootNode.getReference("LopHocPhan");
    }
}
