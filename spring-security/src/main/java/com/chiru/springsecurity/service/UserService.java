package com.chiru.springsecurity.service;

import com.chiru.springsecurity.models.UserAuthDTO;
import com.chiru.springsecurity.models.UserEntity;
import com.chiru.springsecurity.models.UserLoginResponseDTO;
import org.apache.coyote.BadRequestException;

/**
 * @author Chiranjeevi
 */
public interface UserService{
    UserEntity findById(Long id);
    UserEntity findByUserName(String userName);
    boolean existsByUserName(String userName);
    String regUser(UserAuthDTO userAuthDTO) throws BadRequestException;
    UserLoginResponseDTO loginUser(UserAuthDTO userAuthDTO);

}
