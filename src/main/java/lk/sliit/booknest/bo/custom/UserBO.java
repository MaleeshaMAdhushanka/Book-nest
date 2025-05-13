package lk.sliit.booknest.bo.custom;

import lk.sliit.booknest.bo.SuperBo;
import lk.sliit.booknest.dto.UserDto;

import java.util.List;

public interface UserBO extends SuperBo {
    List<UserDto> getAllUsers();
    boolean saveUser(UserDto dto);
    boolean updateUser(UserDto dto);
    boolean updateUserAndImg(UserDto dto);
    boolean deleteUser(String id);
    UserDto searchUser(String id);
    boolean isUserExist(UserDto userDto);
    List<UserDto> getUsersWithOverdueBooks();
}
