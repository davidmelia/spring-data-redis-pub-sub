package com.example.demo;

import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;

public class RedisMessageSubscriber implements MessageListener {

  @Override
  public void onMessage(Message message, byte[] pattern) {
    System.out.println("Recieved action = " + new String(message.getBody()) + " " + " and key info = " + new String(message.getChannel()));

    /*
     * just printing the message here. You can do anything you wish with the received info. For example
     * you can publish it to UI clients using redis pub sub or DeepStream Client.
     */

  }
}
