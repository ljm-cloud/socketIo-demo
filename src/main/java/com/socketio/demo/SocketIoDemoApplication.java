package com.socketio.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@ComponentScan({"com.socketio.demo"})
public class SocketIoDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SocketIoDemoApplication.class, args);
	}

}
