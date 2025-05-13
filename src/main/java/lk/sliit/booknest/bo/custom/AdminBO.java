package lk.sliit.booknest.bo.custom;

import lk.sliit.booknest.bo.SuperBo;
import lk.sliit.booknest.dto.AdminDto;

import java.util.List;

public interface AdminBO extends SuperBo {
    boolean saveAdmin(AdminDto dto);
    boolean updateAdmin(AdminDto dto);
    boolean deleteAdmin(String id);
    List<AdminDto> getAllAdmin();
    boolean isAdminExist(AdminDto dto);
}
