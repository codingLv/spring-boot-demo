package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entity.Role;

public interface UserRoleService {

	public List<Role> findByUserName(String userName);
}
