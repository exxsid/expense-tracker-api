package com.lacortez.expensetrackerapi.repository;

import com.lacortez.expensetrackerapi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
