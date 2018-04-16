package com.example.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.UserRoleDao;
import com.example.demo.entity.Role;
import com.example.demo.service.UserRoleService;

@Service
public class UserRoleServiceImpl implements UserRoleService{
	
	@Autowired
	private UserRoleDao userRoleDao;
	
	@Override
	public List<Role> findByUserName(String userName) {
		return userRoleDao.findByUserName(userName);
	}

}
