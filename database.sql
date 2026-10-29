CREATE DATABASE IF NOT EXISTS quan_ly_giao_dich
  CHARACTER SET utf8mb4
  COLLATE utf8mb4_unicode_ci;

USE quan_ly_giao_dich;

-- Spring Data JPA/Hibernate sẽ tự tạo hai bảng theo Entity khi khởi động.
-- Bảng khach_hang có dữ liệu mẫu được thêm tự động khi bảng còn trống.
