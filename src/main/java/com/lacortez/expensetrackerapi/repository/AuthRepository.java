package com.lacortez.expensetrackerapi.repository;

import com.lacortez.expensetrackerapi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthRepository extends JpaRepository<User, Long> {

    List<User> findByUserName(String userName);

}
