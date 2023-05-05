package com.RevMart.Controllers;

import com.RevMart.Models.Product;
import com.RevMart.Services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = {"*"})
@RequestMapping("product")
public class ProductController {
    ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    /**
     * Returns a list of all products in the system.
     *
     * @return a list of product objects
     * i.e.
     * [
     *     {
     *         "id": 1,
     *         "name": "Organic Gala Apples",
     *         "price": 2.99,
     *         "quantity": 2
     *     },
     *     {
     *         "id": 2,
     *         "name": "Organic Red Grapes",
     *         "price": 2.79,
     *         "quantity": 10
     *     },
     *     {
     *         "id": 3,
     *         "name": "Green Seedless Grapes",
     *         "price": 1.99,
     *         "quantity": 10
     *     }
     * ]
     */
    @GetMapping
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    /**
     * Returns a single product with the specified identifier.
     *
     * @param id is the unique identifier for the product
     * @return a product object
     * i.e.
     * {
     *     "id": 1,
     *     "name": "Organic Gala Apples",
     *     "price": 2.99,
     *     "quantity": 2
     * }
     */
    @GetMapping("{id}")
    public Optional<Product> getProductById(@PathVariable long id) {
        return productService.getProductById(id);
    }
}
