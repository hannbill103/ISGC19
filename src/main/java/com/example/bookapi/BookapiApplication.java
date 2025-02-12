package com.example.bookapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//  denna klass är huvudklassen för en spring boot applikation
@SpringBootApplication
public class BookapiApplication {

	// metod som startar Spring boot applikationen
	public static void main(String[] args) {
		SpringApplication.run(BookapiApplication.class, args);
	}
}
