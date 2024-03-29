package com.git.helloproducer3;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HelloProducerService3 {

	private RabbitTemplate rabbit;

	@Autowired
	public HelloProducerService3(RabbitTemplate rabbit) {
		this.rabbit = rabbit;
	}

	public void sendMessage(byte[] message) {
		System.out.println(message);
		// 1번 - 보내는 쪽은 본인 번호 이외에 큐에 보냄
		rabbit.send("test.hello.2", new Message(message));
		rabbit.send("test.hello.1", new Message(message));
	}
}