package com.example.demo.test_data;

import com.example.demo.entity.Product;
import com.example.demo.repository.ProductRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

@Component
public class DummyProductLoader {

  private final ProductRepository productRepository;

  public DummyProductLoader(ProductRepository productRepository) {
    this.productRepository = productRepository;
  }

  @PostConstruct
  public void init(){
    for(int i = 1; i <= 100; i++){
      Product product = new Product("상품 " + i, i * 1000, "https://dummytest.com");
      product.setCreatedAt(java.time.LocalDateTime.now());
      productRepository.save(product);
    }
  }
}
