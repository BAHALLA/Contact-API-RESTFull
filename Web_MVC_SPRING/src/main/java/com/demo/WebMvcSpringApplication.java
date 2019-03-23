package com.demo;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.demo.dao.ContactRepository;
import com.demo.entities.Contact;

@SpringBootApplication
public class WebMvcSpringApplication implements CommandLineRunner{

	@Autowired
	private ContactRepository contactRepository;
	
	public static void main(String[] args) {
	 SpringApplication.run(WebMvcSpringApplication.class, args);

	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		
		DateFormat sf=new SimpleDateFormat("dd/MM/yyyy");
		contactRepository.save(new Contact("bahalla", "taoufiq", sf.parse("10/10/1997"), "bahalla@gmail.com", 610799605L, "me.jpeg"));
		contactRepository.save(new Contact("abouri", "shay", sf.parse("12/09/1998"), "shay@gmail.com", 642799605L, "you.jpeg"));
		
		contactRepository.findAll().forEach(c -> {
			System.out.println(c.getNom());
		});
	}

}
