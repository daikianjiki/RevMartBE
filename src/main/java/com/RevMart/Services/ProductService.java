package com.RevMart.Services;

import com.RevMart.Models.Product;
import com.RevMart.Repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    ProductRepository productRepository;
    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    /**
     * Returns a list of all products in the system.
     *
     * @return a list of product objects
     */
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }
    /**
     * Returns a single product with the specified identifier.
     *
     * @param id is the unique identifier for the product
     * @return a product object
     */
    public Optional<Product> getProductById(long id) {
        return productRepository.findById(id);
    }
}
