package com.example.quanlydiemsinhvien.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.quanlydiemsinhvien.R;
import com.example.quanlydiemsinhvien.data_models.Accounts;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class LoginActivity extends AppCompatActivity {
    // Properties
    private EditText edtId, edtPassword;
    private Button btnDangNhap, btnThoat;
    private RadioButton radAdmin, radPhongDaoTao, radGiangVien, radSinhVien;
    private RadioGroup radioGroup;
    private ArrayList<Accounts> listAccounts = new ArrayList<Accounts>();

    // Firebase
    public static final String ADMIN_TAG = "accountAdmin";
    public static final String SINHVIEN_TAG = "accountSinhVien";
    public static final String GIANGVIEN_TAG = "accountGiangVien";
    public static final String PDT_TAG = "accountPDT";
    DatabaseReference myDatabase = FirebaseDatabase.getInstance().getReference();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_main);

        // Get all View from layout
        edtId = findViewById(R.id.et_id);
        edtPassword = findViewById(R.id.et_password);
        btnDangNhap = findViewById(R.id.btnDangNhap);
        btnThoat = findViewById(R.id.btnThoat);
        radAdmin = findViewById(R.id.radAmin);
        radPhongDaoTao = findViewById(R.id.radPhongDaoTao);
        radGiangVien = findViewById(R.id.radGiangVien);
        radSinhVien = findViewById(R.id.radSinhVien);
        radioGroup = findViewById(R.id.radioGroup);
        // User click Login button
        btnDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = edtId.getText().toString();
                String password = edtPassword.getText().toString();
                if (radAdmin.isChecked()) {
                    getAccountData(ADMIN_TAG);
                    for (Accounts item : listAccounts) {
                        Log.d("==>", item.getId() + " " + item.getPassword());
                    }
                }
                if (radPhongDaoTao.isChecked()) {
                    getAccountData(PDT_TAG);
                    for (Accounts item : listAccounts) {
                        Log.d("==>", item.getId() + " " + item.getPassword());
                    }
                }
                if (radGiangVien.isChecked()) {
                    getAccountData(GIANGVIEN_TAG);
                    for (Accounts item : listAccounts) {
                        Log.d("==>", item.getId() + " " + item.getPassword());
                    }
                }
                if (radSinhVien.isChecked()) {
                    getAccountData(SINHVIEN_TAG);
                    for (Accounts item : listAccounts) {
                        Log.d("==>", item.getId() + " " + item.getPassword());
                    }
                }
            }
        });


        // User click Thoat Button
        btnThoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this, android.R.style.Theme_DeviceDefault_Light_Dialog);
                builder.setTitle("Thông báo");
                builder.setMessage("Bạn có muốn thoát hay không?");
                builder.setIcon(android.R.drawable.ic_dialog_alert);
                builder.setPositiveButton("Thoát ", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        onBackPressed();
                    }
                });

                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.show();
            }
        });
    }

    // Get data account from database
    public void getAccountData(String child) {
        myDatabase.child(child).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                listAccounts.clear();
                for (DataSnapshot node : snapshot.getChildren()) {
                    Accounts acc = node.getValue(Accounts.class);
                    listAccounts.add(acc);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }
}
