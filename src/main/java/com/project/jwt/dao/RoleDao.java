package com.project.jwt.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.project.jwt.entity.Role;

@Repository
public interface RoleDao extends CrudRepository<Role, String> {

}
