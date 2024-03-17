package com.chiru.springsecurity.repository;

import com.chiru.springsecurity.models.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author Chiranjeevi
 */
@Repository
public interface UserRepo extends JpaRepository<UserEntity,Long> {
    Optional<UserEntity> findByUserName(String userName);
    boolean existsByUserName(String userName);
}
