package com.digest.journalApp.Service;

import com.digest.journalApp.services.EmailService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootTest
public class RedisTest {

    @Autowired
    public RedisTemplate redisTemplate;

    @Test
    void testMail()
    {
        redisTemplate.opsForValue().set("name","Ojas");
        Object name = redisTemplate.opsForValue().get("name");
        System.out.println(name);
        Object salary = redisTemplate.opsForValue().get("salary");
        System.out.println(salary);
    }



}
