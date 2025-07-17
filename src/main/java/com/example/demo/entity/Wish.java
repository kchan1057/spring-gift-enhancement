package com.example.demo.entity;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;

@Entity
@Table(name = "wish")
public class Wish {

  @EmbeddedId
  private WishId id;

  @ManyToOne(fetch = FetchType.LAZY)
  @MapsId("userId")
  @JoinColumn(name = "user_id")
  private User user;

  @ManyToOne(fetch = FetchType.LAZY)
  @MapsId("productId")
  @JoinColumn(name ="product_id")
  private Product product;

  protected Wish(){}

  public Wish(WishId id, User user, Product product) {
    this.id = id;
    this.user = user;
    this.product = product;
  }

  public WishId getId(){
    return id;
  }

  public User getUser(){
    return user;
  }

  public Product getProduct(){
    return product;
  }
}
