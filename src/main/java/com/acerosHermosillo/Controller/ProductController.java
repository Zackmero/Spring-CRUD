package com.acerosHermosillo.Controller;

import com.acerosHermosillo.Products.Product;
import com.acerosHermosillo.Services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping(path = "api/v1/products")
public class ProductController {

    private final ProductService ps;

    @Autowired
    public ProductController(ProductService ps) {
        this.ps = ps;
    }

    @GetMapping
    public List<Product> getProduct() {
        return ps.getProducts();
    }

    @PostMapping
    public ResponseEntity<Object> registerProduct(@RequestBody Product p) {
        return this.ps.newProduct(p);
    }

    @PutMapping
    public ResponseEntity<Object> updateProduct(@RequestBody Product p) {
        return this.ps.newProduct(p);
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity<Object> deleteProduct(@PathVariable("id") Long id) {
        return this.ps.deleteProduct(id);
    }


}
