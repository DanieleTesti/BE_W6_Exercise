package com.Security_JUnit.auth.configuration;

import java.util.Locale;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;

import com.Security_JUnit.auth.entity.User;
import com.github.javafaker.Faker;

public class UserConfiguration {

	@Bean
	@Scope("prototype")
	public User fakeUser() {
		Faker fake = Faker.instance(new Locale("it-IT"));
		User u = new User();
		u.setName(fake.name().firstName());
		u.setUsername(fake.name().lastName());
		u.setEmail(u.getName() + "." + u.getUsername() + "@example.com");
		u.setPassword(fake.internet().password(6, 10, true, true));
		System.out.println("Utente creato");
		return u;

	}
}
