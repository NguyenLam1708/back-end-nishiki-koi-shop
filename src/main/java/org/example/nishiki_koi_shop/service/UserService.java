package org.example.nishiki_koi_shop.service;

import org.example.nishiki_koi_shop.model.dto.UserDto;
import org.example.nishiki_koi_shop.model.payload.ChangePasswordForm;
import org.example.nishiki_koi_shop.model.payload.UserForm;

import java.security.Principal;
import java.util.List;

public interface UserService {
    void softDeleteUser(Long userId, Long loggedInUserId);
    void restoreUser(Long userId);
    UserDto getMyInfo();
    UserDto getUserById(Long id);
    List<UserDto> getAllUsers();
    UserForm updateUser(Long id, UserForm form);
    String changePassword(ChangePasswordForm request, Principal connectedUser);
}
