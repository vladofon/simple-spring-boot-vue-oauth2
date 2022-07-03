package com.annoying.auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.annoying.auth.domain.User;

public interface UserDetailsRepo extends JpaRepository<User, String> {

}
