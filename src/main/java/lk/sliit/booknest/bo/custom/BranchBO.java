package lk.sliit.booknest.bo.custom;

import lk.sliit.booknest.bo.SuperBo;
import lk.sliit.booknest.dto.BranchDto;

import java.util.List;

public interface BranchBO extends SuperBo {
    List<BranchDto> getAllBranch();
    boolean saveBranch(BranchDto dto);
    boolean updateBranch(BranchDto dto);
    boolean deleteBranch(String id);
    BranchDto searchBranch(String id);
}
