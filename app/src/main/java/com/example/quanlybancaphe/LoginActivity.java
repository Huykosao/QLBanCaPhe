package com.example.quanlybancaphe;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.quanlybancaphe.database.DatabaseHelper;

public class LoginActivity extends AppCompatActivity {

    EditText editTaikhoan, editMatKhau;
    Button btnDangNhap;
    TextView txtWarning;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);

        editTaikhoan = findViewById(R.id.editTaikhoan);
        editMatKhau = findViewById(R.id.editMatKhau);

        btnDangNhap = findViewById(R.id.btnDangNhap);

        txtWarning = findViewById(R.id.txtWarning);


        DatabaseHelper dbHelper = new DatabaseHelper(this);
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();

        btnDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tk = editTaikhoan.getText().toString().trim();
                String mk = editMatKhau.getText().toString().trim();
                if (tk.isEmpty() || mk.isEmpty()) {
                    txtWarning.setText("Vui lòng nhập đầy đủ thông tin");
                    txtWarning.setVisibility(View.VISIBLE);
                    return;
                }
                else txtWarning.setVisibility(View.INVISIBLE);
                String role = dbHelper.getRole(tk,mk);
                if (dbHelper.checkUser(tk,mk)) {
                    getSharedPreferences("user", MODE_PRIVATE)
                            .edit()
                            .putString("username", tk)
                            .putString("role", role)
                            .putBoolean("isLogin", true)
                            .apply();
                    Toast.makeText(LoginActivity.this, "Đăng nhập thành công!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }else {
                    txtWarning.setText("Tài khoản hoặc mật khẩu không đúng");
                    txtWarning.setVisibility(View.VISIBLE);

                }
            }
        });



        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}