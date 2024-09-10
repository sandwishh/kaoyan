package com.example.redis1;

import entity.Product;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;
import repository.ProductDao;

import java.util.List;

@SpringBootApplication
@RestController
@RequestMapping("/product")
public class Redis1Application {
    private ProductDao dao;
    @PostMapping
    public Product save(@RequestBody Product product){
        return dao.save(product);
    }

    public List<Product> getAllProducts(){
        return dao.findALL();
    }

    @GetMapping("/{id}")
    public Product findProduct(@PathVariable int id){
        return dao.findProductByID(id);
    }
    @DeleteMapping("/{id}")
    public String remove(@PathVariable int id){
        return dao.deleteProduct(id);
    }
    public static void main(String[] args) {
        SpringApplication.run(Redis1Application.class, args);
    }

}
