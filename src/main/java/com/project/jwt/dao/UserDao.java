package com.project.jwt.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.project.jwt.entity.User;

@Repository
public interface UserDao extends CrudRepository<User, String> {
}
