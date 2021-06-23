package com.heraizen.todo;

import com.heraizen.todo.dao.TodoReports;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class TodoServerAppApplication {

	@Autowired
	private TodoReports todoReports;
	public static void main(String[] args) {
		SpringApplication.run(TodoServerAppApplication.class, args);
	}

	@Bean
	public CommandLineRunner runner(){
		return (String... args)->{
			todoReports.getTodosByUsers(1L).forEach(r->{
				System.out.println(r);
			});
		};
	}

}
