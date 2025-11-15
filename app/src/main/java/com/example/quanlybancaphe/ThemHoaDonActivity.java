package com.example.quanlybancaphe;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import com.example.quanlybancaphe.database.DatabaseHelper;
import com.example.quanlybancaphe.Models.HoaDonItem;

public class ThemHoaDonActivity extends AppCompatActivity {
    Button btnBack,btnAdd,btnLuuHoaDon;
    EditText editSoLuong;
    Spinner spinnerSanPham;
    List<String> tenSanPhamList;
    DatabaseHelper dbHelper;
    String tenSP ;
    TextView txtTongTien;
    ListView listViewSanPham;
    double tongtien = 0;
    ArrayList<HoaDonItem> itemList = new ArrayList<>();
    ArrayAdapter<HoaDonItem> adapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_hoa_don);

        dbHelper = new DatabaseHelper(this);

        btnBack = findViewById(R.id.btnBack);
        btnAdd = findViewById(R.id.btnAdd);
        btnLuuHoaDon = findViewById(R.id.btnLuuHoaDon);

        editSoLuong = findViewById(R.id.editSoLuong);

        txtTongTien = findViewById(R.id.txtTongTien);

        spinnerSanPham = findViewById(R.id.spinnerSanPham);

        listViewSanPham = findViewById(R.id.listviewSanPham);

        tenSanPhamList = dbHelper.getTenSanPham();
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, tenSanPhamList);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerSanPham.setAdapter(spinnerAdapter);

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,itemList);
        listViewSanPham.setAdapter(adapter);

        spinnerSanPham.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                tenSP = tenSanPhamList.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(ThemHoaDonActivity.this, "Vui lòng chọn sản phẩm", Toast.LENGTH_SHORT).show();
            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(tenSP == null){
                    Toast.makeText(ThemHoaDonActivity.this, "Chưa có sản phẩm", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(editSoLuong.getText().toString().trim().isEmpty()){
                    Toast.makeText(ThemHoaDonActivity.this, "Vui lòng nhập số lượng", Toast.LENGTH_SHORT).show();
                    return;
                }

                int soLuong = Integer.parseInt(editSoLuong.getText().toString().trim());

                if (soLuong <= 0){
                    Toast.makeText(ThemHoaDonActivity.this, "Vui lòng nhập đúng số lượng", Toast.LENGTH_SHORT).show();
                    return;
                }

                double gia = dbHelper.getGiaSanPham(tenSP);
                int maSP = dbHelper.getIdSanPham(tenSP);

                boolean daTonTai = false;
                for (HoaDonItem item : itemList) {
                    if (item.getMaSP() == maSP) {
                        item.setSoLuong(item.getSoLuong() + soLuong);
                        daTonTai = true;
                        break;
                    }
                }
                if (!daTonTai) {
                    HoaDonItem newItem = new HoaDonItem(maSP, tenSP, soLuong, gia);
                    itemList.add(newItem);
                }

                //tinh tong
                tongtien = 0;
                for (HoaDonItem item : itemList) {
                    tongtien += item.getSoLuong() * item.getGia();
                }
                DecimalFormat dcf = new DecimalFormat("#,###");
                txtTongTien.setText(dcf.format(tongtien) + " VND");

                adapter.notifyDataSetChanged();
            }
        });

        btnLuuHoaDon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (itemList.isEmpty()) {
                    Toast.makeText(ThemHoaDonActivity.this, "Chưa thêm sản phẩm nào", Toast.LENGTH_SHORT).show();
                    return;
                }
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
                String thoiGianHienTai = sdf.format(new Date());
                long maHD = dbHelper.insertHoaDonAndGetId(thoiGianHienTai, tongtien);
                if (maHD == -1) {
                    Toast.makeText(ThemHoaDonActivity.this, "Lỗi khi tạo hóa đơn!", Toast.LENGTH_SHORT).show();
                    return;
                }
                for (HoaDonItem item : itemList) {
                    dbHelper.insertChiTietHoaDon(maHD, item.getMaSP(), item.getSoLuong(), item.getGia());
                }

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