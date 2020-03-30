package com.example.demo;

import javax.annotation.PostConstruct;
import org.springframework.data.redis.connection.ReactiveSubscription.PatternMessage;
import org.springframework.data.redis.core.ReactiveStringRedisTemplate;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.stereotype.Component;

@Component
public class RedisKeyLogger {
  private final ReactiveStringRedisTemplate reactiveStringRedisTemplate;

  public RedisKeyLogger(ReactiveStringRedisTemplate reactiveStringRedisTemplate) {
    super();
    this.reactiveStringRedisTemplate = reactiveStringRedisTemplate;
  }

  @PostConstruct
  public void post() {
    reactiveStringRedisTemplate.listenTo(new PatternTopic("*"), new PatternTopic("*"), new PatternTopic("*")).cast(PatternMessage.class).map(m -> {
      System.out.println("MELIA*** {}" + m);
      return m;
    }).subscribe();
  }
}
