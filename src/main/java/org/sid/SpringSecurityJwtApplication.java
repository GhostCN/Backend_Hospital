package org.sid;

import java.util.Date;

import org.sid.models.Poste;
import org.sid.models.Role;
import org.sid.models.Service;
import org.sid.models.Utilisateur;
import org.sid.service.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@SpringBootApplication
public class SpringSecurityJwtApplication implements CommandLineRunner{
	@Autowired
	private Account account;
	public static void main(String[] args) {
		SpringApplication.run(SpringSecurityJwtApplication.class, args);
	}
	@Bean
	public BCryptPasswordEncoder getBcryPassEncoder()
	{
		return new BCryptPasswordEncoder();
	}
	@Override
	public void run(String... args) throws Exception {
	/*	// TODO Auto-generated method stub
		account.saveRole(new Role("ADMIN"));
		account.saveUser(
				new Utilisateur("sene", "ghost", "789871234", new Date(), "so", "konate", true, "ghost@gmail.com"));

		account.addRoleToUser("so", "ADMIN");
		*/
	}
	
}
