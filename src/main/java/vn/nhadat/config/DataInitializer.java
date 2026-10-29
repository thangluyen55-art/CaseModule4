package vn.nhadat.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import vn.nhadat.entity.KhachHang;
import vn.nhadat.repository.KhachHangRepository;

@Configuration
public class DataInitializer {
    @Bean
    CommandLineRunner taoKhachHangMau(KhachHangRepository repository) {
        return args -> {
            if (repository.count() == 0) {
                repository.save(new KhachHang("Nguyễn Minh Anh", "0901234567", "minhanh@example.com"));
                repository.save(new KhachHang("Trần Quốc Bảo", "0912345678", "quocbao@example.com"));
                repository.save(new KhachHang("Lê Thu Hà", "0987654321", "thuha@example.com"));
            }
        };
    }
}
