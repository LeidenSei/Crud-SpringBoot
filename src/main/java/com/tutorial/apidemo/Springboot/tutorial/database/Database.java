package com.tutorial.apidemo.Springboot.tutorial.database;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.tutorial.apidemo.Springboot.tutorial.models.Product;
import com.tutorial.apidemo.Springboot.tutorial.repositories.ProductRepository;

@Configuration
public class Database {
	private static final Logger logger = LoggerFactory.getLogger(Database.class);
	@Bean
	CommandLineRunner initDatabse(ProductRepository productRepository) {
		
		return new CommandLineRunner() {
			
			@Override
			public void run(String... args) throws Exception {
				Product productA = new Product("Macbook",2023,2400.2,"");
				Product productB = new Product("ipad",2023,2400.2,"");
				logger.info("insert data: "+ productRepository.save(productA));
				logger.info("insert data: "+ productRepository.save(productB));
			}
		};		
	}
}
