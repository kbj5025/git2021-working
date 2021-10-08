package com.git.helloclient3;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class HelloClientService {

	@RabbitListener(queues = "test.hello.3")
	public void receiveMessage(String message) {
		System.out.println("-- test.hello.3 --");
		System.out.println(message);
	}
}