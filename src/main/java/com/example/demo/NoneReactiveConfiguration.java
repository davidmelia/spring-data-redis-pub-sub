package com.example.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;

@Configuration
public class NoneReactiveConfiguration {

  @Bean
  MessageListenerAdapter messageListener() {
    return new MessageListenerAdapter(
        (MessageListener) (message, pattern) -> System.out.println("Recieved action = " + new String(message.getBody()) + " " + " and key info = " + new String(message.getChannel())));
  }

  @Bean
  RedisMessageListenerContainer redisContainer(LettuceConnectionFactory lettuceConnectionFactory) {
    final RedisMessageListenerContainer container = new RedisMessageListenerContainer();
    container.setConnectionFactory(lettuceConnectionFactory);
    container.addMessageListener(messageListener(), new PatternTopic("*"));
    return container;
  }

}
