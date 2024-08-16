package com.acerosHermosillo.Services;


import com.acerosHermosillo.Products.Product;
import com.acerosHermosillo.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

import java.util.Optional;

@Service
public class ProductService {

    //--------------------- Variables ------------------------------
    private final ProductRepository productRepository;
    HashMap<String, Object> data;
    //--------------------------------------------------------------

    //---------------- Constructors --------------------------------
    @Autowired
    public ProductService(ProductRepository pr) {
        this.productRepository = pr;
    }
    //---------------------------------------------------------------

    //-------------------- Methods -----------------------------------------------------
    public List<Product> getProducts() {
        return this.productRepository.findAll();
    }

    public ResponseEntity<Object> newProduct(Product product) {
        Optional<Product> res = productRepository.findProductByName(product.getName());
        data = new HashMap<>();

        if (res.isPresent() && product.getId() == null) {
            data.put("Product error", "205");
            data.put("Message:", "Existing Product!");

            return new ResponseEntity<>(
                    data,
                    HttpStatus.CONFLICT
            );
        }

        data.put("Product:", product);
        data.put("Message:", "Product registred correctly!");

        if (product.getId() != null) {
            data.put("Product:", product);
            data.put("Message:", "Product updated correctly!");
        }

        productRepository.save(product);


        return new ResponseEntity<>(
                data,
                HttpStatus.CREATED
        );

    }

    public ResponseEntity<Object> deleteProduct(Long idProduct) {
        boolean exist = this.productRepository.existsById(idProduct);
        data = new HashMap<>();
        if (!exist) {
            data.put("Product:", true);
            data.put("Message:", "Non-existent product!");

            return new ResponseEntity<>(
                    data,
                    HttpStatus.CONFLICT
            );
        }

        productRepository.deleteById(idProduct);
        data.put("Message:", "Product removed correctly!");
        return new ResponseEntity<>(
                data,
                HttpStatus.ACCEPTED
        );


    }

    //----------------------------------------------------------------------------
}
