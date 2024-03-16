package com.chiru.springsecurity.service;

import com.chiru.springsecurity.models.AdminEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Chiranjeevi
 */
@Repository
public interface AdminService extends JpaRepository<AdminEntity,Long> {
    AdminEntity findByUserName(String userName);
}
