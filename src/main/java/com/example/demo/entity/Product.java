package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "product")
public class Product {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private String name;

  @Column(nullable = false)
  private int price;

  @Column
  private String imageUrl;

  protected Product() {
  }

  public Product(Long id, String name, int price, String imageUrl){
    this.id = id;
    this.name = name;
    this.price = price;
    this.imageUrl = imageUrl;
  }

  public void update(String name, int price, String imageUrl) {
    if (price < 0) throw new IllegalArgumentException("가격은 음수일 수 없습니다.");
    this.name = name;
    this.price = price;
    this.imageUrl = imageUrl;
  }

  public Product(String name, int price, String imageUrl) {
    this.name = name;
    this.price = price;
    this.imageUrl = imageUrl;
  }

  public Long getId(){
    return id;
  }

  public String getName(){
    return name;
  }

  public int getPrice(){
    return price;
  }

  public String getImageUrl(){
    return imageUrl;
  }

  public void setImageUrl(String imageUrl) {
    this.imageUrl = imageUrl;
  }

  public void setPrice(int price){
    this.price = price;
  }

  public void setName(String name){
    this.name = name;
  }

  public void setId(Long id){
    this.id = id;
  }
}


