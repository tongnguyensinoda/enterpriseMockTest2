package com.example.demo.service;

import com.example.demo.entity.Country;
import com.example.demo.entity.Product;
import com.example.demo.repository.CountryRepository;
import com.example.demo.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CountryRepository countryRepository;

    public Page<Product> getProducts() {
        Pageable pageable = PageRequest.of(1,2);
        return productRepository.findAll(pageable);
    }
    public Page<Product> sortAll(int pageNumber, String sortField, String sortDir){
        Sort sort = Sort.by("productName");
        sort = sortDir.equals("asc")? sort.ascending() : sort.descending();
        Pageable pageable = PageRequest.of(pageNumber-1, 1,sort);
        return  productRepository.findAll(pageable);
    }
    public Page<Product> findByNameLike(String productName, Pageable pageable){
        Page<Product> page = productRepository.findByNameLike(productName, pageable);
        List<Product> listProducts = page.getContent();
        System.out.println(listProducts);
        return page;
    }
    public void addNewProduct(Product product){
        Optional<Product> productOptional = productRepository.findProductByProductName(product.getProductName());
        if (productOptional.isPresent()){
            throw new IllegalStateException("product taken");
        }
        productRepository.save(product);
    }
    public List<Product> searchProduct(String keyword){
        if (keyword!=null){
            return productRepository.search(keyword);
        }
        return productRepository.findAll();
    }
    @Transactional
    public String updateProduct(Long productId,
                                String productName,
                                Float price,
                                String color,
                                String brand,
                                String model){
        Product product = productRepository.findById(productId).orElseThrow(()-> new IllegalStateException(
                "Product with id "+productId+" does not exists"
        ));
        if (productName != null && productName.length() > 0
                && !Objects.equals(product.getProductName(),productName)){
            product.setProductName(productName);
        }
        if (price != null && !Objects.equals(product.getPrice(),price)){
            product.setPrice(price);
        }
        if (color != null && color.length() > 0
                && !Objects.equals(product.getColor(),color)){
            product.setColor(color);
        }
        if (brand != null && brand.length() > 0
                && !Objects.equals(product.getBrand(),brand)){
            product.setBrand(brand);
        }
        if (model != null && model.length() > 0
                && !Objects.equals(product.getModel(),model)){
            product.setModel(model);
        }
        return "{ \"message\": \"Product successfully updated.\" }";
    }
    @Transactional
    public String updateCountryForProduct(Long productId,Long newCountryId){
        Product product = productRepository.findById(productId).orElseThrow(()-> new IllegalStateException(
                "Product with id "+productId+" does not exists"
        ));
        Country country = countryRepository.findCountryByCountryId(newCountryId).get();
        if (newCountryId != null){
            product.setCountry(country);
        }
        return "{ \"message\": \"Product successfully updated.\" }";
    }
}
