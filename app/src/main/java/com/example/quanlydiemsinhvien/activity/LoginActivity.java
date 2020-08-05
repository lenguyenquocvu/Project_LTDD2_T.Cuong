package com.example.quanlydiemsinhvien.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.quanlydiemsinhvien.R;
import com.example.quanlydiemsinhvien.data_models.Accounts;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class LoginActivity extends AppCompatActivity {
    // Properties
    private EditText edtId, edtPassword;
    private Button btnDangNhap, btnThoat;
    private RadioButton radAdmin, radPhongDaoTao, radGiangVien, radSinhVien;
    private RadioGroup radioGroup;
    private ArrayList<Accounts> listAccounts = new ArrayList<Accounts>();
    private Accounts accounts = new Accounts();
    // Firebase
    public static final String ADMIN_TAG = "accountAdmin";
    public static final String SINHVIEN_TAG = "accountSinhVien";
    public static final String GIANGVIEN_TAG = "accountGiangVien";
    public static final String PDT_TAG = "accountPDT";
    DatabaseReference myDatabase = FirebaseDatabase.getInstance().getReference();

    //On Create:
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
                loginForm();
                if (radAdmin.isChecked()) {
                    getAccountData(ADMIN_TAG);
                    for (Accounts item : listAccounts) {
                        Log.d("==>", item.getId() + " " + item.getPassword());
                        if (id.equals(item.getId()) && password.equals(item.getPassword())) {
                            Toast.makeText(LoginActivity.this, "Bạn đã đặng nhập thành công", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(LoginActivity.this, MainDanhSachAdmin.class);
                            startActivity(intent);
                        } else {

                            //Toast.makeText(LoginActivity.this, "Bạn đã đặng nhập thất bại", Toast.LENGTH_SHORT).show();
                        }
                    }

                } else if (radPhongDaoTao.isChecked()) {
                    getAccountData(PDT_TAG);
                    for (Accounts item : listAccounts) {
                        Log.d("==>", item.getId() + " " + item.getPassword());
                        if (id.equals(item.getId()) && password.equals(item.getPassword())) {
                            Toast.makeText(LoginActivity.this, "Bạn đã đặng nhập thành công", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(LoginActivity.this, MainDanhSachPhongDaoTao.class);
                            startActivity(intent);
                        } else {
                            //Toast.makeText(LoginActivity.this, "Bạn đã đặng nhập thất bại", Toast.LENGTH_SHORT).show();
                        }
                    }
                } else if (radGiangVien.isChecked()) {
                    getAccountData(GIANGVIEN_TAG);
                    for (Accounts item : listAccounts) {
                        Log.d("==>", item.getId() + " " + item.getPassword());
                        if (id.equals(item.getId()) && password.equals(item.getPassword())) {
                            Toast.makeText(LoginActivity.this, "Bạn đã đặng nhập thành công", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(LoginActivity.this, MainDanhSachGiangVien.class);
                            startActivity(intent);
                        } else {
                            //Toast.makeText(LoginActivity.this, "Bạn đã đặng nhập thất bại", Toast.LENGTH_SHORT).show();
                        }
                    }
                } else if (radSinhVien.isChecked()) {
                    getAccountData(SINHVIEN_TAG);
                    for (Accounts item : listAccounts) {
                        Log.d("==>", item.getId() + " " + item.getPassword());
                        if (id.equals(item.getId()) && password.equals(item.getPassword())) {
                            Toast.makeText(LoginActivity.this, "Bạn đã đặng nhập thành công", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(LoginActivity.this, MainDanhSachSinhVien.class);
                            startActivity(intent);
                        } else {
                            //Toast.makeText(LoginActivity.this, "Bạn đã đặng nhập thất bại", Toast.LENGTH_SHORT).show();
                        }
                    }
                } else {
                    validateForm();
                    Toast.makeText(LoginActivity.this, "Vui lòng nhập đủ thông tin", Toast.LENGTH_SHORT).show();
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
                        dialog.cancel();
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

    //Validate Form:
    private boolean validateForm() {
        String valID = edtId.getText().toString();
        String valPassword = edtPassword.getText().toString();
        String noWhiteSpace = "(?=\\+!#%$&*~-/:|.^@)";
        if (valID.isEmpty()) {
            edtId.setError("id cannot be empty");
            return false;
        } else if (valPassword.isEmpty()) {
            edtPassword.setError("password cannot be empty");
            return false;
        } else if (valID.isEmpty() && valPassword.isEmpty()) {
            edtId.setError("id cannot be empty");
            edtPassword.setError("password cannot be empty");
            return false;
        } else if (valID.matches(noWhiteSpace) || valPassword.matches(noWhiteSpace)) {
            edtId.setError("id cannot be empty");
            edtPassword.setError("white spaces are not allowed");
            return false;
        } else {
            edtId.setError(null);
            edtPassword.setError(null);
            return true;
        }
    }

    //LoginID:
    public void loginForm() {
        if (!validateForm()) {
            return;
        } else {
            isForm();
        }
    }

    //IsFormAdmin:
    private void isForm() {
        final String idVal = edtId.getText().toString().trim();
        final String passwordVal = edtPassword.getText().toString().trim();
        //
        DatabaseReference referAdmin = FirebaseDatabase.getInstance().getReference("accountAdmin");
        DatabaseReference referPDT = FirebaseDatabase.getInstance().getReference("accountPDT");
        DatabaseReference referGV = FirebaseDatabase.getInstance().getReference("accountGiangVien");
        DatabaseReference referSV = FirebaseDatabase.getInstance().getReference("accountSinhVien");
        //
        Query queryAdmin = referAdmin.orderByChild("id").equalTo(passwordVal);
        Query queryPDT = referPDT.orderByChild("id").equalTo(passwordVal);
        Query queryGV = referGV.orderByChild("id").equalTo(passwordVal);
        Query querySV = referSV.orderByChild("id").equalTo(passwordVal);
        //Admin:
        queryAdmin.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    edtId.setError(null);
                    String passwordDB = snapshot.child(idVal).child("password").getValue(String.class);
                    if (passwordDB.equals(passwordVal)) {
                        String idDB = snapshot.child(idVal).child("id").getValue(String.class);
                        Intent intent = new Intent(getApplicationContext(), Accounts.class);
                        intent.putExtra("id", idDB);
                        intent.putExtra("password", passwordDB);
                        startActivity(intent);
                    } else {
                        edtPassword.setError("Wrong password");
                        edtId.requestFocus();
                    }
                } else {
                    edtId.setError("no such id exits");
                    edtId.requestFocus();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        //Phong Dao Tao:
        queryPDT.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    edtId.setError(null);
                    String passwordDB = snapshot.child(idVal).child("password").getValue(String.class);
                    if (passwordDB.equals(passwordVal)) {
                        String idDB = snapshot.child(idVal).child("id").getValue(String.class);
                        Intent intent = new Intent(getApplicationContext(), Accounts.class);
                        intent.putExtra("id", idDB);
                        intent.putExtra("password", passwordDB);
                        startActivity(intent);
                    } else {
                        edtPassword.setError("Wrong password");
                        edtId.requestFocus();
                    }
                } else {
                    edtId.setError("no such id exit");
                    edtId.requestFocus();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        //Giang Vien:
        queryGV.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    edtId.setError(null);
                    String passwordDB = snapshot.child(idVal).child("password").getValue(String.class);
                    if (passwordDB.equals(passwordVal)) {
                        String idDB = snapshot.child(idVal).child("id").getValue(String.class);
                        Intent intent = new Intent(getApplicationContext(), Accounts.class);
                        intent.putExtra("id", idDB);
                        intent.putExtra("password", passwordDB);
                        startActivity(intent);
                    } else {
                        edtPassword.setError("Wrong password");
                        edtId.requestFocus();
                    }
                } else {
                    edtId.setError("no such id exit");
                    edtId.requestFocus();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        //Sinh Vien:
        querySV.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    edtId.setError(null);
                    String passwordDB = snapshot.child(idVal).child("password").getValue(String.class);
                    if (passwordDB.equals(passwordVal)) {
                        String idDB = snapshot.child(idVal).child("id").getValue(String.class);
                        Intent intent = new Intent(getApplicationContext(), Accounts.class);
                        intent.putExtra("id", idDB);
                        intent.putExtra("password", passwordDB);
                        startActivity(intent);
                    } else {
                        edtPassword.setError("Wrong password");
                        edtId.requestFocus();
                    }
                } else {
                    edtId.setError("no such id exit");
                    edtId.requestFocus();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }


}
