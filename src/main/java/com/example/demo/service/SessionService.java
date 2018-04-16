package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.UserOnline;

public interface SessionService {
	
	List<UserOnline> list();
	boolean forceLogout(String sessionId);
}
