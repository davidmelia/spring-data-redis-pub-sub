package com.example.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.ReactiveSubscription.PatternMessage;
import org.springframework.data.redis.core.ReactiveStringRedisTemplate;
import org.springframework.data.redis.listener.PatternTopic;

@Configuration
public class ReactiveConfiguration {

  public class ReactiveRedisKeyLogger {

    public ReactiveRedisKeyLogger(ReactiveStringRedisTemplate reactiveStringRedisTemplate) {
      reactiveStringRedisTemplate.listenTo(new PatternTopic("*")).cast(PatternMessage.class).map(m -> {
        System.out.println("Recieved (reactive) " + m);
        return m;
      }).subscribe();
    }
  }

  @Bean
  public ReactiveRedisKeyLogger reactiveRedisKeyLogger(ReactiveStringRedisTemplate reactiveStringRedisTemplate) {
    return new ReactiveRedisKeyLogger(reactiveStringRedisTemplate);
  }

}
