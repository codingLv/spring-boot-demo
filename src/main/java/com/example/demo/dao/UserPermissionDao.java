package com.example.demo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.entity.Permission;

@Mapper
public interface UserPermissionDao {

	List<Permission> findByUserName(String userName);
}
