package com.git.helloproducer3;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloProducerController3 {

	private HelloProducerService3 service;

	@Autowired
	public HelloProducerController3(HelloProducerService3 service) {
		this.service = service;
	}

	@PostMapping(value = "/send-message")
	public boolean sendMessage(@RequestBody String message) {
		System.out.println(message);
		service.sendMessage(message.getBytes());
		return true;
	}
}