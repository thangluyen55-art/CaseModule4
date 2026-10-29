# Web quản lý giao dịch nhà đất — Spring Boot MVC

Ứng dụng dùng Java 21, Spring Boot 3.5.16, Spring MVC, Thymeleaf, Spring Data JPA, Bean Validation và MySQL.

## 1. Yêu cầu
- JDK 21 (khuyến nghị dùng đúng phiên bản này trong IntelliJ)
- IntelliJ IDEA
- MySQL Server 8.x (có thể dùng MySQL trong XAMPP)
- Maven (IntelliJ sẽ tự tải dependencies theo `pom.xml`)

## 2. Tạo database
Mở MySQL Workbench hoặc phpMyAdmin và chạy:

```sql
CREATE DATABASE quan_ly_giao_dich
CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
```

Entity của JPA sẽ tự tạo/cập nhật hai bảng `khach_hang` và `giao_dich` khi ứng dụng khởi động.

## 3. Cấu hình tài khoản MySQL
Mở `src/main/resources/application.properties`:

```properties
spring.datasource.username=${DB_USERNAME:root}
spring.datasource.password=${DB_PASSWORD:}
```

Mặc định là tài khoản `root` và mật khẩu trống (thường gặp ở XAMPP). Nếu MySQL có mật khẩu, đặt giá trị `DB_PASSWORD` trong cấu hình Run của IntelliJ hoặc sửa trực tiếp giá trị sau dấu `:`.

## 4. Mở và chạy bằng IntelliJ
1. Giải nén project.
2. IntelliJ → **File → Open** → chọn thư mục chứa `pom.xml`.
3. Chọn JDK 21 tại **File → Project Structure → Project → SDK**. Ở mục **Language level**, chọn **21**; trong **Settings → Build, Execution, Deployment → Compiler → Java Compiler**, đặt **Target bytecode version = 21**. Ở **Settings → Build Tools → Maven → Runner**, chọn JRE là **Project JDK (21)**.
4. Chờ Maven tải thư viện.
5. Mở `src/main/java/vn/nhadat/QuanLyGiaoDichApplication.java` và bấm nút Run cạnh hàm `main`.
6. Truy cập `http://localhost:8080` hoặc `http://localhost:8080/giao-dich`.

Khi database chưa có khách hàng, chương trình tự thêm 3 khách hàng mẫu để có dữ liệu cho dropdown. Giao dịch không được tạo mẫu sẵn.

## 5. Chức năng
- Danh sách giao dịch JOIN thông tin khách hàng.
- Thêm giao dịch với Bean Validation: bắt buộc nhập, mã `MGD-XXXX`, ngày trong tương lai, đơn giá > 500.000, diện tích > 20.
- Chi tiết giao dịch và thông tin liên hệ khách hàng.
- Xóa có hộp thoại xác nhận; chọn Không sẽ quay lại danh sách và không xóa.
- Tìm kiếm gần đúng theo tên khách hàng, lọc loại dịch vụ, hoặc cả hai điều kiện.

## 6. Cấu trúc
- `entity`: ánh xạ bảng MySQL bằng JPA.
- `repository`: truy vấn bằng Spring Data JPA.
- `service`: logic nghiệp vụ.
- `controller`: điều hướng MVC.
- `dto`: form và quy tắc kiểm tra dữ liệu.
- `templates`: giao diện Thymeleaf.
- `static/css/app.css`: giao diện responsive.
