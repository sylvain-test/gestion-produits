package com.example.inventorymanagement;

import com.example.inventorymanagement.entity.Category;
import com.example.inventorymanagement.entity.Product;
import com.example.inventorymanagement.repository.CategoryRepository;
import com.example.inventorymanagement.repository.ProductRepository;
import com.example.inventorymanagement.service.CategoryService;
import com.example.inventorymanagement.service.ProductService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class InventoryManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventoryManagementApplication.class, args);
	}

	@Bean
	CommandLineRunner testServices(ProductService productService, CategoryService categoryService) {
		return args -> {
			// Créer une catégorie
			//Category cat = new Category();
			//cat.setName("Informatique");
			//categoryService.createCategory(cat);

			// Créer un produit
			////////productService.createProduct(product);

			System.out.println("Produits en base : " + productService.getAllProducts().size());
		};
	}
}