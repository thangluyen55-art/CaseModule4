package vn.nhadat.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.nhadat.dto.GiaoDichForm;
import vn.nhadat.entity.GiaoDich;
import vn.nhadat.entity.KhachHang;
import vn.nhadat.repository.GiaoDichRepository;
import vn.nhadat.repository.KhachHangRepository;

import java.util.List;

@Service
public class GiaoDichService {
    private final GiaoDichRepository giaoDichRepository;
    private final KhachHangRepository khachHangRepository;

    public GiaoDichService(GiaoDichRepository giaoDichRepository, KhachHangRepository khachHangRepository) {
        this.giaoDichRepository = giaoDichRepository;
        this.khachHangRepository = khachHangRepository;
    }

    @Transactional(readOnly = true)
    public List<GiaoDich> timKiem(String tenKhachHang, String loaiDichVu) {
        String ten = (tenKhachHang == null || tenKhachHang.isBlank()) ? null : tenKhachHang.trim();
        String loai = (loaiDichVu == null || loaiDichVu.isBlank()) ? null : loaiDichVu.trim();
        return giaoDichRepository.timKiem(ten, loai);
    }

    @Transactional(readOnly = true)
    public boolean daTonTai(String maGiaoDich) {
        return giaoDichRepository.existsByMaGiaoDich(maGiaoDich);
    }

    @Transactional(readOnly = true)
    public GiaoDich timTheoMa(String maGiaoDich) {
        return giaoDichRepository.findById(maGiaoDich)
                .orElseThrow(() -> new IllegalArgumentException("Không tìm thấy giao dịch " + maGiaoDich));
    }

    @Transactional
    public void themMoi(GiaoDichForm form) {
        if (giaoDichRepository.existsByMaGiaoDich(form.getMaGiaoDich())) {
            throw new DuplicateTransactionException("Mã giao dịch đã tồn tại. Vui lòng nhập mã khác.");
        }
        KhachHang khachHang = khachHangRepository.findById(form.getMaKhachHang())
                .orElseThrow(() -> new IllegalArgumentException("Khách hàng đã chọn không tồn tại."));

        GiaoDich giaoDich = new GiaoDich();
        giaoDich.setMaGiaoDich(form.getMaGiaoDich());
        giaoDich.setKhachHang(khachHang);
        giaoDich.setNgayGiaoDich(form.getNgayGiaoDich());
        giaoDich.setLoaiDichVu(form.getLoaiDichVu());
        giaoDich.setDonGia(form.getDonGia());
        giaoDich.setDienTich(form.getDienTich());
        giaoDichRepository.save(giaoDich);
    }

    @Transactional
    public void xoa(String maGiaoDich) {
        GiaoDich giaoDich = timTheoMa(maGiaoDich);
        giaoDichRepository.delete(giaoDich);
    }

    public static class DuplicateTransactionException extends RuntimeException {
        public DuplicateTransactionException(String message) { super(message); }
    }
}
