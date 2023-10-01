package com.humanbooster.Newbiz;

import com.humanbooster.Newbiz.services.StorageService;
import jakarta.annotation.Resource;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class NewbizApplication implements CommandLineRunner {

	@Resource
	StorageService storageService;

	public static void main(String[] args) {
		SpringApplication.run(NewbizApplication.class, args);
	}


	@Override
	public void run(String...arg) throws Exception{
		storageService.init();
	}
}
