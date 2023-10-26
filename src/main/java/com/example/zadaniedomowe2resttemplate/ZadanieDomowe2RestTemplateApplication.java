package com.example.zadaniedomowe2resttemplate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication
public class ZadanieDomowe2RestTemplateApplication {

	@Autowired
	MainApplicationRunner mainApplicationRunner;

	public static void main(String[] args) {
		SpringApplication.run(ZadanieDomowe2RestTemplateApplication.class, args);
	}
	@EventListener(ApplicationStartedEvent.class)
	public void run(){
		mainApplicationRunner.start();
	}
}
