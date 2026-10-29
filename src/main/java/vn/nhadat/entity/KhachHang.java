package vn.nhadat.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "khach_hang")
public class KhachHang {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ma_khach_hang")
    private Long maKhachHang;

    @Column(name = "ten_khach_hang", nullable = false, length = 120)
    private String tenKhachHang;

    @Column(name = "so_dien_thoai", nullable = false, length = 20)
    private String soDienThoai;

    @Column(nullable = false, length = 150)
    private String email;

    public KhachHang() { }

    public KhachHang(String tenKhachHang, String soDienThoai, String email) {
        this.tenKhachHang = tenKhachHang;
        this.soDienThoai = soDienThoai;
        this.email = email;
    }

    public Long getMaKhachHang() { return maKhachHang; }
    public void setMaKhachHang(Long maKhachHang) { this.maKhachHang = maKhachHang; }
    public String getTenKhachHang() { return tenKhachHang; }
    public void setTenKhachHang(String tenKhachHang) { this.tenKhachHang = tenKhachHang; }
    public String getSoDienThoai() { return soDienThoai; }
    public void setSoDienThoai(String soDienThoai) { this.soDienThoai = soDienThoai; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
}
