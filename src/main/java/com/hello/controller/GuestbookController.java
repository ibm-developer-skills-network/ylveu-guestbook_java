package com.hello.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.net.InetAddress;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class GuestbookController {

    private static final String GUESTBOOK_KEY = "guestbook";
    
    @Autowired
    private StringRedisTemplate redisTemplate;
    
    @Autowired
    private Environment environment;

    @GetMapping("/lrange/{key}")
    public List<String> getList(@PathVariable String key) {
        return redisTemplate.opsForList().range(key, 0, -1);
    }

    @GetMapping("/rpush/{key}/{value}")
    public List<String> addToList(@PathVariable String key, @PathVariable String value) {
        redisTemplate.opsForList().rightPush(key, value);
        return redisTemplate.opsForList().range(key, 0, -1);
    }
    
    @GetMapping("/info")
    public String getInfo() {
        try {
            if (redisTemplate.getConnectionFactory() != null && 
                redisTemplate.getConnectionFactory().getConnection() != null) {
                return "Redis connection active";
            }
        } catch (Exception e) {
            return "Error connecting to Redis: " + e.getMessage();
        }
        return "In-memory datastore (not redis)";
    }
    
    @GetMapping("/env")
    public ResponseEntity<Map<String, String>> getEnvironment() {
        Map<String, String> env = new HashMap<>();
        System.getenv().forEach(env::put);
        return ResponseEntity.ok(env);
    }
    
    @GetMapping("/hello")
    public String hello() {
        try {
            String hostname = InetAddress.getLocalHost().getHostName();
            return "Hello from guestbook. Your app is up! (Hostname: " + hostname + ")";
        } catch (Exception e) {
            return "Hello from guestbook. Your app is up! (Hostname unknown)";
        }
    }
}