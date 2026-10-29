package vn.nhadat.repository;

import vn.nhadat.entity.KhachHang;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface KhachHangRepository extends JpaRepository<KhachHang, Long> {
    List<KhachHang> findAllByOrderByTenKhachHangAsc();
}
