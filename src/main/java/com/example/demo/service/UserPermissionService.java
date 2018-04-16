package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entity.Permission;

public interface UserPermissionService {

	public List<Permission> findByUserName(String userName);
}
