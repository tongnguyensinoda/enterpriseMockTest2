package com.example.demo.controller;

import com.example.demo.entity.Product;
import com.example.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(path = "product")
public class ProductController {
    @Autowired
    private ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }
    @PostMapping(path = "add")
    public void addNewProduct(@RequestBody Product product){
        productService.addNewProduct(product);
    }
    @GetMapping(path = "list")
    public List<Product> showListOfProducts(){
        Page<Product> page = productService.getProducts();
        List<Product> listProducts = page.getContent();
        return listProducts;
    }
    @GetMapping("/page/{pageNumber}")
    public List<Product> listByPage(@PathVariable("pageNumber") int currentPage,
                                    @Param("sortField") String sortField,
                                    @Param("sortDir")String sortDir){
        Page<Product> page = productService.sortAll(currentPage,sortField,sortDir);
        List<Product> listProducts = page.getContent();
        return listProducts;
    }
    @GetMapping(path = "search/page")
    public List<Product> getAllProductsByName(@RequestParam String productName, @RequestParam int pageNo, @RequestParam  int pageSize){
        Pageable pageable = PageRequest.of(pageNo,pageSize);
        Page<Product> page = productService.findByNameLike(productName, pageable);
        List<Product> listProducts = page.getContent();
        return listProducts;
    }

    @GetMapping(path = "search")
    public List<Product> searchProduct(@RequestParam("keyword") String keyword){
        return productService.searchProduct(keyword.toLowerCase());
    }
    @PutMapping(path="update/{productId}")
    public String updateProduct(
            @PathVariable("productId") Long productId,
            @RequestParam(required = false) String productName,
            @RequestParam(required = false) Float price,
            @RequestParam(required = false) String color,
            @RequestParam(required = false) String brand,
            @RequestParam(required = false) String model){
        return productService.updateProduct(productId,productName,price,color,brand,model);
    }
    @PutMapping(path="update1/{productId}")
    public String updateProduct(
            @PathVariable("productId") Long productId,
            @RequestParam(required = false) Long newCountryId){
        return productService.updateCountryForProduct(productId,newCountryId);
    }
}
