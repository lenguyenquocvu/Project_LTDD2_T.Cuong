package com.example.quanlydiemsinhvien.firebase_data;

import com.example.quanlydiemsinhvien.data_models.DanhSachLop;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import static com.example.quanlydiemsinhvien.activities.DSLopActivity.LOPTAG;

public class DSLopDatabase {
    DatabaseReference mDataRef;

    public DSLopDatabase() {
        mDataRef = FirebaseDatabase.getInstance().getReference();
    }

    public DSLopDatabase(DatabaseReference mDataRef) {
        this.mDataRef = mDataRef;
    }

    public DatabaseReference getmDataRef() {
        return mDataRef;
    }

    public void setmDataRef(DatabaseReference mDataRef) {
        this.mDataRef = mDataRef;
    }

    //Add new Lop to firebase serverprivate boolean ketThuc;

    public void writeNewLop(String maLHP, String tenLHP, String maGV, String maHK,  String maMH, String maNH, boolean ketThuc ) {
        DanhSachLop newLop = new DanhSachLop(maLHP, tenLHP, maGV, maHK, maMH, maNH , ketThuc);
        mDataRef.child(LOPTAG).child(newLop.getMaLHP()).setValue(newLop);
    }

    //Delete a Lop
    public void deleteLop(String removeLop) {
        mDataRef.child(LOPTAG).child(removeLop).removeValue();
    }

}
