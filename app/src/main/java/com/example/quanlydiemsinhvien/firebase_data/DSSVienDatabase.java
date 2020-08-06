//package com.example.quanlydiemsinhvien.firebase_data;
//
//import com.example.quanlydiemsinhvien.data_models.DanhSachSinhVien;
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;
//
////import static com.example.quanlydiemsinhvien.activities.DSSinhVienActivity.SINHVIENTAG;
//
//public class DSSVienDatabase {
//    DatabaseReference mDataRef;
//
//    public DSSVienDatabase() {
//        mDataRef = FirebaseDatabase.getInstance().getReference();
//    }
//
//    public DSSVienDatabase(DatabaseReference mDataRef) {
//        this.mDataRef = mDataRef;
//    }
//
//    public DatabaseReference getmDataRef() {
//        return mDataRef;
//    }
//
//    public void setmDataRef(DatabaseReference mDataRef) {
//        this.mDataRef = mDataRef;
//    }
//
//    //Add new Lop to firebase server
//    public void writeNewLop(String maSV, String tenSV, int diemSV) {
//        DanhSachSinhVien newSV = new DanhSachSinhVien(maSV, tenSV, diemSV);
//        mDataRef.child(SINHVIENTAG).child(newSV.getMaSV()).setValue(newSV);
//    }
//
//    //Delete a Lop
//    public void deleteSV(String removeSV) {
//        mDataRef.child(SINHVIENTAG).child(removeSV).removeValue();
//    }
//}
