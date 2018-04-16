package com.example.demo.controller;

import javax.json.JsonObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.service.StudentService;

@Controller
@RequestMapping("redis")
public class RediaTestController {

	@Autowired
	private StudentService studentService;
	
	@RequestMapping("/test")
	@ResponseBody
	@Cacheable(value="user-key")
	public Object getStudent(String sno) {
		return studentService.queryStudentBySno(sno);
	}
}
