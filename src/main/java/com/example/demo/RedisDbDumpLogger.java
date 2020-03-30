package com.example.demo;

import org.springframework.data.redis.core.ReactiveStringRedisTemplate;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.stereotype.Component;

@Component
public class RedisDbDumpLogger {

  public RedisDbDumpLogger(ReactiveStringRedisTemplate reactiveStringRedisTemplate) {
    reactiveStringRedisTemplate.scan(ScanOptions.scanOptions().match("*").build()).map(v -> {
      System.out.println("Found the following redis entry: " + v);
      return v;
    }).subscribe();
  }

}
