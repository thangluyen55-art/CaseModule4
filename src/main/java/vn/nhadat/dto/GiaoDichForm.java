package vn.nhadat.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDate;

public class GiaoDichForm {
    @NotBlank(message = "Vui lòng nhập mã giao dịch.")
    @Pattern(regexp = "MGD-[0-9]{4}", message = "Mã giao dịch phải đúng định dạng MGD-XXXX (X là chữ số).")
    private String maGiaoDich;

    @NotNull(message = "Vui lòng chọn khách hàng.")
    private Long maKhachHang;

    @NotNull(message = "Vui lòng chọn ngày giao dịch.")
    @Future(message = "Ngày giao dịch phải lớn hơn ngày hiện tại.")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate ngayGiaoDich;

    @NotBlank(message = "Vui lòng chọn loại dịch vụ.")
    private String loaiDichVu;

    @NotNull(message = "Vui lòng nhập đơn giá.")
    @DecimalMin(value = "500000", inclusive = false, message = "Đơn giá phải lớn hơn 500.000 VND/m².")
    private BigDecimal donGia;

    @NotNull(message = "Vui lòng nhập diện tích.")
    @DecimalMin(value = "20", inclusive = false, message = "Diện tích phải lớn hơn 20 m².")
    private BigDecimal dienTich;

    public String getMaGiaoDich() { return maGiaoDich; }
    public void setMaGiaoDich(String maGiaoDich) { this.maGiaoDich = maGiaoDich == null ? null : maGiaoDich.trim(); }
    public Long getMaKhachHang() { return maKhachHang; }
    public void setMaKhachHang(Long maKhachHang) { this.maKhachHang = maKhachHang; }
    public LocalDate getNgayGiaoDich() { return ngayGiaoDich; }
    public void setNgayGiaoDich(LocalDate ngayGiaoDich) { this.ngayGiaoDich = ngayGiaoDich; }
    public String getLoaiDichVu() { return loaiDichVu; }
    public void setLoaiDichVu(String loaiDichVu) { this.loaiDichVu = loaiDichVu; }
    public BigDecimal getDonGia() { return donGia; }
    public void setDonGia(BigDecimal donGia) { this.donGia = donGia; }
    public BigDecimal getDienTich() { return dienTich; }
    public void setDienTich(BigDecimal dienTich) { this.dienTich = dienTich; }
}
