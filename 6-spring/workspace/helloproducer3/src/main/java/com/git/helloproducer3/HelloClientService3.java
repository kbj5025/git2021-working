package com.git.helloproducer3;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

//���� ������Ʈ
//1. �ܺ� �ý��� ���
//2. ������ Ʈ����� ó��
@Service
public class HelloClientService3 {

	@RabbitListener(queues = "test.hello.3")
	public void receiveMessage(String message) {
		System.out.println("-- test.hello.3 --");
		System.out.println(message);
	}
}