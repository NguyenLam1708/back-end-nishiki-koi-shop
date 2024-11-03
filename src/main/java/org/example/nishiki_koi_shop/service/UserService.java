package org.example.nishiki_koi_shop.service;

import org.example.nishiki_koi_shop.model.dto.UserDto;
import org.example.nishiki_koi_shop.model.payload.ChangePasswordForm;
import org.example.nishiki_koi_shop.model.payload.UserForm;

import java.security.Principal;
import java.util.List;

public interface UserService {
    void softDeleteUser(Long id, Principal principal);

    void restoreUser(Long userId);

    UserDto getMyInfo();

    UserDto getUserById(long id);

    List<UserDto> getAllUsers();

    UserDto updateUser(Long id, UserForm form);

    String changePassword(ChangePasswordForm request, Principal connectedUser);

    UserDto updateMyInfo(long id, Principal principal, UserForm form);
}
