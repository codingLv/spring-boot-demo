package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Student;

@RestController
@RequestMapping("/add")
public class RedisOperationController {

	@Autowired  
	private RedisTemplate<Object, Object> RedisTemplate;  
	  
	@RequestMapping("/entity") 
	public Object putSpringRedisTemplemet(){  
	    ValueOperations<Object, Object> valueOperations = RedisTemplate.opsForValue();  
	    StringRedisSerializer serializer = new StringRedisSerializer();  
	    @SuppressWarnings({ "rawtypes", "unchecked" })
//	    Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
//	    RedisTemplate.setKeySerializer(serializer);  
//	    RedisTemplate.setValueSerializer(jackson2JsonRedisSerializer);  
	    Student personDomain = new Student();  
	    personDomain.setSno("20180415"); 
	    personDomain.setSex("女");
	    personDomain.setName("你大爷的");
	    valueOperations.set("20180415",personDomain);  
	    return valueOperations.get("20180415");
	}  
}
