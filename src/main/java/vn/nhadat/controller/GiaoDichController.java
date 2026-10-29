package vn.nhadat.controller;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import vn.nhadat.dto.GiaoDichForm;
import vn.nhadat.entity.GiaoDich;
import vn.nhadat.repository.KhachHangRepository;
import vn.nhadat.service.GiaoDichService;

import java.time.LocalDate;

@Controller
@RequestMapping("/giao-dich")
public class GiaoDichController {
    private final GiaoDichService giaoDichService;
    private final KhachHangRepository khachHangRepository;

    public GiaoDichController(GiaoDichService giaoDichService, KhachHangRepository khachHangRepository) {
        this.giaoDichService = giaoDichService;
        this.khachHangRepository = khachHangRepository;
    }

    @GetMapping({"", "/"})
    public String danhSach(@RequestParam(required = false) String tenKhachHang,
                           @RequestParam(required = false) String loaiDichVu,
                           Model model) {
        model.addAttribute("giaoDiches", giaoDichService.timKiem(tenKhachHang, loaiDichVu));
        model.addAttribute("tenKhachHang", tenKhachHang == null ? "" : tenKhachHang);
        model.addAttribute("loaiDichVu", loaiDichVu == null ? "" : loaiDichVu);
        return "giao-dich/danh-sach";
    }

    @GetMapping("/them")
    public String hienThiForm(Model model) {
        model.addAttribute("form", new GiaoDichForm());
        themDuLieuForm(model);
        return "giao-dich/form";
    }

    @PostMapping("/them")
    public String themMoi(@Valid @ModelAttribute("form") GiaoDichForm form,
                          BindingResult bindingResult,
                          Model model,
                          RedirectAttributes redirectAttributes) {
        if (!"Đất".equals(form.getLoaiDichVu()) && !"Nhà và đất".equals(form.getLoaiDichVu())) {
            bindingResult.rejectValue("loaiDichVu", "invalid", "Loại dịch vụ không hợp lệ.");
        }
        if (form.getMaGiaoDich() != null && !form.getMaGiaoDich().isBlank()
                && giaoDichService.daTonTai(form.getMaGiaoDich())) {
            bindingResult.rejectValue("maGiaoDich", "duplicate", "Mã giao dịch đã tồn tại.");
        }
        if (bindingResult.hasErrors()) {
            themDuLieuForm(model);
            return "giao-dich/form";
        }
        try {
            giaoDichService.themMoi(form);
            redirectAttributes.addFlashAttribute("thongBao", "Thêm giao dịch thành công!");
            redirectAttributes.addFlashAttribute("loaiThongBao", "success");
            return "redirect:/giao-dich";
        } catch (GiaoDichService.DuplicateTransactionException e) {
            bindingResult.rejectValue("maGiaoDich", "duplicate", e.getMessage());
            themDuLieuForm(model);
            return "giao-dich/form";
        }
    }

    @GetMapping("/{maGiaoDich}")
    public String chiTiet(@PathVariable String maGiaoDich, Model model, RedirectAttributes redirectAttributes) {
        try {
            GiaoDich giaoDich = giaoDichService.timTheoMa(maGiaoDich);
            model.addAttribute("giaoDich", giaoDich);
            return "giao-dich/chi-tiet";
        } catch (IllegalArgumentException e) {
            redirectAttributes.addFlashAttribute("thongBao", e.getMessage());
            redirectAttributes.addFlashAttribute("loaiThongBao", "error");
            return "redirect:/giao-dich";
        }
    }

    @PostMapping("/{maGiaoDich}/xoa")
    public String xoa(@PathVariable String maGiaoDich, RedirectAttributes redirectAttributes) {
        try {
            giaoDichService.xoa(maGiaoDich);
            redirectAttributes.addFlashAttribute("thongBao", "Đã xóa giao dịch " + maGiaoDich + ".");
            redirectAttributes.addFlashAttribute("loaiThongBao", "success");
        } catch (IllegalArgumentException e) {
            redirectAttributes.addFlashAttribute("thongBao", e.getMessage());
            redirectAttributes.addFlashAttribute("loaiThongBao", "error");
        }
        return "redirect:/giao-dich";
    }

    private void themDuLieuForm(Model model) {
        model.addAttribute("khachHangs", khachHangRepository.findAllByOrderByTenKhachHangAsc());
        model.addAttribute("ngayToiThieu", LocalDate.now().plusDays(1));
    }
}
