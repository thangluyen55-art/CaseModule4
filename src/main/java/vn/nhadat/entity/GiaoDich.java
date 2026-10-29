package vn.nhadat.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "giao_dich")
public class GiaoDich {
    @Id
    @Column(name = "ma_giao_dich", length = 8, nullable = false)
    private String maGiaoDich;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ma_khach_hang", nullable = false)
    private KhachHang khachHang;

    @Column(name = "ngay_giao_dich", nullable = false)
    private LocalDate ngayGiaoDich;

    @Column(name = "loai_dich_vu", nullable = false, length = 30)
    private String loaiDichVu;

    @Column(name = "don_gia", nullable = false, precision = 18, scale = 2)
    private BigDecimal donGia;

    @Column(name = "dien_tich", nullable = false, precision = 12, scale = 2)
    private BigDecimal dienTich;

    public GiaoDich() { }

    public String getMaGiaoDich() { return maGiaoDich; }
    public void setMaGiaoDich(String maGiaoDich) { this.maGiaoDich = maGiaoDich; }
    public KhachHang getKhachHang() { return khachHang; }
    public void setKhachHang(KhachHang khachHang) { this.khachHang = khachHang; }
    public LocalDate getNgayGiaoDich() { return ngayGiaoDich; }
    public void setNgayGiaoDich(LocalDate ngayGiaoDich) { this.ngayGiaoDich = ngayGiaoDich; }
    public String getLoaiDichVu() { return loaiDichVu; }
    public void setLoaiDichVu(String loaiDichVu) { this.loaiDichVu = loaiDichVu; }
    public BigDecimal getDonGia() { return donGia; }
    public void setDonGia(BigDecimal donGia) { this.donGia = donGia; }
    public BigDecimal getDienTich() { return dienTich; }
    public void setDienTich(BigDecimal dienTich) { this.dienTich = dienTich; }
}
