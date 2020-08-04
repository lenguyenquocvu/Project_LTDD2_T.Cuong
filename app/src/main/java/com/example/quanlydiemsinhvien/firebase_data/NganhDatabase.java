package com.example.quanlydiemsinhvien.firebase_data;

import com.example.quanlydiemsinhvien.data_models.Nganh;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

import static com.example.quanlydiemsinhvien.activities.NganhActivity.NGANHTAG;

public class NganhDatabase {
    private DatabaseReference myDataRef;

    public NganhDatabase() {
        myDataRef = FirebaseDatabase.getInstance().getReference();
    }

    public NganhDatabase(DatabaseReference myDataRef) {
        myDataRef = FirebaseDatabase.getInstance().getReference();
        this.myDataRef = myDataRef;
    }

    public DatabaseReference getMyDataRef() {
        return myDataRef;
    }

    public void setMyDataRef(DatabaseReference myDataRef) {
        this.myDataRef = myDataRef;
    }

    // Write data to firebase server
    public void writeNewNganh(String maKhoaCuaNganh, String maNganh, String tenNganh) {
        Nganh nganh = new Nganh(maKhoaCuaNganh, maNganh, tenNganh);
        myDataRef.child(NGANHTAG).child(nganh.getMaNganh()).setValue(nganh);
    }

    // Delete Nganh
    public void removeANganh(String maNganh) {
        myDataRef.child(NGANHTAG).child(maNganh).removeValue();
    }

    // Update Nganh
    public void updateANganh(String maKhoa, String maNganh, String tenNganh) {
        Nganh nganh = new Nganh(maKhoa, maNganh, tenNganh);
        Map<String, Object> pushNganhValues = nganh.toMap();

        Map<String, Object> childUpdates = new HashMap<>();
        childUpdates.put("/Nganh/" + maNganh, pushNganhValues);

        myDataRef.updateChildren(childUpdates);
    }
}
