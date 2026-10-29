package vn.nhadat.repository;

import vn.nhadat.entity.GiaoDich;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface GiaoDichRepository extends JpaRepository<GiaoDich, String> {
    boolean existsByMaGiaoDich(String maGiaoDich);

    @EntityGraph(attributePaths = "khachHang")
    @Query("""
        SELECT gd FROM GiaoDich gd
        JOIN gd.khachHang kh
        WHERE (:tenKhachHang IS NULL OR LOWER(kh.tenKhachHang) LIKE LOWER(CONCAT('%', :tenKhachHang, '%')))
          AND (:loaiDichVu IS NULL OR gd.loaiDichVu = :loaiDichVu)
        ORDER BY gd.ngayGiaoDich DESC, gd.maGiaoDich ASC
        """)
    List<GiaoDich> timKiem(@Param("tenKhachHang") String tenKhachHang,
                           @Param("loaiDichVu") String loaiDichVu);
}
