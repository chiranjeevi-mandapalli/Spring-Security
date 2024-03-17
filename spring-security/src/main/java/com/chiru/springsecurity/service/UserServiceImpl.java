package com.chiru.springsecurity.service;

import com.chiru.springsecurity.exceptions.NotFoundException;
import com.chiru.springsecurity.models.UserAuthDTO;
import com.chiru.springsecurity.models.UserEntity;
import com.chiru.springsecurity.models.UserLoginResponseDTO;
import com.chiru.springsecurity.models.UserType;
import com.chiru.springsecurity.jwt.JwtGenerator;
import com.chiru.springsecurity.repository.UserRepo;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @author Chiranjeevi
 */
@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtGenerator jwtGenerator;
    @Override
    public UserEntity findById(Long id){
        return userRepo.findById(id)
                .orElseThrow(()-> new NotFoundException("User not found"));
    }
    @Override
    public UserEntity findByUserName(String userName){
        return userRepo.findByUserName(userName)
                .orElseThrow(()-> new NotFoundException("User not found"));
    }
    @Override
    public boolean existsByUserName(String userName){
        return userRepo.existsByUserName(userName);
    }

    @Override
    public String regUser(UserAuthDTO userAuthDTO) throws BadRequestException {
        if(existsByUserName(userAuthDTO.getUserName())){
            throw new BadRequestException("User is registered");
        }
        UserEntity userEntity = new UserEntity();
        userEntity.setUserName(userAuthDTO.getUserName());
        userEntity.setPassword(passwordEncoder.encode(userAuthDTO.getPassword()));
        userRepo.save(userEntity);
        return "User registration successfull";

    }

    @Override
    public UserLoginResponseDTO loginUser(UserAuthDTO userAuthDTO) {
        String token = jwtGenerator.generateToken(userAuthDTO.getUserName(), UserType.USER.toString());
        UserEntity user = findByUserName(userAuthDTO.getUserName());
        UserLoginResponseDTO responseDTO = new UserLoginResponseDTO();
        responseDTO.setToken(token);
        responseDTO.setUser(user);
        return responseDTO;
    }


}
