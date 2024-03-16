package com.chiru.springsecurity.service;

import com.chiru.springsecurity.models.AdminEntity;
import com.chiru.springsecurity.models.UserEntity;
import com.chiru.springsecurity.models.UserType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

/**
 * @author Chiranjeevi
 */
@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private AdminService adminService;
    @Autowired
    private UserService userService;
    private UserType userType;
    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if(userType == UserType.ADMIN){
            AdminEntity adminEntity = adminService.findByUserName(username);
            SimpleGrantedAuthority adminAuthority = new SimpleGrantedAuthority(UserType.ADMIN.toString());
            Collection<GrantedAuthority> authorities = new ArrayList<>();
            authorities.add(adminAuthority);
            return new User(adminEntity.getUserName(), adminEntity.getPassword(), authorities);
        } else if (userType == UserType.USER) {
            UserEntity userEntity = userService.findByUserName(username);
            SimpleGrantedAuthority adminAuthority = new SimpleGrantedAuthority(UserType.USER.toString());
            Collection<GrantedAuthority> authorities = new ArrayList<>();
            authorities.add(adminAuthority);
            return new User(userEntity.getUserName(), userEntity.getPassword(), authorities);
        }
        return null;
    }
}
