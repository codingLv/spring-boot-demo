package com.example.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.UserPermissionDao;
import com.example.demo.entity.Permission;
import com.example.demo.service.UserPermissionService;

@Service
public class UserPermissionServiceImpl implements UserPermissionService {

	@Autowired
	private UserPermissionDao userPermissionDao;
	
	@Override
	public List<Permission> findByUserName(String userName) {
		return userPermissionDao.findByUserName(userName);
	}

}
