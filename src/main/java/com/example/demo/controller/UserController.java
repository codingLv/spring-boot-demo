package com.example.demo.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.entity.User;
import com.example.demo.service.UserService;


@RestController
@RequestMapping(value = "/user")  
public class UserController {

	/*@Autowired
    private UserService userService; 
	
	
	@RequestMapping(value = "/get", method = RequestMethod.GET)
    public User getUser(HttpServletRequest request, HttpServletResponse response) {  
        String id = request.getParameter("id"); 
        System.out.println("id::::"+id);
        return userService.getUser(id); 
    }  */
	
/*	@RequestMapping("/getuserlist")
	@ResponseBody
	@GetCache()
	public ModelAndView getUserList(String id){
//		String userid=req.getParameter("id");
		User user=userService.getUser(id); 
		jedisClient.setPrefix("user");
		jedisClient.set(id, user);
		logger.fatal("user:"+user);
		ModelAndView mv = new ModelAndView();
		mv.getModel().put("user", user);
		mv.setViewName("userlist");
		System.out.println((User)jedisClient.get(id));
        return mv;  
	}*/
 
	@RequiresPermissions("user:user")
	@RequestMapping("list")
	public ModelAndView userList() {
		ModelAndView model=new ModelAndView();
		model.setViewName("user");
		model.addObject("value", "获取用户信息");
		return model;
	}
	
	@RequiresPermissions("user:add")
	@RequestMapping("add")
	public ModelAndView userAdd() {
		ModelAndView model=new ModelAndView();
		model.setViewName("user");
		model.addObject("value", "新增用户");
		return model;
	}
	
	@RequiresPermissions("user:delete")
	@RequestMapping("delete")
	public ModelAndView userDelete() {
		ModelAndView model=new ModelAndView();
		model.setViewName("user");
		model.addObject("value", "删除用户");
		return model;
	}
}

