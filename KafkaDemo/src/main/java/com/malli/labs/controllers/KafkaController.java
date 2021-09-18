package com.malli.labs.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class KafkaController {
	
	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;
	
	@GetMapping("/msg")
	public String getMsg() {
		return "Hello World!";
	}
	@PostMapping("/publish")
	public String publishMessage(@RequestBody String msg,@RequestParam String topic) {
		kafkaTemplate.send(topic,msg);
		return "Message posted to Kafka successfuly!";
		
	}
	
	@KafkaListener(topics = "Malli", groupId = "chandana")
	public void listenGroupChandanaMalli(String message) {
	    System.out.println("Received Message in group chandana: " + message);
	}
	
	@KafkaListener(topics = "Padma", groupId = "chandana")
	public void listenGroupChandanaPadma(String message) {
	    System.out.println("Received Message in group chandana: " + message);
	}
	@KafkaListener(topics = "Yoga", groupId = "chandana")
	public void listenGroupChandanaYoga(String message) {
	    System.out.println("Received Message in group chandana: " + message);
	}
	@KafkaListener(topics = "Dhanvi", groupId = "chandana")
	public void listenGroupChandanaDhanvi(String message) {
	    System.out.println("Received Message in group chandana: " + message);
	}
	@KafkaListener(topics = "Nithya", groupId = "chandana")
	public void listenGroupChandanaNithya(String message) {
	    System.out.println("Received Message in group chandana: " + message);
	}

}
