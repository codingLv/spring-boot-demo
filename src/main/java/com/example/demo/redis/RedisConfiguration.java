package com.example.demo.redis;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.CacheErrorHandler;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import redis.clients.jedis.JedisPoolConfig;

/**
 * 如果没有这个初始化配置类，则全部都为默认的参数信息。
 * Created by XJH on 2017/9/9.
 */
@Configuration
@EnableCaching
public class RedisConfiguration extends CachingConfigurerSupport {


    @Value("${spring.redis.database:0}")
    private Integer database;

    @Value("${spring.redis.host:127.0.0.1}")
    private String host;

    @Value("${spring.redis.port:6379}")
    private Integer port;

    @Value("${spring.redis.password}")
    private String password;

    @Value("${spring.redis.pool.max-wait:-1}")
    private Integer maxWait;

    @Value("${spring.redis.pool.min-idle:0}")
    private Integer minIdle;

    @Value("${spring.redis.timeout:0}")
    private Integer timeout;


 	// 缓存管理器
 	@Bean
 	public CacheManager cacheManager(@SuppressWarnings("rawtypes") RedisTemplate redisTemplate) {
 		RedisCacheManager cacheManager = new RedisCacheManager(redisTemplate);
 		// 设置缓存过期时间
 		cacheManager.setDefaultExpiration(10000);
 		return cacheManager;
 	}

   @Bean
    public JedisPoolConfig jedisPoolConfig() {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(105);
        jedisPoolConfig.setMaxWaitMillis(5000);
        jedisPoolConfig.setMaxWaitMillis(maxWait);
        jedisPoolConfig.setMinIdle(minIdle);
        jedisPoolConfig.setTestOnBorrow(true);
        return jedisPoolConfig;
    }

    @Bean
    public JedisConnectionFactory jedisConnectionFactory() {
    	Logger.getLogger(getClass()).info("JedisPool注入成功！！");
		Logger.getLogger(getClass()).info("redis地址：" + host + ":" + port);
        JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory();
        jedisConnectionFactory.setHostName("127.0.0.1");
        jedisConnectionFactory.setPort(6379);
        jedisConnectionFactory.setPassword(password);
        jedisConnectionFactory.setDatabase(database);
        jedisConnectionFactory.setTimeout(timeout);
        jedisConnectionFactory.setPoolConfig(jedisPoolConfig());
        return jedisConnectionFactory;
    }

/*    @Bean("redisTemplate")
    public RedisTemplate<String, String> StringRedisTemplate(JedisConnectionFactory jedisConnectionFactory) {
//        StringRedisTemplate stringRedisTemplate = new StringRedisTemplate();
//        stringRedisTemplate.setConnectionFactory(jedisConnectionFactory);
//        return stringRedisTemplate;

        StringRedisTemplate template = new StringRedisTemplate(jedisConnectionFactory);
        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        jackson2JsonRedisSerializer.setObjectMapper(om);
        template.setValueSerializer(jackson2JsonRedisSerializer);
        template.afterPropertiesSet();
        return template;
    }*/
    
    /**
     * redisTemplate 默认使用JDK的序列化机制, 存储二进制字节码, 所以自定义序列化类
     * @param redisConnectionFactory redis连接工厂类
     * @return RedisTemplate
     */
    @Bean
    public RedisTemplate<Object, Object> redisTemplate(RedisConnectionFactory factory) {
        RedisTemplate<Object, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(factory);

        // 使用Jackson2JsonRedisSerialize 替换默认序列化
        @SuppressWarnings({ "rawtypes", "unchecked" })
        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        objectMapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);

        jackson2JsonRedisSerializer.setObjectMapper(objectMapper);

        // 设置value的序列化规则和 key的序列化规则
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(jackson2JsonRedisSerializer);
        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }

    
    /**
     * 自定义缓存key的生成类实现
     *//*
 	@Bean
 	public KeyGenerator keyGenerator() {
 		return new KeyGenerator() {
 			@Override
 			public Object generate(Object target, java.lang.reflect.Method method, Object... params) {
 				StringBuffer sb = new StringBuffer();
 				sb.append(target.getClass().getName());
 				sb.append(method.getName());
 				for (Object obj : params) {
 					sb.append(obj.toString());
 				}
 				return sb.toString();
 			}
 		};
 	}*/
    
 	 @Bean  
     @Override  
     public CacheErrorHandler errorHandler() {  
         CacheErrorHandler cacheErrorHandler = new CacheErrorHandler() {  
             @Override  
             public void handleCacheGetError(RuntimeException e, Cache cache, Object key) {  
                 System.out.println(key);  
             }  
   
             @Override  
             public void handleCachePutError(RuntimeException e, Cache cache, Object key, Object value) {  
                 System.out.println(value);  
             }  
   
             @Override  
             public void handleCacheEvictError(RuntimeException e, Cache cache, Object key) {  
   
             }  
   
             @Override  
             public void handleCacheClearError(RuntimeException e, Cache cache) {  
   
             }  
         };  
         return cacheErrorHandler;  
     }  
}
