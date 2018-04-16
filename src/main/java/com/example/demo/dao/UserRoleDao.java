package com.example.demo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.entity.Role;

@Mapper
public interface UserRoleDao {

	List<Role> findByUserName(String userName);
}
