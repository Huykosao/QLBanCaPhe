package com.example.quanlybancaphe;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import com.example.quanlybancaphe.adapters.SanPhamAdapter;
import com.example.quanlybancaphe.database.DatabaseHelper;
import com.example.quanlybancaphe.entities.SanPham;

public class SanPhamActivity extends AppCompatActivity {
    Button btnBack;
    FloatingActionButton btnAddSanPham;
    ListView listSanPham;
    ArrayList<SanPham> sanPhamList;
    SanPhamAdapter adapter;
    DatabaseHelper dbHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_san_pham);

        btnBack = findViewById(R.id.btnBack);
        btnAddSanPham = findViewById(R.id.btnAddTaiKhoan);

        listSanPham = findViewById(R.id.listTaiKhoan);

        dbHelper = new DatabaseHelper(this);

        sanPhamList = dbHelper.getSanPham();
        adapter = new SanPhamAdapter(this, sanPhamList);
        listSanPham.setAdapter(adapter);

        listSanPham.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                SanPham selectedSanPham = sanPhamList.get(position);
                boolean trangThai = selectedSanPham.isTrangThai();
                if (trangThai) {
                    Intent intent = new Intent(SanPhamActivity.this, EditSanPhamActivity.class);
                    intent.putExtra("maSP", selectedSanPham.getId());
                    intent.putExtra("tenSP", selectedSanPham.getTenSP());
                    intent.putExtra("gia", selectedSanPham.getGia());

                    startActivity(intent);
                }
            }
        });

        btnAddSanPham.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SanPhamActivity.this, ThemSanPhamActivity.class);
                startActivity(intent);

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
    @Override
    protected void onResume() {
        super.onResume();
        sanPhamList = dbHelper.getSanPham();
        adapter = new SanPhamAdapter(this, sanPhamList);
        listSanPham.setAdapter(adapter);

    }

}