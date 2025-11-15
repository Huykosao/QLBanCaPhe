package com.example.quanlybancaphe;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.quanlybancaphe.database.DatabaseHelper;

import java.text.DecimalFormat;

public class EditSanPhamActivity extends AppCompatActivity {

    EditText editTenSanPham, editGia;
    Button btnDungSP, btnLuu, btnBack;
    DatabaseHelper dbHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_edit_san_pham);

        dbHelper = new DatabaseHelper(this);

        editTenSanPham = findViewById(R.id.editTenSanPham);
        editGia = findViewById(R.id.editGia);

        btnBack = findViewById(R.id.btnBack);
        btnDungSP = findViewById(R.id.btnDungSP);
        btnLuu = findViewById(R.id.btnLuu);

        Intent intent = getIntent();
        int maSP = intent.getIntExtra("maSP", -1);
        editTenSanPham.setText(intent.getStringExtra("tenSP"));
        DecimalFormat formatter = new DecimalFormat("#");
        editGia.setText(formatter.format(intent.getDoubleExtra("gia", 0)));


        btnLuu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tenSP = editTenSanPham.getText().toString();
                double gia = Double.parseDouble(editGia.getText().toString());
                dbHelper.updateSanPham(maSP, tenSP, gia);

                finish();
            }
        });

        btnDungSP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(EditSanPhamActivity.this);
                builder.setTitle("Xác nhận")
                        .setMessage("Bạn có muốn hủy sản phẩm này?")
                        .setCancelable(false)
                        .setPositiveButton("Có", (dialog, which) -> {
                            dbHelper.deleteSanPham(maSP);
                            finish();
                        })
                        .setNegativeButton("Không", (dialog, which) -> {}
                        );
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
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