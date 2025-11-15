package com.example.quanlybancaphe;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    LinearLayout btnLapHoaDon,btnQuanLySanPham,btnLichSuHoaDon,layoutQuanly,btnDangXuat;
    TextView txtXinChao;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        SharedPreferences sharedPreferences = getSharedPreferences("user", MODE_PRIVATE);
        String username = sharedPreferences.getString("username", "");
        String role = sharedPreferences.getString("role", "");

        btnLapHoaDon = findViewById(R.id.btnLapHoaDon);
        btnQuanLySanPham = findViewById(R.id.btnQuanLySanPham);
        btnLichSuHoaDon = findViewById(R.id.btnLichSuHoaDon);

        layoutQuanly = findViewById(R.id.btnQuanLyTK);

        txtXinChao = findViewById(R.id.txtXinChao);
        btnDangXuat = findViewById(R.id.btnDangXuat);

        txtXinChao.setText("Xin chào " + username);

        if(role.equals("admin")) {
            layoutQuanly.setVisibility(View.VISIBLE);
        }
        else {
            layoutQuanly.setVisibility(View.INVISIBLE);
        }

        btnLapHoaDon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ThemHoaDonActivity.class);
                startActivity(intent);
            }
        });
        btnQuanLySanPham.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SanPhamActivity.class);
                startActivity(intent);

            }
        });
        btnLichSuHoaDon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, LichSuHoaDonActivity.class);
                startActivity(intent);

            }
        });
        btnDangXuat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = getSharedPreferences("user", MODE_PRIVATE);
                sharedPreferences.edit().clear().apply();
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}