package com.gusrylmubarok.sinaukoding.hris;

import com.gusrylmubarok.sinaukoding.hris.entity.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.core.context.SecurityContextHolder;

@SpringBootApplication
public class HrisApplication {

	public static void main(String[] args) {
		SpringApplication.run(HrisApplication.class, args);
	}

	public static User getCurrentUser() {
		try {
			Object principle = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

			if (principle != null && principle.getClass().equals(User.class)) {
				return (User) principle;
			}
		}catch(Exception ignore) {

		}

		return null;
	}

}
