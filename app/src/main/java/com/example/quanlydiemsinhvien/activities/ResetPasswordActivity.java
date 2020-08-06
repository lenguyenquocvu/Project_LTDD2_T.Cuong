package com.example.quanlydiemsinhvien.activities;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.quanlydiemsinhvien.R;
import com.example.quanlydiemsinhvien.data_models.Accounts;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ResetPasswordActivity extends AppCompatActivity {
    private RadioGroup rdogCheck;
    private EditText edtID;
    FirebaseDatabase rootNode;
    DatabaseReference reference;
    private Button btnReset;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reset_password_layout);

        rdogCheck = findViewById(R.id.radioGroup);
        edtID = findViewById(R.id.edtID);
        btnReset = findViewById(R.id.btnReset);
        rootNode = FirebaseDatabase.getInstance();

        btnReset.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                switch (rdogCheck.getCheckedRadioButtonId()) {
                    case R.id.radAmin: {
                        reference = rootNode.getReference(LoginActivity.ADMIN_TAG);
                        reference.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                if(snapshot.exists()){
                                    String id = edtID.getText().toString();
                                    for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                                        Accounts acc = dataSnapshot.getValue(Accounts.class);
                                        if(id.equals(acc.getId()))
                                            reference.child(acc.getId()).child("password").setValue(id);

                                    }
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });
                    }
                    break;
                    case R.id.radPhongDaoTao: {
                        reference = rootNode.getReference(LoginActivity.PDT_TAG);
                        reference.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                if(snapshot.exists()){
                                    String id = edtID.getText().toString();
                                    for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                                        Accounts acc = dataSnapshot.getValue(Accounts.class);
                                        if(id.equals(acc.getId()))
                                            reference.child(acc.getId()).child("password").setValue(id);

                                    }
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });
                    }
                    break;
                    case R.id.radGiangVien: {
                        reference = rootNode.getReference(LoginActivity.GIANGVIEN_TAG);
                        reference.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                if(snapshot.exists()){
                                    String id = edtID.getText().toString();
                                    for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                                        Accounts acc = dataSnapshot.getValue(Accounts.class);
                                        if(id.equals(acc.getId()))
                                            reference.child(acc.getId()).child("password").setValue(id);

                                    }
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });
                    }
                    break;
                    case R.id.radSinhVien: {
                        reference = rootNode.getReference(LoginActivity.SINHVIEN_TAG);
                        reference.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                if(snapshot.exists()){
                                    String id = edtID.getText().toString();
                                    for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                                        Accounts acc = dataSnapshot.getValue(Accounts.class);
                                        if(id.equals(acc.getId()))
                                            reference.child(acc.getId()).child("password").setValue(id);

                                    }
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });
                    }
                    break;
                    default: {
                    }
                    break;
                }

            }
        });

    }
}
