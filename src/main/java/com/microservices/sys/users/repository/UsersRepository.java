package com.microservices.sys.users.repository;

import java.util.Optional;

import com.microservices.sys.users.models.Users;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends CrudRepository<Users, Long> {
    
    Optional<Users> findById(Long id);
}