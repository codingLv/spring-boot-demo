package com.example.demo.dao;


import org.apache.ibatis.annotations.Mapper;
import com.example.demo.entity.User;
@Mapper
public interface UserDao {

//	User getUserByUserId(String userId);//根据用户ID查询用户信息@Param("userId")
	User findByUserName(String userName);
}
