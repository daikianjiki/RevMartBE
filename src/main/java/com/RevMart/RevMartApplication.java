package com.RevMart;

import com.RevMart.Models.Product;
import com.RevMart.Repositories.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class RevMartApplication {

	public static void main(String[] args) {
		SpringApplication.run(RevMartApplication.class, args);
	}

	@Bean
	public CommandLineRunner cmd (ProductRepository productRepository) {
		return args -> {
			Product p = new Product(1, "Honeycrisp Apples", 3.49, 10, "images/1.jpg");
			productRepository.save(p);
			p = new Product(2, "Organic Red Grapes", 2.79, 10, "images/2.jpg");
			productRepository.save(p);
			p = new Product(3, "Green Seedless Grapes", 1.99, 10, "images/3.png");
			productRepository.save(p);
			p = new Product(4, "Organic Baby Carrots", 1.49, 10, "images/4.jpg");
			productRepository.save(p);
			p = new Product(5, "Organic Broccoli", 1.99, 10, "images/5.jpg");
			productRepository.save(p);
			p = new Product(6, "Organic Spinach", 2.49, 10, "images/6.jpg");
			productRepository.save(p);
			p = new Product(7, "Avocado", 1.29, 10, "images/7.jpg");
			productRepository.save(p);
			p = new Product(8, "Organic Cherry Tomatoes", 2.99, 10, "images/8.jpg");
			productRepository.save(p);
			p = new Product(9, "Organic Red Bell Peppers", 1.99, 10, "images/9.jpg");
			productRepository.save(p);
		};
	}
}
