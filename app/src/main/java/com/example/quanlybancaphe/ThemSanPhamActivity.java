package com.example.quanlybancaphe;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.quanlybancaphe.database.DatabaseHelper;

public class ThemSanPhamActivity extends AppCompatActivity {
    Button btnBack, btnLuu;
    EditText editTenSanPham, editGia;
    DatabaseHelper dbHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_them_san_pham);

        btnLuu = findViewById(R.id.btnDungSP);
        btnBack = findViewById(R.id.btnBack);

        editTenSanPham = findViewById(R.id.editTenSanPham);
        editGia = findViewById(R.id.editGia);

        dbHelper = new DatabaseHelper(this);

        btnLuu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tenSanPham = editTenSanPham.getText().toString().trim();
                double gia = Double.parseDouble(editGia.getText().toString().trim());

                if(tenSanPham.isEmpty() || gia == 0) {
                    Toast.makeText(ThemSanPhamActivity.this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                    return;
                }
                dbHelper.insertSanPham(tenSanPham, gia);

                finish();

            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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