package com.demo.spring_demo.service;

import java.time.LocalDateTime;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class CachedUserService {

    //When controller uses this service to get user by id, 
    // the result will be cached.
    //The timestamp will only update when the cache is refreshed.
    //Method is not invoked again, data is returned from cache.
    
    @Cacheable("users")
    public String getUser(int id) {
        return "User " + id + " loaded at " + LocalDateTime.now();
    }
    
}