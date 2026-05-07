# QLBanCaPhe - Ứng dụng Quản lý Bán Cà phê

Ứng dụng Android Native được phát triển để quản lý các hoạt động bán hàng tại cửa hàng cà phê, bao gồm quản lý sản phẩm, lập hóa đơn và xem lịch sử giao dịch.

## 🚀 Tính năng chính

### 1. Quản lý Đăng nhập & Phân quyền
- Hỗ trợ hai loại quyền: **Admin** và **User**.
- Quản lý tài khoản người dùng và bảo mật cơ bản.

### 2. Quản lý Sản phẩm (Menu)
- Xem danh sách sản phẩm.
- Thêm mới, chỉnh sửa thông tin sản phẩm (Tên, Giá).
- Xóa sản phẩm (Sử dụng cơ chế soft-delete).

### 3. Quản lý Bán hàng & Hóa đơn
- Lập hóa đơn mới cho khách hàng.
- Chọn sản phẩm từ danh mục, tùy chỉnh số lượng.
- Tự động tính tổng tiền hóa đơn.
- Lưu trữ chi tiết từng hóa đơn (sản phẩm, số lượng, đơn giá lúc bán).

### 4. Lịch sử & Thống kê
- Xem danh sách các hóa đơn đã lập.
- Xem chi tiết từng hóa đơn cũ.

## 🛠 Công nghệ sử dụng
- **Ngôn ngữ:** Java (Android Native).
- **Cơ sở dữ liệu:** SQLite (Lưu trữ cục bộ).
- **Công cụ:** Android Studio, Gradle.

## 📂 Cấu trúc dự án
- `com.example.quanlybancaphe.database`: Chứa `DatabaseHelper` quản lý SQLite.
- `com.example.quanlybancaphe.entities`: Các lớp đối tượng (SanPham, HoaDon, ChiTietHoaDon, NguoiDung).
- `com.example.quanlybancaphe.adapters`: Các bộ điều phối hiển thị dữ liệu lên ListView/RecyclerView.
- `com.example.quanlybancaphe`: Các Activity xử lý logic giao diện.

## 📊 Sơ đồ cơ sở dữ liệu
Ứng dụng sử dụng 4 bảng chính:
- `SanPham`: Lưu thông tin đồ uống/thức ăn.
- `HoaDon`: Lưu thông tin tổng quát của đơn hàng.
- `ChiTietHoaDon`: Lưu chi tiết các món trong một hóa đơn.
- `NguoiDung`: Lưu thông tin tài khoản đăng nhập.

## 🔑 Tài khoản mặc định
Khi ứng dụng khởi tạo lần đầu, bạn có thể sử dụng các tài khoản sau:
- **Admin:** `admin` / `admin`
- **User:** `user` / `user`

## ⚙️ Cài đặt & Chạy thử
1. Mở dự án bằng **Android Studio**.
2. Chờ Gradle sync hoàn tất.
3. Chạy ứng dụng trên Emulator hoặc thiết bị Android thật (API level 24+).
